package ws.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoaiHang {

	private int id;
	private String ten;
	private List<MatHang> dsMatHang;
	public LoaiHang(int id, String ten, List<MatHang> dsMatHang) {
		this.id = id;
		this.ten = ten;
		this.dsMatHang = dsMatHang;
	}
	public LoaiHang() {
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
	public List<MatHang> getDsMatHang() {
		return dsMatHang;
	}
	public void setDsMatHang(List<MatHang> dsMatHang) {
		this.dsMatHang = dsMatHang;
	}
}
