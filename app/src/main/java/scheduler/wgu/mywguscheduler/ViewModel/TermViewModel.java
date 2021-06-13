package scheduler.wgu.mywguscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Term;

public class TermViewModel extends AndroidViewModel {

    private ScheduleManagementRepository mRepository;
    private LiveData<List<Term>> mAllTerms;

    public TermViewModel(Application application) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
        mAllTerms = mRepository.getAllLiveTerms();
    }

    public LiveData<List<Term>> getAllTerms() { return mAllTerms; }
    public void insert (Term term) { mRepository.insert(term); }
    public void delete (Term term) { mRepository.delete(term); }
}
