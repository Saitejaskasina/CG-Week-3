import java.util.Scanner;
class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;
    Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}
public class InventoryManagement {
    Item head = null;
    public void addAtBeginning(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        newItem.next = head;
        head = newItem;
    }
    public void addAtEnd(String name, int id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newItem;
    }
    public void addAtPosition(int pos, String name, int id, int qty, double price) {
        if (pos == 1) {
            addAtBeginning(name, id, qty, price);
            return;
        }
        Item newItem = new Item(name, id, qty, price);
        Item temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++) temp = temp.next;
        if (temp == null) {
            System.out.println("Position out of bounds");
            return;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }
    public void removeById(int id) {
        if (head == null) return;
        if (head.itemId == id) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.itemId != id) temp = temp.next;
        if (temp.next != null) temp.next = temp.next.next;
        else System.out.println("Item not found");
    }
    public void updateQuantity(int id, int qty) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == id) {
                temp.quantity = qty;
                System.out.println("Quantity updated");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found");
    }
    public void search(String keyword) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.itemId).equals(keyword) || temp.itemName.equalsIgnoreCase(keyword)) {
                System.out.println("Found: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found");
    }
    public void totalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: Rs. " + total);
    }
    public void sortInventory(String sortBy, boolean ascending) {
        head = mergeSort(head, sortBy, ascending);
    }
    private Item mergeSort(Item head, String sortBy, boolean ascending) {
        if (head == null || head.next == null) return head;
        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;
        Item left = mergeSort(head, sortBy, ascending);
        Item right = mergeSort(nextOfMiddle, sortBy, ascending);
        return sortedMerge(left, right, sortBy, ascending);
    }
    private Item sortedMerge(Item a, Item b, String sortBy, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;
        boolean condition;
        if (sortBy.equals("name"))
            condition = ascending ? a.itemName.compareToIgnoreCase(b.itemName) <= 0 : a.itemName.compareToIgnoreCase(b.itemName) > 0;
        else
            condition = ascending ? a.price <= b.price : a.price > b.price;
        Item result;
        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, sortBy, ascending);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, sortBy, ascending);
        }
        return result;
    }
    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public void displayAll() {
        Item temp = head;
        while (temp != null) {
            System.out.println("Item: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: Rs. " + temp.price);
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        InventoryManagement inv = new InventoryManagement();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. Update Quantity\n4. Search Item\n5. Total Value\n6. Sort Inventory\n7. Display All\n8. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Name, ID, Qty, Price: ");
                    String name = sc.next();
                    int id = sc.nextInt();
                    int qty = sc.nextInt();
                    double price = sc.nextDouble();
                    System.out.print("1. Beginning 2. End 3. Position: ");
                    int opt = sc.nextInt();
                    if (opt == 1) inv.addAtBeginning(name, id, qty, price);
                    else if (opt == 2) inv.addAtEnd(name, id, qty, price);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        inv.addAtPosition(pos, name, id, qty, price);
                    }
                    break;
                case 2:
                    System.out.print("Enter Item ID to Remove: ");
                    inv.removeById(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Item ID and New Quantity: ");
                    inv.updateQuantity(sc.nextInt(), sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter Item ID or Name to Search: ");
                    inv.search(sc.next());
                    break;
                case 5:
                    inv.totalValue();
                    break;
                case 6:
                    System.out.print("Sort by (name/price): ");
                    String sBy = sc.next();
                    System.out.print("Ascending? (true/false): ");
                    boolean asc = sc.nextBoolean();
                    inv.sortInventory(sBy, asc);
                    break;
                case 7:
                    inv.displayAll();
                    break;
            }
        } while (choice != 8);
        sc.close();
    }
}