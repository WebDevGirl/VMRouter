// listener thread for DatagramSocket

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Listener extends Thread {

	// class variables
	private byte[] buffer = new byte[4000];			// max Ethernet frame size
    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    private DatagramSocket datagramSocket;
	
	//constructor
	public Listener(DatagramSocket dgSocket) {
				
		this.datagramSocket = dgSocket;
	}
	/*----------------------------------------------------------------------------------------*/
	// start execution here
	// listen for packets and print data
    public void run() {
    	
		System.out.println("listening on port " + datagramSocket.getLocalPort());

		while(true) {
			
			try {
				
				datagramSocket.receive(packet);
				String dataStr = new String(packet.getData(),0, packet.getLength());
				System.out.println(String.format("UDP received: %d bytes from %s \n%s\n", 
						packet.getLength(), packet.getAddress().toString(), dataStr ));
				
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
        
    }
	/*----------------------------------------------------------------------------------------*/

}
