package scheduler.wgu.mywguscheduler.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import scheduler.wgu.mywguscheduler.DAO.AssessmentDAO;
import scheduler.wgu.mywguscheduler.DAO.CourseDAO;
import scheduler.wgu.mywguscheduler.DAO.InstructorDAO;
import scheduler.wgu.mywguscheduler.DAO.TermDAO;
import scheduler.wgu.mywguscheduler.Entity.AssessmentEntity;
import scheduler.wgu.mywguscheduler.Entity.CourseEntity;
import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;
import scheduler.wgu.mywguscheduler.Entity.TermEntity;

public class ScheduleManagementRepository {
    private final InstructorDAO mInstructorDao;
    private final TermDAO mTermDao;
    private final CourseDAO mCourseDao;
    private final AssessmentDAO mAssessmentDao;


    private LiveData<List<InstructorEntity>> mInstructorsList;
    private List<TermEntity> mTermsList;
    private List<CourseEntity> mCoursesList;
    private List<AssessmentEntity> mAssessmentList;

    public ScheduleManagementRepository(Application application) {
        ScheduleManagementDatabase db = ScheduleManagementDatabase.getDatabase(application);

        mInstructorDao = db.instructorDAO();
        mTermDao = db.termDAO();
        mCourseDao = db.courseDAO();
        mAssessmentDao = db.assessmentDAO();
        // Get all instructors
        mInstructorsList = mInstructorDao.getAllLiveInstructors();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<InstructorEntity>> getAllLiveInstructors() {
        return mInstructorsList;
    }

    public void insert(InstructorEntity instructor) {
        new insertAsyncTask(mInstructorDao).execute(instructor);
    }

    private static class insertAsyncTask extends AsyncTask<InstructorEntity, Void, Void> {
        private InstructorDAO mAsyncTaskDao;

        insertAsyncTask(InstructorDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final InstructorEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

//    public List<InstructorEntity> getAllInstructors() {
//        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
//            mInstructorsList = mInstructorDao.getAllInstructors();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return mInstructorsList;
//    }

    public List<TermEntity> getAllTerms() {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mTermsList = mTermDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mTermsList;
    }

    public List<CourseEntity> getAllCourses() {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mCoursesList = mCourseDao.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mCoursesList;
    }

    public List<AssessmentEntity> getAllAssessments() {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mAssessmentList = mAssessmentDao.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAssessmentList;
    }

//    public void insert (InstructorEntity instructor) {
//        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
//            mInstructorDao.insert(instructor);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void insert (TermEntity term) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mTermDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert (CourseEntity course) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mCourseDao.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert (AssessmentEntity assessment) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mAssessmentDao.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(InstructorEntity instructor) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mInstructorDao.delete(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(TermEntity term) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mTermDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(CourseEntity course) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mCourseDao.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(AssessmentEntity assessment) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mAssessmentDao.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
