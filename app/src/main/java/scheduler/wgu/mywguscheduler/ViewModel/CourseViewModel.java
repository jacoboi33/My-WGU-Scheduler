package scheduler.wgu.mywguscheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Objects;

import scheduler.wgu.mywguscheduler.Database.ScheduleManagementRepository;
import scheduler.wgu.mywguscheduler.Entity.Course;

public class CourseViewModel extends AndroidViewModel {

    private ScheduleManagementRepository mRepository;
    private LiveData<List<Course>> mAllCourses;
//    private LiveData<List<Course>> mAssociatedTerms;
//    private List<Course> mCourses;
    int termId;

    public CourseViewModel(Application application, int termId) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
//        mAssociatedTerms = mRepository.getAssociatedTerms(termId);
    }

    public CourseViewModel(Application application) {
        super(application);
        mRepository = new ScheduleManagementRepository(application);
        mAllCourses = mRepository.getAllLiveCourses();
//        mAssociatedTerms = mRepository.getAssociatedTerms(termId);
//        mCourses = mRepository.getCourseList();
    }

//    public List<Course> getCourses() { return mCourses; }
//    public LiveData<List<Course>> getAssociatedInstructors(int instructorId) { return mRepository.getmAssociatedInstructors(instructorId); }
//    public LiveData<List<Course>> getmAssociatedTerms(int termId) { return mRepository.getAssociatedTerms(termId); }
    public LiveData<List<Course>> getAllCourses() { return mAllCourses; }
//    public LiveData<List<Course>> getAssessmentCourses(int courseId) {return mRepository.getAssessmentAssociatedCourses(courseId); }
    public void insert(Course course) { mRepository.insert(course); }
    public void delete(Course course) { mRepository.delete(course); }
    public int assessmentCount() { return Objects.requireNonNull(mAllCourses.getValue()).size(); }
}
