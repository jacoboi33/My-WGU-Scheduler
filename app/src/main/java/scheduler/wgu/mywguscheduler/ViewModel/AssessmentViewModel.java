package scheduler.wgu.mywguscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementDatabase;
import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;

public class AssessmentViewModel extends AndroidViewModel {

    private ScheduleManagementRepository mRepository;
    private LiveData<List<Assessment>> mAllAssessments;
    private LiveData<List<Assessment>> getUnassignedAssessments;

//    private final MutableLiveData<String> mAssessmentCourseTitle = new MutableLiveData<>();
//    private LiveData<List<Course>> mAssociatedCourses;
//    private Course course;

    int courseId;

    public AssessmentViewModel(Application application, int courseId) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
//        mAssociatedCourses = mRepository.getAssociatedCourses(courseId);
//        mAssociatedCourses = mRepository.getAssessmentAssociatedCourses(courseId);
//        course = mRepository.getCourse(courseId);
    }

    public AssessmentViewModel(Application application) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
        mAllAssessments = mRepository.getAllLiveAssessments();
        getUnassignedAssessments = mRepository.getUnassignedAssessments();
//        mAssociatedCourses = mRepository.getAssociatedCourses(courseId);
//        mAssociatedCourses = mRepository.getAllCourses();
    }

//    public LiveData<List<Assessment>> getAssociatedCourses(int courseId) { return mRepository.getAssociatedCourses(courseId); }
    public LiveData<List<Assessment>> getAllAssessments() { return  mAllAssessments; }
    public LiveData<List<Assessment>> getUnassignedAssessments() { return  getUnassignedAssessments; }

//    public LiveData<List<Course>> getAssessmentAssociatedCourses(int courseId) { return mRepository.getAssessmentAssociatedCourses(courseId); }

    public void insert(Assessment assessment) { mRepository.insert(assessment); }
    public void delete(Assessment assessment) { mRepository.delete(assessment); }
    public int lastId() { return mAllAssessments.getValue().size(); }
//    public Course getCourse() { return course; }
}
