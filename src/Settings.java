// Setting class stores router configuration in the current directory
// file: config.vr
// once the settings are loaded the valuesAreValid flag is set to true
// if the user invalidates any setting the flag is reset to false

public class Settings {

	// class level variables
	
	public boolean valuesAreValid = false;		// false until valid values are loaded
	public boolean settingsChanged = false;		// true after change, false after save
	public MacAddress lanMac = new MacAddress("01:01:01:01:01:01");
	public MacAddress wanMac = new MacAddress("f0:f0:f0:f0:f0:f0");
	
	/*----------------------------------------------------------------------------------------*/
	// constructor
	// attempt to load settings from file config.vr
	public Settings(String fName) {
	
		loadSettings(fName);
	
	}
	/*----------------------------------------------------------------------------------------*/
	// load settings from current directory
	public void loadSettings() {
	
		System.out.println("settings loaded (comming soon)");
		
		// settings are saved
		settingsChanged = false;
		
	}
	/*----------------------------------------------------------------------------------------*/
	// load settings from current directory
	public void loadSettings(String fname) {
	
		System.out.println("settings loaded (comming soon)");
		
		// settings are saved
		settingsChanged = false;
		
	}
	/*----------------------------------------------------------------------------------------*/
	// save settings in current directory
	public void saveSettings() {
		
		System.out.println("settings saved (not realy)");
		
		// settings are saved
		settingsChanged = false;
		
	}
	/*----------------------------------------------------------------------------------------*/
	// print all settings
	public void printAll() {
	
		System.out.println("Router configuration");
		System.out.println("---------------------------------------------------------------------");
		System.out.println(" WanMAC: \t"    + wanMac.toHexString());
		System.out.println(" WanMAC:(r) \t" + wanMac.toHexStringRev());
		System.out.println(" LanMAC: \t"    + lanMac.toHexString());
		System.out.println(" LanMAC:(r) \t" + lanMac.toHexStringRev());
		
		System.out.println(" WanMAC: \t"    + wanMac.toBinString());
		System.out.println(" WanMAC:(r) \t" + wanMac.toBinStringRev());
		System.out.println(" LanMAC: \t"    + lanMac.toBinString());
		System.out.println(" LanMAC:(r) \t" + lanMac.toBinStringRev());
		System.out.println(" IP: ");
		System.out.println(" Subnet: ");
	}
	/*----------------------------------------------------------------------------------------*/



}