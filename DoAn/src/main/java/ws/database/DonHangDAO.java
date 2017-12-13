package ws.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ws.model.CuaHang;
import ws.model.DonHang;
import ws.model.KhachHang;
import ws.model.MatHang;
import ws.model.NguoiVanChuyen;

public class DonHangDAO extends DAO{

	private HangDatDAO hangDatDAO;
	private CuaHangDAO cuaHangDAO;
	private KhachHangDAO khachHangDAO;
	private NguoiVanChuyenDAO nguoiVanChuyenDAO;

	public DonHangDAO() {
		super();
		// TODO Auto-generated constructor stub
		hangDatDAO = new HangDatDAO();
		khachHangDAO = new KhachHangDAO();
		nguoiVanChuyenDAO = new NguoiVanChuyenDAO();
		cuaHangDAO = new CuaHangDAO();
	}
	
	public List<DonHang> layDonHangBangIdCuaHang(int idCuaHang){
		String sql = "Select * from don_hang where id_cuahang=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		CuaHang ch = null;
		List<DonHang> lst = new ArrayList<>();
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, idCuaHang);
			rs = pre.executeQuery();
			
			while(rs.next()){
				Date d = rs.getDate(13);
				DonHang dh = new DonHang(
						rs.getInt(1),
						khachHangDAO.layKhachHangBangId(rs.getInt(2)),
						cuaHangDAO.layCuaHangBangId(rs.getInt(3)),
						rs.getDouble(4),
						rs.getDouble(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						null,
						rs.getString(14),
						hangDatDAO.getTatCaHangDat(rs.getInt(1)));
						if(d!=null){
							dh.setNgay(d.getDate() + "/" + (d.getMonth() + 1) + "/" + (d.getYear() + 1900));
						}
				lst.add(dh);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return lst;
	}
	
	public DonHang layDonHangBangId(int id){
		String sql = "Select * from don_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		CuaHang ch = null;
		List<DonHang> lst = new ArrayList<>();
		DonHang dh = new DonHang();
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			
			while(rs.next()){
				Date d = rs.getDate(13);
				dh = new DonHang(
						rs.getInt(1),
						khachHangDAO.layKhachHangBangId(rs.getInt(2)),
						cuaHangDAO.layCuaHangBangId(rs.getInt(3)),
						rs.getDouble(4),
						rs.getDouble(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						d,
						rs.getString(14),
						hangDatDAO.getTatCaHangDat(rs.getInt(1)));
				if(d!=null){
					dh.setNgay(d.getDate() + "/" + (d.getMonth() + 1) + "/" + (d.getYear() + 1900));
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return dh;
	}
	
	public DonHang  themDonHang(int idKhachHang,DonHang dh){
		String sql = "Insert into don_hang (id_khachhang,id_cuahang,lat,lng,diachi_giaohang,gia_vanchuyen,gia_hang,trangthai,tennguoidat,ghichu,sdt,ngay_dat) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;

		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, idKhachHang);
			pre.setInt(2, dh.getCuaHang().getId());
			pre.setDouble(3, dh.getLat());
			pre.setDouble(4, dh.getLng());
			pre.setString(5,dh.getDiaChi());
			pre.setInt(6,dh.getGiaVanChuyen());
			pre.setInt(7, dh.getGiaHang());
			pre.setString(8, dh.getTrangThai());
			pre.setString(9, dh.getTenNguoiDat());
			pre.setString(10, dh.getGhiChu());
			pre.setString(11, dh.getSdt());
			pre.setDate(12, dh.getNgayDat());

			pre.executeUpdate();
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM don_hang");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);

			dh.setId(lastid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);

		return dh;
	} 
	
	public void capNhapTrangThai(DonHang donHang) throws SQLException{
			String sql = "update don_hang set trangthai=?,sdt_shipper=?,gia_vanchuyen=? where id = ?";
			PreparedStatement pre = null;
			PreparedStatement pre1 = null; 
			
				pre = con.prepareStatement(sql);
				pre.setString(1, donHang.getTrangThai());
				pre.setString(2, donHang.getSdtShip());
				pre.setInt(3, donHang.getGiaVanChuyen());
				pre.setInt(4, donHang.getId());
				
				pre.executeUpdate();
			close(pre, null);
	}
	
	public void xoaDonHang(int id) throws SQLException{
		hangDatDAO.xoaHangDat(id);
		String sql = "delete from don_hang where id = " + id;
		PreparedStatement pre = con.prepareStatement(sql);
		pre.executeUpdate();
	}
	
	public List<DonHang> layDonHangBangIdKhachHang(int idKhachHang){
		String sql = "Select * from don_hang where id_khachhang=? and trangthai <> 'Đã xong' and trangthai <> 'Hủy bỏ'";
		PreparedStatement pre = null;
		ResultSet rs = null;
		CuaHang ch = null;
		List<DonHang> lst = new ArrayList<>();
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, idKhachHang);
			rs = pre.executeQuery();
			KhachHang kh = khachHangDAO.layKhachHangBangId(idKhachHang);
			while(rs.next()){
				Date d = rs.getDate(13);
				DonHang dh = new DonHang(
						rs.getInt(1),
						kh,
						cuaHangDAO.layCuaHangBangId(rs.getInt(3)),
						rs.getDouble(4),
						rs.getDouble(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						null,
						rs.getString(14),
						hangDatDAO.getTatCaHangDat(rs.getInt(1)));
						if(d!=null){
							dh.setNgay(d.getDate() + "/" + (d.getMonth() + 1) + "/" + (d.getYear() + 1900));
						}
				lst.add(dh);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return lst;
	}
	
}
