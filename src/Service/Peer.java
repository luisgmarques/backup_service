package Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import Channel.Channel;
import Channel.MCChannel;
import Channel.MDBChannel;
import Channel.MDRChannel;
import Storage.Storage;

public class Peer implements ServiceInterface {
    static String version;
	static String accessPoint;
    public static int id;
	public static MCChannel MCChannel;
	public static MDBChannel MDBChannel;
	public static MDRChannel MDRChannel;
	public static Storage storage;
	public static ScheduledThreadPoolExecutor executor;


    public static void main(String[] args) {

        Peer.version = args[0];
		Peer.accessPoint = args[2];
        Peer.id = Integer.parseInt(args[1]);

		Peer.MCChannel = new MCChannel(null, 1337);
		Peer.MDBChannel = new MDBChannel(null, 1337);
		Peer.MDRChannel = new MDRChannel(null, 1337);

		Peer.executor = new ScheduledThreadPoolExecutor(100);
		Peer.storage = loadStorage();

		// At Close Peer
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				saveStorage(Peer.storage);
			}
		});		
    }

	@Override
	public void backup(String filepath, int replicationDegree) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restore(String filepath) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String filepath) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reclaim(int space) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String state() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
    
    private static Storage loadStorage() {
		Storage storage = null;
		if (storage == null) {
			try {
				FileInputStream fis = new FileInputStream("<dir>");
				ObjectInputStream ois = new ObjectInputStream(fis);
				storage = (Storage) ois.readObject();
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return storage;
	}

	private static void saveStorage(Storage storage) {
		try {
			FileOutputStream fos = new FileOutputStream("<dir>");
			ObjectOutputStream oos  = new ObjectOutputStream(fos);
			oos.writeObject(storage);
			oos.close();
			fos.close();
			System.out.println("Storage saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
