package Channel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import Message.MessageManager;
import Service.Peer;

public class Channel implements Runnable {
    MulticastSocket socket;
    InetAddress address;
    int port;

    public Channel(InetAddress address, int port) {
        this.address = address;
        this.port = port;

        try {
            this.socket = new MulticastSocket(port);
            this.socket.joinGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        byte[] buf = new byte[64000 + 1000]; 
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
				this.socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
            Peer.executor.execute(new MessageManager(packet));
            

        }
        // socket.leaveGroup(address);
        // socket.close();
    }

    public void sendMessage(byte[] message) {
        DatagramPacket packet = new DatagramPacket(message, message.length);
        try {
			this.socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
