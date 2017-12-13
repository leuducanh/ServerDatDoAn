package ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaiKhoan {

	private int id;
	private String ten;
	private String matkhau;

	public TaiKhoan(int id, String ten, String matkhau) {
		super();
		this.id = id;
		this.ten = ten;
		this.matkhau = matkhau;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
}
