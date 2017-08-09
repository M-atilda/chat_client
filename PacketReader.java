import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class PacketReader {  //Packet‚Ìdata•”•ª(headerˆÈŠO)‚©‚çî•ñ‚ğ“Ç‚İo‚·
							 //input  : packet.getData()
							 //output : some data (depend on packet.kind)

/* private String byteToString (byte[] b) {
	int len = b.length;
	char[] c = new char[len];
	for (int i = 0; i < len; i++) {
		c[i] = (char)b[i];
	}
	String s = String.valueOf(c);
	return s;
 }*/
 private String byteToString(byte[] b){
	String str = "";
	try{
		str = new String(b, "UTF-8");
	}catch(UnsupportedEncodingException e){
		System.out.println(e);
	}
	return str;
 }

 private BufferedImage getImageFromBytes(byte[] bytes) throws IOException{
	ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	BufferedImage img = ImageIO.read(bais);
	return img;
 }


 //[0]talkdata(S->C)
 //data : (String[]){loginStatus, id, cmt, id, cmt, ... ,id, cmt}
 public String[] read_0 (byte[] data_b) {
	String data_s = byteToString(data_b);
//	System.out.println(data_s);
	String[] data = data_s.split("roliroli", 0);
	return data;
 }

 //[1]talkdata(C->S)
 //data : (String)comment
 public String read_1 (byte[] data_b) {
	String data = byteToString(data_b);
	return data;
 }

 //[2]image(S->C)
 //data : (BufferedImage)img
 public BufferedImage read_2 (byte[] data_b) {
	BufferedImage img;
	try{
		img = getImageFromBytes(data_b);
	}catch(IOException ioe){
		System.out.println(ioe);
		img = null;
	}
	return img;
 }

 //[4]image get check(S->C)
 //data : (char)TorF
 public char read_4 (byte[] data_b) {
	char data = (char)data_b[0];
	return data;
 }

 //[254]login(S->C)
 //data : (char)'T'or'F'
 public char read_254 (byte[] data_b) {
	char data = (char)data_b[0];
	return data;
 }

 //[255]login(C->S)
 //data : (String)password
 public String read_255 (byte[] data_b) {
	String data = byteToString(data_b);
	return data;
}

}