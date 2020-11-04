package Message;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import Service.Peer;
import Storage.Chunk;
import Util.Util;

public class ManagePutChunk implements Runnable {
    String[] headerSplited;
    byte[] body;

    public ManagePutChunk(String[] headerSplited, byte[] body) {
        this.body = body;
        this.headerSplited = headerSplited;
    }

	@Override
	public void run() {
        boolean isSender = false, hasChunk = false;
        int senderId = Integer.parseInt(headerSplited[2]);
        if (Peer.id == senderId) {
            isSender = true;
            System.out.println("> Peer is the sender");
        }
        int chunkNo = Integer.parseInt(headerSplited[4]);
        String fileId = headerSplited[3];
        int replicationDegree = Integer.parseInt(headerSplited[5]);
        
        Chunk newChunk = new Chunk(chunkNo, body.length, fileId, body, replicationDegree);
        
        for(Chunk chunk : Peer.storage.getChunks()) {
            if(chunk.equals(newChunk)) {
                hasChunk = true;
                System.out.println("> Peer already stored the chunk");
            }
        }

        if(!isSender && !hasChunk) {
            // Add chunk
            Peer.storage.addChunk(newChunk);
        }

        String stored = Peer.version + " STORED " + Peer.id + ' ' + chunkNo + Util.CRLF;

        Random random = new Random();
        Peer.executor.schedule(new SendMessageThread(stored.getBytes(), "MC"), random.nextInt(400) + 1, TimeUnit.MILLISECONDS);
	}
}
