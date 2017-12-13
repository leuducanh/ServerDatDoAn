package ws.service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import firebase.SendNotiService;
import ws.database.DonHangDAO;
import ws.database.HangDatDAO;
import ws.model.DonHang;

public class DonHangService {
	
	private DonHangDAO donHangDAO;
	private HangDatDAO hangDatDAO;
	private SendNotiService sendNotiService;
	
	public DonHangService() {
		super();
		// TODO Auto-generated constructor stub
		donHangDAO = new DonHangDAO();
		hangDatDAO = new HangDatDAO();
		sendNotiService = new SendNotiService();
	}

	public List<DonHang> layTatCaDonHangTuCuaHang(int idCuaHang){
		return donHangDAO.layDonHangBangIdCuaHang(idCuaHang);
	}
	
	public void themDonHangTuKhachHang(int idKhachHang,DonHang donHang){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		donHang.setNgayDat(new Date(now.getYear()-1900, now.getMonthValue()-1, now.getDayOfMonth()));
		donHang = donHangDAO.themDonHang(idKhachHang, donHang);
		for(int i = 0;i < donHang.getHangDatList().size();i++){
			hangDatDAO.addHangDat(donHang.getId(), donHang.getHangDatList().get(i));
		}
		
		sendNotiService.guiThongDiep("themdonhang", "Có đơn hàng mới từ " + donHang.getTenNguoiDat() + " \n" + dtf.format(now), donHang.getCuaHang(), donHang);
	}
	
	public void capNhapTrangThaiDonHang(DonHang donHang) throws SQLException{
		System.out.println(donHang.getTrangThai());
		donHangDAO.capNhapTrangThai(donHang);
		switch (donHang.getTrangThai()) {
		case "Phê duyệt":
			sendNotiService.guiThongDiep("pheduyet", "Đơn hàng từ người đặt: " + donHang.getTenNguoiDat() + "\nĐơn hàng đã được phê duyệt", donHang.getKhachHang(), donHang);
			break;
		case "Đang giao hàng":
			sendNotiService.guiThongDiep("danggiaohang", "Đơn hàng từ người đặt: " + donHang.getTenNguoiDat() + "\nĐang được vận chuyển", donHang.getKhachHang(), donHang);
			break;
		case "Đã xong":
			sendNotiService.guiThongDiep("daxong", "Đơn hàng từ người đặt: " + donHang.getTenNguoiDat() + "\nĐã giao hàng và thanh toán", donHang.getKhachHang(), donHang);
			break;
		default:
			break;
		}
		
		donHangDAO.capNhapTrangThai(donHang);
	}
	
	public void xoaDonHang(int id) throws SQLException{
		DonHang donHang = donHangDAO.layDonHangBangId(id);
		
		donHangDAO.xoaDonHang(id);
		sendNotiService.guiThongDiep("huypheduyet", "Đơn hàng từ người đặt: " + donHang.getTenNguoiDat() + "\nĐơn hàng đã bị hủy", donHang.getKhachHang(), donHang.getCuaHang());
	}
	
	public List<DonHang> layDonHangBangIdKhachHang(int idKhachHang){
		return donHangDAO.layDonHangBangIdKhachHang(idKhachHang);
	}
}
