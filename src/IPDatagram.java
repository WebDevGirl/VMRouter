import java.nio.ByteBuffer;
import java.util.Arrays;

// IP datagram class
// IPv4 only
// 6 bytes MAC
// 4 bytes IP

public class IPDatagram {
	
	// class variables
	private ByteBuffer versionAndHLength = ByteBuffer.allocate(1);	// IPversion + header length
	private ByteBuffer typeOfService  = ByteBuffer.allocate(1);	// not used set to 0
	private ByteBuffer totalLength =  ByteBuffer.allocate(2);	// total packet length
	private ByteBuffer ID =  ByteBuffer.allocate(2);			// packed id
	private ByteBuffer FlagsFOffset = ByteBuffer.allocate(2);	// flags and fragment offset
	private ByteBuffer TTL  = ByteBuffer.allocate(1);			// max hops
	private ByteBuffer protocol  = ByteBuffer.allocate(1);		// protocol = 4
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
		
		
		this.versionAndHLength.put((byte) 69);						// IPv4=4 HLen=5
		this.typeOfService.put((byte) 123);							// not used
		this.totalLength.putShort(0, (short) (20 + dataIn.length));	// total length of the packet
		this.ID.putShort(0, id);									// packet ID
		this.FlagsFOffset.putShort(0, (short) 0);					// flags and fragment offset
		this.TTL.put((byte) 255);									// Time To Live
		this.protocol.put((byte) 4);								// protocol = 4
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
	public byte[] toByteArray()
	{

		byte[] ipDatagram = new byte[totalLength.getShort(0)];
		
		
		System.arraycopy(versionAndHLength, 0, ipDatagram, 0, 1);	// copy version# and header length to datagram
		System.arraycopy(typeOfService, 0, ipDatagram, 1, 1);		// type of service byte
		System.arraycopy(totalLength, 0, ipDatagram, 2, 2);
		System.arraycopy(ID, 0, ipDatagram, 4, 2);
		System.arraycopy(FlagsFOffset, 0, ipDatagram, 7, 2);
		System.arraycopy(TTL, 0, ipDatagram, 9, 1);
		System.arraycopy(protocol, 0, ipDatagram, 10, 1);
		System.arraycopy(headerChecksum, 0, ipDatagram, 11, 1);
		System.arraycopy(srcIP.getIP(), 0, ipDatagram, 13, 4);
		System.arraycopy(dstIP.getIP(), 0, ipDatagram, 17, 4);
		System.arraycopy(data, 0, ipDatagram, 17, data.length);

		
		return ipDatagram;
	}
	/*----------------------------------------------------------------------------------------*/
	// return datagrtam as hex string
	// human consumption only
	public String toHexString(byte[] buffer) {
		
		String s = "";
		int i=0;
		
		s += String.format("%02x ", buffer[0]) + "\n";
		s += String.format("%02x ", buffer[1]) + "\n";
		for(i=2; i<4; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";	
		for(i=4; i<6; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";
		for(i=6; i<8; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";
		
		s += String.format("%02x ", buffer[8]) + "\n";
		s += String.format("%02x ", buffer[9]) + "\n";
		
		for(i=10; i<12; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";
		
		for(i=12; i<16; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";		
		
		for(i=16; i<20; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";	
		
		for(i=20; i<data.length + 20; i++)
			s += String.format("%02x ", buffer[i]);
		s += "\n";
		return s;
	}
	/*----------------------------------------------------------------------------------------*/
	// 
	public String toString() {
		
		String s,t = "";
		t = new String(data);
		// replace all non printable here
		
		
		s = "version and header length: " + versionAndHLength.get(0)	+ "\n" +
			"type of service:           " + typeOfService.get(0)		+ "\n" +
			"total length:              " + totalLength.getShort(0) 	+ "\n" +
			"packet ID:                 " + ID.getShort(0)				+ "\n" +
			"Flags and fragment offset: " + FlagsFOffset.getShort(0)	+ "\n" +
			"TTL:                       " + TTL.get(0)					+ "\n" +
			"protocol:                  " + protocol.get(0)			 	+ "\n" +
			"header checksum:           " + headerChecksum.getShort(0)	+ "\n" +
			"source IP:                 " + srcIP.toString()			+ "\n" +
			"destinaton IP:             " + dstIP.toString()			+ "\n" +
			"data:                      " + t;                    
		
		return(s);
	}
	/*----------------------------------------------------------------------------------------*/

}
