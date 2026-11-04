import java.net.*;

public class udpcl
{
    public static void main(String[] args) throws Exception
    {
        try (DatagramSocket ds = new DatagramSocket())
        {
            byte[] msg = "hello server".getBytes();
            ds.send(new DatagramPacket(msg, msg.length, InetAddress.getByName("localhost"), 9876));

            DatagramPacket in = new DatagramPacket(new byte[1024], 1024);
            ds.receive(in);
            System.out.println("FROM SERVER:" + new String(in.getData(), 0, in.getLength()));
        }
    }
}
