package ws.service;

import java.sql.SQLException;

import ws.database.NguoiVanChuyenDAO;
import ws.model.NguoiVanChuyen;

public class NguoiVanChuyenService {

	private NguoiVanChuyenDAO nguoiVanChuyenDAO;

	public NguoiVanChuyenService() {
		super();
		// TODO Auto-generated constructor stub
		nguoiVanChuyenDAO = new NguoiVanChuyenDAO();
	}
	
	
	
	public NguoiVanChuyen themNguoiVanChuyen(int id,NguoiVanChuyen nguoiVanChuyen){
		return nguoiVanChuyenDAO.addNguoiVanChuyen(id, nguoiVanChuyen);
	}
	
	public void capNhapTrangThai(int id,int trangThai) throws SQLException{
		nguoiVanChuyenDAO.capNhapTrangThaiNguoiVanChuyen(id, trangThai);
	}
	
	public void capNhapToaDo(int id,double lat,double lng) throws SQLException{
		nguoiVanChuyenDAO.capNhapViTriNguoiVanChuyen(id, lat, lng);
	}
}
