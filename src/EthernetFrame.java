import java.nio.ByteBuffer;
import java.util.Arrays;

// Ethernet frame class
//

public class EthernetFrame {
	
	// constants
	private static final int DATA_PAD_LEN = 46;
	private static final int DATA_MAX_LEN = 1500;
	
	// class variables
	private MacAddress srcAddr; 
	private MacAddress dstAddr;	
	private ByteBuffer typeLength = ByteBuffer.allocate(2);
	private byte[] data;
	private ByteBuffer CRC = ByteBuffer.allocate(4);

	/*----------------------------------------------------------------------------------------*/
	// constructor. Create a frame from args and make new CRC
	public EthernetFrame(MacAddress src, MacAddress dst, short typeLength, byte[] dataIn) {
		
		System.out.println("... Creating new Ethernet Frame");
		this.srcAddr = src.clone();
		this.dstAddr = dst.clone();
		this.setTypeLength(typeLength);
		setData(dataIn); 

		
	}
	/*----------------------------------------------------------------------------------------*/
	// constructor. Create a frame from byte array
	public EthernetFrame(byte[] frameIn) {
		
		System.out.println("... Creating new Ethernet Frame from byte array");
		
		this.srcAddr = new MacAddress();
		this.dstAddr = new MacAddress();
				
		// reverse bits of frameIn here
		// calc CRC and comapare
		// compliment first 32
		// data good now
		this.srcAddr.setMac(Arrays.copyOfRange(frameIn, 0, 6));
		this.dstAddr.setMac(Arrays.copyOfRange(frameIn, 6, 12));
		typeLength.put(Arrays.copyOfRange(frameIn, 12, 14));
		setData(Arrays.copyOfRange(frameIn, 14, getTypeLength() + 14));
		CRC.put(Arrays.copyOfRange(frameIn, frameIn.length-4, frameIn.length));	
			
	}
	/*----------------------------------------------------------------------------------------*/
	// generate a new CRC for this frame
	private int makeCRC(byte[] frame) {
		
		// dummy value 1.2.3.4
		return 16909060;	
	}
	/*----------------------------------------------------------------------------------------*/
	// return current CRC as int
	public int getCRC() {
		
		return CRC.getInt(0);
	}
	/*----------------------------------------------------------------------------------------*/
	// getters, setters
	public MacAddress getSrcAddr() {
		
		return srcAddr;
	}

	public MacAddress getDstAddr() {
		
		return dstAddr;
	}

	public short getTypeLength() {
		
		return typeLength.getShort(0);
	}

	public void setTypeLength(short typeLength) {
		
		this.typeLength.putShort(0, typeLength);
	}
	
	public byte[] getData() {
		
		return data;
	}
	/*----------------------------------------------------------------------------------------*/
	// set data buffer, pad data if necessary
	public void setData(byte[] data) {
		
		byte[] pad = new byte[DATA_PAD_LEN];					// all 0 pad
		
		if(data.length > DATA_MAX_LEN)
			System.out.println("frame data buffer exeeds max length: " + DATA_MAX_LEN);
		
		else if(data.length < DATA_PAD_LEN) {
			System.arraycopy(data, 0, pad, 0, data.length);		// pad if data length < DATA_PAD_LEN
			this.data = pad;	
		}
		
		else
			this.data = data.clone();							// data <= DATA_MAX_LEN
	}
	/*----------------------------------------------------------------------------------------*/
	// return frame content and info as String
	// human consumption only
	public String toString() {
		
		String s,t = "";
		t = new String(data);
		// replace all non printable here
		
		s = "src:  " + srcAddr.toHexString() 	+ "\n" +
			"dst:  " + dstAddr.toHexString() 	+ "\n" +
			"t/l:  " + getTypeLength() 			+ "\n" +
			"dlen: " + t.length()				+ "\n" +
			"data: " + t						+ "\n" +
			"CRC:  " + getCRC()				    + "  " +
			           String.format("%02x:%02x:%02x:%02x", CRC.get(0),CRC.get(1),CRC.get(2),CRC.get(3)) + "  " +
			           CRC.toString();
		
		return s;
	}
	/*----------------------------------------------------------------------------------------*/
	// return frame content as hex string
	// human consumption only
	public String toHexString(byte[] frame) {
		
		String s = "";
		int i;
		
		for(i=0; i<6; i++)
			s += String.format("%02x ", frame[i]);
		s += "\n";
		for(; i<12; i++)
			s += String.format("%02x ", frame[i]);
		s += "\n";
		for(; i<14; i++)
			s += String.format("%02x ", frame[i]);
		s += "\n";	
		for(; i<data.length + 14; i++)
			s += String.format("%02x ", frame[i]);
		s += "\n";
		for(int j=0; j<4; i++, j++)
			s += String.format("%02x ", frame[i]);
		s += "\n";
		
		return s;
	}
	/*----------------------------------------------------------------------------------------*/
	// return frame content as byte array
	public byte[] toByteArray() {
		
		// treat typeLength as length only, not type
		int dataLength = data.length;					// data array length incl. padding
		int totLength = 6 + 6 + 2 + dataLength + 4;		// total frame size
		byte[] frame = new byte[totLength];
		
		System.arraycopy(srcAddr.getMacArray(), 0, frame, 0, 6);	
		System.arraycopy(dstAddr.getMacArray(), 0, frame, 6, 6);
		System.arraycopy(typeLength.array(), 0, frame, 12, 2);	
		System.arraycopy(data, 0, frame, 14, dataLength );
		// compliment first 32 bits here
		this.CRC.putInt(0, makeCRC(Arrays.copyOfRange(frame, 0, totLength-4)));
		System.arraycopy(CRC.array(), 0, frame, 14+dataLength, 4);	
		// reverse bits here
		
		return frame;
	}
		/*----------------------------------------------------------------------------------------*/

}
