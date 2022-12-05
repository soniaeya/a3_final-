package Small_Data;

import java.util.Arrays;
import java.util.Random;

public class DoublyLinkedList {

    private int size;
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        this.size = 0;
        head = null;
        tail = null;

    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int Size) {
        this.size = Size;
    }

    public Node getHead() {
        return this.head;
    }

    public void setHead(Node newHead) {
        this.head = newHead;
    }

    public Node getTail() {
        return this.tail;
    }

    public void setTail(Node newTail) {
        this.tail = newTail;
    }

    public boolean isEmpty() {
        if (getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int generate() {
        Random rnd = new Random();
        boolean dupeCheck = false;
        int newKey = rnd.nextInt(99999999);
        int i = 0;
        Node temp = getHead();
        while(dupeCheck == false){
            while (i < getSize()) {
                if (temp.getKey() == newKey) {
                    dupeCheck = true;
                } else {
                    temp = temp.getNext();
                    i++;
                }
            }
            break;
        }
        return newKey;
    }


    public int [] allKeys() {
        int max = 0;
        int [] keys = new int[getSize()];
        int [] sortedKeys = new int[getSize()];
        int i = 0;
        Node temp = getHead();
        while (i < getSize()) {
            if (temp.getKey() > max) {
                max = temp.getKey();
            } else {
                temp = temp.getNext();
                i++;
            }
        }

        temp = getHead();
        int j = 0;
        while (j < getSize()) {
            keys[j] = temp.getKey();
            temp = temp.getNext();
            j++;
        }

        for(int place = 1; max/place > 0; place *= 10){
            int[] output = new int[keys.length + 1];
            int[] count = new int[max + 1];
            for (int k = 0; k < max; ++k)
                count[k] = 0;

            // Calculate count of elements
            for (int k = 0; k < keys.length; k++)
                count[(keys[k] / place) % 10]++;

            // Calculate cumulative count
            for (int k = 1; k < 10; k++)
                count[k] += count[k - 1];

            // Place the elements in sorted order
            for (int k = keys.length - 1; k >= 0; k--) {
                output[count[(keys[k] / place) % 10] - 1] = keys[k];
                count[(keys[k] / place) % 10]--;
            }

            for (int k = 0; k < keys.length; k++) {
                sortedKeys[k] = output[k];
            }
        }
        return sortedKeys;
    }


    public void addLast(int key, String value) {
        if (isEmpty()) {
            Node newTail = new Node(key,value);
            newTail.setPrev(null);
            newTail.setNext(null);
            setHead(newTail);
            setTail(newTail);
        } else {
            Node newTail = new Node(key, value, getTail(), null);
            getTail().setNext(newTail);
            setTail(newTail);
        }

        setSize(getSize() + 1);

    }

    public String remove(int key) {
        Node temp = getHead();
        String value = "";
        while (temp != getTail().getNext()) {
            if (temp.getKey() == key) {
                if (temp == getHead()) { // removes from head
                    value = temp.getValue();
                    temp = temp.getNext();
                    temp.getPrev().setNext(null);
                    temp.setPrev(null);
                    setHead(temp);
                    break;
                } else if (temp == getTail()) { // removes from tail
                    value = temp.getValue();
                    temp = temp.getPrev();
                    temp.getNext().setPrev(null);
                    setTail(temp);
                    break;
                } else { // removes from anywhere
                    value = temp.getValue();
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    break;
                }
            }

            temp = temp.getNext();

        }
        setSize(getSize() - 1);
        return value;
    }

    public String getValue(int key) {
        int i = 0;
        boolean found = false;
        Node temp = getHead();
        while (i < getSize()) {
            if (temp.getKey() == key) {
                found = true;
                break;
            } else {
                temp = temp.getNext();
                i++;

            }
        }
        if (found) {
            return temp.getValue();
        } else {
            return null;
        }
    }

    public int nextKey(int key) {
        int i = 0;
        boolean found = false;
        Node temp = getHead();
        while (i < getSize()) {
            if (temp.getKey() == key) {
                found = true;
                break;
            } else {
                temp = temp.getNext();
                i++;
            }
        }
        if (found) {
            return temp.getNext().getKey();
        } else {
            return 0;
        }
    }

    public int prevKey(int key) {
        int i = 0;
        boolean found = false;
        Node temp = getHead();
        while (i < getSize()) {
            if (temp.getKey() == key) {
                found = true;
                break;
            } else {
                temp = temp.getNext();
                i++;
            }
        }
        if (found) {
            return temp.getPrev().getKey();
        } else {
            return 0;
        }
    }

    public int rangeKey(int key1, int key2) {
        int i = 0;
        int j = 0;
        boolean found1 = false;
        boolean found2 = false;
        Node temp1 = getHead();
        Node temp2 = getHead();
        while (i < getSize()) {
            if (temp1.getKey() == key1) {
                found1 = true;
                break;
            } else {
                temp1 = temp1.getNext();
                i++;
            }
        }
        while (j < getSize()) {
            if (temp2.getKey() == key2) {
                found2 = true;
                break;
            } else {
                temp2 = temp2.getNext();
                j++;
            }
        }
        if (found1 && found2) {
            return ((j-i)-1);
        } else {
            return 0;
        }
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("The list is empty");
        } else {
            System.out.println("Size of the list is: " + getSize());
            Node temp = getHead();
            while (temp != getTail()) {
                System.out.print("Key: " + temp.getKey());
                System.out.println(" Value: " + temp.getValue());
                temp = temp.getNext();
            }
            System.out.print("Key: " + temp.getKey());
            System.out.println(" Value: " + temp.getValue());
        }
    }

}