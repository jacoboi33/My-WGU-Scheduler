package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.R;

public class InstructorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        getSupportActionBar().setTitle("Instructors");
    }

    public void AddInstructor(View view) {
        Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
        startActivity(intent);
    }
}