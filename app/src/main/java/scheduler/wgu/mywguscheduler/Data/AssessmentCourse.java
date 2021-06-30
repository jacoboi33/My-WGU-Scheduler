package scheduler.wgu.mywguscheduler.Data;

import scheduler.wgu.mywguscheduler.Entity.Assessment;

public class AssessmentCourse {
    private Assessment assessment;
    private String courseTitle;

    public AssessmentCourse(Assessment assessment, String courseTitle) {
        this.assessment = assessment;
        this.courseTitle = courseTitle;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return "AssessmentCourse{" +
                "assessment=" + assessment +
                ", courseTitle='" + courseTitle + '\'' +
                '}';
    }
}
