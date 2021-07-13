package scheduler.wgu.mywguscheduler.UI.Assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.AssessmentViewModel;

public class AssessmentDetailActivity extends AppCompatActivity {

    private TextInputLayout mTitle;
    private TextInputLayout mType;
    private TextInputLayout mDueDate;
    private TextInputLayout mCourseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);

        mTitle = findViewById(R.id.title);
        mType = findViewById(R.id.type);
        mDueDate = findViewById(R.id.due_date);
        mCourseTitle = findViewById(R.id.course_title);

        if (getIntent().getStringExtra("title") != null) {
            mType.getEditText().setText(getIntent().getStringExtra("type"));
            mTitle.getEditText().setText(getIntent().getStringExtra("title"));
            mDueDate.getEditText().setText(getIntent().getStringExtra("assessmentDate"));
//            mCourseTitle.getEditText().setText(getIntent().getStringExtra("courseTitle"));
        }

    }
}