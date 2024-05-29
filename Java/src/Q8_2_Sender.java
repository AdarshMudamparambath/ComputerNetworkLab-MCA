import java.io.*;
import java.net.*;
import java.util.Random;

public class Q8_2_Sender {
    public static void main(String[] args) throws IOException {
        final int WINDOW_SIZE = 4;
        final int TOTAL_FRAMES = 10;
        DatagramSocket socket = new DatagramSocket();
        InetAddress receiverAddress = InetAddress.getByName("localhost");
        Random random = new Random();
        int nextFrameToSend = 0;
        int ackReceived = 0;

        while (ackReceived < TOTAL_FRAMES) {
            while (nextFrameToSend < ackReceived + WINDOW_SIZE && nextFrameToSend < TOTAL_FRAMES) {
                String message = "Frame: " + nextFrameToSend;
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 9876);
                socket.send(packet);
                System.out.println("Sent: " + message);
                nextFrameToSend++;
            }

            byte[] ackBuffer = new byte[256];
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
            socket.receive(ackPacket);
            String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());

            if (ack.startsWith("ACK")) {
                int ackNum = Integer.parseInt(ack.substring(3).trim());
                System.out.println("Received: " + ack);
                ackReceived = ackNum + 1;
                nextFrameToSend = ackReceived;
            } else {
                System.out.println("ACK not received correctly, resending from frame: " + ackReceived);
                nextFrameToSend = ackReceived;
            }
        }
        socket.close();
    }
}
