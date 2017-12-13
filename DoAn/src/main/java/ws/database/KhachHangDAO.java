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

public class KhachHangDAO extends DAO{

	public KhachHang layKhachHangBangIdTaiKhoan(int id){
		String sql = "Select * from khach_hang where id_taikhoan=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		KhachHang kh = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			
			while(rs.next()){
				kh = new KhachHang(rs.getInt(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return kh;
	}
	
	public KhachHang layKhachHangBangId(int id){
		String sql = "Select * from khach_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		KhachHang kh = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			
			while(rs.next()){
				kh = new KhachHang(rs.getInt(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return kh;
	}
	
	public int layIdTaiKhoanBangIdKhachHang(int id){
		String sql = "Select id_taikhoan from khach_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		KhachHang kh = null;
		int idTaiKhoan = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			
			while(rs.next()){
				idTaiKhoan = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return idTaiKhoan;
	}
	
	public KhachHang  addKhachHang(KhachHang kh,int idTaiKhoan){
		String sql = "Insert into khach_hang (ten,dienthoai,id_taikhoan) values(?,?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,kh.getTen());
			pre.setString(2, kh.getDienThoai());
			pre.setInt(3, idTaiKhoan);

			pre.executeUpdate();
			
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM khach_hang");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);
			kh.setId(lastid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);
		return kh;
	} 
}
