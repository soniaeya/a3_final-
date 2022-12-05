package Big_Data.Generate;

import Big_Data.AVL_Entry;
import Big_Data.All_Keys.Ordered_List;
import Big_Data.LinkedList;

public class Generate {
    public static String generate(){
        int min = 0;
        int max = 9;
        String first_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String sec_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String third_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String fourth_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String fifth_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String sixth_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String seventh_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));
        String eight_digit = Integer.toString((int)Math.floor(Math.random()*(max-min+1)+min));

        String random = first_digit+sec_digit+third_digit+fourth_digit+fifth_digit+sixth_digit+seventh_digit+eight_digit;

        return random;
    }

    //insertion sort with the ordered list
    public static AVL_Entry insertion_sort(String generated_string){
        Ordered_List.Node temp = Ordered_List.head;
        while(temp!=null&&temp.next!=null){
            if(Ordered_List.compare(generated_string, temp.element, 0) > 0){
                if(Ordered_List.compare(generated_string, temp.next.element, 0) < 0){

                    AVL_Entry new_entry = new AVL_Entry(generated_string, "");
                    Ordered_List.Node new_node = new Ordered_List.Node(new_entry);
                    temp.next = new_node;
                    new_node.prev = temp;
                    return new_entry;
                }
        }
            temp = temp.next;

        }
        AVL_Entry new_entry = new AVL_Entry(generated_string, "");
        Ordered_List.Node new_node = new Ordered_List.Node(new_entry);
        temp = Ordered_List.tail;
        new_node.prev = temp;
        Ordered_List.tail = new_node;
        return new_entry;
    }
}
