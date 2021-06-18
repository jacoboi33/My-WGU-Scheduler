package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import scheduler.wgu.mywguscheduler.Entity.Term;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.TermViewModel;

public class AddTermActivity extends AppCompatActivity {

    private TermViewModel viewModel;

    private TextInputLayout mTitle;
    private TextInputLayout mStartDate;
    private TextInputLayout mEndDate;

    private Button dateRangePicker;
//    private Button startDateButton;
//    private Button endDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        getSupportActionBar().setTitle("Add New Term");

        mTitle = findViewById(R.id.title_text_input);
        final Button saveButton = findViewById(R.id.button_save_term);
        final Button cancelButton = findViewById(R.id.button_cancel_term);

        viewModel = new ViewModelProvider(this).get(TermViewModel.class);

        mStartDate = findViewById(R.id.start_date_picker_text_input);
//        startDateButton = findViewById(R.id.icon_start_date_picker_button);

        mEndDate = findViewById(R.id.end_date_picker_text_input);
//        endDateButton = findViewById(R.id.icon_end_date_picker_button);

        dateRangePicker = findViewById(R.id.date_range_picker);

        dateRangePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                builder.setTitleText("Select a date range");
                MaterialDatePicker<Pair<Long, Long>> picker = builder.build();

                picker.show(getSupportFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(( new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {

                        String sDate = Utils.dateFormatConverter(selection.first);
                        String lDate = Utils.dateFormatConverter(selection.second);
                        Objects.requireNonNull(mStartDate.getEditText()).setText(sDate);
                        Objects.requireNonNull(mEndDate.getEditText()).setText(lDate);

                        Toast.makeText(AddTermActivity.this, String.format("Start Date %s End Date %s", sDate, lDate), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });

//        startDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
//                builder.setTitleText("Select Date");
//                MaterialDatePicker<Long> picker = builder.build();
//
//                picker.show(getSupportFragmentManager(), picker.toString());
//                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//                    @Override
//                    public void onPositiveButtonClick(Long selection) {
//                        String lDate = Utils.dateFormatConverter(selection);
//                        mStartDate.getEditText().setText(lDate);
//                        Toast.makeText(AddTermActivity.this, String.format("Start date %s", lDate), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//        endDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
//                builder.setTitleText("Select Date");
//                MaterialDatePicker<Long> picker = builder.build();
//
//                picker.show(getSupportFragmentManager(), picker.toString());
//
//                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//                    @Override
//                    public void onPositiveButtonClick(Long selection) {
//                        String lDate = Utils.dateFormatConverter(selection);
//                        mEndDate.getEditText().setText(lDate);
//                        Toast.makeText(AddTermActivity.this, String.format("End date %s", lDate), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        saveButton.setOnClickListener(v -> {
            try {
                String title = mTitle.getEditText().getText().toString();
                String startDate = mStartDate.getEditText().getText().toString();
                String endDate = mEndDate.getEditText().getText().toString();

                Term term = new Term(
                        title, startDate, endDate
                );
                viewModel.insert(term);
                Toast.makeText(AddTermActivity.this, String.format("Terms %s added successfully ", title), Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(AddTermActivity.this, "All fields are required " + e, Toast.LENGTH_SHORT).show();
            }

        });

        cancelButton.setOnClickListener(v -> {
            finish();
        });


    }
}