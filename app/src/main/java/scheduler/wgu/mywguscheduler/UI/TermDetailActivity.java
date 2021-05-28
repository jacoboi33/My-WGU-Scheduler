package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.TermEntity;
import scheduler.wgu.mywguscheduler.R;

public class TermDetailActivity extends AppCompatActivity {

    EditText editTextStartDate;
    EditText editTextEndDate;
    private ImageButton mDatePickerButton;
    private ScheduleManagementRepository repository;

    int id;
    String termTitle;
    String startDate;
    String endDate;

    EditText editTermTitle;
    String editStartDate;
    String editEndDate;

    TextView mStartDate;
    TextView mEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
//        View view = LayoutInflater.from(this).inflate(R.id.datePickerButton);
//        Button datePicker = (Button) findViewById(R.id.datePickerButton);

        mDatePickerButton = findViewById(R.id.startDateImageButton);
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a date");
        MaterialDatePicker materialDatePicker = builder.build();

        mDatePickerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                mStartDate.setText("Selected Date: " + materialDatePicker.getHeaderText());
            }
        });

//        editTextStartDate = (EditText)findViewById(R.id.startDate);


//        editTextEndDate = (EditText)findViewById(R.id.endDate);

        setContentView(R.layout.activity_term_detail);
        id = getIntent().getIntExtra("id", -1);
        termTitle = getIntent().getStringExtra("termTitle");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

        editTermTitle = findViewById(R.id.termTitle);
//        editStartDate = dateConverter(findViewById(R.id.startDate));
//        editEndDate = dateConverter(findViewById(R.id.endDate));

        if(id != -1) {
            editTermTitle.setText(termTitle);
        }
        repository = new ScheduleManagementRepository(getApplication());
    }

    public void addTerm(View view) {
        TermEntity term;
        if (id != -1)
            term = new TermEntity(id, editTermTitle.getText().toString(), editStartDate, editEndDate);
        else {
            List<TermEntity> allTerms = repository.getAllTerms();
            id = allTerms.get(allTerms.size() - 1).getTermId();
            term = new TermEntity(++id, editTermTitle.getText().toString(), editStartDate, editEndDate);
        }
        repository.insert(term);
    }


    public String dateConverter(DatePicker dp) {
        Integer dpYear = dp.getYear();
        Integer dpMonth = dp.getMonth();
        Integer dpDayOfMonth = dp.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(dpYear.toString()).append("-").append(dpMonth.toString()).append("-").append(dpDayOfMonth.toString());
        return sb.toString();
    }
}