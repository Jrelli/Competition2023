package frc.robot.subsystems.communication;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import frc.robot.subsystems.communication.TagData;;

/* Quick explanation of the code below
 * The constructor takes in the address and port to connect to
 * It then connects to the server
 * Once connected the server will start sending data to the client
 * 
 * Each packet sent from the server contains the id, dist, and angle
 * [1 ,2,3,4,5,6,7,8,9]
 * [id,dist 4b, ang 4b]
 * 
 * The id is one byte
 * Both dist & angle are floats, so they take up 4 bytes each
 * 
 * The run() function is called when the client is started to handle the connection
 * seperately from the robots main thread, (multithreading)
*/

public class CommClient implements Runnable
{
    private Thread t;
    /* Create our sockets  */ 
    private DataInputStream m_input   = null;
    private DatagramSocket  m_socket  = null;
    private DatagramPacket  m_receive = null;
    byte[] receive_buf = new byte[9];

    private byte   id;
    private int    port;

    private float  c_Dist;
    private float  c_Angle;

    public CommClient(int port)
    {
        this.port = port;
    }

    /* Binds to port using UDP and sets up the packets */
    public void connectClient(int port)
    {
        try {
            m_socket = new DatagramSocket(port);
            m_receive = new DatagramPacket(receive_buf, 9);
            m_input = new DataInputStream(new ByteArrayInputStream(m_receive.getData(), m_receive.getOffset(), m_receive.getLength()));
        } catch (Exception i) {
            System.out.println(i);
        } 
    }

    /* Read 9 raw bytes from the stream */

    public void receiveMessage()
    {
	try {
        /* .receive blocks thread, below code DOES NOT RUN unless message is received and proccessed */
        m_socket.receive(m_receive);
        try {
            m_input.read(receive_buf, 0, 9);
        } catch (EOFException e) {
            System.out.print(e);
        } 

        /* Set id to first byte */
        this.id = receive_buf[0];
        TagData.last_known_id = receive_buf[0];

        System.out.println("TAG FOUND! ID: " + this.id);

	    /* Grab the 1st float value */
	    c_Dist = ByteBuffer.wrap(receive_buf).order(ByteOrder.BIG_ENDIAN).getFloat(1);

	    /* Grab the 2nd float value */
	    c_Angle = ByteBuffer.wrap(receive_buf).order(ByteOrder.BIG_ENDIAN).getFloat(5);

        TagData.update_Tag(id, c_Angle, c_Dist);
	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        System.out.println("Thread ran!");
        connectClient(port);

        while(true)
        {
            receiveMessage();
        }
    }

    public void start(String name)
    {
        if(t == null)
        {
            t = new Thread(this,name);
            t.start();
        }
    }
}