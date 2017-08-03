import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;

public class test2{
 public static String byteToString (byte[] b) {
	int len = b.length;
	char[] c = new char[len];
	for (int i = 0; i < len; i++) {
		c[i] = (char)b[i];
	}
	String s = String.valueOf(c);
	return s;
 }
 public static byte[] getBytesFromImage(BufferedImage img, String format) throws IOException{
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(img, format, baos);
	return baos.toByteArray();
 }
 public static BufferedImage getImageFromBytes(byte[] bytes) throws IOException{
	ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	BufferedImage img = ImageIO.read(bais);
	return img;
 }

 public static void main(String[] args){
	BufferedImage img = null;
	byte[] b;
	try{
		img = ImageIO.read(new File("./setting/picture/Mayoi.jpg"));
	} catch(Exception e){
		e.printStackTrace();
		img = null;
	}
	try{
		b = getBytesFromImage(img, "jpg");
	} catch(IOException ioe){
		System.out.println(ioe);
		b = new byte[0];
	}


	BufferedImage img2 = null;
	try{
		img2 = getImageFromBytes(b);
	} catch(IOException ioe){
		System.out.println(ioe);
	}
	try{
		ImageIO.write(img2, "jpg", new File("./setting/picture/test.jpg"));
	} catch(IOException ioe){
		System.out.println(ioe);
	}
 }

}