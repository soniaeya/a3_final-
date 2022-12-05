package Small_Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DoublyLinkedList_Main {
    public static int intro_message(){

        System.out.println("\nWelcome! Please choose the option you want: \n"+
                "1. generate()\n" +
                "2. allKeys(ElasticERL)\n" +
                "3. add(ElasticERL,key,value)\n" +
                "4. remove(ElasticERL,key)\n" +
                "5. getValues(ElasticERL,key)\n" +
                "6. nextKey(ElasticERL,key)\n" +
                "7. prevKey(ElasticERL,key)\n" +
                "8. rangeKey(key1, key2)\n"+
                "9. Exit" +
                "\n10. Output keys\n");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        return choice;
    }

    public static void LinkedListDriver(int size) {
        Scanner in = new Scanner (System.in);
        DoublyLinkedList small = new DoublyLinkedList();

        try {
            File myObj = new File("src/5.txt");
            Scanner myReader = new Scanner(myObj);

            for (int i = 0; i < size; i++) {
                int key = Integer.parseInt(myReader.nextLine());
                String value = "None";
                small.addLast(key, value);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (true) {
            int choice = intro_message();
            if (choice == 1) {
                System.out.println("Here is a key that can be generated that is not in the List: " + small.generate());
            }
            else if (choice == 2) {
                System.out.println("Here is the sequence of the sorted keys: \n" + Arrays.toString(small.allKeys()));
            }
            else if (choice == 3) {
                System.out.println("Please enter the key you want to add: ");
                int key = in.nextInt();
                System.out.println("Please enter the value you want to add to "+ key +" : ");
                String value = in.next();
                small.addLast(key, value);
                System.out.println("Key: " + key + " with Value: " + value + " has been added to the list");
            }
            else if (choice == 4) {
                System.out.println("Please enter the key you want to remove: ");
                int key = in.nextInt();
                small.remove(key);
                System.out.println("Key: " + key + " has been removed from the list");
            }
            else if (choice == 5) {
                System.out.println("Please enter the key whose value you are looking for: ");
                int key = in.nextInt();
                System.out.print("Key: " + key + " holds the following value : " + small.getValue(key));
            }
            else if (choice == 6) {
                System.out.println("Please enter the key you want to look up ");
                int key = in.nextInt();
                System.out.print("The next following Key: " + key + " is: " + small.nextKey(key));
            }
            else if (choice == 7) {
                System.out.println("Please enter the key you want to look up ");
                int key = in.nextInt();
                System.out.print("The previous of Key: " + key + " is: " + small.prevKey(key));
            }
            else if (choice == 8) {
                System.out.println("Please enter the lower range (key1): ");
                int key1 = in.nextInt();
                System.out.println("Please enter the upper range (key2): ");
                int key2 = in.nextInt();
                int amount = small.rangeKey(key1, key2);
                System.out.println("There are " + amount + " keys in between key 1: " + key1 + " and key 2: " + key2);
            }
            else if (choice == 9) {
                break;
            }
            else if (choice == 10) {
                small.printList();
            }

        }
    }
}

