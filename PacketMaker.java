import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;

public class PacketMaker {  //id,pass‚Ædata‚©‚çPacketclass‚ð‚Â‚­‚é
							//input  : id , pass(loginŽž‚Í‚È‚µ), some data (depend on packet.kind)
							//output : Packet

 private byte[] StringToBytes (String s) {
	char[] c = s.toCharArray();
	int len = c.length;
	byte[] b = new byte[len];
	for (int i = 0; i < len; i++) {
		b[i] = (byte)c[i];
	}
	return b;
 }
 private byte[] getBytesFromImage(BufferedImage img, String format) throws IOException{
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(img, format, baos);
	return baos.toByteArray();
 }


 //[0]talkdata(S->C)
 //data : (String){loginStatus + id + cmt + id + cmt + ... + id + cmt}
 public Packet make_0 (int id, byte[] pass_b, String data_s) {
	byte[] data_b = StringToBytes(data_s);
	Packet packet = new Packet(id, 0, pass_b, data_b);
	return packet;
 }

 //[1]talkdata(C->S)
 //data : (String)comment
 public Packet make_1 (int id, byte[] pass_b, String cmt) {
	byte[] data_b = StringToBytes(cmt);
	Packet packet = new Packet(id, 1, pass_b, data_b);
	return packet;
 }

 //[2]image(S->C)
 //data : (BufferedImage)img
 public Packet make_2 (int id, byte[] pass_b, BufferedImage img) {
	byte[] data_b;
	try{
		data_b = getBytesFromImage(img, "jpg");
	} catch(IOException ioe){
		System.out.println(ioe);
		data_b = new byte[0];
	}
	Packet packet = new Packet(id, 2, pass_b, data_b);
	return packet;
 }

 //[3]image please(C->S)
 //data : (String)filename
 public Packet make_3 (int id, byte[] pass_b, String fname) {
	byte[] data_b = StringToBytes(fname);
	Packet packet = new Packet(id, 3, pass_b, data_b);
	return packet;
 }

 //[5]image(noname)(C->S)
 //data : (BufferedImage)img
 /*public Packet make_5 (int id, byte[] pass_b, BufferedImage img) {
	byte[] data_b;
	try{
		data_b = getBytesFromImage(img, "jpg");
	} catch(IOException ioe){
		System.out.println(ioe);
		data_b = new byte[0];
	}
	Packet packet = new Packet(id, 5, pass_b, data_b);
	return packet;
 }*/
 public Packet make_5 (int id, byte[] pass_b, byte[] img) {
	return new Packet(id, 5, pass_b, img);
 }

 //[254]login(S->C)
 //data : (char)'T'or'F' + (byte[]) passMadeByServer
 public Packet make_254 (int id, char TorF, byte[] pass_b) {
	byte[] data_b = new byte[1];
	data_b[0] = (byte)TorF;
	Packet packet = new Packet(id, 254, pass_b, data_b);
	return packet;
 }

 //[255]login(C->S)
 //data : (String)password
 public Packet make_255 (int id, String password) {
	byte[] data_b = StringToBytes(password);
	byte[] pass_b = {0, 0, 0, 0, 0, 0, 0 ,0};
	Packet packet = new Packet(id, 255, pass_b, data_b);
	return packet;
 }

}