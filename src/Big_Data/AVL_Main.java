package Big_Data;

import Big_Data.All_Keys.Ordered_List;
import Big_Data.Generate.Generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import static Big_Data.Big_Data_Methods.add;
import static Big_Data.Big_Data_Methods.allKeys;
//import static Big_Data.LinkedList.insert_AVL;

public class AVL_Main {
    public static int intro_message(){

        System.out.println("Welcome! Please choose the option you want: \n"+
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


    public static void AVL_Main(int size) {
        Scanner in = new Scanner (System.in);

        LinkedList big = new LinkedList();

        try {
            //File myObj = new File("src/million.txt");
            //File myObj = new File("src/500k.txt");
            File myObj = new File("src/5.txt");

            Scanner myReader = new Scanner(myObj);

            for (int i = 0; i < size; i++) {
                String key = myReader.nextLine();
                String value = "None";
                AVL_Entry new_entry = new AVL_Entry(key, value);
                big.addNode(new_entry);
                big.set_height();
            }
            myReader.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            //insert elements
        }
        while (true) {
            int choice = intro_message();

            if (choice == 1) {
                //generate();

                if(Generate.insertion_sort(Generate.generate())!=null){
                    LinkedList.Node n = new LinkedList.Node(Generate.insertion_sort(Generate.generate()));
                    //add node to avl tree
                    big.insert_AVL(n, big.head);


                    //O(n)
                    big.set_height();
                    big.insert_height();

                    //O(log(n))
                    big.trinode_restructuring(n);
                }
                else{
                    System.out.println("Null thingz");
                }

            }

            else if (choice == 2) {

                    big.inOrder(big.head);
                    System.out.println();


            }
            else if (choice == 3) {
                System.out.println("Please enter the key you want to add: ");
                String key = in.next();
                System.out.println("Please enter the value you want to add to "+key+" : ");
                String value = in.next();
                AVL_Entry new_entry = new AVL_Entry(key, value);
                LinkedList.Node n = new LinkedList.Node(new_entry);
                //O(log(n))
                //big.addNode(new_entry);
                big.insert_AVL(n, big.head);
                //O(n)
                big.set_height();
                big.insert_height();
                //O(log(n))
                big.trinode_restructuring(n);
                //Test the values
            }
            else if (choice == 4) {
                System.out.println("Please enter the key you want to remove: ");
                String key = in.next();
                AVL_Entry entry = new AVL_Entry(key, "");
                LinkedList.Node temp = new LinkedList.Node(entry);
                big.node_search(temp, big.head);
            }

            else if (choice == 5) {
                System.out.println("Please enter the key whose value you are looking for: ");
                String key = in.next();
                Big_Data_Methods.getValues(big,key);

            } else if (choice == 6) {//nextKey(ElasticERL,key)

                System.out.println("Please enter the key you want to look up: ");
                String s = in.next();
                //String s = "05716101";
                Big_Data_Methods.nextKey(big, s);

            }
            else if (choice == 7) {
                //prevKey(ElasticERL,key)

                System.out.println("Please enter the key you want to look up: ");

                String s = in.next();
                //String s = "05716101";
                Big_Data_Methods.prevKey(big, s);
            }
            else if (choice == 8) {
                System.out.println("Please enter the lower range (key1): ");
                String key1 = in.next();

                System.out.println("Please enter the upper range (key2): ");
                String key2 = in.next();

                Big_Data_Methods.rangeKey(key1, key2, big);
            }
            else if (choice == 10) {

                LinkedList.Node t = big.head;

                while(t!= null){
                    System.out.println("Element: "+ t.element);
                    try{
                        System.out.println("Parent: "+ t.parent.element);
                    }
                    catch(Exception e){
                        System.out.println("No parent");
                    }
                    try{
                        System.out.println("Left: "+ t.left.element);
                    }
                    catch(Exception e){
                        System.out.println("No left");
                    }
                    try{
                        System.out.println("Right: "+ t.right.element);
                    }
                    catch(Exception e){
                        System.out.println("No right");

                    }
                    try{
                        System.out.println("Next: "+ t.next.element);
                    }
                    catch(Exception e){
                        System.out.println("Next: Next is null");

                    }

                    System.out.println();
                    t = t.next;

                }


            }
        }



        }

            /*Test that the prev and next nodes work

            System.out.println("Head: "+ LinkedList.head.getElement());
            System.out.println("Head next2 prev: "+ (LinkedList.head.next).prev.getElement());
            System.out.println("Head next2: "+ LinkedList.head.next.getElement());
            System.out.println("Head next3: "+ LinkedList.head.next.next.getElement());
            System.out.println("Head next4: "+ LinkedList.head.next.next.next.getElement());
            System.out.println("Head next5: "+ LinkedList.head.next.next.next.next.getElement());

            System.out.println();

            System.out.println("Tail: "+ LinkedList.tail.getElement());
            System.out.println("Tail Prev: "+ LinkedList.tail.prev.getElement());
            System.out.println("Tail Prev2: "+ LinkedList.tail.prev.prev.getElement());
            System.out.println("Tail Prev3: "+ LinkedList.tail.prev.prev.prev.getElement());
            System.out.println("Tail Prev4: "+ LinkedList.tail.prev.prev.prev.prev.getElement());

            System.out.println();
*/

    }







