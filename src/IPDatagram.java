import java.nio.ByteBuffer;

// IP datagram class
// IPv4 only
// 6 bytes MAC
// 4 bytes IP

public class IPDatagram {
	
	// class variables
	private byte versionAndHLength;								// IPversion + header length
	private byte typeOfService;									// 
	private ByteBuffer totalLength =  ByteBuffer.allocate(2);	// total packet length
	private ByteBuffer ID =  ByteBuffer.allocate(2);			// packed id
	private ByteBuffer FlagsFOffset = ByteBuffer.allocate(2);	//
	private byte TTL;											// max hops
	private byte protocol;										//
	private ByteBuffer headerChecksum = ByteBuffer.allocate(2);	// simple header checksum
	private IPv4 srcIP = new IPv4("1.1.1.1");					// source IP
	private IPv4 dstIP = new IPv4("2.2.2.2");					// destination IP
	private ByteBuffer options;									// not used
	private byte[] data;										// payload

}