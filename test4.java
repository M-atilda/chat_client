import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class test4{
 public static void main(String[] args){
	Protocol prot = new Protocol("192.168.8.105", 1707);
	PacketMaker pm = new PacketMaker();
	PacketReader pr = new PacketReader();
	Packet sendpacket;
	Packet recievepacket;
	byte[][] pass_b = new byte[7][8];
	boolean[] login_ok = new boolean[7];
	for(int i = 0; i < 7; i++){
		login_ok[i] = false;
	}
	
	while (true){
		int id = new java.util.Scanner(System.in).nextInt();
		if(!(login_ok[id])){
			System.out.print("password : ");
			String pass = new java.util.Scanner(System.in).nextLine();
			sendpacket = pm.make_255(id, pass);
			recievepacket = prot.request(sendpacket);
			if(pr.read_254(recievepacket.getData()) == 'T'){
				login_ok[id] = true;
				System.out.println("login ok.");
				byte[] b = recievepacket.getPass();
				for(int i = 0; i < 8; i++){
					pass_b[id][i] = b[i];
				}
				sendpacket = pm.make_3(id, pass_b[id], "icon0.jpg");
				recievepacket = prot.request(sendpacket);
				BufferedImage img = pr.read_2(recievepacket.getData());
				try{
					ImageIO.write(img, "jpg", new File("./setting/picture/test.jpg"));
				} catch(IOException ioe){
					System.out.println(ioe);
				}
			} else {
				System.out.println("password is wrong.");
			}
		}else{
/*			System.out.print("comment : ");
			String comm = new java.util.Scanner(System.in).nextLine();
			sendpacket = pm.make_1(id, pass_b[id], comm);
			recievepacket = prot.request(sendpacket);
			String[] s = pr.read_0(recievepacket.getData());
			for(int i = 0; i < s.length; i++){
				System.out.print(s[i] + " ");
			}
			System.out.println();*/
			System.out.print("image : ");
			String imgname = new java.util.Scanner(System.in).nextLine();
		/*	BufferedImage img;
			try{
				img = ImageIO.read(new File("./setting/picture/" + imgname));
			} catch(Exception e){
				e.printStackTrace();
				img = null;
			}*/
			ArrayList<Byte> result_buffer = new ArrayList<Byte>();
			try{
				FileInputStream fis = new FileInputStream(new File("./setting/picture/" + imgname));
				int readlength;
				byte[] buffer = new byte[1024];
				do{
					readlength = fis.read(buffer);
					for(int i = 0; i < readlength; i++){
						result_buffer.add(buffer[i]);
					}
				}while(readlength == 1024);
				fis.close();
			}catch(IOException ioe){
				System.out.println(ioe);
			}
			byte[] imgdata = new byte[result_buffer.size()];
			for(int i = 0; i < result_buffer.size(); i++){
				imgdata[i] = result_buffer.get(i);
			}

			sendpacket = pm.make_5(id, pass_b[id], imgdata);
			recievepacket = prot.request(sendpacket);
			System.out.println(recievepacket.getKind());
			if(pr.read_4(recievepacket.getData()) == 'T') {
				System.out.println("‘—‚ê‚½");
			}else{
				System.out.println("‚¾‚ß");
			}
		}
	}
	
 }
}