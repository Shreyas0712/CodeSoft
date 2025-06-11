import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎓 Student Grade Calculator");

        // Take number of subjects
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();

            // Validate input
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("❌ Invalid input. Marks should be between 0 and 100.");
                i--; // repeat the same subject input
                continue;
            }

            totalMarks += marks[i];
        }

        // Calculate average percentage
        double average = (double) totalMarks / numSubjects;

        // Determine grade
        String grade;
        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B";
        } else if (average >= 60) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else {
            grade = "F (Fail)";
        }

        // Display Results
        System.out.println("\n📊 --- Result Summary ---");
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
