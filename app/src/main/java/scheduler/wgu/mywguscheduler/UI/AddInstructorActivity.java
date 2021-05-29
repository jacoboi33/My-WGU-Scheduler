package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;
import scheduler.wgu.mywguscheduler.R;
import scheduler.wgu.mywguscheduler.ViewModel.InstructorActivityViewModel;

public class AddInstructorActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "scheduler.wgu.mywguscheduler.REPLY";

    private EditText name;
    private EditText email;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);
        getSupportActionBar().setTitle("Add New Instructor");
        name = findViewById(R.id.name_text_input);
        email = findViewById(R.id.email_address_text_input);
        phoneNumber = findViewById(R.id.phone_number_text_input);

        final Button button = findViewById(R.id.button_save_instructor);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(phoneNumber.getText())) {
                    setResult(RESULT_CANCELED, intent);
                } else {
                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String p = phoneNumber.getText().toString();
                    int id = -1;
                    InstructorEntity instructor = new InstructorEntity(id, n, e, p);
                    InstructorActivityViewModel model = new InstructorActivityViewModel(getApplication());
                    model.insert(instructor);


//                    intent.putExtra(EXTRA_REPLY, (Parcelable) instructor);
//                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });

    }

    public void addInstructor() {
        name = findViewById(R.id.name_text_input);
        email = findViewById(R.id.email_address_text_input);
        phoneNumber = findViewById(R.id.phone_number_text_input);
        Button save = findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instructorName = name.getText().toString();
                String instructorEmail = email.getText().toString();
                String instructorPhoneNumber = phoneNumber.getText().toString();
                if (TextUtils.isEmpty(instructorName)) {
                    Toast.makeText(AddInstructorActivity.this, "Enter Instructor Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(instructorEmail)) {
                    Toast.makeText(AddInstructorActivity.this, "Enter Instructor Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(instructorPhoneNumber)) {
                    Toast.makeText(AddInstructorActivity.this, "Enter Instructor Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }



                Intent intent = new Intent(AddInstructorActivity.this, InstructorActivity.class);
                startActivity(intent);

            }
        });
    }


}