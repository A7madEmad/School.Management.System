import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_NAME = "school.txt";

    public static void saveData(School school) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("FINANCE|" + school.getTotalMoneyEarned() + "|" + school.getTotalMoneySpent());

            for (Person p : school.getPersons()) {
                if (p instanceof Student) {
                    Student s = (Student) p;
                    writer.println("STUDENT|" + s.getID() + "|" + s.getName() + "|" + s.getGrade() +
                            "|" + s.getFeesPaid() + "|" + s.getFeesTotal() + "|" + s.getDOB().getTime());
                }
                else if (p instanceof Teacher) {
                    Teacher t = (Teacher) p;
                    writer.println("TEACHER|" + t.getID() + "|" + t.getName() + "|" + t.getSalary() +
                            "|" + t.getSalaryEarned() + "|" + t.getDOB().getTime());
                }
            }
            for (Course c : school.getCourses()) {
                writer.println("COURSE|" + c.getCourseName() + "|" + c.getCourseCode() +
                        "|" + c.getCreditHours() + "|" + c.getTeacher().getName());
            }
            for (Person p : school.getPersons()) {
                if (p instanceof Student) {
                    Student s = (Student) p;
                    for (Course c : s.getCourses())
                        writer.println("ENROLLMENT|" + s.getName() + "|" + c.getCourseCode());
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadData(School school) {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("ℹ No previous data found. Starting fresh!");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            // متغيرات مؤقتة لحفظ الحسابات المالية القادمة من الملف
            int fileTotalMoneyEarned = 0;
            int fileTotalMoneySpent = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue; // تخطي أي سطر فارغ

                String[] tokens = line.split("\\|");
                String type = tokens[0];

                switch (type) {
                    case "FINANCE":
                        fileTotalMoneyEarned = Integer.parseInt(tokens[1]);
                        fileTotalMoneySpent = Integer.parseInt(tokens[2]);
                        break;

                    case "STUDENT":
                        int sId = Integer.parseInt(tokens[1]);
                        String sName = tokens[2];
                        int grade = Integer.parseInt(tokens[3]);
                        int feesPaid = Integer.parseInt(tokens[4]);
                        int feesTotal = Integer.parseInt(tokens[5]);
                        long sDobTime = Long.parseLong(tokens[6]);

                        Student s = new Student(sName, sId, grade);
                        s.setFeesPaid(feesPaid);
                        s.setFeesTotal();
                        s.setDOB(new java.util.Date(sDobTime));

                        school.addPerson(s); // الإضافة لكائن المدرسة الممرر
                        break;

                    case "TEACHER":
                        int tId = Integer.parseInt(tokens[1]);
                        String tName = tokens[2];
                        int salary = Integer.parseInt(tokens[3]);
                        int salaryEarned = Integer.parseInt(tokens[4]);
                        long tDobTime = Long.parseLong(tokens[5]);
                        Teacher t = new Teacher(tName, tId, salary);
                        t.receiveSalary(salaryEarned);
                        t.setDOB(new java.util.Date(tDobTime));
                        school.addPerson(t);
                        break;

                    case "COURSE":
                        String cName = tokens[1];
                        String cCode = tokens[2];
                        int credit = Integer.parseInt(tokens[3]);
                        String teacherName = tokens[4];

                        // البحث عن المعلم داخل كائن المدرسة
                        for (Person p : school.getPersons()) {
                            if (p instanceof Teacher && p.getName().equalsIgnoreCase(teacherName)) {
                                Teacher foundTeacher = (Teacher) p;
                                Course c = new Course(cName, cCode, credit, foundTeacher);
                                school.addCourse(c);
                                foundTeacher.addCourse(c);
                                break;
                            }
                        }
                        break;

                    case "ENROLLMENT":
                        String studentName= tokens[1];
                        String courseCode = tokens[2];

                        Student enrolledStudent = null;
                        Course enrolledCourse = null;

                        // 1. ابحث عن الطالب بواسطة الاسم
                        for (Person p : school.getPersons()) {
                            if (p instanceof Student && p.getName() == studentName) {
                                enrolledStudent = (Student) p;
                                break;
                            }
                        }

                        // 2. ابحث عن المادة بواسطة ال Code
                        for (Course c : school.getCourses()) {
                            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                                enrolledCourse = c;
                                break;
                            }
                        }
                        if (enrolledStudent != null && enrolledCourse != null) {
                            enrolledStudent.addCourse(enrolledCourse);
                        }
                        break;
                }
            }

            School.updateTotalMoneyEarned(fileTotalMoneyEarned - school.getTotalMoneyEarned());
            School.updateTotalMoneySpent(fileTotalMoneySpent - school.getTotalMoneySpent());

            System.out.println("Data loaded successfully from " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}