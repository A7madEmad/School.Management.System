
import java.util.Calendar;
import java.util.Date;

public abstract class Person {

    //=========Attributes=========
    private int ID ;
    private String name;
    private Date DOB;


    //========Constructor========
    public Person(){
    }

    public Person(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Person(int ID, String name, Date DOB) {
        this.ID = ID;
        this.name = name;
        this.DOB = DOB;
    }

    //========Seter & Getter============

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setDOB(int iYear, int iMonth, int iDay ) {
      Calendar cal = Calendar.getInstance();
      cal.set( iYear, iMonth-1, iDay );
      this.DOB = cal.getTime();
   }


    //======AbstractMethods============

    public abstract int getGrade();
    public abstract void payFees(int fees);
    public abstract int getRemainingFees();
    public abstract int getSalary();

    @Override
    public  String toString() {
        return " name: " + name +
                 ", ID: "+ getID()+
                ", Date Of Birth:(" + getDOB() +")";
    }


}
    
            

