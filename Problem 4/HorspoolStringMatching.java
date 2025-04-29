// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class HorspoolStringMatching {
    public static int horspoolSearch(String text, String pattern) {
        int pLength = pattern.length();
        int tLength = text.length();
        if(pLength > tLength) {
            return -1; // pattern is longer than text left, so there is no match
        }
        // building shift table
        int[] shiftTable = new int[256]; // ASCII size is 8 bits
        Arrays.fill(shiftTable, pLength); // the default shift length is the pattern length
        for(int j = 0; j < pLength - 1; j++) {
            shiftTable[pattern.charAt(j)] = pLength - 1 - j;
        }
        int i = pLength - 1;
        while(i < tLength) {
            int k = 0;
            while(k < pLength && pattern.charAt(pLength - 1 - k) == text.charAt(i - k)) {
                k++;
            }
            if(k == pLength) {
                return i - pLength + 1; // match found
            } else {
                i += shiftTable[text.charAt(i)];
            }
        }
        return -1; // no match
    }

    public static void main(String[] args) {
        String pattern = "";
        String text = "";

        // read input from input.txt
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))){
            pattern = reader.readLine();
            text = reader.readLine();
            reader.close();
        } catch(IOException e) {
            System.err.println("Couldn't read input file.");
            return;
        }

        if(pattern == null || text == null) {
            System.err.println("Input file format is incorrect.");
            return;
        }

        long start = System.nanoTime();
        int index = horspoolSearch(text, pattern);
        long end = System.nanoTime();

        // write output to output.txt
        try(PrintWriter writer = new PrintWriter(new File("output.txt"))) {
            writer.println(index);
        } catch(IOException e) {
            System.err.println("Couldn't write to output file.");
        }
        double totalTime = end - start;
        System.out.println("Total execution time: " + totalTime + " nanoseconds");
    }
}
