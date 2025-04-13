class TextState {
    String content;
    TextState prev, next;
    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}
public class TextEditor {
    private TextState head = null, tail = null, current = null;
    private int size = 0;
    private final int MAX_HISTORY = 10;
    public void addState(String newContent) {
        TextState newState = new TextState(newContent);
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }
        if (head == null) {
            head = tail = current = newState;
        } else {
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
            current = tail;
        }
        size++;
        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo done.");
        } else {
            System.out.println("Cannot undo further.");
        }
    }
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo done.");
        } else {
            System.out.println("Cannot redo further.");
        }
    }
    public void showCurrentState() {
        if (current != null) {
            System.out.println("Current Content: " + current.content);
        } else {
            System.out.println("No content available.");
        }
    }
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        editor.showCurrentState();
        editor.undo();
        editor.showCurrentState();
        editor.undo();
        editor.showCurrentState();
        editor.redo();
        editor.showCurrentState();
        editor.addState("New Text after Undo");
        editor.showCurrentState();
        editor.redo();
    }
}