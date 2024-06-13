import java.util.Scanner;

public class LSR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Prompt user to enter the number of nodes
        System.out.print("Enter the number of nodes: ");
        int nodes = sc.nextInt();    
        
        // Initialize arrays for predecessor, distance, adjacency matrix, and visited nodes
        int[] preD = new int[nodes];  
        int[] distance = new int[nodes];  
        int[][] matrix = new int[nodes][nodes];  
        int[] visited = new int[nodes];  
        
        // Prompt user to enter the cost matrix
        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < nodes; i++) {
            visited[i] = 0;  // Initialize visited nodes as 0 (false)
            preD[i] = 0;  // Initialize predecessor nodes
            for (int j = 0; j < nodes; j++) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 999;  // Use 999 to indicate no direct path
                }
            }
        }
        
        // Initialize the distance array with the distances from the source node (node 0)
        distance = matrix[0];  
        visited[0] = 1;  // Mark the source node as visited
        distance[0] = 0;  // The distance from the source node to itself is 0
        
        // Implement Dijkstra's algorithm
        for (int counter = 0; counter < nodes - 1; counter++) {  
            int min = 999;
            int nextNode = 0;
            
            // Find the unvisited node with the smallest distance
            for (int i = 0; i < nodes; i++) {
                if (min > distance[i] && visited[i] != 1) {
                    min = distance[i];
                    nextNode = i;
                }
            }
            
            // Mark the next node as visited
            visited[nextNode] = 1;
            
            // Update the distances array
            for (int i = 0; i < nodes; i++) {
                if (visited[i] != 1) {
                    if (min + matrix[nextNode][i] < distance[i]) {
                        distance[i] = min + matrix[nextNode][i];
                        preD[i] = nextNode;
                    }
                }
            }
        }
        
        // Print the shortest paths and their costs
        for (int i = 1; i < nodes; i++) {
            System.out.print("Path = " + i);
            int j = i;
            do {
                j = preD[j];
                System.out.print(" <- " + j);
            } while (j != 0);
            System.out.println();
            System.out.println("Cost = " + distance[i]);
            System.out.println();
        }
        
        // Close the scanner to free up resources
        sc.close();
    }
}
