package scheduler.wgu.mywguscheduler.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment")
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String type;
    private String endDate;

    public Assessment(String title, String type, String endDate) {
        this.title = title;
        this.type = type;
        this.endDate = endDate;
    }
    @Ignore
    public Assessment(int id, String title, String type, String endDate) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.endDate = endDate;
    }

    @Ignore
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
