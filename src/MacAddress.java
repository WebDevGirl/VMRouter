import java.util.regex.Matcher;
import java.util.regex.Pattern;

// MAC address class

public class MacAddress {

	// class level variables
	public boolean validAddress = false;	// verify there are six bytes in macArray
	private byte[] macArray = new byte[6];	// byte[0] is MSB of address
	private boolean validMac = false;
	
	/*----------------------------------------------------------------------------------------*/
	// constructor accepts hex string in form "00:00:00:00:00:00"
	public MacAddress(String hexStr) {
		
		// regex for hex string validation
		// six groups separated by ':'
		// each group is 2 digit hex
		// upper or lower case
		hexStr = hexStr.trim();											// trim spaces 	
		Pattern macPattern = Pattern.compile("([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}");		// matching pattern
		Matcher macMatcher = macPattern.matcher(hexStr);				// pattern to be matched

		if(macMatcher.find()) {
			String[] tokens = hexStr.split(":");						// split string into array
			for(int i = 0; i < 6; i++) {			
				macArray[i] = (byte)(Integer.parseInt(tokens[i],16));	// convert hex to bytes
			}
		}
		else
		{System.out.print("bad");}// bad mac throw exception
	
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
		}	
		
	}
	/*----------------------------------------------------------------------------------------*/
	// return hex formated MAC address
	public String toString() {
		
		return String.format("%02x:%02x:%02x:%02x:%02x:%02x", macArray[0],macArray[1],macArray[2],macArray[3],macArray[4],macArray[5]);
	}
	/*----------------------------------------------------------------------------------------*/
}
