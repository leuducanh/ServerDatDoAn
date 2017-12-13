package ws.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ws.model.MatHang;


public class MatHangDAO extends DAO{

	public List<MatHang> getMatHang(int loaiHang){
		ArrayList<MatHang> list = new ArrayList<>();
		
		String sql = "Select * from mat_hang where id_loaihang = " + loaiHang;
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				list.add(new MatHang(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(pre, rs);
		return list;
	}
	
	public MatHang getMatHangBangId(int id){
		ArrayList<MatHang> list = new ArrayList<>();
		
		String sql = "Select * from mat_hang where id = " + id;
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		MatHang mh = null;
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				mh = new MatHang(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(pre, rs);
		return mh;
	}
	
	public MatHang  addMatHang(MatHang matHang,int idLoaiHang){
		String sql = "Insert into mat_hang (ten,mota,gia,ten_anh,id_loaihang) values(?,?,?,?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, matHang.getTen());
			pre.setString(2, matHang.getMoTa());
			pre.setDouble(3, matHang.getGia());
			pre.setString(4, matHang.getUrlAnh());
			pre.setInt(5, idLoaiHang);

			pre.executeUpdate();
			
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM mat_hang");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);
			matHang.setId(lastid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);
		return matHang;
	}
	
	public void updateMatHang(MatHang mh) throws SQLException{
		String sql = "update mat_hang set ten=?,mota=?,gia=?,ten_anh=? where id = ?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null; 
		
		
			pre = con.prepareStatement(sql);
			pre.setString(1, mh.getTen());
			pre.setString(2, mh.getMoTa());
			pre.setDouble(3, mh.getGia());
			pre.setString(4, mh.getUrlAnh());
			pre.setInt(5, mh.getId());
			
			pre.executeUpdate();
			
		
		
		close(pre, null);
	}
	
	public void deleteMatHang(int idMatHang) throws SQLException{
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		String sql = "select * from hang_dat where id_mathang = ?" ;
		pre = con.prepareStatement(sql);
		pre.setInt(1, idMatHang);
		rs = pre.executeQuery();
		
		boolean flag = rs.next();
		
		if(flag){
			sql = "update mat_hang set id_loaihang = ? where id = ?";
			pre = con.prepareStatement(sql);
			pre.setNull(1, Types.INTEGER);
			pre.setInt(2, idMatHang);
			pre.executeUpdate();
			
		}else{
			sql = "delete from mat_hang where id = " + idMatHang;
			pre = con.prepareStatement(sql);
			pre.executeUpdate();
		}
		
		close(pre, null);
	}
	
	public String updatePicture(String tenAnh,int id) throws SQLException{
		String sql  =  "select * from mat_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		pre = con.prepareStatement(sql);
		pre.setInt(1, id);
		rs = pre.executeQuery();
		
		String ten = "";
		
		while(rs.next()){
			ten = rs.getString(5);
		}
		
		sql = "update mat_hang set ten_anh=? where id = ?";
			pre = con.prepareStatement(sql);
			pre.setString(1,tenAnh);
			pre.setInt(2,id);
			
			pre.executeUpdate();
		
		close(pre, null);
		return ten;
	}

}
