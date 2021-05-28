package scheduler.wgu.mywguscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementDatabase;
import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;
import scheduler.wgu.mywguscheduler.UI.InstructorActivity;

public class InstructorActivityViewModel extends AndroidViewModel {

    private ScheduleManagementRepository mRepository;
    private LiveData<List<InstructorEntity>> mAllInstructors;

    public InstructorActivityViewModel(Application application) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
        mAllInstructors = mRepository.getAllLiveInstructors();
    }
}
