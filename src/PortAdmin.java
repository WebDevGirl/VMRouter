import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

// maintain a list of ports

public class PortAdmin {

	// hash map 
	ConcurrentHashMap<Integer, Port> VRPorts;
	
	// max ports
	int maxPorts;
	
	/*----------------------------------------------------------------------------------------*/
	// constructor
	// create hash map w/ initial capacity 16 and load factor 0.75
	public PortAdmin(int maxPorts) {
		
		VRPorts = new  ConcurrentHashMap<Integer, Port>(16, (float) 0.75);
		this.maxPorts = maxPorts;
	}
	/*----------------------------------------------------------------------------------------*/
	// add port
	public void add(int portNo, String myIP, int mtu) {
		
		
		if(existsPort(portNo)) {
			System.out.println("port " + portNo + " exists");
		}
		else {
			try {
				
				Port p = new Port(portNo, myIP, mtu);
				VRPorts.put(portNo, p);
			
			} catch (SocketException e) {
				
				System.out.println("PortAdmin: add port " + portNo + " failed");
				e.printStackTrace();
				
			} catch (UnknownHostException e) {
				
				System.out.println("PortAdmin: add port " + portNo + " failed");
				e.printStackTrace();
			}
		}
			
	}
	/*----------------------------------------------------------------------------------------*/
	// delete port and call garbage collector
	public void remove(int portNo) {
		
		if(existsPort(portNo)) {
			VRPorts.remove(portNo);
			System.out.println("port " + portNo + " deleted");
			try {
				
				finalize();
				
			} catch (Throwable e) {
				
				System.out.println("PortAdmin: errror finalizing"); 
				e.printStackTrace();
			}
		}
		else {
			System.out.println("port " + portNo + " does not exist");
		}
	}	
	/*----------------------------------------------------------------------------------------*/
	public void connect(int portNo, String connectStr) {
		
		if(existsPort(portNo)) {
			VRPorts.get(portNo).connect(connectStr);
		}
		else
			System.out.println("port " + portNo + " does not exists");
	}
	/*----------------------------------------------------------------------------------------*/
	public void disconnect(int portNo) {
				
		if(existsPort(portNo)) {
			VRPorts.get(portNo).disconnect();
		}
		else
			System.out.println("port " + portNo + " does not exists");
	}
	/*----------------------------------------------------------------------------------------*/
	private boolean existsPort(int portNo) {
		
		return(VRPorts.containsKey(portNo));
	}
	/*----------------------------------------------------------------------------------------*/
	// send string in UDP 
	public void usend(int portNo, byte[] data) {
		
		if(existsPort(portNo)) {
			try {
				
				VRPorts.get(portNo).send(data);
				
			} catch (IOException e) {
				
				System.out.println("PortAdmin: error sending data on port " + portNo);
				e.printStackTrace();
			}
		}
		else
			System.out.println("port " + portNo + " does not exists");	
	}
	/*----------------------------------------------------------------------------------------*/
	// send string in UDP 
	public void asend(byte[] data) {
		
		Enumeration<Integer> keys = VRPorts.keys();
		
		while(keys.hasMoreElements()) { 
			Object key = keys.nextElement();
			try {
				
				VRPorts.get(key).send(data);
				
			} catch (IOException e) {
				
				System.out.println("PortAdmin: error sending data on port " + key);
				e.printStackTrace();
			}
		}	
	}
	/*----------------------------------------------------------------------------------------*/
	public String getAllPortsConfig() {
		
		String s = "";
		Enumeration<Integer> keys = VRPorts.keys();
		
		while(keys.hasMoreElements()) {
			Object key = keys.nextElement();
			s += VRPorts.get(key).getSettings();
		}
		
		return s;
	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
}
