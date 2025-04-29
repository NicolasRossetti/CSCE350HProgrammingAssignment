// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class MinHeapBottomUp {
    public static void minHeap(int[] heap, int n, int i) {
        int smallest = i;
        int left = 2 * i;
        int right = 2 * i + 1;
        if(left <= n && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if(right <= n && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if(smallest != i) {
            // swap
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            minHeap(heap, n, smallest);
        }
    }
    
    public static void buildMinHeap(int[] heap, int n) {
        for(int i = n / 2; i >= 1; i--) {
            minHeap(heap, n, i);
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
        buildMinHeap(heap, n);
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
