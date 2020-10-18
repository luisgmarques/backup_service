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
    ArrayList<Chunk> chunks;

    public Storage() {

    }

}
