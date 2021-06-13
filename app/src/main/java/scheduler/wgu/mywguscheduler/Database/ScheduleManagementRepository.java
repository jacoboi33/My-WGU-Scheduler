package scheduler.wgu.mywguscheduler.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import scheduler.wgu.mywguscheduler.DAO.AssessmentDAO;
import scheduler.wgu.mywguscheduler.DAO.CourseDAO;
import scheduler.wgu.mywguscheduler.DAO.InstructorDAO;
import scheduler.wgu.mywguscheduler.DAO.TermDAO;
import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.Entity.Instructor;
import scheduler.wgu.mywguscheduler.Entity.Term;

public class ScheduleManagementRepository {
    private final InstructorDAO mInstructorDao;
    private final TermDAO mTermDao;
    private final CourseDAO mCourseDao;
    private final AssessmentDAO mAssessmentDao;


    private LiveData<List<Instructor>> mInstructorsList;

    private LiveData<List<Assessment>> mAssessmentsList;
    private LiveData<List<Assessment>> mAssociatedCourses;

    private int courseId;

    private LiveData<List<Term>> mTermsList;

    private LiveData<List<Course>> mCoursesList;
    private LiveData<List<Course>> mAssociatedTerms;

    public ScheduleManagementRepository(Application application) {
        ScheduleManagementDatabase db = ScheduleManagementDatabase.getDatabase(application);

        mInstructorDao = db.instructorDAO();
        mTermDao = db.termDAO();
        mCourseDao = db.courseDAO();
        mAssessmentDao = db.assessmentDAO();
        // Get all instructors
        mInstructorsList = mInstructorDao.getAllLiveInstructors();

        mAssessmentsList = mAssessmentDao.getAllLiveAssessments();
        mAssociatedCourses = mAssessmentDao.getLiveAllAssociatedCourses(courseId);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instructors
     *
     * */
    public LiveData<List<Instructor>> getAllLiveInstructors() {
        return mInstructorsList;
    }

    public void insert(Instructor instructor) {
        new insertAsyncTask(mInstructorDao).execute(instructor);
    }

    private static class insertAsyncTask extends AsyncTask<Instructor, Void, Void> {
        private InstructorDAO mAsyncTaskDao;

        insertAsyncTask(InstructorDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Instructor... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Assessments
     * */
    private static class insertAssessmentAsyncTask extends AsyncTask<Assessment, Void, Void> {
        private AssessmentDAO mAsyncTaskDao;

        insertAssessmentAsyncTask(AssessmentDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Assessment... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Courses
     * */
    private static class insertCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDAO mAsyncTaskDao;

        insertCourseAsyncTask(CourseDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Course... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Term
     * */
    public LiveData<List<Term>> getAllLiveTerms() { return mTermsList; }

    private static class insertTermAsyncTask extends AsyncTask<Term, Void, Void> {
        private TermDAO mAsyncTaskDao;

        insertTermAsyncTask(TermDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Term... params) {
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
    /**
     * Terms
     * */
//    public List<Term> getAllTerms() {
//        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
//            mTermsList = mTermDao.getAllTerms();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return mTermsList;
//    }

    /**
     * Courses
     * */
    public LiveData<List<Course>> getAllLiveCourses() { return mCoursesList; }
    public LiveData<List<Course>> getAssociatedTerms(int termId) { return mAssociatedTerms; }
//    public List<Course> getAllCourses() {
//        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
//            mCoursesList = mCourseDao.getAllCourses();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return mCoursesList;
//    }

    /**
     * Assessments
     *
     * */

    public LiveData<List<Assessment>> getAllLiveAssessments() {
        return mAssessmentsList;
    }
    public LiveData<List<Assessment>> getAssociatedCourses(int courseId) { return mAssociatedCourses; }


    public void insert(Assessment assessment) {
        new insertAssessmentAsyncTask(mAssessmentDao).execute(assessment);
    }

//    public List<Assessment> getAllAssessments() {
//        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
//            mAssessmentList = mAssessmentDao.getAllAssessments();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return mAssessmentList;
//    }

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

    public void insert (Term term) {
        new insertTermAsyncTask(mTermDao).execute(term);
    }

    public void insert (Course course) {
        new insertCourseAsyncTask(mCourseDao).execute(course);
    }

//    public void insert (Assessment assessment) {
//        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
//            mAssessmentDao.insert(assessment);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void delete(Instructor instructor) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mInstructorDao.delete(instructor);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mTermDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        ScheduleManagementDatabase.databaseWriteExecuter.execute(() -> {
            mCourseDao.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
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
