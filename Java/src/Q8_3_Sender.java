import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Q8_3_Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress receiverAddress = InetAddress.getByName("localhost");
        int port = 9876;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of messages to send:");
        int messageCount = Integer.parseInt(scanner.nextLine());

        int windowSize = 4;
        boolean[] acked = new boolean[messageCount];
        String[] messages = new String[messageCount];

        for (int i = 0; i < messageCount; i++) {
            System.out.println("Enter message " + (i + 1) + ":");
            messages[i] = scanner.nextLine();
        }

        int base = 0;
        int nextSeqNum = 0;

        while (base < messageCount) {
            while (nextSeqNum < base + windowSize && nextSeqNum < messageCount) {
                if (!acked[nextSeqNum]) {
                    String message = messages[nextSeqNum];
                    byte[] buffer = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, port);
                    socket.send(packet);
                    System.out.println("Sent: " + message + " with SeqNum: " + nextSeqNum);
                }
                nextSeqNum++;
            }

            // Wait for acknowledgment
            byte[] ackBuffer = new byte[256];
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
            socket.receive(ackPacket);
            String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
            int ackNum = Integer.parseInt(ack);
            System.out.println("Received ACK for SeqNum: " + ackNum);
            acked[ackNum] = true;

            while (base < messageCount && acked[base]) {
                base++;
            }

            if (base == nextSeqNum) {
                // Stop timer
            } else {
                // Start timer
            }
        }
        socket.close();
        scanner.close();
    }
}
