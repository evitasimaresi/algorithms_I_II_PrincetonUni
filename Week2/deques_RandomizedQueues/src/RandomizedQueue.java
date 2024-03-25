import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int[] indexes;
    private int size;
    private int rIndex;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        indexes = new int[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size] = item;
        indexes[size] = size;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        validateEmpty();
        rIndex = StdRandom.uniformInt(size);
        Item rItem = items[rIndex];
        swap(rIndex, size - 1);
        size--;
        if (size > 0 && items.length == size / 4) {
            resize(size / 2);
        }
        return rItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateEmpty();
        rIndex = StdRandom.uniformInt(size);
        return items[rIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int currentIndex = 0;
        private int[] randomIndexes;

        public RandomIterator() {
            randomIndexes = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = i;
            }
            StdRandom.shuffle(randomIndexes);
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return items[randomIndexes[currentIndex++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randmQueue = new RandomizedQueue<>();
        randmQueue.enqueue("Hello");
        randmQueue.enqueue("World");
        randmQueue.enqueue("!");
        Iterator<String> randIterator = randmQueue.iterator();
        while (randIterator.hasNext()) {
            StdOut.println(randIterator.next());
        }
        StdOut.println(randmQueue.dequeue());
        StdOut.println(randmQueue.dequeue());
    }

    private void resize(int newLength) {
        Item[] newItem = (Item[]) new Object[newLength];
        int[] newIndexes = new int[newLength];
        for (int i = 0; i < size; i++) {
            newItem[i] = items[indexes[i]];
            newIndexes[i] = i;
        }
        items = newItem;
        indexes = newIndexes;
    }

    private void validateEmpty() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
    }

    private void swap(int a, int b) {
        Item tempA = items[a];
        int tempAIndex = indexes[a];

        items[a] = items[b];
        items[b] = tempA;

        indexes[a] = indexes[b];
        indexes[b] = tempAIndex;
    }
}
