import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            System.out.println("Enter message (type 'exit' to quit): ");
            while ((inputLine = userInput.readLine()) != null && !inputLine.equalsIgnoreCase("exit")) {
                out.println(inputLine);
                System.out.println("Server: " + in.readLine());
                System.out.println("Enter message (type 'exit' to quit): ");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
