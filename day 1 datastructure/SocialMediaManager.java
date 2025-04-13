import java.util.*;

class FriendNode {
    int userId;
    FriendNode next;

    FriendNode(int id) {
        this.userId = id;
        this.next = null;
    }
}

class UserNode {
    int userId;
    String name;
    int age;
    FriendNode friendList;
    UserNode next;

    UserNode(int id, String name, int age) {
        this.userId = id;
        this.name = name;
        this.age = age;
        this.friendList = null;
        this.next = null;
    }
}

public class SocialMediaManager {
    UserNode head = null;

    public void addUser(int id, String name, int age) {
        UserNode newUser = new UserNode(id, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newUser;
        }
    }

    private UserNode findUser(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    public void addFriend(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);
        if (user1 == null || user2 == null)
            return;
        addToFriendList(user1, userId2);
        addToFriendList(user2, userId1);
    }

    private void addToFriendList(UserNode user, int friendId) {
        FriendNode newFriend = new FriendNode(friendId);
        if (user.friendList == null) {
            user.friendList = newFriend;
        } else {
            FriendNode temp = user.friendList;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newFriend;
        }
    }

    public void removeFriend(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);
        if (user1 == null || user2 == null)
            return;
        removeFromFriendList(user1, userId2);
        removeFromFriendList(user2, userId1);
    }

    private void removeFromFriendList(UserNode user, int friendId) {
        if (user.friendList == null)
            return;
        if (user.friendList.userId == friendId) {
            user.friendList = user.friendList.next;
            return;
        }
        FriendNode temp = user.friendList;
        while (temp.next != null && temp.next.userId != friendId) {
            temp = temp.next;
        }
        if (temp.next != null)
            temp.next = temp.next.next;
    }

    public void displayFriends(int userId) {
        UserNode user = findUser(userId);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        System.out.print("Friends of " + user.name + ": ");
        FriendNode temp = user.friendList;
        while (temp != null) {
            System.out.print(temp.userId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = findUser(userId1);
        UserNode user2 = findUser(userId2);
        if (user1 == null || user2 == null)
            return;
        System.out.print("Mutual friends between " + user1.name + " and " + user2.name + ": ");
        FriendNode temp1 = user1.friendList;
        while (temp1 != null) {
            FriendNode temp2 = user2.friendList;
            while (temp2 != null) {
                if (temp1.userId == temp2.userId) {
                    System.out.print(temp1.userId + " ");
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        System.out.println();
    }

    public void searchUser(String query) {
        UserNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(query) || String.valueOf(temp.userId).equals(query)) {
                System.out.println("User found: ID=" + temp.userId + ", Name=" + temp.name + ", Age=" + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found)
            System.out.println("User not found.");
    }

    public void countFriends() {
        UserNode temp = head;
        while (temp != null) {
            int count = 0;
            FriendNode f = temp.friendList;
            while (f != null) {
                count++;
                f = f.next;
            }
            System.out.println(temp.name + " has " + count + " friends.");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SocialMediaManager sm = new SocialMediaManager();
        sm.addUser(1, "Alice", 22);
        sm.addUser(2, "Bob", 25);
        sm.addUser(3, "Charlie", 20);
        sm.addUser(4, "David", 23);
        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);
        sm.addFriend(3, 4);
        sm.displayFriends(1);
        sm.findMutualFriends(1, 2);
        sm.searchUser("Bob");
        sm.countFriends();
        sm.removeFriend(1, 2);
        sm.displayFriends(1);
    }
}