package Message;

import java.net.DatagramPacket;

import Service.Peer;

public class MessageManager implements Runnable {
	int offset;
	String header;
	byte[] body;

	public MessageManager(DatagramPacket packet) {
		this.offset = getOffset(packet);
		this.header = getHeader(packet);
		this.body = getBody(packet);
	}

	private int getOffset(DatagramPacket packet) {
		int offset = 0;
		String stringPacket = new String(packet.getData(), 0, packet.getLength());
		for(int i = 0; i < stringPacket.length(); i++) {
			if(
			stringPacket.charAt(i) == (char)0xD && 
			stringPacket.charAt(i+1) == (char)0xA && 
			stringPacket.charAt(i+2) == (char)0xD && 
			stringPacket.charAt(i+3) == (char)0xA
			) {
				offset = i+3;
				break;
			}
		}
		return offset;
	}

	private String getHeader(DatagramPacket packet) {
		String header = new String(packet.getData(), 0, offset);
		return header.trim();
	}

	private byte[] getBody(DatagramPacket packet) {
		byte[] body = new byte[64000];
		System.arraycopy(packet.getData(), offset+1, body, 0, body.length);
		return body;
	}

	@Override
	public void run() { 
		String[] headerSplited = this.header.split(" ");
		switch(headerSplited[1]) {
			case "PUTCHUNK":
				Peer.executor.execute(new ManagePutChunk(headerSplited, this.body));
				break;
			case "STORED":
				Peer.executor.execute(new ManageStored(headerSplited));
				break;

			case "GETCHUNK":
				
				break;
			case "CHUNK":

				break;

			case "DELETE":

				break;

			case "REMOVED":

				break;
			default:
				
				break;
		}	
	}
}
