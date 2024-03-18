// Instructions
// javac -d bin -cp ".:lib/algs4.jar" src/RandomWord.java
// java -cp ".:lib/algs4.jar" src/RandomWord.java
// java -cp ".:lib/algs4.jar" src/RandomWord.java < animals.txt
// To terminate sequence: Enter and Ctrl+D

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champ = "";
        int i = 1;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / (double) i)) {
                champ = word;
            }
            i++;
        }
        StdOut.println(champ);
    }
}

