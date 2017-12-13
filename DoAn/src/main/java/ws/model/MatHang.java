package ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MatHang {
	private int id;
	private String ten;
	private String moTa;
	private double gia;
	private String urlAnh;
	
	
	
	public MatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatHang(int id, String ten, String moTa, double gia, String urlAnh) {
		super();
		this.id = id;
		this.ten = ten;
		this.moTa = moTa;
		this.gia = gia;
		this.urlAnh = urlAnh;
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
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public String getUrlAnh() {
		return urlAnh;
	}
	public void setUrlAnh(String urlAnh) {
		this.urlAnh = urlAnh;
	}

	
	
}
