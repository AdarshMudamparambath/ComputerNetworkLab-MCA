import java.net.*;

public class Q2Server {

    public static void main(String[] args) throws Exception {
        // Define port number
        int port = 5000;

        // Create a DatagramSocket
        DatagramSocket serverSocket = new DatagramSocket(port);

        System.out.println("Server started on port: " + port);

        // Continuously listen for incoming messages
        while (true) {
            try {
                // Create a byte array to receive data
                byte[] receiveData = new byte[1024];

                // Create a DatagramPacket to receive the data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive data from the client
                serverSocket.receive(receivePacket);

                // Convert received data to string
                String message = new String(receivePacket.getData()).trim();

                System.out.println("Received message: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
