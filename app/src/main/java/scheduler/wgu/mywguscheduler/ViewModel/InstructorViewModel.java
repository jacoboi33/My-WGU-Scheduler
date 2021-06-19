package scheduler.wgu.mywguscheduler.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Instructor;

public class InstructorViewModel extends AndroidViewModel {

    private ScheduleManagementRepository mRepository;
    private LiveData<List<Instructor>> mAllLLiveInstructors;
    private List<Instructor> mAllInstructors;

    public InstructorViewModel(Application application) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
        mAllLLiveInstructors = mRepository.getAllLiveInstructors();
        mAllInstructors = mRepository.getAllInstructors();
    }

    public LiveData<List<Instructor>> getAllLiveInstructors() { return mAllLLiveInstructors; }
    public List<Instructor> getAllInstructors() { return mAllInstructors; }

    public void insert (Instructor instructor) {
        mRepository.insert(instructor);
    }
    public void delete (Instructor instructor) { mRepository.delete(instructor); }


}
