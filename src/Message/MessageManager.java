package Message;

import java.net.DatagramPacket;

public class MessageManager implements Runnable {
	int offset;
	String header;
	byte[] body;

	public MessageManager(DatagramPacket packet) {
		this.header = new String(packet.getData(), 0, packet.getLength());
	}

	private int getOffset(DatagramPacket packet) {
		int offset = 0;
		for(int i = 0; i < packet.getLength(); i++) {
			
		}
		return offset;
	}

	private byte[] getBody(DatagramPacket packet) {
		byte[] body = null;


		return body;
	}

	@Override
	public void run() {
		// TODO Auto-generated method 
		String[] headerSplit = this.header.split(" ");
		switch(headerSplit[1]) {
			case "PUTCHUNK":
				
				break;
			
			default:
				
				break;
		}
		
	}
    
}
