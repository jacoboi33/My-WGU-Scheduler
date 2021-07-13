package scheduler.wgu.mywguscheduler.UI.Assessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.AssessmentViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.CourseViewModel;

public class AddAssessmentActivity extends AppCompatActivity {

    private AssessmentViewModel viewModel;
    private CourseViewModel courseViewModel;
    private int courseId;

    private TextInputLayout mTitle;
    private TextInputLayout mTypeTextInput;
    private TextInputLayout datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        getSupportActionBar().setTitle("Add New Assessment");

        mTitle = findViewById(R.id.title_text_input);
        final Button saveButton = findViewById(R.id.button_save_assessment);
        final Button cancelButton = findViewById(R.id.button_cancel_assessment);
        viewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView mType = (AutoCompleteTextView) findViewById(R.id.assessment_type);
        // Get the string array
        String[] items = {"Objective", "Performance"};
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        mType.setAdapter(adapter);

//        TODO Set dueDate = picker.tostring
//        TODO Set type = autocompleteview
//        TODO insert delete edit assessments

//        AutoCompleteTextView courses = findViewById(R.id.ac_course_title);
//        try {
//            final CourseAdapter courseAdapter= new CourseAdapter(this);
//            courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
//            courseViewModel.getAllCourses().observe(this, courseAdapter::setWords);
//            courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
//                @Override
//                public void onChanged(List<Course> coursesList) {
//                    ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<>(AddAssessmentActivity.this, R.layout.list_item, coursesList);
//                    if (!coursesList.isEmpty()) {
//                        courses.setText(coursesList.get(0).getTitle());
//                        courses.setAdapter(courseArrayAdapter);
//
//                        courses.setOnItemClickListener((parent, view, position, id) -> {
//                            Course selectedCourse = (Course) parent.getItemAtPosition(position);
//                            courseId = selectedCourse.getId();
//                        });
//                    } else {
//                        courses.setText("No Courses Available");
//                        courseId = 0;
//                    }
//
//                }
//            });
//
//
//
//        } catch (Exception e) {
//            Toast.makeText(AddAssessmentActivity.this, String.format("Error %s", e.getMessage()), Toast.LENGTH_SHORT).show();
//        }

        datePicker = findViewById(R.id.date_picker_text_input);
        Button datePickerButton = findViewById(R.id.icon_date_picker_button);
        mTypeTextInput = findViewById(R.id.type_text_input);
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

                        TimeZone timeZoneUTC = TimeZone.getDefault();
                        // It will be negative, so that's the -1
                        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                        // Create a date format, then a date object with our offset
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                        Date date = new Date(selection + offsetFromUTC);

                        datePicker.getEditText().setText(simpleFormat.format(date));

//                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//                        calendar.setTimeInMillis(selection);
                        Toast.makeText(AddAssessmentActivity.this, String.format("Due Date %s",  simpleFormat.format(date)), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        saveButton.setOnClickListener(v -> {
            try {
                String title = mTitle.getEditText().getText().toString();
                String type = mTypeTextInput.getEditText().getText().toString();
                String date = datePicker.getEditText().getText().toString();

                Assessment assessment = new Assessment(
                        title,
                        type,
                        date,
                        0
                );
                viewModel.insert(assessment);
                Toast.makeText(AddAssessmentActivity.this, String.format("Assessment %s added successfully ", title), Toast.LENGTH_SHORT).show();
                finish();

            } catch (Exception e) {
                Toast.makeText(AddAssessmentActivity.this, "All Fields are required " + e, Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> {
            finish();
        });

    }
}