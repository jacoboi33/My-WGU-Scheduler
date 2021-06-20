package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.CourseViewModel;

public class CourseActivity extends AppCompatActivity implements CourseAdapter.HandleCourseClick {

    private CourseViewModel mCourseViewModel;
    private Course mEditCourse;
    private int numCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getSupportActionBar().setTitle("Courses");

        RecyclerView courseRecylclerView = findViewById(R.id.coursesRecyclerView);
        final CourseAdapter adapter = new CourseAdapter(this, this);
        courseRecylclerView.setAdapter(adapter);
        courseRecylclerView.setLayoutManager(new LinearLayoutManager(this));

        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        mCourseViewModel.getAllCourses().observe(this, adapter::setWords);
        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                List<Course> filteredWords = new ArrayList<>();
                for (Course c : courses)
                    filteredWords.add(c);

                adapter.setWords(filteredWords);
                numCourses = filteredWords.size();

            }
        });

        findViewById(R.id.add_course_button).setOnClickListener(v -> {
            Intent intent = new Intent(CourseActivity.this, AddCourseActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(CourseActivity.this, AssessmentDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void removeCourse(Course course) {
        mCourseViewModel.delete(course);
    }

    @Override
    public void editCourse(Course course) {
        this.mEditCourse = course;
        showEditCourseDialog();
    }

    private void showEditCourseDialog() {
        AlertDialog dialogBuilder = new MaterialAlertDialogBuilder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.activity_add_course, null);

        int id = mEditCourse.getId();
        int termId = mEditCourse.getTermId();
        int instructorId = mEditCourse.getInstructorId();

        TextInputLayout mTitle = dialogView.findViewById(R.id.title_text_input);
        TextInputLayout mNotes = dialogView.findViewById(R.id.notes_text_input);
        TextInputLayout startDate = dialogView.findViewById(R.id.start_date);
        TextInputLayout endDate = dialogView.findViewById(R.id.end_date);

    }
}