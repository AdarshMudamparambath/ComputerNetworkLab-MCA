import java.net.*;
import java.io.*;

public class Q4SR {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Usage: java datagramSenderReceiver <receiverHost> <receiverPort>");
                return;
            }

            // Get receiver host and port from command line arguments
            InetAddress receiverHost = InetAddress.getByName(args[0]);
            int receiverPort = Integer.parseInt(args[1]);

            // Create a DatagramSocket
            DatagramSocket mySocket = new DatagramSocket();

            // Create a BufferedReader to read user input
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Prompt user for input
                System.out.print("Enter message to send (or type 'exit' to quit): ");
                String userInput = userInputReader.readLine();

                // Check if user wants to exit
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                // Send user input message
                byte[] sendBuffer = userInput.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
                mySocket.send(sendPacket);

                // Receive response message
                byte[] recvBuffer = new byte[1024]; // Adjust buffer size as needed
                DatagramPacket receivePacket = new DatagramPacket(recvBuffer, recvBuffer.length);
                mySocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message: " + receivedMessage);
            }

            // Close the socket and input reader
            mySocket.close();
            userInputReader.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java datagramSenderReceiver <receiverHost> <receiverPort>");
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number provided.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
