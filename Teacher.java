import java.util.ArrayList;
import java.util.Date;

public class Teacher extends Person implements Payable {


    //=========Attributes========

    private int salary;
    private int salaryEarned;
    private ArrayList<Course> courses = new ArrayList<>();;


    //========Constructor========
    public Teacher() {
        salary=0 ;
        salaryEarned = 0;
    }

//    public Teacher(int salary) {
//        this.salary = salary;
//    }

//    public Teacher( String name, int salary) {
//        super(name);
//        this.salary = salary;
//    }

    public Teacher(String name, int ID , int salary) {
        super(ID, name );
        this.salary = salary;
    }

    //========Seter & Getter============


    public int getSalaryEarned() {
        return salaryEarned;
    }
    public int getSalary(){
        return  salary;
    }
     
    public void setSalary(int salary){
        this.salary=salary;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    //========Methods============
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void receiveSalary(int salary){
        salaryEarned+=salary;
        School.updateTotalMoneySpent(salary);
    }

    @Override
    public int getGrade() {
        return -1;
    }

    @Override
    public void payFees(int fees) {
        System.out.println("Teacher don't pay fees");
    }

    @Override
    public int getRemainingFees() {
        return 0;
    }
    @Override
    public String toString() {
        return "Teacher: " +super.toString()+
                ", Salary: $" + salary +
                ", Salary Earned: $" + salaryEarned;
    }

}
