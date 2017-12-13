package ws.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ThongKeMatHang {

	private String matHang;
	private int soLuong;
	public ThongKeMatHang(String matHang, int soLuong) {
		super();
		this.matHang = matHang;
		this.soLuong = soLuong;
	}
	public ThongKeMatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMatHang() {
		return matHang;
	}
	public void setMatHang(String matHang) {
		this.matHang = matHang;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
}
