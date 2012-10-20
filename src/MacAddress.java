import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// MAC address class

public class MacAddress {

	// class level variables
	public boolean validAddress = false;		// verify there are six bytes in macArray
	private byte[] macArray = new byte[6];		// byte[0] is MSB of address
	private byte[] macArrayRev = new byte[6];	// bits reversed (not bytes)
	private boolean validMac = false;			// not implemented
	
	/*----------------------------------------------------------------------------------------*/
	// constructor accepts hex string in form "00:00:00:00:00:00"
	public MacAddress(String hexStr) {
		
		setHexMac(hexStr);
	}
	/*----------------------------------------------------------------------------------------*/
	// constructor accepts 6 byte array
	// any six bytes is validMac
	public MacAddress(byte[] arrayIn) {
		
		if(arrayIn.length != 6)
			System.out.print("need six bytes array for mac address ");
		else {
			for(int i = 0; i < 6; i++)
				macArray[i] = arrayIn[i];
			validMac = true;
			reverseBits();
		}	
	}
	/*----------------------------------------------------------------------------------------*/
	// set new hex mac address 
	public void setHexMac(String hexStr) {
		
		// regex for hex string validation
		// six groups separated by ':'
		// each group is 2 digit hex
		// upper or lower case
		hexStr = hexStr.trim();															// trim spaces
		
		if(hexStr.length() != 17) {
			{System.out.print("bad MAC\n");}// bad mac throw exception
			return;
		}
		
		Pattern macPattern = Pattern.compile("([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}");		// matching pattern
		Matcher macMatcher = macPattern.matcher(hexStr);								// pattern to be matched

		if(macMatcher.find()) {															// valid mac string
			String[] tokens = hexStr.split(":");										// split string into array
			for(int i = 0; i < 6; i++) {			
				macArray[i] = (byte)Integer.parseInt(tokens[i],16);						// convert hex to byte
			}
			reverseBits();
		}
		else
			{System.out.print("bad MAC\n");}// bad mac throw exception
	}

	/*----------------------------------------------------------------------------------------*/
	// any six bytes is validMac
	public void setMac(byte[] arrayIn) {
		
		if(arrayIn.length != 6)
			System.out.print("need six bytes array for mac address ");
		else {
			for(int i = 0; i < 6; i++)
				macArray[i] = arrayIn[i];
			validMac = true;
			reverseBits();
		}	
	}
	/*----------------------------------------------------------------------------------------*/
	// reverses the bits in each macAddress byte and stores result in macArrayRev[]
	private void reverseBits() {
		
		for(int i = 0; i < 6; i++) 
			macArrayRev[i] = (byte) (Integer.reverse((int) macArray[i]) >>> 24);
	}
	/*----------------------------------------------------------------------------------------*/
	// return hex formated MAC address string
	public String toHexString() {
		
		return String.format("%02x:%02x:%02x:%02x:%02x:%02x", macArray[0],macArray[1],macArray[2],macArray[3],macArray[4],macArray[5]);
	}
	/*----------------------------------------------------------------------------------------*/
	// return hex formated MAC address string with bits reversed
	public String toHexStringRev() {
		
		return String.format("%02x:%02x:%02x:%02x:%02x:%02x", macArrayRev[0],macArrayRev[1],macArrayRev[2],macArrayRev[3],macArrayRev[4],macArrayRev[5]);
	}
	/*----------------------------------------------------------------------------------------*/
	// return binary formated address string
	public String toBinString() {

		String s = "";
		
		for(int i = 0; i < 6; i++)
			s += String.format("%8s", Integer.toBinaryString(macArray[i] & 255)).replace(' ','0') + " ";
		
		return s;
	}
	/*----------------------------------------------------------------------------------------*/
	// return binary formated address string with bits reversed
	public String toBinStringRev() {
		
		String s = "";
		
		for(int i = 0; i < 6; i++)
			s += String.format("%8s", Integer.toBinaryString(macArrayRev[i] & 255)).replace(' ','0') + " ";

		return s;
	}
	/*----------------------------------------------------------------------------------------*/
	// return true if byte arrays are equal
	public boolean equals(byte[] a) {
		
		if(Arrays.equals(a, macArray))
			return true;
		else
			return false;
	}
	/*----------------------------------------------------------------------------------------*/
	// return true if byte arrays in are equal in a and this
	public boolean equals(MacAddress a) {
		
		if(Arrays.equals(a.macArray, macArray))
			return true;
		else
			return false;
	}
	/*----------------------------------------------------------------------------------------*/
}
