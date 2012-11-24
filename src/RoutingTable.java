import java.util.ArrayList;

// RoutingTable Class
// the class returns an IP of the next 'hop'
// should only have one instance of this class for all threads to search

public class RoutingTable {
	private ArrayList<String[]> _RoutingTable = new ArrayList<String[]>();
    private IPv4 _defaultRoute = new IPv4 ("0.0.0.0");
	
	// constructor 
	public RoutingTable() {
		
	
		
	}
	
	/*----------------------------------------------------------------------------------------*/
	public IPv4 nextRoute() {
		IPv4 ip;
		
		//-- Search for Gateway in Routing Table
		//---- TODO
		
		//-- Return default route if none found
		ip = _defaultRoute; 
		
		//-- Sanity Check that an IP address has been set
		if (ip.equals(new IPv4("0.0.0.0"))) {
			System.out.println("RoutingTable: errror no default route set"); 
		}
		
		return ip;
	}
	/*----------------------------------------------------------------------------------------*/
	// Add the default route to Routing Table
	public void addDefaultRoute(IPv4 defaultRoute) {
		_defaultRoute = defaultRoute; 
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
