package Storage;

public class Chunk {
    int number;
    int size;
    String fileId;
    byte[] data;
    int desiredRepDegree;

    public Chunk(int number, int size, String fileId, byte[] data, int desiredRepDegree) {
        this.number = number;
        this.size = data.length;
        this.fileId = fileId;
        this.desiredRepDegree = desiredRepDegree;
        this.data = data;
    }

    public String getChunkId() {
        return this.fileId + "-" + Integer.toString(this.number);
    }

    public int getRepDegree() {
        return this.desiredRepDegree;
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean equals(Object o) {
        Chunk c = (Chunk)o;
        if (this.getChunkId().equals(c.getChunkId())) {
            return true;
        }
        
        return false;
    }

    public int getSize() {
        return size;
    }
}
