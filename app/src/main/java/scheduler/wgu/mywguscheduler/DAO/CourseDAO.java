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

    @Query("SELECT assessment.id FROM assessment where courseId = :courseId ORDER BY id ASC")
    LiveData<List<AssessmentsByCourse>> getLiveAllAssociatedCourses(int courseId);


    static class AssessmentsByCourse {
        public int assessmentId;
        public String assessmentTitle;
        public int courseId;
        public String courseTitle;
    }



}
