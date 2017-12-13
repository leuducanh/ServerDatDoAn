package ws.service;

import java.sql.Date;
import java.util.List;

import ws.database.CuaHangDAO;
import ws.model.CuaHang;
import ws.model.ThongKe;

public class CuaHangService {
	private CuaHangDAO cuaHangDAO;
	
	

	public CuaHangService() {
		super();
		cuaHangDAO = new CuaHangDAO();
		// TODO Auto-generated constructor stub
	}
	
	public void capNhapCuaHang(CuaHang ch){
		cuaHangDAO.updateCuaHang(ch);
	}
	
	public List<CuaHang> layCuaHangBangTen(String ten){
		return cuaHangDAO.layCuaHangBangTen(ten);
	}
	
	public List<CuaHang> layCuaHangGanNhat(double lat,double lng){
		return cuaHangDAO.layCuaHangGanViTri(lat, lng);
	}
	
	public CuaHang themCuaHang(CuaHang ch,int idTaiKhoan){
		return cuaHangDAO.addCuaHang(ch, idTaiKhoan);
	}
	
	public void xoaCuaHang(int idCuaHang) throws Exception{
		cuaHangDAO.deleteCuaHang(idCuaHang);
	}
	
	public void suaCuaHang(CuaHang ch){
		cuaHangDAO.updateCuaHang(ch);
	}
	public ThongKe thongKeCuaHang(int id,Date date,int cheDo){
		System.out.println(id + " " + date.getDate() + " " + date.getMonth() + " " + date.getYear() + " " + cheDo);
		ThongKe tk = new ThongKe(cuaHangDAO.thongKeLoaiHang(id,date, cheDo), cuaHangDAO.thongKeMatHanng(id,date, cheDo));
		return tk;
	}
}
