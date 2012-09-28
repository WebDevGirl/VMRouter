// COMP 429 Virtual router class project
// Ursula, Mo, Kash

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Router {

	// class level variables
	
	// show the long menu
	static boolean longMenu = true;
	
	// reader for user input from console
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
	// router configuration settings
	static Settings vrmSettings = new Settings();

	
 	// constructor
 	public Router() {
 		
 	}
	/*----------------------------------------------------------------------------------------*/
	// program entry point
	public static void main(String[] args) {

		// local variables
		int mItem;
		
		// say hello
		print("Virtual router 1.0 is live\n\n");
		
		// main loop
		while(true) {
			mItem = getMenuItem();
			processMenuItem(mItem);
		}
	}

	/*----------------------------------------------------------------------------------------*/
	// output string to console
	private static void print(String s) {
		
		System.out.print(s);
	}
	/*----------------------------------------------------------------------------------------*/
	// output integer to console
	private static void print(int i) {
		
		System.out.print(i);
	}
	/*----------------------------------------------------------------------------------------*/
	// get user input
	private static int getMenuItem(){
		
		int maxItems = 8;
		int value = 0;
		boolean keepGoing = true;
		String inputString;
		// BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		while(keepGoing) {
			if(longMenu) {
				print("\n1. Show configuration\n");
				print("2. Save settings\n");
				print("3. Load last saved\n");
				print("4. Enter Wan/Lan MAC address\n");
				print("5. Enter IP address\n");
				print("6. Run the test method\n");
				print("7. Short menu\n");
				print("8. Quit\n\n");
				print(": ");
			}
			else {
				print("\n1=Show 2=Save 3=Load 4=MAC 5=IP 6=Test 7=Long 8=Quit : ");
			}
			try {
					inputString = console.readLine();
					value = Integer.parseInt(inputString);
					if(value >= 1 && value <= maxItems)
						keepGoing = false;
					else
						print("enter a value (1-7): ");
			}
			catch (IOException e) {
					print("IO error: " + e.getMessage() + "\n");
				}
			catch (NumberFormatException e) {
					print("Can't parse input: " + e.getMessage() + "\n");
				}							
		}
			
		return value;
	}
	/*----------------------------------------------------------------------------------------*/
	// process user input
	private static void processMenuItem(int com) {
		
		switch(com) {
		
			case 1 : printSettings();		break;
			case 2 : saveSettings();		break;
			case 3 : loadSettings();		break;
			case 4 : getMAC();				break;
			case 5 : getIP();				break;
			case 6 : testSomething();		break;
			case 7 : switchMenu();			break;
			case 8 : appQuit();				break;
			default:
		}
	}
	/*----------------------------------------------------------------------------------------*/
	// print router settings 
	private static void printSettings() {
	
		// system info
		String nameOS = "os.name";  
		String versionOS = "os.version";  
		String architectureOS = "os.arch";

		// print some OS info
		System.out.println("\nName of the OS: " + 
		System.getProperty(nameOS));
		System.out.println("Version of the OS: " + 
		System.getProperty(versionOS));
		System.out.println("Architecture of THe OS: " + 
		System.getProperty(architectureOS));
		
		// router settings
		print("\n");
		vrmSettings.printAll();	
	}
	/*----------------------------------------------------------------------------------------*/
	// save router settings 
	private static void saveSettings() {
	
		vrmSettings.saveSettings();
	}
	/*----------------------------------------------------------------------------------------*/
	// load router settings 
	private static void loadSettings() {
	
		vrmSettings.loadSettings();	
	}
	/*----------------------------------------------------------------------------------------*/
	// get MAC address from user
	private static void getMAC() {
	
		print("get MAC\n");
		vrmSettings.settingsChanged = true;	
	}
	/*----------------------------------------------------------------------------------------*/
	// get IP address from user 
	private static void getIP() {
	
		print("get IP\n");	
		vrmSettings.settingsChanged = true;
	}
	/*----------------------------------------------------------------------------------------*/
	// switch between long and short menu
	private static void switchMenu() {
	
		if(longMenu)
			longMenu = false;
		else
			longMenu = true;
	}
	/*----------------------------------------------------------------------------------------*/
	// exit application properly
	private static void appQuit() {
	
		String s = "";
		char c = 'x' ;
		boolean keepGoing = true;
	
		try {
			// confirm quit, force a y/n or Y/N answer
			if(vrmSettings.settingsChanged) {
				print("save settings before exit? (y/n): ");
				do {
					s = console.readLine();
					c = s.charAt(0);
					if(c == 'y' || c == 'Y') {
						vrmSettings.saveSettings();
						keepGoing = false;
					}
					else {
						if(c == 'n' || c == 'N')
							keepGoing = false;
						else
							print("press y or n: ");
					}
					
				} while(keepGoing);
			}
		}
		catch (IOException e) {
				// this is only to please the Java compiler
				// if we really get an IO exception life has become meaningless and we quit 
				print("IO error: " + e.getMessage() + "\n");
		}

		print("\nreleasing resources\n");
		print("good bye\n");
		try {console.close();} 
		catch (IOException e)
			{print("IO error: " + e.getMessage() + "\n");}		// nothing we can do here
		System.exit(0);											// exit application
	}
	/*----------------------------------------------------------------------------------------*/
	// use this method to test code 
	private static void testSomething() {
	
		try {
		
			print("enter a mac address in hex separated by :\n");	
			String s = console.readLine();
			MacAddress test = new MacAddress(s);
			print("hex:\t" + test.toHexString() + "\n");
			print("hex:r\t" + test.toHexStringRev() + "\n");
			print("bin:\t" + test.toBinString() + "\n");
			print("bin:r\t" + test.toBinStringRev() + "\n");
			
		}
		catch(Throwable e) {
			print("something went wrong with the test: " + e.getMessage() + "\n"); 
		}
	}
	/*----------------------------------------------------------------------------------------*/

}
