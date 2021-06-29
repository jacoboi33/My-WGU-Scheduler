package scheduler.wgu.mywguscheduler.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int instructorId;
    private int termId;
    private String note;
    private String title;
    private String startDate;
    private String endDate;
    private String status;

    public Course(int instructorId, int termId, String note, String title, String startDate, String endDate, String status) {
        this.instructorId = instructorId;
        this.termId = termId;
        this.note = note;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @Ignore
    public Course(int id, int instructorId, int termId, String note, String title, String startDate, String endDate, String status) {
        this.id = id;
        this.instructorId = instructorId;
        this.termId = termId;
        this.note = note;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @Ignore
    public Course(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Ignore
    public Course() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String  note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", instructorId=" + instructorId +
                ", termId=" + termId +
                ", note='" + note + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
