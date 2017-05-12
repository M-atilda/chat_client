public class Packet{
 private int id;
 private char kind;
 private byte[] data;

 Packet(int id, char kind, byte[] data) {
	this.id = id;
	this.kind = kind;
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

 public byte[] getData() {
	return this.data;
 }

 public void setData(byte[] data) {
	this.data = data;
 }
}