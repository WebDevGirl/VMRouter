import java.nio.ByteBuffer;

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
		this.setSrcAddr(src.clone());
		this.setDstAddr(dst.clone());
		this.setTypeLength(typeLength);
		setData(dataIn); 
		this.CRC.putInt(0, makeCRC());
		
	}
	/*----------------------------------------------------------------------------------------*/
	// generate a new CRC for this frame
	private int makeCRC() {
		
		// temp dummy value 1.2.3.4
		return 16909060;	
	}
	/*----------------------------------------------------------------------------------------*/
	// generate new CRC for this frame and compare to existing value. Return true if equal
	public boolean checkCRC() {
		
		return CRC.getInt(0) == makeCRC();
	}
	/*----------------------------------------------------------------------------------------*/
	// return current CRC as int
	public int getCRC() {
		
		return CRC.getInt(0);
	}
	/*----------------------------------------------------------------------------------------*/
	// auto generated getters, setters
	public MacAddress getSrcAddr() {
		
		return srcAddr;
	}

	public void setSrcAddr(MacAddress srcAddr) {
		
		this.srcAddr = srcAddr;
	}

	public MacAddress getDstAddr() {
		
		return dstAddr;
	}

	public void setDstAddr(MacAddress dstAddr) {
		
		this.dstAddr = dstAddr;
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

}
