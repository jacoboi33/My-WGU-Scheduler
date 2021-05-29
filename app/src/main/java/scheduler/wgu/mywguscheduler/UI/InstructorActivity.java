package scheduler.wgu.mywguscheduler.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementDatabase;
import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorActivityViewModel;

public class InstructorActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private ScheduleManagementRepository repository;
    private RecyclerView instructorRecyclerView;
    private InstructorActivityViewModel mInstructorViewModel;


    int id;
    String name;
    String phoneNumber;
    String emailAddress;
    EditText editName;
    EditText editPhoneNumber;
    EditText editEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        repository= new ScheduleManagementRepository(getApplication());
        repository.getAllLiveInstructors();// this is really just to set up the database if there isn't one on your device yet-otherwise synch errors later

        RecyclerView recyclerView = findViewById(R.id.instructorRecyclerView);
        final InstructorAdapter adapter = new InstructorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setLayoutManager(repository.getAllLiveInstructors());

        mInstructorViewModel = new ViewModelProvider(this).get(InstructorActivityViewModel.class);
        mInstructorViewModel.getmAllInstructors().observe(this, new Observer<List<InstructorEntity>>() {
            @Override
            public void onChanged(List<InstructorEntity> instructorEntities) {
                adapter.setWords(instructorEntities);
            }
        });

    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            InstructorEntity instructor = new InstructorEntity(data);
//
//        }
//    }

    public void AddInstructor(View view) {
        Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
        startActivity(intent);
    }
}