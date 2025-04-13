class CustomHashMap {
 private static class Entry {
  int key, value;
  Entry next;
  Entry(int key, int value) {
   this.key = key;
   this.value = value;
  }
 }
 private final int SIZE = 10;
 private Entry[] table;
 public CustomHashMap() {
  table = new Entry[SIZE];
 }
 private int hash(int key) {
  return key % SIZE;
 }
 public void put(int key, int value) {
  int index = hash(key);
  Entry head = table[index];
  while (head != null) {
   if (head.key == key) {
    head.value = value;
    return;
   }
   head = head.next;
  }
  Entry newEntry = new Entry(key, value);
  newEntry.next = table[index];
  table[index] = newEntry;
 }
 public Integer get(int key) {
  int index = hash(key);
  Entry head = table[index];
  while (head != null) {
   if (head.key == key) return head.value;
   head = head.next;
  }
  return null;
 }
 public void remove(int key) {
  int index = hash(key);
  Entry head = table[index];
  Entry prev = null;
  while (head != null) {
   if (head.key == key) {
    if (prev == null) table[index] = head.next;
    else prev.next = head.next;
    return;
   }
   prev = head;
   head = head.next;
  }
 }
 public static void main(String[] args) {
  CustomHashMap map = new CustomHashMap();
  map.put(1, 10);
  map.put(2, 20);
  map.put(12, 30);
  System.out.println(map.get(1));
  System.out.println(map.get(2));
  System.out.println(map.get(12));
  map.remove(2);
  System.out.println(map.get(2));
 }
}
