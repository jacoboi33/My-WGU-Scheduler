package scheduler.wgu.mywguscheduler.UI.Term;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Term;
import scheduler.wgu.mywguscheduler.R;

public class TermDetailActivity extends AppCompatActivity {

    private TextInputLayout termTitle;
    private TextInputLayout startDate;
    private TextInputLayout endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

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