package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import scheduler.wgu.mywguscheduler.R;

public class AddInstructorActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructor);
        getSupportActionBar().setTitle("Add New Instructor");

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