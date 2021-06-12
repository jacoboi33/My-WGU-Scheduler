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

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("DELETE FROM Assessment")
    void deleteAllAssessments();

    @Query("Select * FROM Assessment")
    List<Assessment> getAllAssessments();

    @Query("SELECT * FROM assessment ORDER BY id ASC")
    LiveData<List<Assessment>> getAllLiveAssessments();
}
