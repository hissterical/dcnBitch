import java.util.Scanner;

public class Ford {
    private int[] D;
    private int numVertices;
    public static final int MAX_VALUE = 999;

    public Ford(int numVertices) {
        this.numVertices = numVertices;
        D = new int[numVertices + 1];
    }

    public void bellmanFordEvaluation(int source, int[][] graph) {
        // Step 1: Initialize distances from source
        for (int i = 1; i <= numVertices; i++) {
            D[i] = MAX_VALUE;
        }
        D[source] = 0;

        // Step 2: Relax all edges |V| - 1 times
        for (int i = 1; i <= numVertices - 1; i++) {
            for (int u = 1; u <= numVertices; u++) {
                for (int v = 1; v <= numVertices; v++) {
                    if (graph[u][v] != MAX_VALUE && D[u] != MAX_VALUE) {
                        if (D[v] > D[u] + graph[u][v]) {
                            D[v] = D[u] + graph[u][v];
                        }
                    }
                }
            }
        }

        // Step 3: Check for negative-weight cycles
        for (int u = 1; u <= numVertices; u++) {
            for (int v = 1; v <= numVertices; v++) {
                if (graph[u][v] != MAX_VALUE && D[u] != MAX_VALUE) {
                    if (D[v] > D[u] + graph[u][v]) {
                        System.out.println("The graph contains a negative weight cycle.");
                        return;
                    }
                }
            }
        }

        // Print distances
        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 1; i <= numVertices; i++) {
            System.out.println("To vertex " + i + " = " + (D[i] == MAX_VALUE ? "INF" : D[i]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int numVertices = scanner.nextInt();

        int[][] graph = new int[numVertices + 1][numVertices + 1];
        System.out.println("Enter the adjacency matrix (use 0 for no edge):");

        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                graph[i][j] = scanner.nextInt();
                if (i == j) {
                    graph[i][j] = 0;
                } else if (graph[i][j] == 0) {
                    graph[i][j] = MAX_VALUE;
                }
            }
        }

        System.out.println("Enter the source vertex:");
        int source = scanner.nextInt();

        Ford bellmanFord = new Ford(numVertices);
        bellmanFord.bellmanFordEvaluation(source, graph);

        scanner.close();
    }
}
