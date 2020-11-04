package Storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import Service.Peer;

public class Storage implements Serializable {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    
    ArrayList<File> files;
    
    /**
     * Chunk - Chunks received
     */
    ArrayList<Chunk> receivedChunks;

    /**
     * String - fileId-chunkNo (chunkId)
     * Integer - replicationDegree
     */
    ConcurrentHashMap<String, Integer> storedChunks;

    /**
     * String - chunkId
     * ArrayList - peers that stored the chunk
     */
    ConcurrentHashMap<String, HashSet<Integer>> peersList;


    int totalSpace;
    int availableSpace;

    public Storage() {
        this.totalSpace = 64000 * 1000;
        this.availableSpace = this.totalSpace;
        this.files = new ArrayList<>();
        this.receivedChunks = new ArrayList<>();
        this.storedChunks = new ConcurrentHashMap<>();
        this.peersList = new ConcurrentHashMap<>();
    }

    public void addFile(File file) {
        files.add(file);
        // TODO
    }

    public void addChunk(Chunk chunk) {
        receivedChunks.add(chunk);
        availableSpace -= chunk.getSize();
        // Write to fileSystem
        try {
            File file = new File(Peer.id + "/CHUNKS/" + chunk.getChunkId());
			if (file.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(chunk.getData());
                fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public int getStoredChunks(String chunkId) {
        return storedChunks.get(chunkId);
    }

    public ArrayList<Chunk> getChunks() {
        return this.receivedChunks;
    }

    public boolean addPeer(String chunkId, Integer peerId) {
        if (!this.peersList.containsKey(chunkId)) {
            this.peersList.put(chunkId, new HashSet<Integer>());
        }
        return this.peersList.get(chunkId).add(peerId);
    }

    public boolean removePeer(String chunkId, Integer peerId) {
        return this.peersList.get(chunkId).remove(peerId);
    }

    public void incStoredChunk(String chunkId) {
        if (!this.storedChunks.containsKey(chunkId)) {
            this.storedChunks.put(chunkId, 0);
        }
        int stored = this.storedChunks.get(chunkId);
        stored += 1;
        this.storedChunks.put(chunkId, stored);
    }

    public void decStoredChunk(String chunkId) {
        int stored = this.storedChunks.get(chunkId);
        stored -= 1;
        this.storedChunks.put(chunkId, stored);
    }    
}
