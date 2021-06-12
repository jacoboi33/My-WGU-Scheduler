package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

import scheduler.wgu.mywguscheduler.R;

public class AddAssessmentActivity extends AppCompatActivity {

    private EditText editText;
    private AutoCompleteTextView autoCompleteTextView;
    private TextInputLayout datePicker;
    private String dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        getSupportActionBar().setTitle("Add New Assessment");

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.assessment_type);
        // Get the string array
        String[] items = {"Objective", "Performance"};
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        textView.setAdapter(adapter);

//        TODO Set dueDate = picker.tostring
//        TODO Set type = autocompleteview
//        TODO insert delete edit assessments

        datePicker = findViewById(R.id.outlinedTextField);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select Date");
                MaterialDatePicker<Long> picker = builder.build();
                picker.show(getSupportFragmentManager(), picker.toString());
            }
        });

    }
}