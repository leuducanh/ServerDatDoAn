package ws.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ws.model.MatHang;
import ws.model.NguoiVanChuyen;

public class NguoiVanChuyenDAO extends DAO{

	public List<NguoiVanChuyen> getNguoiVanChuyenOnline(double lat,double lng){
		NguoiVanChuyen nguoiVanChuyen = null;
		String sql = "SELECT *, ( 6371 * acos( cos( radians("+lat+") ) * cos( radians( lat ) ) * cos( radians(lng ) - radians("+lng+") ) + sin( radians("+lat+") ) * sin( radians(lat ) ) ) ) AS distance FROM cua_hang where trangthai=onchuanhandon HAVING distance < 1 ORDER BY distance LIMIT 0 , 20";
		List<NguoiVanChuyen> nguoiVanChuyens = new ArrayList<>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				nguoiVanChuyen = new NguoiVanChuyen(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6));
				nguoiVanChuyens.add(nguoiVanChuyen);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(pre, rs);
		return nguoiVanChuyens;
	}
	
	public NguoiVanChuyen getNguoiVanChuyenBangId(int id){
		NguoiVanChuyen nguoiVanChuyen = null;
		String sql = "Select * from nguoi_van_chuyen where id = " + id;
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				nguoiVanChuyen = new NguoiVanChuyen(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(pre, rs);
		return nguoiVanChuyen;
	}
	
	public NguoiVanChuyen getNguoiVanChuyenBangIdTaiKhoan(int idTaiKhoan){
		NguoiVanChuyen nguoiVanChuyen = null;
		String sql = "Select * from nguoi_van_chuyen where id_taikhoan = " + idTaiKhoan;
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				nguoiVanChuyen = new NguoiVanChuyen(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(pre, rs);
		return nguoiVanChuyen;
	}
	
	public void  capNhapNguoiVanChuyen(NguoiVanChuyen nguoiVanChuyen) throws SQLException{
		String sql = "update nguoi_van_chuyen set bien_so,dienthoai where id = ?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null; 
		
			pre = con.prepareStatement(sql);
			pre.setString(1, nguoiVanChuyen.getBienSo());
			pre.setString(2, nguoiVanChuyen.getDienThoai());
			pre.setInt(3, nguoiVanChuyen.getId());
			
			pre.executeUpdate();
		
		close(pre, null);
	}
	
	public void  capNhapTrangThaiNguoiVanChuyen(int id,int trangThai) throws SQLException{
		String sql = "update nguoi_van_chuyen set trangthai = ? where id = ?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null; 
		
			pre = con.prepareStatement(sql);
			pre.setInt(1, trangThai);
			pre.setInt(2, id);
			
			pre.executeUpdate();
		
		close(pre, null);
	}
	
	public void capNhapViTriNguoiVanChuyen(int id,double lat,double lng) throws SQLException{
		String sql = "update nguoi_van_chuyen set lat=?,lng=? where id = ?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null; 
		
			pre = con.prepareStatement(sql);
			pre.setDouble(1, lat);
			pre.setDouble(2, lng);
			pre.setInt(3, id);
			
			pre.executeUpdate();
		
		close(pre, null);
	}
	
	
	public NguoiVanChuyen  addNguoiVanChuyen(int idTaiKhoan,NguoiVanChuyen nguoiVanChuyen){
		String sql = "Insert into nguoi_van_chuyen (bien_so,dienthoai,trangthai,id_taikhoan) values(?,?,?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, nguoiVanChuyen.getBienSo());
			pre.setString(2, nguoiVanChuyen.getDienThoai());
			pre.setInt(3, nguoiVanChuyen.getTrangThai());
			pre.setInt(4, idTaiKhoan);

			pre.executeUpdate();
			
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM nguoi_van_chuyen");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);
			nguoiVanChuyen.setId(lastid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);
		return nguoiVanChuyen;
	}

}
