import java.nio.ByteBuffer;

public class Router {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-- Main Router() --");
		
		MacAddress test1 = new MacAddress();
		MacAddress test2 = new MacAddress();
		ByteBuffer test3 = ByteBuffer.allocate( 1 );
		
		EthernetFrame eth = new EthernetFrame(test1, test2, 1, test3);
		
		
	}

}
