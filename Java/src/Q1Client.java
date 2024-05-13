import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Q1Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // Server IP address (replace with the actual server IP)
        String serverIp = "localhost";

        // Choose a port number (same as the server)
        int port = 8080;

        // Get user input for the message
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message to send: ");
        String message = scanner.nextLine();

        try (Socket clientSocket = new Socket(InetAddress.getByName(serverIp), port)) {
            System.out.println("Connected to server!");

            // Send the message to the server
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            outputStream.writeUTF(message);

            // Close resources
            outputStream.flush();
        }
    }
}
