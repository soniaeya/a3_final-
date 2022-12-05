package Big_Data;
import Big_Data.All_Keys.Ordered_List;
import java.util.Scanner;
import static Big_Data.All_Keys.Ordered_List.head;

public class Big_Data_Methods {
    public static int generate() {
        // O(n)
        // Generate a random 8-digit number
        // Quick sort to see if it already exists in the list

        return 0;
    }

    public static void allKeys(LinkedList ElasticERL) {
        //return all keys in ElasticERL as a sorted sequence
        LinkedList.Node t = LinkedList.head;
        int counter = 0;
        while(t !=null){
            System.out.print(counter + ": ");
            System.out.println(t.element);
            t = t.next;
            counter++;
        }


    }
    //Methods for adding nodes (Trinode restructuring)



    public static void getValues(LinkedList ElasticERL, String key) {
        AVL_Entry entry = new AVL_Entry(key, "");
        LinkedList.Node temp = new LinkedList.Node(entry);

        // return the values of the given key
        ElasticERL.node_search_4_get_values(temp, ElasticERL.head);


    }

    public static void nextKey(LinkedList ElasticERL, String key) {
        boolean exists = false;
        LinkedList.Node t = ElasticERL.head;
        while(t!=null && exists == false){
            if(t.element.equals(key)){
                try{
                    System.out.println("The key after "+key+" is: "+t.next.element);

                }
                catch(Exception e){
                    System.out.println("There is no key after "+key);
                }
                exists = true;
            }
            t = t.next;
        }
        if (exists == false){
            if(key == ElasticERL.tail.element){
                System.out.println("The here is no key after "+key);
                System.out.println();

            }
            else{
                System.out.println("The key you have inserted is not in the database!");
                System.out.println();
            }
        }

    }

    public static void prevKey(LinkedList ElasticERL, String key) {
        //  return the key for the predecessor of key;
        boolean exists = false;
        LinkedList.Node temp = ElasticERL.head;

        while(temp.next!=null && temp.element!=null){
            if(temp.element.equals(key)){
                System.out.println("The key before "+key+" is: "+temp.prev.element);
                System.out.println();
                exists = true;
                temp = ElasticERL.tail.prev;
            }
            temp = temp.next;
        }
        if (exists == false){
            if(temp == ElasticERL.head){
                System.out.println("The here is no key before "+temp.element);
            }
            else{
                System.out.println("The key you have inserted is not in the database!");
            }
        }


    }

    public static void rangeKey(String key1, String key2, LinkedList big) {
        // returns the number of keys that are within the specified range of
        //the two keys key1 and key2
        //key1 = "51281754";
        //key2 = "70162601";

        LinkedList.Node t = big.head;

        while(t!=null){
            if(t.element.equals(key1)){
                while(t!=null&&!t.prev.element.equals(key2)){
                    System.out.println(t.element);
                    t = t.next;
                }
                t = big.tail;
                System.out.println();
            }

            t = t.next;

        }


    }
}
