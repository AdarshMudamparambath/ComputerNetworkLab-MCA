import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT + "...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection accepted from " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                out.println(inputLine);
            }

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
}
}
}
