import java.util.ArrayList;

// RoutingTable Class
// the class returns an IP of the next 'hop'
// should only have one instance of this class for all threads to search

public class RoutingTable {
	private ArrayList<String[]> routeTable = new ArrayList<String[]>();
    private IPv4 defaultRoute;
	
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
	public void addDefaultRoute(IPv4 route) {
		defaultRoute = route; 
	}
	
	/*----------------------------------------------------------------------------------------*/
	// Add route to Routing Table
	public void addRoute(IPv4 networkID, IPv4 gatewayIP) {
		//---- TODO
		
	}
	
	/*----------------------------------------------------------------------------------------*/
	// Remove route from the Routing Table 
	public void delRoute(IPv4 networkID, IPv4 gatewayIP) {
		//---- TODO
		
	}
	/*----------------------------------------------------------------------------------------*/
	
}
