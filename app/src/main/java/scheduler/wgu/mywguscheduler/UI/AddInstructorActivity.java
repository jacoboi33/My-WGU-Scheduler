package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorViewModel;

public class AddInstructorActivity extends AppCompatActivity {

    private InstructorViewModel viewModel;

    private TextInputLayout mName;
    private TextInputLayout mEmail;
    private TextInputLayout mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);

//        getSupportActionBar().setTitle("Add New Instructor");
        mName = findViewById(R.id.name_text_input);
        mEmail = findViewById(R.id.email_text_input);
        mPhoneNumber = findViewById(R.id.phone_text_input);

        final Button saveButton = findViewById(R.id.button_save_instructor);
        final Button cancelButton = findViewById(R.id.button_cancel_instructor);
        viewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        saveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            try {
                String name = mName.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString();
                String phoneNumber = mPhoneNumber.getEditText().getText().toString();

                Instructor instructor = new Instructor(
                        name,
                        email,
                        phoneNumber
                );

                viewModel.insert(instructor);
                Toast.makeText(AddInstructorActivity.this, String.format("Instructor %s added successfully ",  name), Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e) {
                Toast.makeText(AddInstructorActivity.this, "All Fields are required ", Toast.LENGTH_SHORT).show();
            }
//            if (TextUtils.isEmpty(Objects.requireNonNull(mName.getEditText()).getText())
//                    || TextUtils.isEmpty(Objects.requireNonNull(mEmail.getEditText()).getText())
//                    || TextUtils.isEmpty(Objects.requireNonNull(mPhoneNumber.getEditText()).getText())) {
//                Toast.makeText(AddInstructorActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
//                setResult(RESULT_CANCELED, replyIntent);
//            } else {
//                String name = mName.getEditText().getText().toString();
//                String email = mEmail.getEditText().getText().toString();
//                String phoneNumber = mPhoneNumber.getEditText().getText().toString();
//
//                Instructor instructor = new Instructor(
//                        name,
//                        email,
//                        phoneNumber
//                        );
//
//                viewModel.insert(instructor);
//                Toast.makeText(AddInstructorActivity.this, String.format("Instructor %s added successfully ",  name), Toast.LENGTH_SHORT).show();
////                replyIntent.putExtra(EXTRA_REPLY, name);
////                setResult(RESULT_OK, replyIntent);
//            }

        });

        cancelButton.setOnClickListener(v -> {
            finish();
        });


//        addInstructor();

//        final Button button = findViewById(R.id.button_save_instructor);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                if (TextUtils.isEmpty(Objects.requireNonNull(name.getEditText()).getText()) ||
//                        TextUtils.isEmpty(Objects.requireNonNull(email.getEditText()).getText()) ||
//                        TextUtils.isEmpty(Objects.requireNonNull(phoneNumber.getEditText()).getText())) {
//                    setResult(RESULT_CANCELED, intent);
//                } else {
//                    String n = name.getEditText().getText().toString();
//                    String e = email.getEditText().getText().toString();
//                    String p = phoneNumber.getEditText().getText().toString();
//                    int id = -1;
//                    Instructor instructor = new Instructor(id, n, e, p);
//                    InstructorViewModel model = new InstructorViewModel(getApplication());
//                    model.insert(instructor);
//
//
////                    intent.putExtra(EXTRA_REPLY, (Parcelable) instructor);
////                    setResult(RESULT_OK, intent);
//                }
//                finish();
//            }
//        });

    }

//    public void addInstructor() {
//        mName = findViewById(R.id.name_text_input);
//        mEmail = findViewById(R.id.email_address_text_input);
//        phoneNumber = findViewById(R.id.phone_number_text_input);
//        Button save = findViewById(R.id.save_button);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String instructorName = mName.getEditText().getText().toString();
//                String instructorEmail = mEmail.getEditText().getText().toString();
//                String instructorPhoneNumber = phoneNumber.getEditText().getText().toString();
//
//                if (verifyInstructor(instructorName, instructorEmail, instructorPhoneNumber) == true) {
//                    Instructor instructor = new Instructor(instructorName, instructorEmail, instructorPhoneNumber);
//                    InstructorViewModel model = new InstructorViewModel(getApplication());
//                    model.insert(instructor);
//                } else {
//                    verifyInstructor(instructorName, instructorEmail, instructorPhoneNumber);
//                }
//                finish();
//
//
////                if (TextUtils.isEmpty(instructorName)) {
////                    Toast.makeText(AddInstructorActivity.this, "Enter Instructor Name", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                if (TextUtils.isEmpty(instructorEmail)) {
////                    Toast.makeText(AddInstructorActivity.this, "Enter Instructor Email Address", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                if (TextUtils.isEmpty(instructorPhoneNumber)) {
////                    Toast.makeText(AddInstructorActivity.this, "Enter Instructor Phone Number", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                Intent intent = new Intent(AddInstructorActivity.this, InstructorActivity.class);
////                startActivity(intent);
//            }
//        });
//    }

    private boolean verifyInstructor(String name, String email, String phoneNumber) {
        boolean isValid = false;
        do {
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(AddInstructorActivity.this, "Enter Instructor Name", Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(AddInstructorActivity.this, "Enter Instructor Email Address", Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(AddInstructorActivity.this, "Enter Instructor Phone Number", Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(email) || TextUtils.isEmpty(name))
                isValid = false;
            else
                isValid = true;


        } while (!isValid);

        return isValid;
    }


}