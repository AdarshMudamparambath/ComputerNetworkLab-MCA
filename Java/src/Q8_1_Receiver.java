import java.io.*;
import java.net.*;

public class Q8_1_Receiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buffer = new byte[256];

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + message);

            // Send acknowledgment
            String ack = "ACK";
            byte[] ackBuffer = ack.getBytes();
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, packet.getAddress(), packet.getPort());
            socket.send(ackPacket);
            System.out.println("Sent ACK");
        }
    }
}
