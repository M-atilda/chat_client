import java.io.UnsupportedEncodingException;

public class test5{

 private static String byteToString(byte[] b){
	String str = "";
	try{
		str = new String(b, "UTF-8");
	}catch(UnsupportedEncodingException e){
		System.out.println(e);
	}
	return str;
 }

 private static byte[] StringToBytes(String str){
	byte[] bytes;
	try{
		bytes = str.getBytes("UTF-8");
	}catch(UnsupportedEncodingException e){
		System.out.println(e);
		bytes = new byte[0];
	}
	return bytes;
 }

 public static void main(String[] args){
	String str0 = new java.util.Scanner(System.in).nextLine();
	byte[] b = StringToBytes(str0);
	String str1 = byteToString(b);
	System.out.println(str1);
 }
}