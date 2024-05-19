import java.io.*;
import java.net.*;
import java.util.Random;

public class Q7Sender {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public Q7Sender(String address, int port) throws IOException {
        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName(address);
        this.port = port;
    }

    public void sendData() {
        Random random = new Random();
        while (true) {
            try {
                // Generate random data
                String data = generateData();
                int checksum = calculateChecksum(data);

                // Prepare the message with data and checksum
                String message = data + ":" + checksum;

                // Send the message
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);
                System.out.println("Sent: " + message);

                // Simulate a delay
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateData() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
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
            Q7Sender sender = new Q7Sender("localhost", 9876);
            sender.sendData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
