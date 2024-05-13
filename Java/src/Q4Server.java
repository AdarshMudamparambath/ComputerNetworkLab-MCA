import java.io.*;
import java.net.*;

public class Q4Server {
    public static void main(String[] args) {
        int serverPort = 9876;

        // Start the server
        try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {
            System.out.println("Server started. Waiting for client...");

            // Communication loop
            while (true) {
                // Receive client request
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message received from client: " + clientMessage);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Input message from server
                System.out.print("Enter message for client: ");
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                String serverMessage = serverInput.readLine();
                byte[] sendData = serverMessage.getBytes();

                // Send message to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                // Check if server wants to exit
                if (serverMessage.equals("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
