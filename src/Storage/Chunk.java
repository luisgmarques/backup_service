package Storage;

import java.util.HashSet;
import java.util.Set;

public class Chunk {
    int number;
    int size;
    String fileId;
    byte[] data;
    int desiredRepDegree;
    Set<Integer> mirrors;

    public Chunk(int number, int size, String fileId, byte[] data, int desiredRepDegree) {
        this.number = number;
        this.size = data.length;
        this.fileId = fileId;
        this.desiredRepDegree = desiredRepDegree;
        this.data = data;
        this.mirrors = new HashSet<>();
    }

    public void addMirror(int peerId) {
        Integer iPeerId = Integer.valueOf(peerId);
        this.mirrors.add(iPeerId);
    }

    public void removeMirror(int peerId) {
        Integer iPeerId = Integer.valueOf(peerId);
        this.mirrors.remove(iPeerId);
    }

    public String getChunkId() {
        String id = this.fileId + "-" + Integer.toString(this.number);
        return id;
    }

    public int getRepDegree() {
        return this.desiredRepDegree;
    }
}
