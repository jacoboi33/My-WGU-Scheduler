package scheduler.wgu.mywguscheduler.Database;

import android.app.Application;
import android.net.CaptivePortal;
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


    private LiveData<List<Instructor>> mInstructorsListLiveData;
    private LiveData<List<Assessment>> mAssessmentsList;
    private LiveData<List<Assessment>> getUnassignedAssessments;
    private LiveData<List<Course>> mAssociatedCourses;
    private List<Course> mCourseList;
    private List<Course> mCourseTitle;

    private List<Instructor> mInstructorList;

    private int courseId;
    private int instructorId;
    private int termId;

    private LiveData<List<Term>> mTermsList;

    private LiveData<List<Course>> mCoursesList;
    private LiveData<List<Course>> mAssociatedTerms;
    private LiveData<List<Course>> mAssociatedInstructors;
    private Course course;

    private List<Course> mCourses;

    public ScheduleManagementRepository(Application application) {
        ScheduleManagementDatabase db = ScheduleManagementDatabase.getDatabase(application);

        mInstructorDao = db.instructorDAO();
        mTermDao = db.termDAO();
        mCourseDao = db.courseDAO();
        mAssessmentDao = db.assessmentDAO();
        // Get all instructors
        mInstructorsListLiveData = mInstructorDao.getAllLiveInstructors();
//        mInstructorList = mInstructorDao.getAllInstructors();
        //        Get all Assessments
        mAssessmentsList = mAssessmentDao.getAllLiveAssessments();
        getUnassignedAssessments = mAssessmentDao.getUnassignedAssessments();
//        mAssociatedCourses = mAssessmentDao.getAssessmentAssociatedCourses(courseId);
        //        GET ALL TERMS
        mTermsList = mTermDao.getAllTerms();

        //        GET ALL COURSES
//        mCourseList = mCourseDao.getCourseList();
        mCoursesList = mCourseDao.getAllCourses();
//        mAssociatedTerms = mCourseDao.getAllAssociatedTerms(termId);
//        mAssociatedInstructors = mCourseDao.getAllAssociatedInstructors(instructorId);
//        course = mCourseDao.getCourse(courseId);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * TERMS SECTION
     *
     */

    /***
     * GET TERMS
     * @return
     */
    public LiveData<List<Term>> getAllLiveTerms() { return mTermsList; }

    /***
     * INSERT TERM
     * @param term
     */
    public void insert (Term term) {
        new insertTermAsyncTask(mTermDao).execute(term);
    }

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




    /***
     * DELETE TERM
     * @param term
     */
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



    /***
     * COURSES SECTION
     *
     */

    /***
     * GET COURSES
     * @return
     */
    public LiveData<List<Course>> getAllLiveCourses() { return mCoursesList; }

    /***
     * INSERT AND UPDATE COURSES
     *
     */
    public void insert (Course course) {
        new insertCourseAsyncTask(mCourseDao).execute(course);
    }

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


    /***
     * DELETE COURSE
     * @param course
     */
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


    /***
     * INSTRUCTOR SECTION
     *
     *
     */

    /***
     * GET INSTRUCTORS
     */
    public LiveData<List<Instructor>> getAllLiveInstructors() {
        return mInstructorsListLiveData;
    }


    /***
     * INSERT INSTRUCTOR
     * @param instructor
     */
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

    /***
     * DELETE INSTRUCTOR
     * @param instructor
     */
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


    /***
     * ASSESSMENT SECTION
     *
     */

    /***
     * GET ASSESSMENTS
     * @return
     */
    public LiveData<List<Assessment>> getAllLiveAssessments() {
        return mAssessmentsList;
    }
    public LiveData<List<Assessment>> getUnassignedAssessments() { return getUnassignedAssessments; }

    /***
     * INSERT ASSESSMENT
     * @param assessment
     */
    public void insert(Assessment assessment) {
        new insertAssessmentAsyncTask(mAssessmentDao).execute(assessment);
    }

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

    /***
     * DELETE ASSESSMENT
     * @param assessment
     */
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
