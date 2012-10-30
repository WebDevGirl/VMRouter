import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;
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
		Random random = new Random();
		
		for(int i = 0; i < 6; i++) 
			randomMac[i] = (byte) random.nextInt(256) ;
		
		return randomMac;
	}
	/*----------------------------------------------------------------------------------------*/
	// return CRC32 as int
	public static int getCRC(byte[] data) {
		
		CRC32 testCRC = new CRC32();
		
		testCRC.reset();
		testCRC.update(data);
		// System.out.println("long value: " + testCRC.getValue());	
		return (int)testCRC.getValue();
		
	}
	/*----------------------------------------------------------------------------------------*/
	// return CRC32 as 4 byte hex string
	public static String getCRCStr(byte[] data) {
		
		int crc = getCRC(data);
		
		return String.format("%04x", crc);
		
	}
	/*----------------------------------------------------------------------------------------*/
	// invert first 32 bits of data[]
	public static void invertFirst32(byte[] data) {
		
		for(int i = 0; i < 4; i++)
			data[i] ^= 0xff;												// invert byte
		
	}
	/*----------------------------------------------------------------------------------------*/
	// check if frame is valid
	public static boolean frameValid(byte[] data) {
		
		byte[] frame;
		ByteBuffer crc = ByteBuffer.allocate(4);
		
		crc.put(Arrays.copyOfRange(data, data.length-4, data.length));		// extract CRC
		frame = Arrays.copyOfRange(data, 0, data.length-4);					// remove CRC from frame
		
		invertFirst32(frame);
		boolean ret = (crc.getInt(0) == getCRC(frame));						// compare CRCs
		invertFirst32(frame);

		return ret;
	}
	/*----------------------------------------------------------------------------------------*/

}
