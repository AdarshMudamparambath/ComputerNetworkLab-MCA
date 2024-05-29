import java.io.*;
import java.net.*;

public class Q8_3_Receiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buffer = new byte[256];
        int expectedSeqNum = 0;
        String[] receivedMessages = new String[256];

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            int seqNum = Integer.parseInt(message.split(" ")[1]);
            System.out.println("Received: " + message);

            // Send acknowledgment
            String ack = String.valueOf(seqNum);
            byte[] ackBuffer = ack.getBytes();
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, packet.getAddress(), packet.getPort());
            socket.send(ackPacket);
            System.out.println("Sent ACK: " + ack);

            // Store the received message
            if (seqNum == expectedSeqNum) {
                System.out.println("Message in order: " + message);
                receivedMessages[expectedSeqNum] = message;
                expectedSeqNum++;
                // Deliver any buffered, in-order messages
                while (receivedMessages[expectedSeqNum] != null) {
                    System.out.println("Delivering buffered message: " + receivedMessages[expectedSeqNum]);
                    receivedMessages[expectedSeqNum] = null;
                    expectedSeqNum++;
                }
            } else if (seqNum > expectedSeqNum) {
                receivedMessages[seqNum] = message;
            }
        }
    }
}
