import java.util.Scanner;
class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;
    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}
public class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;
    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }
    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }
    public void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos == 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail) tail = newTask;
    }
    public void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            if (head == tail) {
                head = tail = null;
                return;
            }
            head = head.next;
            tail.next = head;
            return;
        }
        Task temp = head;
        while (temp.next != head && temp.next.id != id) {
            temp = temp.next;
        }
        if (temp.next.id == id) {
            if (temp.next == tail) tail = temp;
            temp.next = temp.next.next;
        }
    }
    public void viewAndMoveNext() {
        if (current == null) current = head;
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task: " + current.name + " (ID: " + current.id + ", Priority: " + current.priority + ", Due: " + current.dueDate + ")");
        current = current.next;
    }
    public void displayAll() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks found.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task ID: " + temp.id + ", Name: " + temp.name + ", Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No tasks with priority " + priority);
    }
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. View Current Task\n6. Display All\n7. Search by Priority\n8. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter ID, Name, Priority, DueDate: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    int priority = sc.nextInt();
                    String dueDate = sc.next();
                    if (choice == 1) scheduler.addAtBeginning(id, name, priority, dueDate);
                    else if (choice == 2) scheduler.addAtEnd(id, name, priority, dueDate);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        scheduler.addAtPosition(pos, id, name, priority, dueDate);
                    }
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    scheduler.removeById(sc.nextInt());
                    break;
                case 5:
                    scheduler.viewAndMoveNext();
                    break;
                case 6:
                    scheduler.displayAll();
                    break;
                case 7:
                    System.out.print("Enter Priority to search: ");
                    scheduler.searchByPriority(sc.nextInt());
                    break;
            }
        } while (choice != 8);
        sc.close();
    }
}