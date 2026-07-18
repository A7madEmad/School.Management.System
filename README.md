# 🏫 School Management System (SMS)

A Java console application that digitizes core administrative tasks for a school: registering students and teachers, creating courses, enrolling students in courses, and tracking the school's finances (tuition fees and salaries). All data is persisted to a text file so nothing is lost between runs.

---

## 👥 Student Information & Contribution
* **Ahmed Abu Yousef** — ID: `2549011076` (Implemented the `Student` class, fixed file-parsing bugs, and wrote the report)
* **Ahmed Ekhzaiq** — ID: `2549011068` (Implemented the `Teacher` and `Course` classes, UML design)
* **Ahmed Alhajjar** — ID: `2549011060` (Implemented `School`, `Payable`, `FileManager` with try-with-resources, and the main menu workflow)

---

## 🚀 Project Description & Objective
The **School Management System** is a console-based backend solution built for a first-year OOP course assessment. It digitizes school administrative operations — course registration, student enrollment, salary disbursement, and fee processing — and persists every record to a flat text file (`school.txt`) so the school's state survives across separate runs of the program.

---

## ✨ Main Features
* **Interactive console menu** — 10 options covering the full workflow: add student, add teacher, add course, register a student in a course, pay fees, pay salary, list all people, list all courses, view financial status, and save & exit.
* **Persistent storage** — `FileManager` automatically saves and loads all students, teachers, courses, enrollments, and financial totals to/from `school.txt`.
* **Financial ledger** — Fee payments and salary payments update the school's running totals in real time, with validation to prevent a student from overpaying.
* **Course management** — Courses are linked to the teacher who gives them, and students can be enrolled in one or more courses.
* **Input validation & error handling** — Invalid menu choices, non-numeric input, and missing/corrupted save-file lines are caught and reported instead of crashing the program.

---

## 🏗️ Main Classes
| Class | Responsibility |
|---|---|
| `Person` (abstract) | Base class for anyone in the school. Stores ID, name, and date of birth; declares abstract methods (`getGrade`, `payFees`, `getRemainingFees`, `getSalary`) that force each subclass to define its own role-specific behavior. |
| `Student` | Extends `Person`, implements `Payable`. Tracks grade, fees paid/owed, and enrolled courses. |
| `Teacher` | Extends `Person`, implements `Payable`. Tracks salary, total salary received, and the courses they teach. |
| `Course` | Represents a single course: name, code, credit hours, and the `Teacher` who teaches it. |
| `Payable` (interface) | Declares the financial contract (`payFees`, `receiveSalary`) shared by `Student` and `Teacher`. |
| `School` | Central aggregator holding every `Person` and `Course` in the system, plus the school-wide financial totals. |
| `FileManager` | Saves/loads the entire school state to and from `school.txt`, using `try-with-resources` for safe file I/O. |
| `SchoolManagementSystem` | Entry point of the program; drives the console menu loop. |

---

## 💻 OOP Concepts Applied
* **Encapsulation** — All fields are private, accessed only through public getters/setters.
* **Inheritance** — `Student` and `Teacher` both extend the abstract `Person` class.
* **Polymorphism** — `Student` and `Teacher` each implement `Payable` differently, and each overrides `Person`'s abstract methods with its own logic.
* **Abstract Class** — `Person` cannot be instantiated directly; it forces every subclass to define role-specific behavior.
* **Interface** — `Payable` defines a shared financial contract implemented differently by `Student` and `Teacher`.
* **Composition / Aggregation** — `School` holds collections of `Person` and `Course` objects; `Course` holds a reference to its `Teacher`; `Student` holds a list of enrolled `Course` objects.

---

## 🛠️ File Handling Implementation
Data persistence uses standard Java text file streaming (`Scanner` for reading, `PrintWriter` for writing) wrapped in `try-with-resources` blocks in `FileManager`, so file handles are always closed safely — even if an error occurs mid-read or mid-write.

---

## 🖥️ Project Nature
* **Application Type:** 100% Java Console Application (Command-Line Interface) — no GUI.

---

## 🏃 How to Run the Project
1. Clone this repository:
   ```bash
   git clone [Paste Your Repository URL Here]
   ```
2. Open the project folder in your IDE of choice (IntelliJ IDEA, Eclipse, NetBeans, or VS Code with the Java extension).
3. Make sure all `.java` files (`Person`, `Student`, `Teacher`, `Course`, `Payable`, `School`, `FileManager`, `SchoolManagementSystem`) are in the same source folder/package.
4. Compile and run `SchoolManagementSystem.java` — this is the class containing `main()`.
   ```bash
   javac *.java
   java SchoolManagementSystem
   ```
5. Follow the on-screen menu to add students/teachers, register courses, process payments, and view the school's financial status.
6. Choose option **10 (Exit & Save)** to persist all data to `school.txt` before closing — this file will be loaded automatically the next time you run the program.

---

## 🤖 AI Usage Declaration
Parts of this project (code review and bug fixes, the UML class diagram, and the project report) were developed with the assistance of Claude (Anthropic). All AI-assisted contributions were reviewed and understood by the team before submission.

---

## 🔗 Links
* **Video Presentation:** [https://drive.google.com/file/d/1EvDyVB93-1vI9ATBZcWGLajDt2JiM8J_/view?usp=drive_link]
* **GitHub Repository:** [https://github.com/A7madEmad/School.Management.System.git]