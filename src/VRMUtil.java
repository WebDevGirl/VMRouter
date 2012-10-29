import java.nio.ByteBuffer;
import java.util.zip.CRC32;

// static class for miscellaneous functions
public class VRMUtil {
	
	// no variables

	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// reverse bits in byteIn
	static public byte reverseByte(byte byteIn) {
		
		return (byte) (Integer.reverse((int) byteIn) >>> 24);
	}
	/*----------------------------------------------------------------------------------------*/
	// return an array with 6 random bytes
	public static byte[] getRandomdMac() {
		
		byte randomMac[] = new byte[6];
		
		for(int i = 0; i < 6; i++) 
			randomMac[i] = (byte)(Math.random() * 256) ;
		
		return randomMac;
	}
	/*----------------------------------------------------------------------------------------*/
	// return CRC32 as 4 byte ByteBuffer
	public static ByteBuffer getCRC(byte[] data) {
		
		CRC32 testCRC = new CRC32();
		ByteBuffer ret = ByteBuffer.allocate(4);
		
		testCRC.reset();
		testCRC.update(data);
		ret.putInt(0, (int)testCRC.getValue());
		System.out.println("long value: " + testCRC.getValue());
		return ret;
		
	}
	/*----------------------------------------------------------------------------------------*/
	// return CRC32 as 8 byte hex string
	public static String getCRCStr(byte[] data) {
		
		ByteBuffer crc = getCRC(data);
		
		return String.format("%02x:%02x:%02x:%02x", crc.get(0), crc.get(1),	crc.get(2), crc.get(3));
		
	}
	/*----------------------------------------------------------------------------------------*/

}
