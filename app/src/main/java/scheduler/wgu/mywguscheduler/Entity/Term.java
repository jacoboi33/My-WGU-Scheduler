package scheduler.wgu.mywguscheduler.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "term")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String termTitle;
    private String startDate;;
    private String endDate;

    public Term(String termTitle, String startDate, String endDate) {
        this.termTitle = termTitle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Ignore
    public Term(int id, String termTitle, String startDate, String endDate) {
        this.id = id;
        this.termTitle = termTitle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Ignore
    public Term(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return termTitle;
    }
}
