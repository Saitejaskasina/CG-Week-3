import java.util.Scanner;
class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;
    Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
public class StudentLinkedList {
    Student head = null;
    public void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }
    public void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }
    public void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos == 1) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }
    public void deleteByRollNumber(int roll) {
        if (head == null) return;
        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        } else {
            System.out.println("Student not found");
        }
    }
    public void searchByRollNumber(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Found: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found");
    }
    public void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
    public void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll Number\n5. Search\n6. Display All\n7. Update Grade\n8. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Roll, Name, Age, Grade: ");
                    int roll = sc.nextInt();
                    String name = sc.next();
                    int age = sc.nextInt();
                    String grade = sc.next();
                    if (choice == 1) list.addAtBeginning(roll, name, age, grade);
                    else if (choice == 2) list.addAtEnd(roll, name, age, grade);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        list.addAtPosition(pos, roll, name, age, grade);
                    }
                    break;
                case 4:
                    System.out.print("Enter Roll to Delete: ");
                    list.deleteByRollNumber(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Roll to Search: ");
                    list.searchByRollNumber(sc.nextInt());
                    break;
                case 6:
                    list.displayAll();
                    break;
                case 7:
                    System.out.print("Enter Roll and New Grade: ");
                    list.updateGrade(sc.nextInt(), sc.next());
                    break;
            }
        } while (choice != 8);
        sc.close();
    }
}