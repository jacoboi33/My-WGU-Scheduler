package scheduler.wgu.mywguscheduler.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.Entity.Term;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.TermViewModel;

public class TermsActivity extends AppCompatActivity implements TermAdapter.HandleTermClick {

    private TermViewModel mTermViewModel;
    private Term mEditTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setTitle("Terms");

        RecyclerView termRecyclerView = findViewById(R.id.termsRecyclerView);
        final TermAdapter adapter = new TermAdapter(this, this);
        termRecyclerView.setAdapter(adapter);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        mTermViewModel.getAllTerms().observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(@Nullable List<Term> terms) {
                if (terms != null) {
//                    List<Term> filteredTerms = new ArrayList<>(terms);
                    adapter.setWords(terms);
                }
            }
        });

//        mTermViewModel.getAllTerms().observe(this, adapter::setWords);

//        try {
//            mTermViewModel.getAllTerms().observe(this, new Observer<List<Term>>() {
//                @Override
//                public void onChanged(@Nullable List<Term> terms) {
////                if (terms == null || terms.isEmpty())
////                    adapter.setWords(terms.add(0, Term("No terms"));
//                    adapter.setWords(terms);
//                }
//            });
//        } catch (Exception e) {
//            Toast.makeText(TermsActivity.this, "No Terms Available", Toast.LENGTH_SHORT).show();
//        }


        findViewById(R.id.add_term_button).setOnClickListener(v -> {
            Intent intent = new Intent(TermsActivity.this, AddTermActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void removeTerm(Term term) {
        mTermViewModel.delete(term);
    }

    @Override
    public void editTerm(Term term) {
        this.mEditTerm = term;
        showEditTermDialog();
    }

    private void showEditTermDialog() {
        AlertDialog dialogBuilder = new MaterialAlertDialogBuilder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.activity_add_term, null);

        int id = mEditTerm.getId();
        TextInputLayout mTitle = dialogView.findViewById(R.id.title_text_input);
        CheckBox mNotifications = dialogView.findViewById(R.id.notifications);
        TextInputLayout mStartDate = dialogView.findViewById(R.id.start_date_picker_text_input);
        mStartDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select start Date");
                MaterialDatePicker<Long> picker = builder.build();

                picker.show(getSupportFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String d = Utils.dateFormatConverter(selection);
                        mStartDate.getEditText().setText(d);
                        Toast.makeText(TermsActivity.this, String.format("Start Date %s", d), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        TextInputLayout mEndDate = dialogView.findViewById(R.id.end_date_picker_text_input);

        mEndDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select start Date");
                MaterialDatePicker<Long> picker = builder.build();

                picker.show(getSupportFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        String d = Utils.dateFormatConverter(selection);
                        mEndDate.getEditText().setText(d);
                        Toast.makeText(TermsActivity.this, String.format("End Date %s", d), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button saveButton = dialogView.findViewById(R.id.button_save_term);
        Button cancelButton = dialogView.findViewById(R.id.button_cancel_term);

        saveButton.setText("Update");
        mTitle.getEditText().setText(mEditTerm.getTermTitle());
        mStartDate.getEditText().setText(mEditTerm.getStartDate());
        mEndDate.getEditText().setText(mEditTerm.getEndDate());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getEditText().getText().toString();
                String startDate = mStartDate.getEditText().getText().toString();
                String endDate = mEndDate.getEditText().getText().toString();

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(TermsActivity.this, "Enter term title ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(startDate)) {
                    Toast.makeText(TermsActivity.this, "Enter term startDate ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(endDate)) {
                    Toast.makeText(TermsActivity.this, "Enter term endDate ", Toast.LENGTH_SHORT).show();
                    return;
                }

                Term term = new Term(id, title, startDate, endDate);
                mTermViewModel.insert(term);
                Toast.makeText(TermsActivity.this, String.format("Term %s updated successfully ", title), Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
}