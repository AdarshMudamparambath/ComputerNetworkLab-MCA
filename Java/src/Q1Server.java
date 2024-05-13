import java.io.*;
import java.net.*;

public class Q1Server {

    public static void main(String[] args) throws IOException {
        // Choose a port number (replace with a valid port if needed)
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            // Wait for a client connection
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected!");

            // Read the message sent from the client
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            String message = inputStream.readUTF();

            System.out.println("Received message: " + message);

            // Close resources
            clientSocket.close();
        }
    }
}
