import java.util.Scanner;
import java.util.InputMismatchException;

public class SchoolManagementSystem {
    public static void main(String[] args) {
        School school = new School();
        Scanner input = new Scanner(System.in);

        FileManager.loadData(school);

        while (true) {
            System.out.println("\n=== School Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Register Student in Course");
            System.out.println("5. Pay Fees");
            System.out.println("6. Pay Salary");
            System.out.println("7. Display All Persons");
            System.out.println("8. Display All Courses");
            System.out.println("9. Show Financial Status");
            System.out.println("10. Exit & Save");
            System.out.print("Choose option: ");

            int choice = 0;
            try {
                choice = input.nextInt();
                input.nextLine(); // تنظيف البافر
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Please enter a number between 1 and 10.");
                input.nextLine(); // تنظيف الإدخال الخاطئ
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter student ID: ");
                        int sID = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter student name: ");
                        String sName = input.nextLine();
                        System.out.print("Enter grade: ");
                        int grade = input.nextInt();
                        System.out.print("Enter DOB (year month day): ");
                        int year = input.nextInt();
                        int month = input.nextInt();
                        int day = input.nextInt();

                        Student s = new Student(sName, sID, grade);
                        s.setDOB(year, month, day);
                        school.addPerson(s);
                        System.out.println("Student added successfully!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input data types! Student was not added.");
                        input.nextLine();
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter teacher ID: ");
                        int tID = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter teacher name: ");
                        String tName = input.nextLine();
                        System.out.print("Enter salary: ");
                        int salary = input.nextInt();
                        System.out.print("Enter DOB (year month day): ");
                        int tYear = input.nextInt();
                        int tMonth = input.nextInt();
                        int tDay = input.nextInt();

                        Teacher t = new Teacher(tName, tID, salary);
                        t.setDOB(tYear, tMonth, tDay);
                        school.addPerson(t);
                        System.out.println("Teacher added successfully!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input data types! Teacher was not added.");
                        input.nextLine();
                    }
                    break;

                case 3:
                    System.out.print("Enter course name: ");
                    String cName = input.nextLine();
                    System.out.print("Enter course code: ");
                    String cCode = input.nextLine();
                    try {
                        System.out.print("Enter credit hours: ");
                        int ch = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter teacher name for this course: ");
                        String teacherName = input.nextLine();

                        Teacher foundTeacher = null;
                        for (Person p : school.getPersons()) {
                            if (p instanceof Teacher && p.getName().equalsIgnoreCase(teacherName)) {
                                foundTeacher = (Teacher) p;
                                break;
                            }
                        }

                        if (foundTeacher != null) {
                            Course c = new Course(cName, cCode, ch, foundTeacher);
                            school.addCourse(c);
                            foundTeacher.addCourse(c);
                            System.out.println("✔ Course added successfully!");
                        } else {
                            System.out.println("Teacher not found!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid credit hours input!");
                        input.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Enter student name: ");
                    String studentName = input.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = input.nextLine();

                    Student foundStudent = null;
                    Course foundCourse = null;

                    for (Person p : school.getPersons()) {
                        if (p instanceof Student && p.getName().equalsIgnoreCase(studentName)) {
                            foundStudent = (Student) p;
                            break;
                        }
                    }

                    for (Course c : school.getCourses()) {
                        if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                            foundCourse = c;
                            break;
                        }
                    }

                    if (foundStudent != null && foundCourse != null) {
                        foundStudent.addCourse(foundCourse);
                        System.out.println("Student registered in course!");
                    } else {
                        System.out.println("Student or course not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter student name: ");
                    String sPayName = input.nextLine();
                    Student found_Student = null;

                    for (Person p : school.getPersons()) {
                        if (p instanceof Student && p.getName().equalsIgnoreCase(sPayName)) {
                            found_Student = (Student) p;
                            break;
                        }
                    }

                    if (found_Student == null) {
                        System.out.println("Student not found");
                        break;
                    }

                    System.out.println("Total fees = $" + found_Student.getFeesTotal());
                    System.out.println("Paid = $" + found_Student.getFeesPaid());
                    System.out.println("Remaining = $" + found_Student.getRemainingFees());

                    try {
                        System.out.print("Enter fees amount to pay: ");
                        int fees = input.nextInt();
                        found_Student.payFees(fees);
                        System.out.println("Payment registered!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid amount!");
                        input.nextLine();
                    }
                    break;

                case 6:
                    System.out.print("Enter teacher name: ");
                    String teacherPayName = input.nextLine();
                    try {
                        System.out.print("Enter salary amount: ");
                        int sal = input.nextInt();
                        boolean teacherPaid = false;
                        for (Person p : school.getPersons()) {
                            if (p instanceof Teacher && p.getName().equalsIgnoreCase(teacherPayName)) {
                                ((Teacher) p).receiveSalary(sal);
                                System.out.println("✔ Salary paid!");
                                teacherPaid = true;
                                break;
                            }
                        }
                        if (!teacherPaid) System.out.println("Teacher not found.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid salary amount!");
                        input.nextLine();
                    }
                    break;

                case 7:
                    System.out.println("\n=== All Persons ===");
                    school.displayAllPersons();
                    break;

                case 8:
                    System.out.println("\n=== All Courses ===");
                    school.displayAllCourses();
                    break;

                case 9:
                    System.out.println("\n=== Financial Status ===");
                    System.out.println("Total Money Earned: $" + school.getTotalMoneyEarned());
                    System.out.println("Total Money Spent: $" + school.getTotalMoneySpent());
                    System.out.println("Net Profit: $" + (school.getTotalMoneyEarned() - school.getTotalMoneySpent()));
                    break;

                case 10:
                    System.out.println("Saving data...");
                    FileManager.saveData(school);
                    System.out.println("Exiting... Goodbye!");
                    input.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}