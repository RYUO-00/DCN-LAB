import java.net.*;
import java.io.*;

/*
 * Experiment 3: TCP server
 * Waits for a client, reads a file name from it, and sends the
 * contents of that file back, line by line.
 * Run this FIRST, then run tcpcl in another terminal.
 */
public class tcpser
{
    public static void main(String args[]) throws Exception
    {
        // Listen on port 4000
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("sever ready for connection");

        // Wait until a client connects
        Socket sock = sersock.accept();
        System.out.println("connection is successfull and waiting to serve");

        // Read the file name sent by the client
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();

        // Open that file on the server
        BufferedReader contentRead = new BufferedReader(new FileReader(fname));

        // Send the file contents to the client, one line at a time
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        String str;
        while ((str = contentRead.readLine()) != null)
        {
            pwrite.println(str);
        }

        // Close everything (the client stops reading when the socket closes)
        contentRead.close();
        pwrite.close();
        sock.close();
        sersock.close();
    }
}
