package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.R;

public class AssessmentActivity extends AppCompatActivity {

    private RecyclerView assessmentRecylcerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        getSupportActionBar().setTitle("Assessments");

        assessmentRecylcerView = findViewById(R.id.assessmentRecyclerView);
        findViewById(R.id.add_assessment_button).setOnClickListener(v -> {
            Intent intent = new Intent(AssessmentActivity.this, AddAssessmentActivity.class);
            startActivity(intent);
        });
    }

}