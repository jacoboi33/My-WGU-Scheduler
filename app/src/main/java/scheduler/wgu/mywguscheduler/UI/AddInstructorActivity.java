package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import scheduler.wgu.mywguscheduler.R;

public class AddInstructorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);
        getSupportActionBar().setTitle("Add New Instructor");

    }
}