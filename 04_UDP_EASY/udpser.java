import java.net.*;
import java.util.Scanner;

public class udpser
{
    public static void main(String[] args) throws Exception
    {
        try (DatagramSocket ds = new DatagramSocket(9876))
        {
            DatagramPacket in = new DatagramPacket(new byte[1024], 1024);
            ds.receive(in);
            System.out.println("RECEIVED:" + new String(in.getData(), 0, in.getLength()));

            System.out.println("enter the message");
            byte[] reply = new Scanner(System.in).nextLine().getBytes();
            ds.send(new DatagramPacket(reply, reply.length, in.getAddress(), in.getPort()));
        }
    }
}
