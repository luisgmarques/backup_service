package Message;

import Service.Peer;
import Storage.Chunk;

public class SendMessageThread implements Runnable {

    byte[] message;
    String channel;
    Chunk chunk;

    public SendMessageThread(byte[] message, String channel) {
        this.message = message;
        this.channel = channel;
    }

    public SendMessageThread(byte[] message, String channel, Chunk chunk) {
        this.message = message;
        this.channel = channel;
        this.chunk = chunk;
    }

	@Override
	public void run() {
        switch (channel) {
            case "MC":
                Peer.MCChannel.sendMessage(message);
                break;
            
            case "MDB":
                int attempt = 1; // first attempt
                int time = 1000; // 1 second
                int storedChunks = 0;
                do {
                    // Send message
                    Peer.MDBChannel.sendMessage(message);

                    try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
                    }
                    
                    // update conditions
                    storedChunks = Peer.storage.getStoredChunks(chunk.getChunkId());
                    attempt++;
                    time *= 2;
                } while(attempt < 5 || storedChunks < chunk.getRepDegree());
                break;

            case "MDR":
                Peer.MDRChannel.sendMessage(message);
                break;
        
            default:
                break;
        }        		
    }   
}
