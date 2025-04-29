// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class MergeSort {
    
    public static void mergeSort(double[] arr, int left, int right) {
        if(left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(arr, left, middle); // from start to middle
            mergeSort(arr, middle + 1, right); // from middle to end
            merge(arr, left, middle, right);
        }
    }
    
    public static void merge(double[] arr, int left, int middle, int right) {
        int size1 = middle - left + 1;
        int size2 = right - middle;
        double[] leftArr = new double[size1];
        double[] rightArr = new double[size2];
        for(int i = 0; i < size1; i++) {
            leftArr[i] = arr[left + i];
        }
        for(int i = 0; i < size2; i++) {
            rightArr[i] = arr[middle + 1 + i];
        }
        // merges and sorts the arrays
        int i = 0;
        int j = 0;
        int k = left;
        while(i < size1 && j < size2) {
            if(leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        // make sure all elements are copied
        while(i < size1) {
            arr[k++] = leftArr[i++];
        }
        while(j < size2) {
            arr[k++] = rightArr[j++];
        }
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

            mergeSort(nums, 0, nums.length - 1);

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
