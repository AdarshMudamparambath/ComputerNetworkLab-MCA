import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Q8_1_Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress receiverAddress = InetAddress.getByName("localhost");
        int port = 9876;
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Enter the number of messages to send:");
       // int messageCount = Integer.parseInt(scanner.nextLine());

        while (true) {
            System.out.println("Enter message " +  ":");
            String message = scanner.nextLine();
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, port);
            socket.send(packet);
            System.out.println("Sent: " + message);

            // Wait for acknowledgment
            byte[] ackBuffer = new byte[256];
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
            socket.receive(ackPacket);
            String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
            System.out.println("Received ACK: " + ack);
        }
       // socket.close();
       // scanner.close();
    }
}
