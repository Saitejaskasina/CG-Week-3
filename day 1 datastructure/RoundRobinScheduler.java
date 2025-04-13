import java.util.*;
class Process {
    int processID;
    int burstTime;
    int priority;
    int remainingTime;
    Process next;
    public Process(int id, int burst, int priority) {
        this.processID = id;
        this.burstTime = burst;
        this.remainingTime = burst;
        this.priority = priority;
        this.next = null;
    }
}
public class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    public void addProcess(int id, int burst, int priority) {
        Process newProcess = new Process(id, burst, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }
    public void removeProcess(int id) {
        if (head == null) return;
        Process temp = head;
        Process prev = tail;
        do {
            if (temp.processID == id) {
                if (temp == head && temp == tail) {
                    head = null;
                    tail = null;
                } else {
                    prev.next = temp.next;
                    if (temp == head) head = temp.next;
                    if (temp == tail) tail = prev;
                }
                break;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }
    public void simulate(int timeQuantum) {
        if (head == null) return;

        Map<Integer, Integer> waitingTime = new HashMap<>();
        Map<Integer, Integer> turnAroundTime = new HashMap<>();
        Map<Integer, Integer> completionTime = new HashMap<>();
        Map<Integer, Integer> burstTimeMap = new HashMap<>();

        Process current = head;
        int time = 0;
        do {
            burstTimeMap.put(current.processID, current.burstTime);
            waitingTime.put(current.processID, 0);
            turnAroundTime.put(current.processID, 0);
            current = current.next;
        } while (current != head);

        current = head;
        System.out.println("\n-- Round Robin Scheduling Simulation --");
        while (head != null) {
            if (current.remainingTime > 0) {
                int execTime = Math.min(timeQuantum, current.remainingTime);
                System.out.println("Process " + current.processID + " executed for " + execTime + " units");
                current.remainingTime -= execTime;
                time += execTime;
                if (current.remainingTime == 0) {
                    completionTime.put(current.processID, time);
                    removeProcess(current.processID);
                    if (current == head) head = head.next;
                }
            }
            current = current.next;
        }
        double totalWaiting = 0, totalTurnaround = 0;
        System.out.println("\nProcess Summary:");
        for (int pid : burstTimeMap.keySet()) {
            int turnaround = completionTime.get(pid);
            int waiting = turnaround - burstTimeMap.get(pid);
            turnAroundTime.put(pid, turnaround);
            waitingTime.put(pid, waiting);
            totalWaiting += waiting;
            totalTurnaround += turnaround;
            System.out.println("PID: " + pid + ", Waiting: " + waiting + ", Turnaround: " + turnaround);
        }
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWaiting / burstTimeMap.size());
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / burstTimeMap.size());
    }
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID, Burst Time, Priority: ");
            int id = sc.nextInt();
            int burst = sc.nextInt();
            int priority = sc.nextInt();
            scheduler.addProcess(id, burst, priority);
        }
        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();
        scheduler.simulate(tq);
        sc.close();
    }
}