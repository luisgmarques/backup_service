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
}
