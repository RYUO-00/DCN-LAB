import java.io.*;
import java.net.*;

/*
 * Experiment 4: UDP server
 * Receives one datagram from the client, prints it, then sends back a
 * message typed on the server's keyboard.
 * Run this FIRST, then run udpcl in another terminal.
 */
class udpser
{
    public static void main(String args[]) throws Exception
    {
        // UDP socket listening on port 9876 (no connection is set up)
        DatagramSocket serversocket = new DatagramSocket(9876);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        // Wait for a datagram from the client
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serversocket.receive(receivePacket);

        // Convert only the bytes actually received into a String
        String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("RECEIVED:" + sentence);

        // Remember who sent it, so we can reply
        InetAddress IPaddress = receivePacket.getAddress();
        int port = receivePacket.getPort();

        // Type the reply message
        System.out.println("enter the message");
        String data = br.readLine();
        sendData = data.getBytes();

        // Send the reply back to the client
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, port);
        serversocket.send(sendPacket);
        serversocket.close();
    }
}
