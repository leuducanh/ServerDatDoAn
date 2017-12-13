package ws.service;

import java.sql.SQLException;
import java.util.List;

import ws.database.LoaiHangDAO;
import ws.model.LoaiHang;


public class LoaiHangService {

	private LoaiHangDAO loaiHangDAO =  new LoaiHangDAO();
	
	public LoaiHangService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LoaiHang> layTatCaLoaiHang(int cuaHangId){
		return loaiHangDAO.getTatCaLoaiHang(cuaHangId);
	}
	
	public LoaiHang themLoaiHang(LoaiHang lh,int idCuaHang){
		return loaiHangDAO.addLoaiHang(lh, idCuaHang);
	}
	
	public void suaLoaiHang(LoaiHang lh) throws SQLException{
		loaiHangDAO.updateLoaiHang(lh);
	}
	
	public void xoaLoaiHang(int idLoaiHang) throws SQLException{
		loaiHangDAO.deleteLoaiHang(idLoaiHang);
	}
}
