// Client code
import java.io.*;
import java.net.*;

public class Q3Client {
    public static void main(String[] args) throws IOException {
        // Server address and port
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 9876;

        // Connect to the server
        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            // Communication loop
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                // Input message from user
                System.out.print("Enter message for server (type 'exit' to quit): ");
                message = userInput.readLine();

                // Send message to server
                output.println(message);

                // Check if client wants to exit
                if (message.equals("exit")) {
                    break;
                }

                // Receive response from server
                String serverMessage = input.readLine();
                System.out.println("Message received from server: " + serverMessage);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
