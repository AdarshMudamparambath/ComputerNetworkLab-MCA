import java.io.*;
import java.net.*;

class Q3Server {
    public static void main(String args[]) {
        try {
            // Create server Socket
            ServerSocket ss = new ServerSocket(888);
            System.out.println("Server started, waiting for clients...");

            // Connect to client socket
            Socket s = ss.accept();
            System.out.println("Connection established with client: " + s.getInetAddress());

            // To send data to the client
            PrintStream ps = new PrintStream(s.getOutputStream());

            // To read data coming from the client
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // To read data from the keyboard
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

            // Server executes continuously
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println("Client: " + str);
                System.out.print("Server: ");
                String response = kb.readLine();
                ps.println(response);
            }

            // Close connection
            ps.close();
            br.close();
            kb.close();
            ss.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
