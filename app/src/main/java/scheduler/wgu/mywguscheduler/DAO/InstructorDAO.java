package scheduler.wgu.mywguscheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.InstructorEntity;

@Dao
public interface InstructorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InstructorEntity instructor);

    @Update
    void update(InstructorEntity instructor);

    @Delete
    void delete(InstructorEntity instructor);

    @Query("DELETE FROM instructor_table")
    void deleteAllInstructors();

    @Query("SELECT * FROM instructor_table")
    List<InstructorEntity> getAllInstructors();

    @Query("SELECT * FROM instructor_table")
    LiveData<List<InstructorEntity>> getAllLiveInstructors();
}
