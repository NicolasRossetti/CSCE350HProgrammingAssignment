// Written by Nicolas Rossetti
import java.io.*;
import java.util.*;

public class MaximumBipartiteMatching {
    public static boolean bipartiteMatching(int u, boolean[][] graph, boolean[] visited, int[] matchR) {
        int V = graph[0].length;
        for(int v = 0; v < V; v++) {
            if(graph[u][v] && !visited[v]) {
                visited[v] = true;
                if(matchR[v] == -1 || bipartiteMatching(matchR[v], graph, visited, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
    return false;
    }
    
    public static int[] maxBipartiteMatching(boolean[][] graph, int U, int V) {
        int[] matchR = new int[V];
        Arrays.fill(matchR, -1);
        for(int u = 0; u < U; u++) {
            boolean[] visited = new boolean[V];
            bipartiteMatching(u, graph, visited, matchR);
        }
        return matchR;
    }

    public static void main(String[] args) {
        List<String> uList = new ArrayList<>();
        List<String> vList = new ArrayList<>();
        List<boolean[]> mRows = new ArrayList<>();

        // read input from input.txt
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            // .split idea taken from stackoverflow, as used in previous problem
            uList = Arrays.asList(reader.readLine().split("\\s+"));
            vList = Arrays.asList(reader.readLine().split("\\s+"));

            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                boolean[] row = new boolean[parts.length];
                for(int i = 0; i < parts.length; i++) {
                    row[i] = parts[i].equals("1"); // 1 = true
                }
                mRows.add(row);
            }
        } catch(IOException e) {
            System.err.println("Couldn't read input file.");
        }
        int U = uList.size();
        int V = vList.size();
        boolean[][] graph = new boolean[U][V];
        for(int i = 0; i < U; i++) {
            graph[i] = mRows.get(i);
        }

        long start = System.nanoTime();
        int[] matchR = maxBipartiteMatching(graph, U, V);
        long end = System.nanoTime();

        int[][] mOutput = new int[U][V];
        for(int v = 0; v < V; v++) {
            if(matchR[v] != -1) {
                mOutput[matchR[v]][v] = 1;
            }
        }

        try(PrintWriter writer = new PrintWriter("output.txt")) {
            writer.println(String.join(" ", uList));
            writer.println(String.join(" ", vList));
            for(int i = 0; i < U; i++) {
                for(int j = 0; j < V; j++) {
                    writer.print(mOutput[i][j]);
                    if(j < V - 1) {
                        writer.print(" ");
                    }
                }
                writer.println();
            }
        } catch(IOException e) {
            System.err.println("Couldn't write to output file.");
        }
        double totalTime = end - start;
        System.out.println("Total execution time: " + totalTime + " nanoseconds");
    }
}
