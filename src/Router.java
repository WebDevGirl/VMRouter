// COMP 429 Virtual router 
// Ursula, Moe, and Kash

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.util.zip.CRC32;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class Router {


	// port admin class w/ 24 ports max
	static PortAdmin portAdmin = new PortAdmin(24);

	// reader for user input from console
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
	// startup commands
	static String[] defaultCom = {   "port add 9000 111.212.323.44/16 1500", "connect add 9000 130.166.45.147:9000",
									 "port add 9001 111.212.323.44/16 1500", "connect add 9001 130.166.45.147:9001",
									 "port add 9002 111.212.323.44/16 1500", "connect add 9002 130.166.45.147:9002",
									 "port add 9003 111.212.323.44/16 1500", 
									 "port add 9004 111.212.323.44/16 1500", "connect add 9004 130.166.45.147:9004",
									 "port add 9005 111.212.323.44/16 1500", "connect add 9005 130.166.45.147:5000",
									 "port add 9006 111.212.323.44/16 1500", "connect add 9006 130.166.45.147:5000",
									 "usend 9000 junk","usend 9001 junk", "usend 9002 junk", "usend 9003 junk", 
									 "usend 9004 junk", "usend 9005 junk", "usend 9006 junk" 
	};
	
	
 	// constructor 
 	public Router() {
 		
 	}
	/*----------------------------------------------------------------------------------------*/
	// program entry point
	public static void main(String[] args) {

		// local variables
		String[] command;
		
		// say hello
		print("Virtual router 1.0\n");
		print("type help for list of commands\n\n");
		
		// run the built in startup commands
		for(int i = 0; i < defaultCom.length; i++) {
			doCommand(defaultCom[i].split(" "));
			try {Thread.sleep(50);} catch (InterruptedException e) {}
		}

		// try {Thread.sleep(100);} catch (InterruptedException e) {}
	
		// main loop
		while(true){
			
			command = getCommand();
			doCommand(command);
		}
	}
	/*----------------------------------------------------------------------------------------*/
	// output string to console
	private static void print(String s) {
		
		System.out.print(s);
	}
	/*----------------------------------------------------------------------------------------*/
	// get command from console
	private static String[] getCommand() {
		
		String inputString = null;
	
		try {Thread.sleep(100);} catch (InterruptedException e) {}
		System.out.print("> ");
		
		try { inputString = console.readLine();} 
		catch (IOException e) {
			System.out.println(e.toString());
		}
		
		inputString = inputString.trim();
		String[] ret = inputString.split(" ");
		return ret;
	}
	/*----------------------------------------------------------------------------------------*/
	// process command
	private static void doCommand(String[] command) {
		
		if(command[0].length() == 0)								// empty, do nothing 
			return;
		
		switch(command[0]){
		
		case "help" 	: showHelp();				break;
		case "config"	: showSettings();			break;
		case "route"	: route(command);			break;
		case "port"		: port(command);			break;
		case "connect"	: connect(command);			break;
		case "send"		: send(command);			break;
		case "usend"	: uSend(command);			break;
		case "include"	: loadSettings(command);	break;
		case "t"		: testSomething();			break;
		case "quit" 	: appQuit();				break;
		case "q" 		: appQuit();				break;
		default     	: System.out.println("unknown command: " + command[0]);
		}
	}
	/*----------------------------------------------------------------------------------------*/
	private static void showHelp(){
		
		 System.out.println("help                                                 ");
		 System.out.println("config                                               ");
		 System.out.println("include <file>                                       ");
		 System.out.println("port add <port number> <virtual IP/bits> <mtu>       ");
		 System.out.println("port del <port number>                               ");
		 System.out.println("connect add <local real port> <remote Real IP:port>  ");
		 System.out.println("connect del <port number>                            ");
		 System.out.println("route add <network ID/subnet bits> <virtual IP>      ");
		 System.out.println("route del <network ID/subnet bits> <virtual IP>      ");
		 System.out.println("send <SRC Virtual IP> <DST Virtual IP> <ID> <N bytes>");
		 System.out.println("usend <local port> <str>                             ");
		 System.out.println("quit                                                 ");
	}
	/*----------------------------------------------------------------------------------------*/
	private static void port(String[] command) {
		
		try {
			switch(command[1]){
			case "add" : portAdmin.add(Integer.parseInt(command[2]), command[3], Integer.parseInt(command[4]));
						 break;
			case "del" : portAdmin.remove(Integer.parseInt(command[2]));
						 break;
		    default    : throw new Exception();
			}
		}
		catch (Exception e){
			System.out.println("usage: port add <port number> <virtual IP/bits> <mtu>");
			System.out.println("usage: port del <port number>");
			System.out.println(e.toString());
		}
	}
	/*----------------------------------------------------------------------------------------*/
	private static void connect(String[] command) {
		
		try {
			switch(command[1]){
			case "add" : portAdmin.connect(Integer.parseInt(command[2]), command[3]);
						 break;
			case "del" : portAdmin.disconnect(Integer.parseInt(command[2]));
						 break;
			default    : throw new Exception();
			}
		}
		catch (Exception e){
			System.out.println("usage: connect add <local port> <remote IP:port>");
			System.out.println("usage: connect del <local port>");
			System.out.println(e.toString());
		}
	}
	/*----------------------------------------------------------------------------------------*/
	private static void send(String[] command) {
		
		try {
			System.out.println("command: " + command[0] + " " + command[1] + " " +
								command[2] + " " + command[3] + " " + command[4]);
		}
		catch (Exception e){
			System.out.println("usage: send <SRC Virtual IP> <DST Virtual IP> <ID> <N bytes>");
			System.out.println(e.toString());
		}
	}
	/*----------------------------------------------------------------------------------------*/
	private static void uSend(String[] command) {
		
		try {
			portAdmin.usend(Integer.parseInt(command[1]), command[2].getBytes());
		}
		catch (Exception e){
			System.out.println("usage: usend <port> <str>");
			System.out.println(e.toString());
		}
	}
	/*----------------------------------------------------------------------------------------*/
	private static void route(String[] command) {
		
		try {
			switch(command[1]){
			case "add" : System.out.println("command: " + command[0] + " " + command[1] + " " +
								command[2] + " " + command[3]);
						 break;
			case "del" : System.out.println("command: " + command[0] + " " + command[1] + " " +
						    command[2] + " " + command[3]);
						 break;
			default    : throw new Exception();
			}
		}
		catch (Exception e){
			System.out.println("usage: route add <network ID/subnet bits> <virtual IP>");
			System.out.println("usage: route del <network ID/subnet bits> <virtual IP>");
			System.out.println(e.toString());
		}
	}	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// print router settings 
	private static void showSettings() {
	
//		// system info
//		String nameOS = "os.name";  
//		String versionOS = "os.version";  
//		String architectureOS = "os.arch";
//
//		// print some OS info
//		System.out.println("\nName of the OS: " + 
//		System.getProperty(nameOS));
//		System.out.println("Version of the OS: " + 
//		System.getProperty(versionOS));
//		System.out.println("Architecture of THe OS: " + 
//		System.getProperty(architectureOS));
		
		System.out.println("\nMAC                   port  virtual IP      MTU    remote IP     : port  conn");
		System.out.println("_______________________________________________________________________________");
		System.out.println(portAdmin.getAllPortsConfig());
		
		// router settings

	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// load router settings 
	private static void loadSettings(String[] command) {
	
		String[] s = null;
		
		try {
			
			FileReader getCommand = new FileReader(command[1]);
			while(getCommand.hasNext()) { 
				s = (getCommand.readLine().trim()).split(" ");
				doCommand(s);
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			
			System.out.println("usage: include <filename>");
		}
		catch (FileNotFoundException e) {
			
			System.out.println("file not found: " + command[1]);
		}
	}
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// exit application properly
	private static void appQuit() {
	
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
	
		//String s = "Whenever digital data is stored or interfaced, data corruption might occur. Since the beginning of computer science";
	

		try {
			
//			byte[] ip = {(byte) 192, (byte) 168, 1, 0};
//			byte[] ip0 = {(byte) 192, (byte) 168, 1, 1};
		
			byte a1[] = {1,2,3,4,5,6};
			byte a2[] = {6,5,4,3,2,1};
			
			print("setup 2 macAddress classes\n");
			MacAddress m1 = new MacAddress(a1);
			MacAddress m2 = new MacAddress(a2);
			System.out.println(m1.toHexString());
			System.out.println(m2.toHexString());
			

			
			print("\nmaking ethernet frame\n");
			byte[] b = new byte[45];
			Arrays.fill(b, (byte)97);
			EthernetFrame ef1 = new EthernetFrame(m1,m2,(short)b.length,b);
			System.out.println(ef1.toString() + "\n");
			System.out.println(ef1.toHexString(ef1.toByteArray()));
			
			print("testing Ethernet Frame byte array constructor\n");
			EthernetFrame ef2 = new EthernetFrame(ef1.toByteArray());
			System.out.println(ef2.toString() + "\n");
			System.out.println(ef2.toHexString(ef1.toByteArray()) + "\n");
			System.out.println(VRMUtil.frameValid(ef2.toByteArray()) + "\n");	
			
//			print("test crc32 class\n");
//			print("enter a string: ");
//			String s = console.readLine();
//		
//			long t = 0;
//			t = VRMUtil.getCRC(s.getBytes());
//			System.out.println(t);
//			System.out.println(VRMUtil.getCRCStr(s.getBytes()));


			
		}
		catch(Throwable e) {
			print("something went wrong with the test: " + e.getMessage() + "\n"); 
		}
	}
	/*----------------------------------------------------------------------------------------*/

} 
