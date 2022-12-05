package Big_Data.All_Keys;
import Big_Data.*;
public class Ordered_List{
    public static Node head;
    public static Node tail;

    public static class Node {
        public String element;
        public String value = "";
        public Node next;
        public Node prev;

        public Node(AVL_Entry new_entry) {
            this.element = new_entry.key;
            this.value = new_entry.value;

        }

        public String getElement() {

            return element;
        }
    }

    public String toString(){

        //while next is not empty
        Node t = head;
        while(tail!=null){

            System.out.println(t.element);
            t = t.next;
        }
        return null;
    }
    public static int compare(String s1, String s2, int counter){
        if(s1==s2){
            return 0;
        }
        while (counter<8){

            if(s1.charAt(counter)< s2.charAt(counter)){
                //Returns -1 if the first object is smaller than the second object
                return -1;
            }
            else if(s1.charAt(counter) > s2.charAt(counter)){
                return 1;
            }
            else if(s1.charAt(counter) == s2.charAt(counter)){
                ++counter;
                compare(s1, s2, counter);
            }
        }
        return 100;
    }

}
