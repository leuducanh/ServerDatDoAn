package ws.service;

import java.util.List;

import ws.database.CuaHangDAO;
import ws.database.DonHangDAO;
import ws.database.KhachHangDAO;
import ws.model.DonHang;
import ws.model.KhachHang;

public class KhachHangService {

	private KhachHangDAO khachHangDAO;
	
	 public KhachHangService() {
		// TODO Auto-generated constructor stub
		khachHangDAO = new KhachHangDAO();
	}
	
	public KhachHang layKhachHangTuTaiKhoanId(int id){
		return khachHangDAO.layKhachHangBangIdTaiKhoan(id);
	}
	
	public KhachHang themKhachHang(KhachHang kh,int idTaiKhoan){
		return khachHangDAO.addKhachHang(kh, idTaiKhoan);
	}
	

}
