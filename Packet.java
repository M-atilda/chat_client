public class Packet{
 private int id;
 private int kind;
 private byte[] pass;
 private byte[] data;

 Packet(int id, int kind, byte[] pass, byte[] data) {
	this.id = id;
	this.kind = kind;
	this.pass = pass;
	this.data = data;
 }

 public int getId() {
	return this.id;
 }

 public void setId(int id) {
	this.id = id;
 }

 public int getKind() {
	return this.kind;
 }

 public void setKind(int kind) {
	this.kind = kind;
 }

 public byte[] getPass() {
	return this.pass;
}

 public void setPass(byte[] pass) {
	this.pass = pass;
 }

 public byte[] getData() {
	return this.data;
 }

 public void setData(byte[] data) {
	this.data = data;
 }
}