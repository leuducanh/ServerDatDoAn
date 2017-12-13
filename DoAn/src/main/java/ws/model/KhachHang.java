package ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KhachHang {

	private int id;
	private String ten;
	private String dienThoai;
	
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public KhachHang(int id, String ten, String dienThoai) {
		super();
		this.id = id;
		this.ten = ten;
		this.dienThoai = dienThoai;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
}
