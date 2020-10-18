package Service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Util.Util;

public class Client {
    ServiceInterface peer;

    public static void main(String[] args) {
        int size = args.length;
        if (size < 2 || size > 4) {
            Util.logerr("USAGE: java Client ...");
            return;
        }
        String peerAccessPoint = args[0];
        String subProtocol = args[1];
        String filepath = "";
        int maxSize = 0;
        if (subProtocol == "RECLAIM")
            maxSize = Integer.parseInt(args[2]);
        else
            filepath = args[2];
        int replicationDegree = 0;
        if (size == 4)
            replicationDegree = Integer.parseInt(args[3]);

        Client client = new Client();

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            client.peer = (ServiceInterface) registry.lookup(peerAccessPoint);

        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        client.doWork(subProtocol, filepath, replicationDegree, maxSize);

    }

    private void backup(String filepath, int replicationDegree) throws Exception {
        peer.backup(filepath, replicationDegree);
    }

    private void restore(String filepath) throws Exception {
        peer.restore(filepath);

    }

    private void delete(String filepath) throws Exception {
        peer.delete(filepath);
    }

    private void reclaim(int storageSize) throws Exception {
        peer.reclaim(storageSize);
    }

    private void state() throws Exception {
        Util.log(peer.state());
    }

    private void doWork(String protocol, String filepath, int replicationDegree, int storageSize) {
        try {
            switch (protocol) {
                case "BACKUP":
                    backup(filepath, replicationDegree);
                    break;
                case "RESTORE":
                    restore(filepath);
                    break;
                case "DELETE":
                    delete(filepath);
                    break;
                case "RECLAIM":
                    reclaim(storageSize);
                    break;
                case "STATE":
                    state();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
