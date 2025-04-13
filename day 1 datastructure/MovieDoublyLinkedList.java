import java.util.Scanner;
class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next, prev;
    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = this.prev = null;
    }
}
public class MovieDoublyLinkedList {
    Movie head = null, tail = null;
    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }
    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }
    public void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
    }
    public void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Movie removed successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }
    public void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                displayMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found by this director.");
    }
    public void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                displayMovie(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with this rating.");
    }
    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }
    public void displayForward() {
        Movie temp = head;
        while (temp != null) {
            displayMovie(temp);
            temp = temp.next;
        }
    }
    public void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            displayMovie(temp);
            temp = temp.prev;
        }
    }
    private void displayMovie(Movie m) {
        System.out.println("Title: " + m.title + ", Director: " + m.director + ", Year: " + m.year + ", Rating: " + m.rating);
    }
    public static void main(String[] args) {
        MovieDoublyLinkedList list = new MovieDoublyLinkedList();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Title\n5. Search by Director\n6. Search by Rating\n7. Display Forward\n8. Display Reverse\n9. Update Rating\n10. Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    String title = sc.nextLine();
                    String director = sc.nextLine();
                    int year = sc.nextInt();
                    double rating = sc.nextDouble();
                    if (choice == 1) list.addAtBeginning(title, director, year, rating);
                    else if (choice == 2) list.addAtEnd(title, director, year, rating);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        list.addAtPosition(pos, title, director, year, rating);
                    }
                    break;
                case 4:
                    System.out.print("Enter Title to Remove: ");
                    list.removeByTitle(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Director to Search: ");
                    list.searchByDirector(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Rating to Search: ");
                    list.searchByRating(sc.nextDouble());
                    break;
                case 7:
                    list.displayForward();
                    break;
                case 8:
                    list.displayReverse();
                    break;
                case 9:
                    System.out.print("Enter Title and New Rating: ");
                    String t = sc.nextLine();
                    double newR = sc.nextDouble();
                    list.updateRating(t, newR);
                    break;
            }
        } while (choice != 10);
        sc.close();
    }
}