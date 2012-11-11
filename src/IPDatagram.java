import java.nio.ByteBuffer;
import java.util.Arrays;

// IP datagram class
// IPv4 only
// 6 bytes MAC
// 4 bytes IP

public class IPDatagram {
	
	// class variables
	private byte versionAndHLength;								// IPversion + header length
	private byte typeOfService;									// not used set to 0
	private ByteBuffer totalLength =  ByteBuffer.allocate(2);	// total packet length
	private ByteBuffer ID =  ByteBuffer.allocate(2);			// packed id
	private ByteBuffer FlagsFOffset = ByteBuffer.allocate(2);	// flags and fragment offset
	private byte TTL;											// max hops
	private byte protocol;										// protocol = 4
	private ByteBuffer headerChecksum = ByteBuffer.allocate(2);	// simple header checksum
	private IPv4 srcIP = new IPv4("1.1.1.1");					// source IP
	private IPv4 dstIP = new IPv4("2.2.2.2");					// destination IP
	//private ByteBuffer options;									// not used
	private byte[] data;										// pay load

	/*----------------------------------------------------------------------------------------*/
	public IPDatagram(byte[] dataIn, short id, IPv4 srcIP, IPv4 dstIP) {
		
		if(dataIn.length > 65535) {
			System.out.println("IPDatagram: data truncated to 65,515 bytes");
		}
		
		
		this.versionAndHLength = 69;								// IPv4=4 HLen=5
		this.typeOfService = 0;										// not used
		this.totalLength.putShort(0, (short) (20 + dataIn.length));	// total length of the packet
		this.ID.putShort(0, id);									// packet ID
		this.FlagsFOffset.putShort(0, (short) 0);					// flags and fragment offset
		this.TTL = (byte) 255;										// Time To Live
		this.protocol = 4;											// protocol = 4
		this.headerChecksum.putShort(0, (short) 0);					// 0 for now
		this.srcIP = srcIP;											// source IP
		this.dstIP = dstIP;											// destination IP
		
		if(dataIn.length > 65515) {									// max pay load = 65,535 bytes
			this.data = Arrays.copyOfRange(dataIn, 0, 65515);		// truncate
			System.out.println("IPDatagram: data truncated to 65,515 bytes");
		}
		else
			this.data = dataIn;
		
	}	
	/*----------------------------------------------------------------------------------------*/
	public IPDatagram(byte[] packetIn) {
	
	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// convert the contents of the IP
	public String toString() {
		
		String s,t = "";
		t = new String(data);
		// replace all non printable here
		
		s = "version and header length: " + versionAndHLength 			+ "\n" +
			"type of service:           " + typeOfService     			+ "\n" +
			"total length:              " + totalLength.getShort(0) 	+ "\n" +
			"packet ID:                 " + ID.getShort(0)				+ "\n" +
			"Flags and fragment offset: " + FlagsFOffset.getShort(0)	+ "\n" +
			"TTL:                       " + TTL							+ "\n" +
			"protocol:                  " + protocol				 	+ "\n" +
			"header checksum:           " + headerChecksum.getShort(0)	+ "\n" +
			"source IP:                 " + srcIP.toString()			+ "\n" +
			"destinaton IP:             " + dstIP.toString()			+ "\n" +
			"data:                      " + t;                    
		
		return(s);
	}
	/*----------------------------------------------------------------------------------------*/

}