import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;

        Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateItem(item);
        Node oldFirst = first;
        first = new Node(item, null, oldFirst);
        if (oldFirst == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateItem(item);
        Node oldLast = last;
        last = new Node(item, oldLast, null);
        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateEmpty();
        Item oldItem = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return oldItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        validateEmpty();
        Item oldItem = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return oldItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("Hello");
        deque.addLast("World");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            StdOut.println(item);
        }
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
    }

    private void validateItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item is empty.");
    }

    private void validateEmpty() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deques is empty.");
        }
    }
}
