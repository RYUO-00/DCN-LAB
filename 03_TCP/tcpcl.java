import java.net.*;
import java.io.*;

/*
 * Experiment 3: TCP client
 * Connects to the server, sends a file name, and prints the file
 * contents received from the server.
 */
public class tcpcl
{
    public static void main(String args[]) throws Exception
    {
        // Connect to the server (127.0.0.1 = same computer), port 4000
        Socket sock = new Socket("127.0.0.1", 4000);

        // Ask the user for the file name
        System.out.println("enter the file name");
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = keyRead.readLine();

        // Send the file name to the server
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        // Read and print the reply until the server closes the connection
        InputStream istream = sock.getInputStream();
        BufferedReader SocketRead = new BufferedReader(new InputStreamReader(istream));
        String str;
        while ((str = SocketRead.readLine()) != null)
        {
            System.out.println(str);
        }

        pwrite.close();
        SocketRead.close();
        keyRead.close();
        sock.close();
    }
}
