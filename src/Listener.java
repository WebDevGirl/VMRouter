// test class for DatagramSocket
//

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;


public class Listener extends Thread {

	// class variables
	private int port = 12345;						// default port
	private byte[] buffer = new byte[1518];			// max Ethernet frame size
    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    private DatagramSocket datagramSocket;
	
	//constructor
	public Listener(int port) {
		
		this.port = port;
		
		try {
			
			this.datagramSocket = new DatagramSocket(port);
			
		} catch (SocketException e) {
			
			System.out.println("listener on port " + port + " says:");
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
	}
	/*----------------------------------------------------------------------------------------*/
	// start execution here
	// listen for packets
    public void run() {
    	
		System.out.println("listening on port " + port);

		while(true) {
			
			try {
				
				datagramSocket.receive(packet);
				System.out.println("received packet: " + packet.getData());
				
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
        
    }
	/*----------------------------------------------------------------------------------------*/

}
