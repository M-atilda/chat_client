import java.io.InputStream;
import java.util.ArrayList;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Protocol {

 public Packet recievePacket(InputStream is) {
	ArrayList<Byte> result_buffer = new ArrayList<Byte>();
	byte[] temp_buffer = new byte[1024];
	byte[] data;

	int len;
	while((len = is.read(temp_buffer)) > 0) {
		for (int i = 0; i < len; i++) {
			result_buffer.add(temp_buffer[i]);
		}
	}
	data = new byte[result_buffer.size()];
	for (int i = 2; i < result_buffer.size(); i++) {
		data[i-2] = result_buffer.get(i);
	}
	return new Packet(result_buffer.get(0), result_buffer.get(1), data);
 }

 public void sendPacket(OutputStream os ,Packet p) {
	
 }

}