import java.io.*;
import java.net.*;

public class Dupserver {
    public static void main(String args[]) throws Exception {
        // Create a server socket listening on port 888
        ServerSocket ss = new ServerSocket(888);
        // Accept an incoming client connection
        Socket s = ss.accept();
        System.out.println("Connection established");

        // Create output stream to send data to the client
        PrintStream ps = new PrintStream(s.getOutputStream());
        // Create input stream to receive data from the client
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // Create input stream to read from the keyboard (server-side input)
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str, str1;
            // Read data from the client and print it to the console
            while ((str = br.readLine()) != null) {
                System.out.println("Client: " + str);
                // Read a response from the keyboard and send it to the client
                str1 = kb.readLine();
                ps.println(str1);
            }
            // Close resources and terminate the program
            ps.close();
            br.close();
            kb.close();
            ss.close();
            s.close();
            System.exit(0);
        }
    }
}
