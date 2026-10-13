import java.io.*;
import java.net.*;

/*
 * Experiment 4: UDP client
 * Sends "hello server" to the server and prints the server's reply.
 */
class udpcl
{
    public static void main(String[] args) throws Exception
    {
        // UDP socket (the system picks a free port for the client)
        DatagramSocket clientsocket = new DatagramSocket();
        InetAddress IPaddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        // Message to send
        String sentence = "hello server";
        sendData = sentence.getBytes();

        // Send it to the server on port 9876
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, 9876);
        clientsocket.send(sendPacket);

        // Wait for the server's reply
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientsocket.receive(receivePacket);

        // Convert only the bytes actually received into a String
        String modifiedsentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("FROM SERVER:" + modifiedsentence);

        clientsocket.close();
    }
}
