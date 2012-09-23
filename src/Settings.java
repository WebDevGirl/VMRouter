// Setting class stores router configuration in the current directory
// file: config.vr
// once the settings are loaded the valuesAreValid flag is set to true
// if the user invalidates any setting the flag is reset to false

public class Settings {

	// class level variables
	
	public boolean valuesAreValid = false;		// false until valid values are loaded
	public boolean settingsChanged = false;		// true after change, false after save
	public String a = "new";
	public int b = 0;
	
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
		System.out.println(" MAC: ");
		System.out.println(" IP: ");
		System.out.println(" Subnet: ");
		System.out.println(" Gateway: ");
	
	}
	/*----------------------------------------------------------------------------------------*/



}