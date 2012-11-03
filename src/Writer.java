import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

// writer port test class
public class Writer {

	// class variables
	int localPort;
	int remotePort;
	
	IPv4 remoteIP;
	DatagramSocket datagramSocket;
	
	//constructor
	public Writer(int localPort) {
		
		this.localPort = localPort;
		
		try {
			
			this.datagramSocket = new DatagramSocket();
			
		} catch (SocketException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	/*----------------------------------------------------------------------------------------*/
	public void connect(IPv4 remoteIP, int remotePort) {
	
		this.remotePort = remotePort;
		this.remoteIP = remoteIP;
		
		InetAddress t = null;
		
		try {
			t = InetAddress.getByAddress(remoteIP.getIP());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		datagramSocket.connect(t, remotePort);
		System.out.println(String.format("connected to %s:%d", remoteIP.toString(), remotePort));
	}
	/*----------------------------------------------------------------------------------------*/
	public void disconnect() {
		
		datagramSocket.disconnect();
		
	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/

}
