package Message;

import Service.Peer;

public class ManageStored implements Runnable {
    String[] headerSplited;

    public ManageStored(String[] headerSplited) {
        this.headerSplited = headerSplited;
    }

	@Override
	public void run() {
        String chunkId = headerSplited[3] + '-' + headerSplited[4]; 
        int peerId = Integer.parseInt(headerSplited[2]);
        if(Peer.storage.addPeer(chunkId, peerId)) {
            Peer.storage.incStoredChunk(chunkId);
        }
	}
}
