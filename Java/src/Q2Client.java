import java.net.*;
import java.util.Scanner;

public class Q2Client {

    public static void main(String[] args) throws Exception {
        // Define server IP address and port number
        String serverIP = "localhost";
        int port = 5000;

        // Get user input for message
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message to send: ");
        String message = scanner.nextLine();

        // Create a byte array from the message
        byte[] sendData = message.getBytes();

        // Create a DatagramSocket
        DatagramSocket clientSocket = new DatagramSocket();

        // Create a DatagramPacket to send the data
        InetAddress serverAddress = InetAddress.getByName(serverIP);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);

        // Send the message to the server
        clientSocket.send(sendPacket);

        System.out.println("Message sent to server: " + message);

        // Close the socket (optional for one-way communication)
        clientSocket.close();
        scanner.close();
    }
}
