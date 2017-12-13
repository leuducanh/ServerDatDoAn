package ws.service;

import java.sql.SQLException;
import java.util.List;

import ws.database.MatHangDAO;
import ws.model.MatHang;

public class MatHangService {

	private MatHangDAO matHangDAO;

	public MatHangService() {
		super();
		// TODO Auto-generated constructor stub
		matHangDAO = new MatHangDAO();
	}
	
	public MatHang themMatHang(MatHang mh,int idLoaiHang){
		return matHangDAO.addMatHang(mh, idLoaiHang);
	}
	
	
	public void suaMatHang(MatHang mh) throws SQLException{
		matHangDAO.updateMatHang(mh);
	}
	
	public void xoaMatHang(int idMatHang) throws SQLException{
		matHangDAO.deleteMatHang(idMatHang);
	}
}
