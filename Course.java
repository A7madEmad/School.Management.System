public class Course {
    private String courseName;
    private String courseCode;
    private int creditHours;
    private Teacher teacher;

    public Course(String courseName, String courseCode, int creditHours, Teacher teacher) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHours = creditHours;
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Course: " + courseName + " (" + courseCode + "), Credit Hours: " + creditHours +
                ", Taught by: " + teacher.getName();
    }
}
