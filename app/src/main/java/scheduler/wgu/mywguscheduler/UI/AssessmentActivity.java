package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.AssessmentViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.CourseViewModel;

public class AssessmentActivity extends AppCompatActivity implements AssessmentAdapter.HandleAssessmentClick{

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private int numAssessments;
    private int mCourseId;
    private AssessmentViewModel mAssessmentViewModel;
    private CourseViewModel mCourseViewModel;
    private List<Assessment> mAssessments;
    private List<Course> mCourseList;
    private Assessment mEditAssessment;
    private boolean courseChange = false;
//    private final LayoutInflater mInflater;
//    private final Context context;

//    public AssessmentActivity(LayoutInflater mInflater, Context context){
//        this.mInflater = mInflater;
//        this.context = context;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        getSupportActionBar().setTitle("Assessments");
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        RecyclerView assessmentRecylcerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter adapter = new AssessmentAdapter(this, this);
        assessmentRecylcerView.setAdapter(adapter);
        assessmentRecylcerView.setLayoutManager(new LinearLayoutManager(this));

        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
//        mAssessmentViewModel.getAllAssessments().observe(this, adapter::setWords);
        mAssessmentViewModel.getAllAssessments().observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(List<Assessment> assessments) {
                List<Assessment> filteredWords = new ArrayList<>();
                List<Course> associatedCourses = new ArrayList<>();
                for (Assessment a: assessments) {
                    if(a.getCourseId() != -1){
                        for (Course c: associatedCourses) {
                           c.setId(a.getCourseId());

                        }
                        mAssessmentViewModel.getAssociatedCourses(a.getCourseId());
                    }
                    filteredWords.add(a);
                }

//                    if (a.getCourseId() == getIntent().getIntExtra("courseId", 0))
//                        filteredWords.add(a);

                adapter.setWords(filteredWords);
                numAssessments = filteredWords.size();
            }
        });

        findViewById(R.id.add_assessment_button).setOnClickListener(v -> {
            Intent intent = new Intent(AssessmentActivity.this, AddAssessmentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onAssessmentClick(int position) {
//        mAssessments.get(position);
        //        TODO Add the intent to the detail view in My WGU Scheduler.
//        final Assessment current = mAssessments.get(position);
//        Intent intent = new Intent();
//        intent.putExtra("title", current.getTitle());
//        intent.putExtra("type", current.getType());
//        intent.putExtra("position",position);
//        intent.putExtra("courseTitle", current.getCourseId());
//        intent.putExtra("id",current.getId());
//        intent.putExtra("assessmentDate", current.getEndDate());
//        context.startActivity(intent);

        Intent intent = new Intent(AssessmentActivity.this, AssessmentDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void removeAssessments(Assessment assessment) {
        mAssessmentViewModel.delete(assessment);
    }

    @Override
    public void editAssessments(Assessment assessment) {
        this.mEditAssessment = assessment;
        showEditAssessmentDialog();
    }

    private void showEditAssessmentDialog() {
        AlertDialog dialogBuilder = new MaterialAlertDialogBuilder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.activity_add_assessment, null);

        AutoCompleteTextView type = (AutoCompleteTextView) dialogView.findViewById(R.id.assessment_type);
        String[] items = {"Objective", "Performance"};
        type.setText(items[0]);

        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        type.setAdapter(adapter);

        int editId = mEditAssessment.getId();
        int courseId = mEditAssessment.getCourseId();
        TextInputLayout mTitle = dialogView.findViewById(R.id.title_text_input);
        TextInputLayout mType = dialogView.findViewById(R.id.type_text_input);
        TextInputLayout datePicker = dialogView.findViewById(R.id.date_picker_text_input);

        // TODO Create associated courses to get all the associated courses with a give item.
        AutoCompleteTextView courses = (AutoCompleteTextView) dialogView.findViewById(R.id.ac_course_title);
        try {
            final Course acCourse = new Course();
            final CourseAdapter courseAdapter= new CourseAdapter(this);
            mAssessmentViewModel.getAssociatedCourses(courseId);
            mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
            mCourseViewModel.getAllCourses().observe(this, courseAdapter::setWords);
            mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
                @Override
                public void onChanged(List<Course> coursesList) {
                    ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<>(AssessmentActivity.this, R.layout.list_item, coursesList);

//                    coursesList.stream()
//                            .filter(a -> {
//                        if (a.getId() == courseId) {
//                            courses.setText(a.getTitle());
//                        }
//                        return Boolean.parseBoolean(a.getTitle());
//                    });
                    int i = coursesList.indexOf(mEditAssessment);
                    courses.setText(coursesList.get(coursesList.indexOf(mEditAssessment.getCourseId())).getTitle());
                    courses.setAdapter(courseArrayAdapter);
                }
            });

            courses.setOnItemClickListener((parent, view, position, id) -> {
                courseChange = true;
                Course selectedCourse = (Course) parent.getItemAtPosition(position);
                mCourseId = selectedCourse.getId();
            });

        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(AssessmentActivity.this, String.format("Error %s", e.getMessage()), Toast.LENGTH_SHORT).show();
        }

        Button datePickerButton = (Button)dialogView.findViewById(R.id.icon_date_picker_button);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select Date");
                MaterialDatePicker<Long> picker = builder.build();

                picker.show(getSupportFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String d = Utils.dateFormatConverter(selection);
                        datePicker.getEditText().setText(d);
                        Toast.makeText(AssessmentActivity.this, String.format("Due Date %s", d), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button saveButton = dialogView.findViewById(R.id.button_save_assessment);
        Button cancelButton = dialogView.findViewById(R.id.button_cancel_assessment);

        saveButton.setText("Update");
        Objects.requireNonNull(mTitle.getEditText()).setText(mEditAssessment.getTitle());
        Objects.requireNonNull(mType.getEditText()).setText(mEditAssessment.getType());
        Objects.requireNonNull(datePicker.getEditText()).setText(mEditAssessment.getEndDate());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (courseChange == false)
                    mCourseId = courseId;
                String title = mTitle.getEditText().getText().toString();
                String type = mType.getEditText().getText().toString();
                String date = datePicker.getEditText().getText().toString();

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(AssessmentActivity.this, "Enter assessment title ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(type)) {
                    Toast.makeText(AssessmentActivity.this, "Select assessment type ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(date)) {
                    Toast.makeText(AssessmentActivity.this, "Select a date ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Assessment assessment = new Assessment(editId, title, type, date, mCourseId);
                mAssessmentViewModel.insert(assessment);
                Toast.makeText(AssessmentActivity.this, String.format("Assessment %s updated successfully ", title), Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
}