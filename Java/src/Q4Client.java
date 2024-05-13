import java.io.*;
import java.net.*;

public class Q4Client {
    public static void main(String[] args) throws IOException {
        // Server address and port
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 9876;

        // Create socket
        try (DatagramSocket socket = new DatagramSocket()) {

            // Communication loop
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                // Input message from user
                System.out.print("Enter message for server (type 'exit' to quit): ");
                message = userInput.readLine();
                byte[] sendData = message.getBytes();

                // Send message to server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);

                // Check if client wants to exit
                if (message.equals("exit")) {
                    break;
                }

                // Receive response from server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message received from server: " + serverMessage);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
