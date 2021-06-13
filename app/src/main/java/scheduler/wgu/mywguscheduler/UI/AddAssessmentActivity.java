package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.AssessmentViewModel;

public class AddAssessmentActivity extends AppCompatActivity {

    private AssessmentViewModel viewModel;

    private TextInputLayout mTitle;
    private TextInputLayout mTypeTextInput;
    private TextInputLayout datePicker;
    private Button datePickerButton;


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

        datePicker = findViewById(R.id.date_picker_text_input);
        datePickerButton = findViewById(R.id.icon_date_picker_button);
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