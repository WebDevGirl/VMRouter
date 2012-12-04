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
	// reverse bits in each byte byteIn
	// return new array
	static public void reverseArrayBits(byte[] bytesIn) {
		
		for(int i = 0; i < bytesIn.length; i++)
			bytesIn[i] = reverseByte(bytesIn[i]);
		
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
		int ret;
		
//		reverseArrayBits(data);
//		invertFirst32(data);

		testCRC.reset();
		testCRC.update(data, 0, data.length);
		System.out.println("long value: " + Long.toHexString(testCRC.getValue()));	
		ret = (int)testCRC.getValue();
		
//		invertFirst32(data);
//		reverseArrayBits(data);

		return ret /*^ 0xffffffff*/;
		
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
		
		boolean ret = (crc.getInt(0) == getCRC(frame));						// compare CRCs

		return ret;
	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// test crc
    public static byte[] crcByLibrary(byte[] bytes)
    {
            java.util.zip.CRC32 crc = new java.util.zip.CRC32();
                    crc.update(bytes);
                    //System.out.println("CRC32 (via Java's library)     = " + Long.toHexString(x.getValue()));
           
                    byte[] check = java.math.BigInteger.valueOf(crc.getValue() ^ 0xffffffff).toByteArray();
                    byte[] finalcheck = new byte[4];
                    for (int x = 0; x < 4; x++)
                    {
                            if (check.length > x)
                            finalcheck[x] = check[check.length-1-x];
                           
                    }
                    return finalcheck;
    }

		/*----------------------------------------------------------------------------------------*/

}
