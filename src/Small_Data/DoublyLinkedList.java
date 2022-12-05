package Small_Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DoublyLinkedList {
    static int [] positions;
    int indexOfEntry;
    Node head = null;
    Node tail = null;

    static class Node{
        int key;
        String value;
        Node next;
        Node prev;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    public DoublyLinkedList(int size) {
        positions = new int[size];
        indexOfEntry = 0;
    }

    public static int SetEINThreshold(int Size) {
        if(Size > 0 && Size <= 100){
            return 100;
        }
        else if (Size > 100 && Size <= 500000) {
            return 500000;
        }
        else{
            return 0;
        }
    }

    public static int generate() {
        Random rnd = new Random();
        boolean dupeCheck = false;
        int newKey = rnd.nextInt(99999999);
        while(dupeCheck == false){
            for(int i = 0; i < positions.length; i++){
                if(positions[i] == newKey){
                    dupeCheck = true;
                }
            }
            break;
        }
        return newKey;
    }

    public static int [] allKeys(DoublyLinkedList list) {
        int max = 0;
        int [] sortedKeys = new int[list.positions.length];
        for(int i = 0; i < list.positions.length; i++) {
            if (list.positions[i] > max) {
                max = list.positions[i];
            }
        }

        for(int place = 1; max/place > 0; place *= 10){
            int[] output = new int[positions.length + 1];
            int[] count = new int[max + 1];
            for (int i = 0; i < max; ++i)
                count[i] = 0;

            // Calculate count of elements
            for (int i = 0; i < positions.length; i++)
                count[(positions[i] / place) % 10]++;

            // Calculate cumulative count
            for (int i = 1; i < 10; i++)
                count[i] += count[i - 1];

            // Place the elements in sorted order
            for (int i = positions.length - 1; i >= 0; i--) {
                output[count[(positions[i] / place) % 10] - 1] = positions[i];
                count[(positions[i] / place) % 10]--;
            }

            for (int i = 0; i < positions.length; i++) {
                sortedKeys[i] = output[i];
            }
        }
        return sortedKeys;
    }
    public static void add(DoublyLinkedList list, int key, String value) {
        Node entry = new Node(key, value);

        if(list.head == null) {
            list.head = list.tail = entry;
            list.head.prev = null;
            list.tail.next = null;
        }

        else {
            list.tail.next = entry;
            entry.prev = list.tail;
            list.tail = entry;
            list.tail.next = null;
        }
        list.positions[list.indexOfEntry] = key;
        list.indexOfEntry++;
    }
    public static void remove(DoublyLinkedList list, int key) {
        int pos = 0;
        for(int i = 0; i < positions.length; i++){
            if(positions[i] != key) {
                pos = i-1;
            }
        }

        Node current = list.head;
        for(int i = 1; i < pos; i++){
            current = current.next;
        }

        if(list.head.key == key){
            list.head.key = current.next.key;
        }

        if(current.next != null){
            current.next.prev = current.prev;
        }

        if(current.prev != null){
            current.prev.next = current.next;
        }
        current = null;

        int j = 0;
        int [] temp = new int[positions.length];
        for(int i = 0; i < positions.length; i++){
            if(positions[i] != key) {
                temp[j++] = positions[i];
            }
        }
        positions = temp;
        list.indexOfEntry = j;
    }

    public static String getValue(DoublyLinkedList list, int key) {
        Node current = list.head;
        Node entry = null;
        while(current != null) {
            if(current.key == key) {
                entry = current;
                break;
            }
            current = current.next;
        }
        return current.value;
    }

    public static int nextKey(DoublyLinkedList list, int key) {
        Node current = list.head;
        Node next = null;
        while(current != null) {
            if(current.key == key) {
                next = current.next;
                break;
            }
            current = current.next;
        }
        return next.key;
    }

    public static int prevKey(DoublyLinkedList list, int key) {
        Node current = list.head;
        Node prev = null;
        while(current != null) {
            if(current.key == key) {
                prev = current.prev;
                break;
            }
            current = current.next;
        }
        return prev.key;
    }

    public static int rangeKey(int key1, int key2) {
        int left = 0;
        int right= 0;
        boolean check = false;
        int rangeCounter = 0;
        for(int i = 0; i < positions.length; i++) {
            if(positions[i] == key1) {
                left = i;
                check = true;
            }

        }
        for(int i = left; i < positions.length; i++) {
            if(positions[i] == key2) {
                right = i;
                check = true;
            }
        }

        if(check == true) {
            return right - left - 1;
        }

        else {
            return 0;
        }
    }
    static void printList(DoublyLinkedList list)
    {
        Node temp = list.head;
        if (temp == null)
            System.out.print("Doubly Linked list empty");

        while (temp != null)
        {
            System.out.print(temp.key + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String [] args) {
        int size = 0;
        DoublyLinkedList list = null;

        try{
            File file = new File("src/5.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                size++;
                reader.nextLine();
            }
            reader.close();
            list = new DoublyLinkedList(size);

            reader = new Scanner(file);
            while(reader.hasNextLine()) {
                int key = Integer.parseInt(reader.nextLine());
                String value = "";
                add(list, key, value);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found");
        }

		/*System.out.println("Welcome! Please choose the option you want: \n"+
	                "1. allKeys(ElasticERL)\n" +
	                "2. add(ElasticERL,key,value)\n" +
	                "3. remove(ElasticERL,key)\n" +
	                "4. getValue(ElasticERL,key)\n" +
	                "5. nextKey(ElasticERL,key)\n" +
	                "6. prevKey(ElasticERL,key)\n" +
	                "7. rangeKey(key1, key2)\n" +
	                "8. Exit\n");
		Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
        	System.out.print(Arrays.toString(allKeys(list)));
        }
        else if(choice == 2) {
        	String value = in.nextLine();
        	add(list, generate(), value);
        }
        else if(choice == 3) {
        	int key = in.nextInt();
        	remove(list, key);
        }
        else if(choice == 4) {
        	int key = in.nextInt();
        	getValue(list, key);
        }
        else if(choice == 5) {
        	int key = in.nextInt();
        	nextKey(list, key);
        }
        else if(choice == 5) {
        	int key = in.nextInt();
        	prevKey(list, key);
        }
        else if(choice == 5) {
        	int key1 = in.nextInt();
        	int key2 = in.nextInt();
        	rangeKey(key1, key2);
        }*/

        //printNodes(list);
        //System.out.println(Arrays.toString(allKeys(list)));
        //System.out.print(Arrays.toString(allKeys(list)));
        System.out.println(Arrays.toString(list.positions));
        printList(list);
        remove(list, 70149901);
        printList(list);
        System.out.println(Arrays.toString(list.positions));
    }

}
