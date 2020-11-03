package Storage;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Storage implements Serializable {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    
	ArrayList<File> files;
    ArrayList<Chunk> receivedChunks;

    /**
     * String - fileId-chunkNo
     * Integer - replicationDegree
     */
    ConcurrentHashMap<String, Integer> actualChunkReplicationDegree;

    int totalSpace;
    int availableSpace;

    public Storage() {
        this.totalSpace = 64000 * 1000;
        this.availableSpace = this.totalSpace;
        this.files = new ArrayList<>();
        this.receivedChunks = new ArrayList<Chunk>();
        this.actualChunkReplicationDegree = new ConcurrentHashMap<String, Integer>();
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addChunk(Chunk chunk) {
        receivedChunks.add(chunk);
    }

    public int getActualRepDegree(String chunkId) {
        return actualChunkReplicationDegree.get(chunkId);
    }

}
