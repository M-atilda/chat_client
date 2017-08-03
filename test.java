public class test{
 public static void main(String[] args){
	PacketReader pr = new PacketReader();
	PacketMaker pm = new PacketMaker();
	Packet p = pm.make_255(2, "croi");
	byte[] b = p.getData();
	for(int i = 0; i < b.length; i++){
		System.out.print(b[i] + " ");
	}
	System.out.println("id=" + p.getId());
	String s = pr.read_255(b);
	System.out.println(s);
 }
}