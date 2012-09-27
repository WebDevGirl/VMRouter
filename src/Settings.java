// Setting class stores router configuration in the current directory
// file: config.vr
// once the settings are loaded the valuesAreValid flag is set to true
// if the user invalidates any setting the flag is reset to false

public class Settings {

	// class level variables
	
	public boolean valuesAreValid = false;		// false until valid values are loaded
	public boolean settingsChanged = false;		// true after change, false after save
	public MacAddress wanMac = new MacAddress("01:df:10:ff:ad:af");
	public MacAddress lanMac = new MacAddress("df:3d:45:1a:1f:ae");
	
	/*----------------------------------------------------------------------------------------*/
	// constructor
	// attempt to load settings from file config.vr
	// otherwise print message and keep default values
	public Settings() {
	
		loadSettings();
	
	}
	/*----------------------------------------------------------------------------------------*/
	// load settings from current directory
	public void loadSettings() {
	
		System.out.println("settings loaded (not realy)");
		
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
		System.out.println("--------------------");
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
		System.out.println(" Gateway: ");
	
	}
	/*----------------------------------------------------------------------------------------*/



}