package Big_Data;

import Big_Data.All_Keys.Ordered_List;

import java.util.Objects;

public class LinkedList {
    public static Node head;
    public static Node tail;
    public static Node od_head;
    public static Node od_tail;

    public static class Node {
        String value;

        String element;
        Node parent;
        Node left;
        Node right;
        Node prev;
        Node next;
        public Node od_prev;
        public Node od_next;

        int height = 1;
        int real_height;
        public Node(AVL_Entry new_entry) {
            this.element = new_entry.key;
            this.value = new_entry.value;

        }
        public String getElement() {
            return element;
        }
    }

    public static int compare(String s1, String s2, int counter){
        if(Objects.equals(s1, s2)){
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

    public static void addNode(AVL_Entry new_entry) {
        //Create new node
        Node newNode = new Node(new_entry);

        //If list is empty set head and tail to new node
        if (head == null) {
            head = tail = newNode;

        }
        else {
            insert_AVL(newNode, head);

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;

            //method recurse through the avl tree to insert
        }
    }

    /*public static void Create_AVL_Tree(String insert, int idx){
        addNode(insert);
        // Checks if it is the first element
    }*/
    public static void inOrder(LinkedList.Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        if(od_head == null){
            od_head = node;
            od_tail = node;
            od_tail.next = null;
            od_tail.prev = null;
        }
        else{
            od_tail.next = node;
            node.prev = od_tail;
            od_tail = node;
            od_tail.next = null;
        }
        System.out.println(node.element);
        inOrder(node.right);
    }



    public static void insert_AVL(Node newNode, Node root){
        //Setting prev and next of head and second element
        if(root == null){
            root = newNode;
            root.prev = null;
            root.next = null;
            tail = newNode;
            tail.prev = null;
            tail.next = null;
            newNode.height++;
        }

        if (compare(newNode.getElement(),root.getElement(),0) < 0){ //Compares the new node < the root values -> if the root's left node is empty, insert at that node
            if(root.left == null){
                newNode.height++;
                newNode.real_height= tail.height - newNode.height;

                root.left = newNode;
                tail.next = newNode;
                newNode.parent = root;
                newNode.prev = tail;
                tail = newNode;
                tail.next = null;
            }
            else{
                insert_AVL(newNode, root.left);
                newNode.height++;

            }
        }
        else if (root.getElement() == newNode.getElement()){
            //Impossible to have double elements, use to verify code
            //System.out.println("Double elements not allowed!");
        }
        else
        {
            if(root.right == null){
                newNode.height++;
                newNode.real_height= tail.height - newNode.height;
                root.right = newNode;
                newNode.parent = root;
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                tail.next = null;


                //sonia add inorder traversal
            }
            else{
                insert_AVL(newNode, root.right);
                newNode.height++;
            }
        }

    }

    public static void node_search_4_get_values(Node newNode, Node root){
        if(root == null){
            return;
        }
        //System.out.println("Root is" + root.element);

        if(root.element.equals(newNode.element)){
            System.out.println("The value is: "+ root.value);
            System.out.println();
        }
        else if (compare(newNode.getElement(),root.getElement(),0) < 0){ //new node < the root values
            node_search_4_get_values(newNode, root.left);
        }
        else
        {
            node_search_4_get_values(newNode, root.right);
        }
    }



    public static void node_search(Node newNode, Node root){
        if(root == null){
            return;
        }
        if(root.getElement().equals(newNode.getElement())){
            System.out.println("Removing");
            remove_AVL(root);
        }
        else if (compare(newNode.getElement(),root.getElement(),0) < 0){ //new node < the root values
            node_search(newNode, root.left);
        }
        else
        {
            node_search(newNode, root.right);
        }
    }

    public static void remove_AVL(Node remove){
        // Node is external
        if(remove.left == null && remove.right == null){

            remove.prev.next = remove.next;
            remove.next.prev = remove.prev;

            remove.od_prev.next = remove.od_next;

            remove.od_next.prev = remove.od_prev;

        }
        // Node is internal
        // Removing an internal node, Case 1: Left child node is empty, R not empty
        try{
            if(remove.left == null && remove.right != null){
                remove.right.parent = remove.parent;
                remove.parent.right = remove.right;
                remove.prev = remove.next;
                remove.od_prev = remove.od_next;
                remove = null;
            }
        }
        catch(Exception e){
        }

        // Removing an internal node, Case 2: Right child node is empty, L not empty
        try{
            if(remove.right == null && remove.left != null){
                remove.right.parent = remove.parent;
                remove.parent.left = remove.left;
                remove.prev = remove.next;
                remove.od_prev = remove.od_next;
                remove = null;
            }
        }
        catch(Exception e){
        }

        // Removing an internal node, Case 3: Both R and L not empty

        try{
            if(remove.left != null && remove.right != null){
                //Replace the node with the node that follows it in inorder traversal
                Node follows_remove = remove.od_next;//5

                // 1. Node L = Node Next L

                // Set the follow node's parent
                //if remove is a left child
                try{
                    if(remove.parent.left == remove){
                        remove.parent.left = follows_remove;
                        follows_remove.parent = remove.parent;
                    }
                }
                catch(Exception e){
                }

                //if remove is a right child
                try{
                    //if remove is a left child
                    if (remove.parent.right == remove){
                        remove.parent.right = follows_remove;
                        follows_remove.parent = remove.parent;
                    }
                }
                catch(Exception e){

                }

                //set L node
                follows_remove.left= remove.left;
                remove.left.parent = follows_remove;

                //set R node
                follows_remove.right = remove.right;
                remove.right.parent = follows_remove;


                //Remove the node that follows the node in a inorder traversal

                //check if follow_node is a left node
                if(follows_remove.parent.left == follows_remove){

                    follows_remove.parent.left = null;
                }
                //check if follow_node is a right node
                if(follows_remove.parent.right == follows_remove){
                    follows_remove.parent.right = null;
                }

            }

        }
        catch(Exception e){

        }

    }













    public static void set_height(){
        Node n = head;
        while(n!= null && n.next!=null){
            n.real_height = tail.height - n.height;
            n = n.next;
        }
    }

    public static void insert_height(){
        Node temp = tail;
        while(temp.parent!=null){
            temp.parent.real_height++;
            temp = temp.parent;
        }
    }


    public static void trinode_restructuring(Node n){
        // n is the added node
        /*
        System.out.println("Entered Trinode Restructuring ");
        System.out.println("N value = "+n.element);
        System.out.println("N parent = "+n.parent.element);
        System.out.println("Real height = "+n.real_height);

        System.out.println(n.parent!= null);


         */
        n = n.parent;

        boolean tri_res = false;
        //88888888
        boolean cont;

        while(n.parent!= null){
            //  |R height - L height| > 1
            // R is null, L > 1
            // L is null, R > 1

            Node z = null;
            Node y= null;
            Node x = null;
            //Determine if restructuring is needed



            try{
                if(n.right == null && n.left.real_height > 1){
                    if(Math.abs(n.right.real_height - n.left.real_height) > 1){
                        z = n;
                        System.out.println("Restructuring needed at: "+ n.element);
                        tri_res = true;

                    }
                }
            }
            catch(Exception e){

            }
            try{
                if(n.right == null && n.left.real_height > 1){
                    z = n;
                    System.out.println("Restructuring needed at: "+ n.element);
                    tri_res = true;
                }
            }
            catch(Exception e){

            }
            try{
                if(n.left == null && n.right.real_height > 1){
                    z = n;
                    System.out.println("Restructuring needed at: "+ n.element);
                    tri_res = true;
                }
            }
            catch(Exception e){

            }
            if(tri_res){

                //Determine which child is y
                // In terms of height:
                // R > L then R
                // L > R then L
                // R is null then L
                // L is null then R

                // R > L then R
                try{
                    if(n.right.real_height > n.left.real_height){
                        y = n.right;
                    }
                }
                catch(Exception e){
                }

                // L > R then L
                try{
                    if(n.right.real_height < n.left.real_height){
                        y = n.left;
                    }
                }
                catch(Exception e){
                }

                // R is null then L
                try{
                    if(n.right==null){
                        y = n.left;
                    }
                }
                catch(Exception e){

                }
                // L is null then R
                try{
                    if(n.left==null){
                        y = n.right;
                    }
                }
                catch(Exception e){
                }


                //Determine which child is X
                try{
                    if(y.right.real_height > y.left.real_height){
                        x = y.right;
                    }
                }
                catch(Exception e){
                }

                // L > R then L
                try{
                    if(y.right.real_height < y.left.real_height){
                        x = y.left;
                    }
                }
                catch(Exception e){
                }

                // R is null then L
                try{
                    if(y.right==null){
                        x = y.left;
                    }
                }
                catch(Exception e){

                }
                // L is null then R
                try{
                    if(y.left==null){
                        x = y.right;
                    }
                }
                catch(Exception e){
                }

                //Restructuring
                //1
                try{
                    if(y.right == x && z.right == y){
                        //1
                        // X is R of Y and Y is R of Z
                        y.parent = z.parent;
                        try{
                            y.left.parent = z;
                        }
                        catch(Exception e){
                        }
                        try{
                            z.right = y.left;
                        }
                        catch(Exception e){
                        }
                        try{
                            y.left = z;
                        }
                        catch(Exception e){
                        }
                        try{
                            if(z.parent.left == z){
                                z.parent.left = y;
                            }
                            else{
                                z.parent.right = y;
                            }
                        }
                        catch(Exception e){
                        }
                    }

                }
                catch(Exception e){

                }






                //2
                try{
                    if(y.left == x && z.left == y){
                        //2

                        // X is L of Y and Y is L of Z
                        y.parent = z.parent;

                        try{
                            y.right.parent = z;
                        }
                        catch(Exception e){

                        }
                        try{
                            z.left = y.right;
                        }
                        catch(Exception e){

                        }
                        try{
                            y.right = z;
                        }
                        catch(Exception e){

                        }

                        try{
                            if(z.parent.left == z){
                                z.parent.left = y;
                            }
                            else{
                                z.parent.right = y;
                            }
                        }
                        catch(Exception e){
                        }

                    }

                }
                catch(Exception e){

                }



                //3
                try{
                    if(y.left == x && z.right == y){
                        y.left = x.right;
                        x.right = y;
                        x.parent = z.parent;
                        x.left = z;
                        z.parent = x;
                        z.right = x.left;
                        try{
                            if(z.parent.left == z){
                                z.parent.left = x;
                            }
                            else{
                                z.parent.right = x;

                            }

                        }
                        catch(Exception e){

                        }
                    }

                }
                catch(Exception e){

                }
                // X is L of Y and Y is R of Z

                //4
                try{
                    if(y.right == x && z.left == y){

                        y.right = x.left;
                        z.left = x.right;

                        x.parent = z.parent;
                        x.left = y;
                        x.right = z;
                        z.parent = x;


                        try{
                            if(z.parent.left == z){
                                z.parent.left = x;
                            }
                            else{
                                z.parent.right = x;

                            }

                        }
                        catch(Exception e){

                        }


                    }

                }
                catch(Exception e){

                }
                // X is R of Y and Y is L of Z




                /*
                System.out.println("Z is: "+ z.element);
                System.out.println("Y is: "+ y.element);
                System.out.println("X is: "+ x.element);
                 */










            }








            n = n.parent;




        }


    }


}