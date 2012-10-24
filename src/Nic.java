// Nic class inherits from thread. 

public class Nic extends Thread {
	
	// name of this instance
	String nicName = "Nic"; 
	
	// default MAC address for this Nic
	MacAddress myMac = new MacAddress();				// address is random
	
	/*----------------------------------------------------------------------------------------*/
	// simple constructor name and no MAC
	// use this only for testing
	Nic(String s) {
		
		nicName = s;
	}
	/*----------------------------------------------------------------------------------------*/
	// normal constructor accepts name and MacAddress
	Nic(String s, MacAddress mac) {
		
		nicName = s;
		myMac = mac;
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
