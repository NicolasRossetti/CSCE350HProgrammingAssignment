// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class QuickSort {
    public static void quickSort(double[] arr, int low, int high) {
        if(low<high) {
            int parti = partition(arr, low, high);
            quickSort(arr, low, parti - 1);
            quickSort(arr, parti + 1, high);
        }
    }
    public static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = low - 1; // index of the smaller element
        for(int j = low; j < high; j++) {
            if(arr[j] <= pivot) {
                i++;
                // swap arr[i] and arr[j]
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (pivot element)
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    public static void main(String[] args) {
        List<Double> numList = new ArrayList<>();

        // read input from input.txt
        try(Scanner reader = new Scanner(new File("input.txt"))){
            while(reader.hasNextDouble()) {
                numList.add(reader.nextDouble());
            }
        } catch(FileNotFoundException e) {
            System.err.println("No input file found.");
            return;
        }
        double[] nums = new double[numList.size()];
        for(int i = 0; i < numList.size(); i++) {
            nums[i] = numList.get(i);
        }
        if(nums.length > 0) {
            // start time before sorting starts
            long start = System.nanoTime();

            quickSort(nums, 0, nums.length - 1);

            long end = System.nanoTime();

            try(PrintWriter printer = new PrintWriter(new FileOutputStream("output.txt"))) {
                for(int i = 0; i < nums.length; i++) {
                    printer.print(nums[i] + " ");
                }
            } catch(IOException e) {
                System.err.println("Failed to write to output file.");
            }

            // finds total time
            double totalTime = end - start;
            System.out.println("Total execution time: " + totalTime + " nanoseconds");
        } else {
            System.out.println("No numbers in file.");
        }
    }
}
