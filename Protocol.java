import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Protocol {

 public Packet recievePacket(InputStream is) {
	ArrayList<Byte> result_buffer = new ArrayList<Byte>();
	byte[] temp_buffer = new byte[1024];
	byte[] ID = new byte[1];
	byte KIND;
	byte[] PASS;
	byte[] data;
	try {
		int len;
		while((len = is.read(temp_buffer)) > 0) {
			for (int i = 0; i < len; i++) {
				result_buffer.add(temp_buffer[i]);
			}
		}
		ID[0] = result_buffer.get(0);
		KIND = result_buffer.get(1);
		PASS = new byte[8];
		for (int i = 0; i < 8; i++) {
			PASS[i] = result_buffer.get(i+2);
		}
		data = new byte[result_buffer.size()];
		for (int i = 10; i < result_buffer.size(); i++) {
			data[i-10] = result_buffer.get(i);
		}
		is.close();
		return new Packet(ByteBuffer.wrap(ID).getInt(), (char)KIND, PASS.toString(), data);
 	} catch (IOException e) {
		System.out.println(e);
		PASS = new byte[0];
		data = new byte[0];
		return new Packet(0, '0', PASS.toString(), data);
	}
 }

 public boolean sendPacket(OutputStream os ,Packet p) {
	byte[] ID = ByteBuffer.allocate(4).putInt(p.getId()).array();
	byte KIND = (byte)p.getKind();
	byte[] PASS = p.getPass().getBytes();
	byte[] data = p.getData();
	byte[] packet = new byte[10 + data.length];
	try {
		os.write(packet, 0, packet.length);
		return true;
	} catch (IOException e) {
		System.out.println(e);
		return false;
	}
 }

}