public class Packet{
 private int id;
 private char kind;
 private String pass;
 private byte[] data;

 Packet(int id, char kind, String pass, byte[] data) {
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

 public char getKind() {
	return this.kind;
 }

 public void setKind(char kind) {
	this.kind = kind;
 }

 public String getPass() {
	return this.pass;
}

 public void setPass(String pss) {
	this.pass = pass;
 }

 public byte[] getData() {
	return this.data;
 }

 public void setData(byte[] data) {
	this.data = data;
 }
}