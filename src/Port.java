import java.net.DatagramSocket;
import java.net.SocketException;

// Port class
// 
public class Port {

	private int localPort;					// real port on this PC for UDP
	private IPv4 virtualIP;					// virtual IP for routing on this port
	private IPv4 remoteIP;					// physical remote IP for UDP packets
	private int remotePort;					// physical remote port for UDP packets
	private int MTU;						// this ports' MTU 
	private Listener listenPort;			// listener thread
	private Writer writePort;				// writer class
	private DatagramSocket datagramSocket;	// used by listener and writer
	private boolean connected = false;		// true after connect, false after disconnect
	
	/*----------------------------------------------------------------------------------------*/
	// constructor 
	// setup a port, don't connect to anything yet
	public Port(int localPort, String myIP, int mtu) throws SocketException {

		this.localPort = localPort;									// port on this PC
		this.virtualIP = new IPv4(myIP);							// this ports virtual IP
		this.MTU = mtu;												// this ports (segment) MTU

		this.listenPort.start();									// start listening
		this.datagramSocket = new DatagramSocket(localPort);		// used by writer & listener class
	
	}
	/*----------------------------------------------------------------------------------------*/
	// store remote IP and remote port number
	// start listener
	public void connect(String ipRemotePort) {
		
		String[] t = ipRemotePort.split(":");						// no input checking
		remoteIP = new IPv4(t[0]);									// store remote IP
		remotePort = Integer.parseInt(t[1]);						// store remote port num
		this.listenPort = new Listener(datagramSocket);				// new listener thread
		
		System.out.println("connected to " + remoteIP.toString() + ":" + localPort);
		connected = true;											

	}
	/*----------------------------------------------------------------------------------------*/
	// stop listening for packets
	public void disconnect() {
		
		if(connected == false) {
			System.out.println("port " + localPort + " already disconnected");
			return;
		}
		
		listenPort = null;											// destroy listener thread
		try {														// by forcing garbage collection
																	// 
			finalize();												// collect garbage
			
		} catch (Throwable e) {
			
			System.out.println("disconnecting port " + localPort);
			e.printStackTrace();
		}	
		
		connected = false;	
		
	}
	/*----------------------------------------------------------------------------------------*/
	// package the data array in a UDP packed and send it to remoteIP:port
	public void send(byte[] data) {
		
	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// return port number
	public int getPortNum() {
		return localPort;
	}
	/*----------------------------------------------------------------------------------------*/

}
