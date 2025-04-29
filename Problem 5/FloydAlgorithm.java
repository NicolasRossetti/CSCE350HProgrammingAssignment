// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class FloydAlgorithm {
    public static void floydAlgo(double[][] D, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(D[j][i] + D[i][k] < D[j][k]) {
                        D[j][k] = D[j][i] + D[i][k];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        List<double[]> matrix = new ArrayList<>();

        // read input from input.txt
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // trim() to get rid of whitespace
                // regex \\s+ part was found on stackoverflow, for leading and trailing whitespace
                String[] parts = line.trim().split("\\s+");
                double[] row = new double[parts.length];
                for(int i = 0; i < parts.length; i++) {
                    row[i] = Double.parseDouble(parts[i]);
                }
                matrix.add(row);
            }
        } catch(IOException e) {
            System.err.println("Couldn't read input file.");
            return;
        }

        int n = matrix.size();
        double[][] D = new double[n][n];
        for (int i = 0; i < n; i++) {
            D[i] = matrix.get(i);
        }

        long start = System.nanoTime();
        floydAlgo(D, n);
        long end = System.nanoTime();

        // write output to output.txt
        try(PrintWriter printer = new PrintWriter(new File("output.txt"))) {
            for(int i = 0;i < n; i++) {
                for(int j = 0; j < n; j++) {
                    printer.print(D[i][j]);
                    if(j < n - 1) {
                        printer.print(" ");
                    }
                }
                printer.println();
            }
        } catch(IOException e) {
            System.err.println("Couldn't write to output file.");
        }
        double totalTime = end - start;
        System.out.println("Total execution time: " + totalTime + " nanoseconds");
    }
}
