package scheduler.wgu.mywguscheduler.UI.Course;

import androidx.annotation.MenuRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.Entity.Term;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.UI.Instructor.InstructorAdapter;
import scheduler.wgu.mywguscheduler.UI.Utils;
import scheduler.wgu.mywguscheduler.ViewModel.AssessmentViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.CourseViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.TermViewModel;

public class AddCourseActivity extends AppCompatActivity {

//    private List<Instructor> mInstructors;
//    private List<Term> mTerms;
    private CourseViewModel courseViewModel;
    private InstructorViewModel instructorViewModel;
//    private TermViewModel termViewModel;
    private ArrayAdapter myAdapter;
    boolean[] selectedAssessments;
    List<Integer> assessmentList = new ArrayList<>();
    private List<Assessment> courseAssessments;
    private AssessmentViewModel assessmentViewModel;

    private int instructorId;
//    private int termId;

    // TODO DONT DELETE TERM IF COURSES ARE ADDED TO A TERM
    // TODO DONT DELETE COURSE IF ASSESSMENTS ARE ADDED TO A COURSE
    // TODO CREATE A QUERY TO CHECK IF THERE ARE COURSES ADDED TO TERMS AND ASSESSMENTS ADDED TO COURSES USE COUNT SQL


    private TextInputLayout mTitle;
    private TextInputLayout mNote;
    private TextInputLayout mStatus; // TODO Selection add only one status per course
    private TextInputLayout mInstructorName; // TODO Selection add only one instructor per course
//    private TextInputLayout mTermTitle; // TODO Selection add only one term per course
    private TextInputLayout startDate;
    private TextInputLayout endDate;

    private Button dateRangePickerButton;
    private Button selectAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setTitle("Add New Course");

//        List<String> itemInstructors = new ArrayList<>();
//        List<Integer> itemInstructorId = new ArrayList<>();
//        mInstructors = new ArrayList<>();ArrayList
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        AutoCompleteTextView status = findViewById(R.id.course_status_selection);
        final Button saveButton = findViewById(R.id.button_save_course);
        final Button cancelButton = findViewById(R.id.button_cancel_course);
        selectAssessments = findViewById(R.id.menu_button);

        String[] items = {"Plan to take", "In Progress", "Completed", "Dropped"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, R.layout.list_item, items);
        status.setText(items[0]);
        status.setAdapter(statusAdapter);

        AutoCompleteTextView instructors = findViewById(R.id.course_instructor_selection);
        try {
            final InstructorAdapter adapter = new InstructorAdapter(this);
            instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
            instructorViewModel.getAllLiveInstructors().observe(this, adapter::setWords);
            instructorViewModel.getAllLiveInstructors().observe(this, new Observer<List<Instructor>>() {
                @Override
                public void onChanged(List<Instructor> instructorList) {
                    ArrayAdapter<Instructor> instructorArrayAdapter = new ArrayAdapter<>(AddCourseActivity.this, R.layout.list_item, instructorList);
//                    ArrayAdapter<String> nameArrayAdapter = new ArrayAdapter<String>(AddCourseActivity.this, R.layout.list_item, itemInstructors);
                    instructors.setText(instructorList.get(0).getName());
                    instructors.setAdapter(instructorArrayAdapter);
                }
            });

            instructors.setOnItemClickListener((parent, view, position, id) -> {
                Instructor selectedInstructor = (Instructor)parent.getItemAtPosition(position);
                instructorId = selectedInstructor.getId();
            });

        } catch (Exception e) {
            Toast.makeText(AddCourseActivity.this, String.format("Error %s", e.getMessage()), Toast.LENGTH_SHORT).show();
        }

        mTitle = findViewById(R.id.title_text_input);
        mNote = findViewById(R.id.notes_text_input);
        mStatus = findViewById(R.id.course_status_input);
        mInstructorName = findViewById(R.id.select_instructor_input);
//        mTermTitle = findViewById(R.id.select_term_input);

        startDate = findViewById(R.id.start_date_picker_text_input);
        endDate = findViewById(R.id.end_date_picker_text_input);

        dateRangePickerButton = findViewById(R.id.date_range_picker_button);
        try {
            dateRangePickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                    builder.setTitleText("Select a course start and end date");
                    MaterialDatePicker<Pair<Long, Long>> picker = builder.build();

                    picker.show(getSupportFragmentManager(), picker.toString());
                    picker.addOnPositiveButtonClickListener((new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                        @Override
                        public void onPositiveButtonClick(Pair<Long, Long> selection) {
                            String sDate = Utils.dateFormatConverter(selection.first);
                            String lDate = Utils.dateFormatConverter(selection.second);
                            Objects.requireNonNull(startDate.getEditText()).setText(sDate);
                            Objects.requireNonNull(endDate.getEditText()).setText(lDate);

//                            Toast.makeText(AddCourseActivity.this, String.format("Start Date %", sDate), Toast.LENGTH_SHORT).show();
                        }
                    }));
                }
            });
        } catch (Exception e) {
            Toast.makeText(AddCourseActivity.this, String.format("Error %s", e.getMessage()), Toast.LENGTH_SHORT).show();
        }


        selectAssessments.setOnClickListener(v -> {
            showAssessmentDialog();
        });

        saveButton.setOnClickListener(v -> {
            try {
                String title = Objects.requireNonNull(mTitle.getEditText()).getText().toString();
                Course course = new Course(
                        instructorId,
                        0,
                        Objects.requireNonNull(mNote.getEditText()).getText().toString(),
                        title,
                        Objects.requireNonNull(startDate.getEditText()).getText().toString(),
                        Objects.requireNonNull(endDate.getEditText()).getText().toString(),
                        Objects.requireNonNull(mStatus.getEditText()).getText().toString()
                );
                courseViewModel.insert(course);
                Toast.makeText(AddCourseActivity.this, String.format("Course %s added successfully ", title), Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(AddCourseActivity.this, "All Fields are required " + e, Toast.LENGTH_SHORT).show();
            }

        });

        cancelButton.setOnClickListener(v -> {
            finish();
        });

    }

    /***
     * SELECT ASSESSMENTS FOR COURSES
     */
    private void showAssessmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
            AddCourseActivity.this
        );
//        View dialogView = getLayoutInflater().inflate(R.layout.activity_add_assessment, null);

        builder.setTitle("Add Assessments to course");
        builder.setCancelable(false);

//        CheckBox box = (CheckBox) dialogView.findViewById(R.id.)
        RecyclerView courseRecyclerView = findViewById(R.id.rv_assessments_by_course);

        // Get All Assessments to create check box and select them.
        final CustomCourseAdapter adapter = new CustomCourseAdapter(this, courseAssessments);
        courseRecyclerView.setAdapter(adapter);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        assessmentViewModel.getAllAssessments().observe(this, adapter::setAssessments);
        assessmentViewModel.getAllAssessments().observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(List<Assessment> assessments) {


//                Collections.sort(assessments.toArray().);
                selectedAssessments = new boolean[assessments.size()];

//                builder.setMultiChoiceItems(assessments.toArray(T[] a), selectedAssessments, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        if (isChecked) {
//                            assessmentList.add(which);
//
//                            Collections.sort(assessmentList);
//
//                        } else {
//                            assessmentList.remove(which);
//                        }
//                    }
//                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                adapter.setAssessments(assessments);

            }
        });
    }

}