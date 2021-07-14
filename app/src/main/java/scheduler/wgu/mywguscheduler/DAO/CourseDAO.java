package scheduler.wgu.mywguscheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Assessment;
import scheduler.wgu.mywguscheduler.Entity.Course;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course ORDER BY id ASC")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course where termId = :termId ORDER BY id ASC")
    LiveData<List<Course>> getAllAssociatedTerms(int termId);

    @Query("SELECT * FROM course where instructorId = :instructorId ORDER BY id ASC")
    LiveData<List<Course>> getAllAssociatedInstructors(int instructorId);

    @Query("SELECT assessment.id as assessmentId, assessment.title, course.id as courseId, course.title " +
            "FROM course " +
            "inner join assessment on assessment.courseId = course.id where assessment.courseId = :courseId")
    LiveData<List<AssessmentsByCourse>> getAssessmentsByCourse(int courseId);

//    @Query("SELECT assessment.id, assessment.title, course.id, course.title FROM assessment " +
//            "where assessment.courseId = :courseId" +
//            "inner join course on course.id = assessment.courseId" +
//            "")
//    LiveData<List<AssessmentsByCourse>> getAssessmentsByCourse(int courseId);


    static class AssessmentsByCourse {
        public int assessmentId;
        public String assessmentTitle;
        public int courseId;
        public String courseTitle;
    }



}
