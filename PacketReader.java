public class PacketReader {

 public String[] reader_0 (byte[] b) {
	String s = new String(b, "UTF-8");
	String[] data = s.split("roliroli", 0);
	return data;
 }

}