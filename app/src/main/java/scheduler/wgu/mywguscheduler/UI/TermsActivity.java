package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.R;

public class TermsActivity extends AppCompatActivity {
    private ScheduleManagementRepository scheduleRepository;
    private RecyclerView termsRecyclerView;
    private ConstraintLayout snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setTitle("Terms");
        scheduleRepository = new ScheduleManagementRepository(getApplication());
        scheduleRepository.getAllTerms();
        termsRecyclerView = findViewById(R.id.termsRecyclerView);

        final TermAdapter adapter = new TermAdapter(this);
        termsRecyclerView.setAdapter(adapter);
        termsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(scheduleRepository.getAllTerms());

        snackbar = findViewById(R.id.snackbar);
        showSnackBar();

//        Snackbar.make(contextView, "Text label", Snackbar.LENGTH_LONG)
//                .setBackgroundTint(getResources().getColor(R.color.backgroundTint))
//                .setActionTextColor(getResources().getColor(R.color.actionTextColor))
//                .show();
//        Snackbar.make(contextView, "Check out this snackbar", Snackbar.LENGTH_SHORT).show();

    }

    private void showSnackBar() {
        Snackbar.make(snackbar, "Terms Page", Snackbar.LENGTH_SHORT).show();
    }

    public void AddTerm(View view) {
        Intent intent = new Intent(TermsActivity.this, TermDetailActivity.class);
        startActivity(intent);
    }
}