package scheduler.wgu.mywguscheduler.DAO;

import androidx.core.util.Pair;
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
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM assessment ORDER BY id ASC")
    LiveData<List<Assessment>> getAllLiveAssessments();

//    @Query("SELECT * FROM assessment where courseId = :courseId ORDER BY id ASC")
//    LiveData<List<Assessment>> getLiveAllAssociatedCourses(int courseId);

    @Query("SELECT * FROM assessment where courseId = 0 ORDER BY id ASC")
    LiveData<List<Assessment>> getUnassignedAssessments();

    static class AssessmentCourse {
        public int assessmentId;
        public String assessmentTitle;
        public int courseId;
        public String courseTitle;
    }
}