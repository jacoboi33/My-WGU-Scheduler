package scheduler.wgu.mywguscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.TermEntity;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermEntity term);

    @Update
    void update(TermEntity term);

    @Delete
    void delete(TermEntity term);

    @Query("DELETE FROM term_table")
    void deleteAllTerms();

    @Query("SELECT * FROM term_table")
    List<TermEntity> getAllTerms();

    @Query("SELECT * FROM term_table WHERE termId = :id")
    public TermEntity getTerm(long id);
}
