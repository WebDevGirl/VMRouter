// Nic class inherits from thread. 

public class Nic extends Thread {
	
	// name of this instance
	String nicName; 
	
	// default MAC address for this Nic
	MacAddress myMac;									
	
	/*----------------------------------------------------------------------------------------*/
	// constructor name and no MAC
	// use this only for testing
	Nic(String s) {
		
		nicName = s;
		myMac = new MacAddress();						// random address
	}
	/*----------------------------------------------------------------------------------------*/
	// constructor accepts name and MacAddress hex string
	Nic(String s, String mac) {
		
		nicName = s;
		myMac = new MacAddress();
	}
	/*----------------------------------------------------------------------------------------*/
	// constructor accepts name and MacAddress 
	Nic(String s, MacAddress mac) {
		
		nicName = s;
		myMac = mac.clone();
	}
	/*----------------------------------------------------------------------------------------*/
	// start execution here
    public void run() {
    	
        System.out.println("created NIC: " + nicName);
        System.out.println("MAC address: " + myMac.toHexString());
        
        // do work here
    }
	/*----------------------------------------------------------------------------------------*/
}
