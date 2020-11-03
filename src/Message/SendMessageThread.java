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
                
                break;
            
            case "MDB":
                int attempt = 1; // first attempt
                int time = 1000; // 1 second
                int actualRD = 0;
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
                    actualRD = Peer.storage.getActualRepDegree(chunk.getChunkId());
                    attempt++;
                    time *= 2;
                } while(attempt < 5 || actualRD < chunk.getRepDegree());

                break;

            case "MDR":

                break;
        
            default:
                break;
        }
        // TODO Auto-generated method stub
        
		
    }   
}
