package scheduler.wgu.mywguscheduler.DAO;

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

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("DELETE FROM Term")
    void deleteAllTerms();

    @Query("SELECT * FROM Term")
    List<Term> getAllTerms();

    @Query("SELECT * FROM Term WHERE id = :id")
    public Term getTerm(long id);
}
