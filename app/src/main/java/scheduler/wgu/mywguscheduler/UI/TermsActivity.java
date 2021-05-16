package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.R;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setTitle("Terms");
    }

    public void AddTerm(View view) {
        Intent intent = new Intent(TermsActivity.this, AddTermActivity.class);
        startActivity(intent);
    }
}