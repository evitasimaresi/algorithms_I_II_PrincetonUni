import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randmQueue = new RandomizedQueue<>();
        int count = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randmQueue.enqueue(item);
        }
        while (count > 0) {
            StdOut.println(randmQueue.dequeue());
            count--;
        }
    }
}