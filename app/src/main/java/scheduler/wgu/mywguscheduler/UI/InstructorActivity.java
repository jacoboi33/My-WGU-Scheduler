package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.R;

public class InstructorActivity extends AppCompatActivity {

    private RecyclerView instructorRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        getSupportActionBar().setTitle("Instructors");
        instructorRecyclerView = findViewById(R.id.termsRecyclerView);
    }

    public void AddInstructor(View view) {
        Intent intent = new Intent(InstructorActivity.this, InstructorDetailActivity.class);
        startActivity(intent);
    }
}