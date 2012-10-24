import java.nio.ByteBuffer;

public class EthernetFrame {
	
	// constants
	private static final int DATA_PAD_LEN = 46;
	private static final int DATA_MAX_LEN = 1500;
	
	// class variables
	private MacAddress srcAddr; 
	private MacAddress dstAddr;	
	private int typeLength;
	private byte[] data;
	private byte[] CRC;

	/*----------------------------------------------------------------------------------------*/
	// constructor. Create a frame from args and make new CRC
	public EthernetFrame(MacAddress src, MacAddress dst, int type_length, byte[] dataIn) {
		
		System.out.println("... Creating new Ethernet Frame");
		this.setSrcAddr(src.clone());
		this.setDstAddr(dst.clone());
		this.setTypeLength(type_length);
		setData(dataIn); 
		this.CRC = makeCRC();
		
	}
	/*----------------------------------------------------------------------------------------*/
	// generate a new CRC for this frame
	private byte[] makeCRC() {
		
		// temp dummy value
		return new byte[] {0, 0, 0, 0};
	}
	/*----------------------------------------------------------------------------------------*/
	// generate new CRC for this frame and compare to existing value. Return true if equal
	public boolean checkCRC() {
		
		return CRC.equals(makeCRC());
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

	public int getTypeLength() {
		return typeLength;
	}

	public void setTypeLength(int typeLength) {
		this.typeLength = typeLength;
	}
	public byte[] getData() {
		return data;
	}
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
	// return entire frame as String
	// human consumption only
	public String toString() {
		
		String s = "";
		
		s = srcAddr.toHexString() 	+ "\n" +
			dstAddr.toHexString() 	+ "\n" +
			typeLength 				+ "\n" + 
			data 					+ "\n" +
			CRC.toString();
		
		return s;
	}

}
