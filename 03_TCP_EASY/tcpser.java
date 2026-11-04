import java.io.*;
import java.net.*;
import java.nio.file.*;

public class tcpser
{
    public static void main(String[] args) throws Exception
    {
        try (ServerSocket server = new ServerSocket(4000))
        {
            System.out.println("sever ready for connection");
            try (Socket s = server.accept())
            {
                System.out.println("connection is successfull and waiting to serve");
                String name = new BufferedReader(new InputStreamReader(s.getInputStream())).readLine();
                s.getOutputStream().write(Files.readAllBytes(Paths.get(name)));
            }
        }
    }
}
