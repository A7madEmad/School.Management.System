
import java.util.ArrayList;

public class School {
    private ArrayList<Course> courses;
    private ArrayList<Person> persons;
    private static int totalMoneyEarned = 0;
    private static int totalMoneySpent = 0;

    // اسم الملف النصي الذي سنخزن فيه البيانات
    private static final String FILE_NAME = "school_data.txt";

    public School() {
        this.persons = new ArrayList<>();
        this.courses = new ArrayList<>();

    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void displayAllPersons() {
        if (persons.isEmpty()) {

            System.out.println("No persons registered yet.");
            return;
        }
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses registered yet.");
            return;
        }
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    public int getTotalMoneyEarned() {
        return totalMoneyEarned;

    }

    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public static void updateTotalMoneyEarned(int moneyEarned) {
        totalMoneyEarned += moneyEarned;
    }

    public static void updateTotalMoneySpent(int moneySpent) {
        totalMoneySpent += moneySpent;
    }

}

