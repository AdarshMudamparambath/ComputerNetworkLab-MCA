import java.net.*;
import java.io.*;

public class Q4RS {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage: java datagramReceiverSender <localPortNum>");
                return;
            }

            // Parse the local port number from command line arguments
            int localPortNum = Integer.parseInt(args[0]);

            // Create a DatagramSocket bound to the specified local port
            DatagramSocket mySocket = new DatagramSocket(localPortNum);

            // Create a BufferedReader to read user input
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Receive message from sender
                byte[] recvBuffer = new byte[1024]; // Adjust buffer size as needed
                DatagramPacket receivePacket = new DatagramPacket(recvBuffer, recvBuffer.length);
                mySocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("\nReceived message: " + receivedMessage);

                // Prompt user for response message
                System.out.print("Enter response message to send (or type 'exit' to quit): ");
                String responseMessage = userInputReader.readLine();

                // Check if user wants to exit
                if (responseMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                // Send response message to sender
                InetAddress senderAddress = receivePacket.getAddress();
                int senderPort = receivePacket.getPort();
                byte[] sendBuffer = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, senderAddress, senderPort);
                mySocket.send(sendPacket);
            }

            // Close the socket and input reader
            mySocket.close();
            userInputReader.close();
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number provided.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
