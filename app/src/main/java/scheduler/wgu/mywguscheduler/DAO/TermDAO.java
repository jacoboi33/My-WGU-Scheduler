package scheduler.wgu.mywguscheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Term;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Term term);

    @Delete
    void delete(Term term);

    @Query("SELECT * FROM Term ORDER BY id ASC")
    LiveData<List<Term>> getAllTerms();
}
