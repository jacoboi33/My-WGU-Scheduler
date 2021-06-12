package scheduler.wgu.mywguscheduler.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment")
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String endDate;
    private String type;

    public Assessment(String title, String endDate, String type) {
        this.title = title;
        this.endDate = endDate;
        this.type = type;
    }

    public Assessment(int id, String title, String endDate, String type) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.type = type;
    }

    public Assessment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", endDate='" + endDate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
