package scheduler.wgu.mywguscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.R;

public class TermsActivity extends AppCompatActivity {
    private ScheduleManagementRepository scheduleRepository;
    private RecyclerView termsRecyclerView;

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

    }

    public void AddTerm(View view) {
        Intent intent = new Intent(TermsActivity.this, AddTermActivity.class);
        startActivity(intent);
    }
}