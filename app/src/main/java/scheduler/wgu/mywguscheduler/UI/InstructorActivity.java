package scheduler.wgu.mywguscheduler.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public class InstructorActivity extends AppCompatActivity {

    private ScheduleManagementRepository repository;
    private RecyclerView instructorRecyclerView;

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
        repository.getAllInstructors();// this is really just to set up the database if there isn't one on your device yet-otherwise synch errors later
        RecyclerView recyclerView = findViewById(R.id.instructorRecyclerView);

        final InstructorAdapter adapter = new InstructorAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords(repository.getAllInstructors());

    }

    public void AddInstructor(View view) {
        Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
        startActivity(intent);
    }
}