import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;

public class test3{
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
	BufferedImage img1 = null;
	byte[] b1;
	try{
		img1 = ImageIO.read(new File("./setting/picture/Mayoi.jpg"));
	} catch(Exception e){
		e.printStackTrace();
		img1 = null;
	}
	try{
		b1 = getBytesFromImage(img1, "jpg");
	} catch(IOException ioe){
		System.out.println(ioe);
		b1 = new byte[0];
    }
 	BufferedImage img2 = null;
	byte[] b2;
	try{
		img2 = ImageIO.read(new File("./setting/picture/icon3.jpg"));
	} catch(Exception e){
		e.printStackTrace();
		img2 = null;
	}
	try{
		b2 = getBytesFromImage(img2, "jpg");
	} catch(IOException ioe){
		System.out.println(ioe);
		b2 = new byte[0];
    }
	int n = b1.length;
	int m = b2.length;
	int delta;
	int[][] ld = new int[n+1][m+1];
	for(int i = 0; i <= n; i++){
		ld[i][0] = i;
	}
	for(int j = 0; j <= m; j++){
		ld[0][j] = j;
	}
	for(int i = 1; i <= n; i++){
		for(int j = 1; j <= m; j++){
			if(b1[i-1] == b2[j-1]){
				delta = 1;
			} else {
				delta = 0;
			}
			ld[i][j] = Math.min(Math.min(ld[i-1][j]+1, ld[i][j-1]+1), ld[i-1][j-1]+1-delta);
		}
	}
	System.out.println(n);
	System.out.println(m);
	System.out.println(ld[n][m]);
 }
}