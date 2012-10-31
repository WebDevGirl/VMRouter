import java.net.DatagramSocket;
import java.net.SocketException;

// writer  port test class
public class WriterPort {

	// class variables
	int port;
	DatagramSocket datagramSocket;
	
	
	
	//constructor
	public WriterPort(int port) {
		
		this.port = port;
		
		try {
			
			this.datagramSocket = new DatagramSocket(port);
			
		} catch (SocketException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	/*----------------------------------------------------------------------------------------*/

}
