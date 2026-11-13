class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class LinkedListJava{
    Node head;

    public static void main(String[] args){
        LinkedListJava list = new LinkedListJava();

        // the basic operations
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.insertAtBeginning(10);
        list.insertAtEnd(40);
        System.out.print("List: ");
        list.printList();

        list.delete(30);
        System.out.print("After deleting 30: ");
        list.printList();
        System.out.println("Search 40: " + list.search(40));
        System.out.println("Search 30: " + list.search(30));

        // task 1
        System.out.println();
        System.out.println("get(head, 1): " + get(list.head, 1));
        System.out.println("get(head, 3): " + get(list.head, 3));
        System.out.println("get(head, 4): " + get(list.head, 4));

        // task 2
        insert_sorted(list.head, 25); // in the middle
        insert_sorted(list.head, 50); // at the end
        insert_sorted(list.head, 5);  // before the head
        insert_sorted(list.head, 20); // a value that is already there
        System.out.print("After insert_sorted 25, 50, 5, 20: ");
        list.printList();

        // task 3
        Node rev = reverse(list.head);
        System.out.print("Reversed copy: ");
        printList(rev);
        System.out.print("Original after reverse: ");
        list.printList();

        // task 4
        LinkedListJava unsorted = new LinkedListJava();
        int[] values = {7, 3, 9, 1, 3, 8};
        for(int i = 0; i < values.length; i++) unsorted.insertAtEnd(values[i]);
        System.out.println();
        System.out.print("Unsorted: ");
        unsorted.printList();
        System.out.print("sort_list: ");
        printList(sort_list(unsorted.head));

        // task 5
        LinkedListJava a = new LinkedListJava();
        LinkedListJava b = new LinkedListJava();
        int[] first = {1, 4, 6, 10};
        int[] second = {2, 3, 6, 12, 15};
        for(int i = 0; i < first.length; i++) a.insertAtEnd(first[i]);
        for(int i = 0; i < second.length; i++) b.insertAtEnd(second[i]);
        System.out.println();
        System.out.print("List 1: ");
        a.printList();
        System.out.print("List 2: ");
        b.printList();
        System.out.print("merge_lists: ");
        printList(merge_lists(a.head, b.head));
        System.out.print("merge_lists with the unsorted list: ");
        printList(merge_lists(unsorted.head, a.head));
        System.out.print("merge_lists with an empty list: ");
        printList(merge_lists(null, b.head));
    }

    // adds a new node in front of the current head
    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // walks to the last node and adds the new node after it
    public void insertAtEnd(int data){
        Node newNode = new Node(data);

        if(head == null){ // empty list, the new node is the head
            head = newNode;
            return;
        }

        Node current = head;
        while(current.next != null) current = current.next;
        current.next = newNode;
    }

    // removes the first node holding key, does nothing if key isn't in the list
    public void delete(int key){
        if(head == null) return;

        if(head.data == key){ // deleting the head just moves head along
            head = head.next;
            return;
        }

        Node current = head;
        while(current.next != null && current.next.data != key) current = current.next;

        if(current.next != null) current.next = current.next.next; // skip over the node
    }

    // true if key is somewhere in the list
    public boolean search(int key){
        Node current = head;

        while(current != null){
            if(current.data == key) return true;
            current = current.next;
        }

        return false;
    }

    public void printList(){
        printList(head);
    }

    // prints a list given its head, used for the lists the tasks return
    public static void printList(Node head){
        Node current = head;

        while(current != null){
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /*
     * get
     * returns the element at a given position in the list.
     * Arguments:
     *   head  - the first node of the list
     *   index - the position wanted, the first element is at index 1
     * Returns:
     *   the value stored at that position, or -1 if index is less than 1 or past
     *   the end of the list
     */
    public static int get(Node head, int index){
        if(index < 1) return -1;

        Node current = head;
        int pos = 1;

        while(current != null){
            if(pos == index) return current.data;
            current = current.next;
            pos++;
        }

        return -1; // ran off the end, the list is shorter than index
    }

    /*
     * insert_sorted
     * inserts a value into a list that is sorted in ascending order, keeping it
     * sorted.
     * the method is void so it can't hand back a new head. when the value is
     * smaller than the head, the new node goes in second and takes the head's old
     * value, and the head node takes the new value. the head node stays the same
     * object so the caller's reference is still right.
     * eg. inserting 5 into 10 -> 20 gives 10 -> 10 -> 20 then 5 -> 10 -> 20
     * Arguments:
     *   head         - the first node of a sorted list, must not be null
     *   value_to_add - the value to insert
     */
    public static void insert_sorted(Node head, int value_to_add){
        if(head == null) return; // an empty list can't be changed through a null reference

        if(value_to_add < head.data){
            Node newNode = new Node(head.data); // copy of the old head, placed second
            newNode.next = head.next;
            head.next = newNode;
            head.data = value_to_add;
            return;
        }

        // stop at the last node that is not bigger than the new value
        Node current = head;
        while(current.next != null && current.next.data <= value_to_add) current = current.next;

        Node newNode = new Node(value_to_add);
        newNode.next = current.next;
        current.next = newNode;
    }

    /*
     * reverse
     * builds a new list holding the same values as the input list in reverse
     * order. each value is copied into a new node placed at the front of the new
     * list, so the first value ends up last. the input list is not changed.
     * eg. 1 -> 2 -> 3 : new list goes 1, then 2 -> 1, then 3 -> 2 -> 1
     * Arguments:
     *   head - the first node of the list to reverse
     * Returns:
     *   the head of the new reversed list, or null if the input list is empty
     */
    public static Node reverse(Node head){
        Node newHead = null;
        Node current = head;

        while(current != null){
            Node newNode = new Node(current.data);
            newNode.next = newHead; // goes in front of everything copied so far
            newHead = newNode;
            current = current.next;
        }

        return newHead;
    }

    /*
     * sort_list
     * sorts a list by building a new one. the new list starts with the first
     * value, then every other value is put in its place with insert_sorted. the
     * input list is not changed.
     * this is O(n^2), each of the n inserts can walk the whole new list.
     * Arguments:
     *   head - the first node of the list to sort
     * Returns:
     *   the head of the new sorted list, or null if the input list is empty
     */
    public static Node sort_list(Node head){
        if(head == null) return null;

        Node newHead = new Node(head.data); // insert_sorted needs a list with at least one node
        Node current = head.next;

        while(current != null){
            insert_sorted(newHead, current.data);
            current = current.next;
        }

        return newHead;
    }

    /*
     * merge_lists
     * merges two lists into a new list sorted in ascending order. the input
     * lists don't have to be sorted, as every value from both is put in its
     * place with insert_sorted. this gives the right answer either way. neither input list is changed.
     * Arguments:
     *   head1 - the first node of the first list
     *   head2 - the first node of the second list
     * Returns:
     *   the head of the new merged list, or null if both lists are empty
     */
    public static Node merge_lists(Node head1, Node head2){
        Node newHead = sort_list(head1); // sorted copy of the first list

        Node current = head2;
        if(newHead == null && current != null){ // first list was empty, start from the second
            newHead = new Node(current.data);
            current = current.next;
        }

        while(current != null){
            insert_sorted(newHead, current.data);
            current = current.next;
        }

        return newHead;
    }
}
