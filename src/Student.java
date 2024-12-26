import java.util.Arrays;
import java.util.Comparator;

public class Student {

    private String name;
    private String surname;
    private int[] grades = new int[10];
    private int gradeCount = 0;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int[] getGrades() {
        return grades;
    }

    public void addGrade(int grade) {
        if (gradeCount < 10) {
            grades[gradeCount] = grade;
            gradeCount++;
        } else {
            System.arraycopy(grades, 1, grades, 0, 9);
            grades[9] = grade;
        }
    }

    public double getAverageGrade() {
        if (gradeCount == 0) return 0;
        int sum = 0;
        for (int i = 0; i < gradeCount; i++) {
            sum += grades[i];
        }
        return (double) sum / gradeCount;
    }

    public static class StudentService {
        public Student bestStudent(Student[] students) {
            if (students == null || students.length == 0) return null;
            Student best = students[0];
            for (Student student : students) {
                if (student.getAverageGrade() > best.getAverageGrade()) {
                    best = student;
                }
            }
            return best;
        }

        public void sortBySurname(Student[] students) {
            Arrays.sort(students, Comparator.comparing(Student::getSurname));
        }
    }

    public static void main(String[] args) {

        Student student1 = new Student("Никита", "Шарипов");
        student1.addGrade(5);
        student1.addGrade(4);
        student1.addGrade(3);

        Student student2 = new Student("Максим", "Солодов");
        student2.addGrade(3);
        student2.addGrade(4);
        student2.addGrade(4);

        Student student3 = new Student("Андрей", "Максимов");
        student3.addGrade(5);
        student3.addGrade(5);
        student3.addGrade(5);

        Student[] students = {student1, student2, student3};

        StudentService studentService = new StudentService();

        studentService.sortBySurname(students);

        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getSurname() +
                    " - Средний балл: " + student.getAverageGrade());
        }
    }
}
