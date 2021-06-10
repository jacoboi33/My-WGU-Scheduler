package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorViewModel;

public class InstructorActivity extends AppCompatActivity implements InstructorAdapter.HandleInstructorClick {

//    private ScheduleManagementRepository repository;
//    private RecyclerView instructorRecyclerView;
    private InstructorViewModel mInstructorViewModel;
    private Button button;


//    int id;
//    String name;
//    String phoneNumber;
//    String emailAddress;
//    EditText editName;
//    EditText editPhoneNumber;
//    EditText editEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        repository = new ScheduleManagementRepository(getApplication());
//        repository.getAllLiveInstructors();  // this is really just to set up the database if there isn't one on your device yet-otherwise synch errors later
//        LiveData<List<Instructor>> liveInstructors = repository.getAllLiveInstructors();
//        liveInstructors.observe(this, new Observer<List<Instructor>>() {
//            @Override
//            public void onChanged(List<Instructor> instructors) {
//                for (Instructor instructor: instructors) {
//
//                }
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.instructorRecyclerView);
        final InstructorAdapter adapter = new InstructorAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setLayoutManager(repository.getAllLiveInstructors());

        mInstructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        mInstructorViewModel.getAllInstructors().observe(this, adapter::setWords);

        mInstructorViewModel.getAllInstructors().observe(this, new Observer<List<Instructor>>() {
            @Override
            public void onChanged(List<Instructor> instructors) {
                adapter.setWords(instructors);
            }
        });

        findViewById(R.id.addInstructorsButton).setOnClickListener(v -> {
            Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
            startActivity(intent);
        });
    }

    public void removeItem(Instructor instructor) { mInstructorViewModel.delete(instructor); }

    @Override
    public void removeInstructor(Instructor instructor) {
        mInstructorViewModel.delete(instructor);
    }

    @Override
    public void editInstructor(Instructor instructor) {

    }

//    public void addInstructor(View view) {
//    }

//    public void addInstructor(View view) {
//        Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
//        startActivity(intent);
//    }
}