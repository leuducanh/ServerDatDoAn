package ws.service;

import java.sql.SQLException;

import ws.database.CuaHangDAO;
import ws.database.NguoiVanChuyenDAO;
import ws.database.TaiKhoanDAO;
import ws.model.CuaHang;
import ws.model.NguoiVanChuyen;
import ws.model.TaiKhoan;
import ws.service.sendsms.SpeedSMS;

public class TaiKhoanService {

	private SpeedSMS sms;
	private TaiKhoanDAO taiKhoanDAO;
	private CuaHangDAO cuaHangDAO;
	private NguoiVanChuyenDAO nguoiVanChuyenDAO;
	
	public TaiKhoanService() {
		super();
		sms = new SpeedSMS();
		taiKhoanDAO = new TaiKhoanDAO();
		cuaHangDAO = new CuaHangDAO();
		nguoiVanChuyenDAO = new NguoiVanChuyenDAO();
	}
	
	public TaiKhoan taoTaiKhoan(TaiKhoan tk,String token,String loaiNguoiDung) throws SQLException{
		return taiKhoanDAO.taoTaiKhoan(tk,token,loaiNguoiDung);
	}
	
	public String guiSmsMaXacNhan(int idTaiKhoan,String sdt) throws SQLException{
		String ma = taiKhoanDAO.timMaXacNhan(idTaiKhoan);
		if(!ma.equals("0")){
			sms.sendSMS(sdt, ma);
			return "Thanh cong";
		}
		return "That bai";
	}
	
	public String xacNhanMa(int idTaiKhoan,String maXacNhan) throws SQLException{
		boolean flag = taiKhoanDAO.ktraMaXacNhan(idTaiKhoan, maXacNhan);
		if(flag){
			return "Thanh cong";
		}
		return "That bai";
	}
	
	public String kiemTraTaiKhoan(TaiKhoan tk) throws SQLException{
		return taiKhoanDAO.kiemTraTaiKhoan(tk);
	}
	
	public CuaHang layCuaHangBangTaiKhoanId(int idTaiKhoan){
		return cuaHangDAO.layCuaHangBangTaiKhoanId(idTaiKhoan);
	}
	
	public NguoiVanChuyen layNguoiVanChuyenBangIdTaiKhoan(int idTaiKhoan){
		return nguoiVanChuyenDAO.getNguoiVanChuyenBangIdTaiKhoan(idTaiKhoan);
	}
	
	public void capNhapTokenFirebase(int id,String token){
		taiKhoanDAO.capNhapToken(id, token);
	}
	
	
}