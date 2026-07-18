import java.util.ArrayList;

public class Student extends Person implements Payable {

    //=========Attributes=========
    private int grade;
    private int feesPaid = 0;
    private static int[] fees = {5000,5000,5000,5000,6000,6000,7000,7000,7000,8000,9000,10000};//1 --> 12
    private int feesTotal;
    private static int numberOfStudent = 0 ;
    private int studentNumber ;
    private ArrayList<Course> courses = new ArrayList<>();;

    //========Constructor========
    public Student(){
        super();
        numberOfStudent++;
        this.studentNumber=numberOfStudent ;
        setFeesTotal();
    }



    public Student(  String name,int ID ,int grade) {
        super(ID, name);
        this.grade = grade;
        numberOfStudent++ ;
        this.studentNumber=numberOfStudent ;
        setFeesTotal();

    }

    //========Seter & Getter============
    public int getGrade() {
        return grade;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public static int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(int feesPaid) {
        this.feesPaid = feesPaid;
    }

    public void setFeesTotal() {
        this.feesTotal = fees[grade-1];
    }
    public int getFeesTotal() {
        return feesTotal ;
    }
    //========Methods============


    @Override
    public void payFees(int fees) {
        if (fees < 0 ) {
            System.out.println("Error: fees must be positive");
            return;
        }
        if (feesPaid+fees>getFeesTotal()){
            System.out.println("Error: You pied more than required");
            return;
        }
            feesPaid += fees;
            School.updateTotalMoneyEarned(fees);
            System.out.println("Fees paid successfully");

    }

    @Override
    public int getRemainingFees(){
        return feesTotal-feesPaid;
    }

    @Override
    public int getSalary() {
        return 0;
    }

    @Override
    public void receiveSalary(int amount) {
        System.out.println("Students don't receive salary!");
    }

    @Override
    public String toString() {
        return "Student "+ studentNumber +
                ":" + super.toString() +
                ", Grade: " + grade +
                ", Fees Paid: $" + feesPaid +
                ", Remaining Fees: $" + getRemainingFees();
    }
    

    
}
