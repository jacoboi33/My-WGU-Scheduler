package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.R;

public class CoursesActivity extends AppCompatActivity {

    private RecyclerView courseRecylclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getSupportActionBar().setTitle("Courses");

        courseRecylclerView = findViewById(R.id.coursesRecyclerView);
    }

    public void AddCourses(View view) {
        Intent intent = new Intent(CoursesActivity.this, AddCourseActivity.class);
        startActivity(intent);
    }
}