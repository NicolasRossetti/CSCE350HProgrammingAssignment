// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class MaxHeapBottomUp {
    public static void maxHeap(int[] heap, int n, int i) {
        int largest = i;
        int left = 2 * i; // left child
        int right = 2 * i + 1; // right child
        if(left <= n && heap[left] > heap[largest]) {
            largest = left;
        }
        if(right <= n && heap[right] > heap[largest]) {
            largest = right;
        }
        if(largest != i) {
            // swap
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            // apply heap rules to sub tree
            maxHeap(heap, n, largest);
        }
    }
    
    public static void buildMaxHeap(int[] heap, int n) {
        for(int i = n / 2; i >= 1; i--) {
            maxHeap(heap, n, i);
        }
    }

    public static void main(String[] args) {
        int n = 0;
        int[] heap = null;
        // read input from input.txt
        try(Scanner reader = new Scanner(new File("input.txt"))){
            if(reader.hasNextInt()) {
                n = reader.nextInt();
            }
            heap = new int[n + 1]; // we don't use index 0
            for(int i = 1; i <= n; i++) {
                if(reader.hasNextInt()) {
                    heap[i] = reader.nextInt();
                }
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.err.println("No input file found.");
            return;
        }
        long start = System.nanoTime();
        buildMaxHeap(heap, n);
        long end = System.nanoTime();
        try(PrintWriter printer = new PrintWriter(new FileOutputStream("output.txt"))) {
            for(int i = 1; i <= n; i++) {
                printer.print(heap[i]);
                if(i < n) {
                    printer.print(" ");
                }
            }
            printer.close();
        } catch(IOException e) {
            System.err.println("Failed to write to output file.");
        }
        double totalTime = end - start;
        System.out.println("Total execution time: " + totalTime + " nanoseconds");
    }

}
