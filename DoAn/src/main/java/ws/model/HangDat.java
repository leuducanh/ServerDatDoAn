package ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HangDat {

	private int id;
    private MatHang matHang;
    private int soluong;

    
    public HangDat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HangDat(int id, MatHang matHang, int soluong) {
		super();
		this.id = id;
		this.matHang = matHang;
		this.soluong = soluong;
	}

	public HangDat(MatHang matHang, int soluong) {
        this.matHang = matHang;
        this.soluong = soluong;
    }

    public MatHang getMatHang() {
        return matHang;
    }

    public void setMatHang(MatHang matHang) {
        this.matHang = matHang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

}
