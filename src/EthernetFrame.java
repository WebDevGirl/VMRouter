import java.nio.ByteBuffer;

public class EthernetFrame {
	
	private MacAddress _src_addr; 
	private MacAddress _dst_addr;	
	private int _type_length;
	private ByteBuffer _data; 
	
	public EthernetFrame(MacAddress src, MacAddress dst, int type_length, ByteBuffer data) {
		System.out.println("... Creating new Ethernet Frame");
		_src_addr = src;
		_dst_addr = dst;
		_type_length = type_length;
		_data = data; 
		
	}

}
