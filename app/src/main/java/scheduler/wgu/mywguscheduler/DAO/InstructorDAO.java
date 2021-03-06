package scheduler.wgu.mywguscheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Instructor;

@Dao
public interface InstructorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Instructor instructor);

    @Delete
    void delete(Instructor instructor);

    @Query("DELETE FROM Instructor")
    void deleteAllInstructors();

    @Query("SELECT * FROM Instructor ORDER BY name ASC")
    LiveData<List<Instructor>> getAllLiveInstructors();

    @Query("SELECT * FROM Instructor ORDER BY name ASC")
    List<Instructor> getAllInstructors();
}
