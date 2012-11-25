import java.util.ArrayList;

// RoutingTable Class
// the class returns an IP of the next 'hop'
// should only have one instance of this class for all threads to search

public class RoutingTable {
	private ArrayList<IPv4[]> routeTable = new ArrayList<IPv4[]>();
    private IPv4 defaultRoute;
    private IPv4 badIP = new IPv4("0.0.0.0");
	
	// constructor 
	public RoutingTable() {
		
	/*
	 	RoutingTable rt = new RoutingTable();
		rt.addDefaultRoute(new IPv4("127.0.0.1"));
		IPv4 route2 = rt.nextRoute(new IPv4("127.1.1.1"));
		System.out.println(route2.toString());
	 */
		
	}
	
	/*----------------------------------------------------------------------------------------*/
	public IPv4 nextRoute(IPv4 ip) {
		//System.out.println("RoutingTable: ... search table for gateway for IP: " + ip.toString()); 
		IPv4 gateway = null; 
		
		//-- Search for Gateway in Routing Table
		//---- TODO loop through routeTable;
		
		//-- Return Default if no ip found
		if (gateway == null) {
			//System.out.println("RoutingTable: ... no IP found, using default"); 
			gateway = defaultRoute; 
		}
		
		//-- Sanity Check that an IP address has been set
		if (gateway == null) {
			//TODO: Throw an exception up
			System.out.println("RoutingTable: errror no default route set"); 
		}
		
		return gateway;
	}
	/*----------------------------------------------------------------------------------------*/
	// Add the default route to Routing Table
	public void addDefaultRoute(String route) {
		IPv4 ipRoute = new IPv4(route);
		if (!ipRoute.equals(badIP)) {
			defaultRoute = new IPv4(route);
		} else {System.out.println("Bad IP, default route not set");}
		
	}
	/*----------------------------------------------------------------------------------------*/
	// Add route to Routing Table
	public void addRoute(IPv4 networkID, IPv4 gatewayIP) {
	
		 IPv4[] ipArr = new IPv4[2];
		 
		 ipArr[0] = networkID;
		 ipArr[1] = gatewayIP;
		 
		//-- Add record to table 
		routeTable.add(ipArr);
		
	}
	
	public void addRoute(String network, String gateway) {
		
		IPv4 networkID = new IPv4(network);
		IPv4 gatewayIP = new IPv4(gateway);
		
		
		if (networkID.equals(badIP) || networkID.IPSubBits == 0 || gatewayIP.equals(badIP)) {
			System.out.println("RT Error: Need a valid networkID with subnet mask and a valid gateway");
		} else {
			IPv4[] ipArr = new IPv4[2];
			 
			ipArr[0] = networkID;
			ipArr[1] = gatewayIP;
			 
			//-- Add record to table 
			routeTable.add(ipArr);					
		}
	}
	/*----------------------------------------------------------------------------------------*/
	// Remove route from the Routing Table 
	public void delRoute(String network, String gateway) {
		
		IPv4 networkID = new IPv4(network);
		IPv4 gatewayIP = new IPv4(gateway);
		int rtIndex = -1;
		
		
		if (networkID.equals(badIP) || networkID.IPSubBits == 0 || gatewayIP.equals(badIP)) {
			System.out.println("RT Error: Need a valid networkID with subnet mask and a valid gateway");
		} else {
			
			// Define IP Record
			IPv4[] ipArr = new IPv4[2];			 
			ipArr[0] = networkID;
			ipArr[1] = gatewayIP;
			
			// Search for record in Array List
			for (IPv4[] rtIPArr : routeTable) {
				if (rtIPArr[0].equals(ipArr[0]) && rtIPArr[1].equals(ipArr[1])) {
					rtIndex = routeTable.indexOf(rtIPArr);
				}
			} 
			 
			// Remove record if match found
			if(rtIndex > -1) {
				routeTable.remove(rtIndex);
			} else { 
				// No match found
				System.out.println("No record found in routing table");
			}
			
		}		
	}
	/*----------------------------------------------------------------------------------------*/
	// Display table in our console. 
	public void printTable() {
		   int size = routeTable.size();
		   
		   System.out.println("");
		   System.out.println("-------------------------------------------------------------------------------");
		   System.out.println("NetworkID   \tSubnet Mask   \tGateway");
		   System.out.println("-------------------------------------------------------------------------------");
		   	 
		   for (IPv4[] ipArr : routeTable) {
			   System.out.println(
					   String.format("%1$-" + 12 + "s", ipArr[0].toString()) +  // networkID (padded)
					   "\t  /" + ipArr[0].IPSubBits + 							// subnet mask
					   "      \t" + ipArr[1].toString());						// gateway 
		   }
		   
		   if(!defaultRoute.equals(badIP)) {
			   // Display default gateway if set
			   System.out.println("default       \t   --     \t" + defaultRoute.toString());
		   }
		   
		   
		  
		   System.out.println("_______________________________________________________________________________");
		      
			
		}
		/*----------------------------------------------------------------------------------------*/
		
}
