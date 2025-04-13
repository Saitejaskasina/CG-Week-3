import java.util.Scanner;
class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;
    Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}
class TicketCircularList {
    private Ticket head = null;
    public void addTicket(int id, String custName, String movie, String seat, String time) {
        Ticket newTicket = new Ticket(id, custName, movie, seat, time);
        if (head == null) {
            head = newTicket;
            head.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
        System.out.println("Ticket booked successfully!");
    }
    public void removeTicket(int id) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }
        Ticket curr = head, prev = null;
        do {
            if (curr.ticketID == id) {
                if (curr == head) {
                    if (head.next == head) {
                        head = null;
                    } else {
                        Ticket last = head;
                        while (last.next != head) {
                            last = last.next;
                        }
                        head = head.next;
                        last.next = head;
                    }
                } else {
                    prev.next = curr.next;
                }
                System.out.println("Ticket removed.");
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
        System.out.println("Ticket ID not found.");
    }
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        System.out.println("\nBooked Tickets:");
        do {
            System.out.println("ID: " + temp.ticketID + ", Name: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchTicket(String key) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(key) || temp.movieName.equalsIgnoreCase(key)) {
                System.out.println("Found - ID: " + temp.ticketID + ", Name: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No matching ticket found.");
        }
    }
    public int countTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}
public class TicketReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketCircularList system = new TicketCircularList();
        int choice;
        do {
            System.out.println("\n--- Ticket Reservation System ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket");
            System.out.println("5. Count Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Ticket ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Movie Name: ");
                    String movie = sc.nextLine();
                    System.out.print("Seat Number: ");
                    String seat = sc.nextLine();
                    System.out.print("Booking Time: ");
                    String time = sc.nextLine();
                    system.addTicket(id, name, movie, seat, time);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    system.removeTicket(sc.nextInt());
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String key = sc.nextLine();
                    system.searchTicket(key);
                    break;
                case 5:
                    System.out.println("Total Booked Tickets: " + system.countTickets());
                    break;
            }
        } while (choice != 6);
        sc.close();
    }
}