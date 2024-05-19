import java.io.*;
import java.net.*;

public class Q7Receiver {
    private DatagramSocket socket;
    private int port;

    public Q7Receiver(int port) throws IOException {
        this.socket = new DatagramSocket(port);
        this.port = port;
    }

    public void receiveData() {
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                // Split the received message into data and checksum
                String[] parts = received.split(":");
                String data = parts[0];
                int receivedChecksum = Integer.parseInt(parts[1]);
                int calculatedChecksum = calculateChecksum(data);

                // Check if the checksum is correct
                if (receivedChecksum == calculatedChecksum) {
                    System.out.println("Received (valid): " + data);
                } else {
                    System.out.println("Received (corrupt): " + data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int calculateChecksum(String data) {
        int checksum = 0;
        for (char c : data.toCharArray()) {
            checksum += c;
        }
        return checksum;
    }

    public static void main(String[] args) {
        try {
            Q7Receiver receiver = new Q7Receiver(9876);
            receiver.receiveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
