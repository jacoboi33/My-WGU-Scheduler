package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ViewTerms(View view) {
        Intent intent = new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);
    }

    public void ViewCourses(View view) {
        Intent intent = new Intent(MainActivity.this, CourseActivity.class);
        startActivity(intent);
    }

    public void ViewAssessments(View view) {
        Intent intent = new Intent(MainActivity.this, AssessmentActivity.class);
        startActivity(intent);
    }

    public void ViewInstructors(View view) {
        Intent intent = new Intent(MainActivity.this, InstructorActivity.class);
        startActivity(intent);
    }
}