import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Protocol {
 static String URL;
 static int PORT;
 Protocol(String url, int port){
	URL = url;
	PORT = port;
 }

 public Packet recievePacket(InputStream is) {                   //is‚©‚ç‚Ìbyte—ñ‚ðPacket‚É‚µ‚Ä•Ô‚·
	ArrayList<Byte> result_buffer = new ArrayList<Byte>();
	byte[] temp_buffer = new byte[1024];
	byte id_b;
	byte kind_b;
	byte[] pass;
	byte[] data;
	try {
		int len;
		while((len = is.read(temp_buffer)) > 0) {
			for (int i = 0; i < len; i++) {
				result_buffer.add(temp_buffer[i]);
			}
		}
		id_b = result_buffer.get(0);
		kind_b = result_buffer.get(1);
		pass = new byte[8];
		for (int i = 0; i < 8; i++) {
			pass[i] = result_buffer.get(i+2);
		}
		data = new byte[result_buffer.size()-10];
		for (int i = 0; i < result_buffer.size()-10; i++) {
			data[i] = result_buffer.get(i+10);
		}
		is.close();
		return new Packet((int)id_b, (int)kind_b, pass, data);
 	} catch (IOException e) {
		System.out.println(e);
		pass = new byte[0];
		data = new byte[0];
		return new Packet(0, 0, pass, data);
	}
 }

 public boolean sendPacket(OutputStream os ,Packet p) {              //Packet‚ðbyte—ñ‚É‚µ‚Äos‚É—¬‚·
	byte id_b = (byte)p.getId();
	byte kind_b = (byte)p.getKind();
	byte[] pass = p.getPass();
	byte[] data = p.getData();
	byte[] packet = new byte[10 + data.length];
	packet[0] = id_b;
	packet[1] = kind_b;
	for (int i = 0; i < 8; i++) {
		packet[i+2] = pass[i];
	}
	for (int i = 0; i < data.length; i++) {
		packet[i+10] = data[i];
	}
	try {
		os.write(packet, 0, packet.length);
		return true;
	} catch (IOException e) {
		System.out.println(e);
		return false;
	}
 }

 public Packet request(Packet sendp) {
	Socket socket = null;
	InputStream is = null;
	OutputStream os = null;
	try{
	socket = new Socket(URL, PORT);
	is = socket.getInputStream();
	os = socket.getOutputStream();
	}catch(IOException ioe){
		System.out.println(ioe);
	}
	sendPacket(os, sendp);
	Packet recievep = recievePacket(is);
	try{
		socket.close();
	}catch(IOException ioe){
		System.out.println(ioe);
	}
	return recievep;
 }

}