package Small_Data;

public class Node {

    private int key;
    private String value;
    private Node prev;
    private Node next;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public Node(int key, String value, Node prev, Node next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
    public Node getPrev() {
        return prev;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
