package scheduler.wgu.mywguscheduler.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructor_table")
public class InstructorEntity {

    @PrimaryKey(autoGenerate = true)
    private int instructorId;
    private String name;
    private String phoneNumber;
    private String emailAddress;

    @Override
    public String toString() {
        return "InstructorEntity{" +
                "instructorId=" + instructorId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public InstructorEntity(){}

    public InstructorEntity(int instructorId, String name, String phoneNumber, String emailAddress) {
        this.instructorId = instructorId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
