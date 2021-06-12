package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorViewModel;

public class InstructorActivity extends AppCompatActivity implements InstructorAdapter.HandleInstructorClick {

    private InstructorViewModel mInstructorViewModel;
    private Instructor mEditInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        repository = new ScheduleManagementRepository(getApplication());
//        repository.getAllLiveInstructors();  // this is really just to set up the database if there isn't one on your device yet-otherwise synch errors later
//        LiveData<List<Instructor>> liveInstructors = repository.getAllLiveInstructors();
//        liveInstructors.observe(this, new Observer<List<Instructor>>() {
//            @Override
//            public void onChanged(List<Instructor> instructors) {
//                for (Instructor instructor: instructors) {
//
//                }
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.instructorRecyclerView);
        final InstructorAdapter adapter = new InstructorAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setLayoutManager(repository.getAllLiveInstructors());

        mInstructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        mInstructorViewModel.getAllInstructors().observe(this, adapter::setWords);

        mInstructorViewModel.getAllInstructors().observe(this, new Observer<List<Instructor>>() {
            @Override
            public void onChanged(List<Instructor> instructors) {
                adapter.setWords(instructors);
            }
        });

        findViewById(R.id.addInstructorsButton).setOnClickListener(v -> {
            Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void removeInstructor(Instructor instructor) {
        mInstructorViewModel.delete(instructor);
    }

    @Override
    public void editInstructor(Instructor instructor) {
        this.mEditInstructor = instructor;
        showEditInstructorDialog();
    }

    private void showEditInstructorDialog() {
        AlertDialog dialogBuilder = new MaterialAlertDialogBuilder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.activity_add_instructor, null);

        int id = mEditInstructor.getId();
        TextInputLayout mName = dialogView.findViewById(R.id.name_text_input);
        TextInputLayout mEmail = dialogView.findViewById(R.id.email_text_input);
        TextInputLayout mPhoneNumber = dialogView.findViewById(R.id.phone_text_input);

        Button saveButton = dialogView.findViewById(R.id.button_save_instructor);
        Button cancelButton = dialogView.findViewById(R.id.button_cancel_instructor);

        saveButton.setText("Update");
        Objects.requireNonNull(mName.getEditText()).setText(mEditInstructor.getName());
        Objects.requireNonNull(mEmail.getEditText()).setText(mEditInstructor.getEmailAddress());
        Objects.requireNonNull(mPhoneNumber.getEditText()).setText(mEditInstructor.getPhoneNumber());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString();
                String phoneNumber = mPhoneNumber.getEditText().getText().toString();

                if(TextUtils.isEmpty(name)) {
                    Toast.makeText(InstructorActivity.this, "Enter instructor name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(InstructorActivity.this, "Enter instructor email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(InstructorActivity.this, "Enter instructor phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Instructor instructor = new Instructor(id, name, email, phoneNumber);

                mInstructorViewModel.insert(instructor);
                Toast.makeText(InstructorActivity.this, String.format("Instructor %s updated successfully", name), Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }

        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();



//        MaterialAlertDialogBuilder(context)
//                .setTitle(resources.getString(R.string.title))
//                .setMessage(resources.getString(R.string.supporting_text))
//                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
//            // Respond to neutral button press
//        }
//        .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
//            // Respond to negative button press
//        }
//        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
//            // Respond to positive button press
//        }
//        .show()
    }

//    public void addInstructor(View view) {
//    }

//    public void addInstructor(View view) {
//        Intent intent = new Intent(InstructorActivity.this, AddInstructorActivity.class);
//        startActivity(intent);
//    }
}