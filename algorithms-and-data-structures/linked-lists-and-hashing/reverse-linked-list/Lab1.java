import java.util.Scanner;

class Link {
    int data;
    Link next;

    Link(int data) {
        this.data = data;
        this.next = null;
    }
}


class LinkedList {
    private Link first;

    public LinkedList() {
        first = null;
    }

    public void insert(int value) {
        Link newLink = new Link(value);

        if (first == null) {
            first = newLink;
            return;
        }

        Link current = first;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newLink;
    }
    public void display() {
        Link current = first;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }
}

public class Lab1 {

    /*
    reverses the list in place by walking along it once and flipping each next
    pointer so it points at the link before it instead of the one after it.
    i keep three references, prev (the part already reversed), current (the link
    being flipped) and next (saved so the rest of the list isn't lost when
    current.next gets overwritten). when current runs off the end, prev is sitting
    on the old last link, which is the new first.
    eg. 1 -> 2 -> 3 becomes 1 <- 2 <- 3 and first is moved to 3
    this is O(n) time as every link is visited once, and O(1) space as no new
    links are created.
    */
    public static void reverse(LinkedList list) {
        Link prev = null;
        Link current = list.getFirst();

        while(current != null){
            Link next = current.next; // save the rest of the list before the pointer is changed
            current.next = prev;      // flip the pointer to face backwards
            prev = current;           // move both references one link along
            current = next;
        }

        list.setFirst(prev); // prev is the old last link, or null if the list was empty
    }


    public static void main (String args[]) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter integers (separated by spaces, ending with 'a'");
        // Input "10 20 30 a"

        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            list.insert(value);
        }
        scanner.close();

        list.display();

        reverse(list);

        list.display();
        // It should output 30, 20, 10

    }

}
