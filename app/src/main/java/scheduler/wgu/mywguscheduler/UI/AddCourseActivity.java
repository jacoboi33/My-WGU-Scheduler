package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.Entity.Term;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.CourseViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorViewModel;
import scheduler.wgu.mywguscheduler.ViewModel.TermViewModel;

public class AddCourseActivity extends AppCompatActivity {

    private List<Instructor> mInstructors;
    private List<Term> mTerms;
    private CourseViewModel courseViewModel;
    private InstructorViewModel instructorViewModel;
    private TermViewModel termViewModel;
    private ArrayAdapter myAdapter;

    private int instructorId;
    private int termId;

    private TextInputLayout mTitle;
    private TextInputLayout mNote;
    private TextInputLayout mStatus; // TODO Selection add only one status per course
    private TextInputLayout mInstructorName; // TODO Selection add only one instructor per course
    private TextInputLayout mTermTitle; // TODO Selection add only one term per course
    private TextInputLayout startDate;
    private TextInputLayout endDate;

    private Button dateRangePickerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setTitle("Add New Course");

        List<String> itemInstructors = new ArrayList<>();
//        mInstructors = new ArrayList<>();ArrayList

        AutoCompleteTextView status = findViewById(R.id.course_status_selection);
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
                public void onChanged(List<Instructor> instructors) {

                    for (Instructor i : instructors) {
                        itemInstructors.add(i.getName());
                    }
                    adapter.setWords(instructors);
                }
            });

            ArrayAdapter<String> iAdapter = new ArrayAdapter<>(this, R.layout.list_item, itemInstructors);
            instructors.setText(itemInstructors.get(0));
            instructors.setAdapter(iAdapter);

        } catch (Exception e) {
            Toast.makeText(AddCourseActivity.this, String.format("Error %s", e.getMessage()), Toast.LENGTH_SHORT).show();
        }


//        instructorViewModel.getAllInstructors().observe(this, new Observer<List<Instructor>>() {
//            @Override
//            public void onChanged(List<Instructor> instructors) {
//                for (Instructor i : instructors) {
//                    itemInstructors.add(i.getName());
//                }
//            }
//        });

//        ArrayAdapter<String> iAdapter = new ArrayAdapter<>(this, R.layout.list_item, itemInstructors);
//        instructors.setText(itemInstructors.get(0));
//        instructors.setAdapter(iAdapter);

        AutoCompleteTextView terms = findViewById(R.id.course_term_selection);
        List<String> itemTerms = new ArrayList<>();
        try {
            final TermAdapter adapter = new TermAdapter(this);
            termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
            termViewModel.getAllTerms().observe(this, adapter::setWords);
            termViewModel.getAllTerms().observe(this, new Observer<List<Term>>() {
                @Override
                public void onChanged(List<Term> terms) {
                    for (Term i : terms) {
                        itemTerms.add(i.getTermTitle());
                    }
                    adapter.setWords(terms);
                }
            });

            //                mTerms = termViewModel.getAllTerms();
//                for (Instructor i : mInstructors) {
//                    itemInstructors.add(i.getName());
//                }
            ArrayAdapter<String> iAdapter = new ArrayAdapter<>(this, R.layout.list_item, itemTerms);
            terms.setText(itemTerms.get(0));
            terms.setAdapter(iAdapter);
        } catch (Exception e) {
            Toast.makeText(AddCourseActivity.this, String.format("Error %s", e.getMessage()), Toast.LENGTH_SHORT).show();
        }



        mTitle = findViewById(R.id.title_text_input);
        mNote = findViewById(R.id.notes_text_input);
        mStatus = findViewById(R.id.course_status_input);
        mInstructorName = findViewById(R.id.select_instructor_input);
        mTermTitle = findViewById(R.id.select_term_input);

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

    }

    public void addCourse(View view) {
    }

}