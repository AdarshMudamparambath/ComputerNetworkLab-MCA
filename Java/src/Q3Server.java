// Server code
import java.io.*;
import java.net.*;

public class Q3Server {
    public static void main(String[] args) {
        int serverPort = 9876;

        // Start the server and listen for connections
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Server started. Waiting for client...");

            // Accept client connection
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Communication loop
                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Message received from client: " + clientMessage);

                    // Input message from server
                    System.out.print("Enter message for client: ");
                    BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                    String serverMessage = serverInput.readLine();

                    // Send message to client
                    output.println(serverMessage);

                    // Check if server wants to exit
                    if (serverMessage.equals("exit")) {
                        break;
                    }

                    // Receive acknowledgment from client (not needed in TCP)
                    // Output is already synchronized with the client
                }

            } catch (IOException e) {
                System.err.println("Error in client communication: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error in server socket: " + e.getMessage());
        }
    }
}
