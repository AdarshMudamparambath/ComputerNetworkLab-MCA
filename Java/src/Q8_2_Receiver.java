import java.io.*;
import java.net.*;

public class Q8_2_Receiver {
    public static void main(String[] args) throws IOException {
        final int WINDOW_SIZE = 4;
        DatagramSocket socket = new DatagramSocket(9876);
        int expectedFrame = 0;

        while (true) {
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + message);

            String frame = "Frame: " + expectedFrame;
            if (message.equals(frame)) {
                String ack = "ACK" + expectedFrame;
                byte[] ackBuffer = ack.getBytes();
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(ackPacket);
                System.out.println("Sent: " + ack);
                expectedFrame++;
            } else {
                String ack = "ACK" + (expectedFrame - 1);
                byte[] ackBuffer = ack.getBytes();
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(ackPacket);
                System.out.println("Sent: " + ack);
            }
        }
    }
}
