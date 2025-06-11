import java.io.*;
import java.util.*;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toCSV() {
        return name + "," + rollNumber + "," + grade;
    }

    public static Student fromCSV(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], parts[1], parts[2]);
    }

    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String filename = "students.csv";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public Student searchByRoll(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    public boolean removeStudent(String rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public void editStudent(String rollNumber, String newName, String newGrade) {
        Student s = searchByRoll(rollNumber);
        if (s != null) {
            s.setName(newName);
            s.setGrade(newGrade);
            saveToFile();
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.write(s.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    public void loadFromFile() {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromCSV(line));
            }
        } catch (IOException e) {
            // File may not exist yet
        }
    }
}

public class StudentSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n📚 Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    String roll = scanner.nextLine().trim();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine().trim();

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                        System.out.println("❌ All fields are required.");
                        break;
                    }

                    if (sms.searchByRoll(roll) != null) {
                        System.out.println("❌ Student with this roll number already exists.");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    System.out.println("✅ Student added.");
                    break;

                case 2:
                    sms.displayAllStudents();
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    String rSearch = scanner.nextLine().trim();
                    Student found = sms.searchByRoll(rSearch);
                    if (found != null) {
                        System.out.println("🔍 Found: " + found);
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter roll number to edit: ");
                    String rEdit = scanner.nextLine().trim();
                    Student sEdit = sms.searchByRoll(rEdit);
                    if (sEdit == null) {
                        System.out.println("❌ Student not found.");
                        break;
                    }
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine().trim();
                    sms.editStudent(rEdit, newName, newGrade);
                    System.out.println("✅ Student updated.");
                    break;

                case 5:
                    System.out.print("Enter roll number to remove: ");
                    String rRemove = scanner.nextLine().trim();
                    if (sms.removeStudent(rRemove)) {
                        System.out.println("✅ Student removed.");
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("👋 Exiting system.");
                    return;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}
