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
}
