// Port class
// 
public class Port {

	private int localPort;			// real port on this PC
	private int remotePort;			// 
	private IPv4 virtualIP;			// virtual IP for this port
	private IPv4 remoteIP;
	private int MTU;				// this ports' MTU 
	private Listener listenPort;	// listener thread
	private Writer writePort;		// writer class
	
	/*----------------------------------------------------------------------------------------*/
	// constructor 
	public Port(int localPort, String ip, int mtu) {

		this.localPort = localPort;
		this.virtualIP = new IPv4(ip);
		this.MTU = mtu;
		this.listenPort = new Listener(localPort);					// new listener thread
		this.writePort = new Writer(localPort);						// new writer class
		this.listenPort.start();									// start listening

	
	}
	/*----------------------------------------------------------------------------------------*/
	public void connect(String ipRemotePort) {
		
		String[] t = ipRemotePort.split(":");						// no input checking
		remoteIP = new IPv4(t[0]);
		remotePort = Integer.parseInt(t[1]);						// no error checking
		
		this.writePort.connect(remoteIP, remotePort);				// connect to remote
	
	}
	/*----------------------------------------------------------------------------------------*/
	public void disconnect() {
		
		
		writePort.disconnect();
		listenPort = null;
		try {
			finalize();
		} catch (Throwable e) {
			
			e.printStackTrace();
		}	
		
		
//		System.out.println("disconnecting port " + localPort);
//		listenPort = null;							//
//		writePort = null;
//		
//		try {
//			finalize();								// force garbage collection
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	/*----------------------------------------------------------------------------------------*/
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
