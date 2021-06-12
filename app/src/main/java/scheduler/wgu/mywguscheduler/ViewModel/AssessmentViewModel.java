package scheduler.wgu.mywguscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementDatabase;
import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Assessment;

public class AssessmentViewModel extends AndroidViewModel {

    private ScheduleManagementRepository mRepository;
    private LiveData<List<Assessment>> mAllAssessments;

    public AssessmentViewModel(Application application) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
        mAllAssessments = mRepository.getAllLiveAssessments();
    }

    public LiveData<List<Assessment>> getAllAssessments() { return  mAllAssessments; }
    public void insert(Assessment assessment) { mRepository.insert(assessment); }
    public void delete(Assessment assessment) { mRepository.delete(assessment); }
}
