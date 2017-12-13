package ws.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ws.model.LoaiHang;
import ws.model.MatHang;


public class LoaiHangDAO extends DAO{
	
	private MatHangDAO matHangDAO = new MatHangDAO();

	public List<LoaiHang> getTatCaLoaiHang(int idCuaHang){
		ArrayList<LoaiHang> list = new ArrayList<>();
		
		String sql = "Select * from loai_hang where id_cuahang = " + idCuaHang;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				list.add(new LoaiHang(
						rs.getInt(1),
						rs.getString(2),
						matHangDAO.getMatHang(rs.getInt(1))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return list;
	}
	
	public LoaiHang addLoaiHang(LoaiHang lh,int idCuaHang){
		String sql = "Insert into loai_hang (ten,id_cuahang) values(?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,lh.getTen());
			pre.setInt(2, idCuaHang);

			pre.executeUpdate();
			
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM loai_hang");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);
			lh.setId(lastid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);
		return lh;
	}
	
	public void updateLoaiHang(LoaiHang lh) throws SQLException{
		String sql = "update loai_hang set ten=? where id = ?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null; 
		
			pre = con.prepareStatement(sql);
			pre.setString(1, lh.getTen());
			pre.setInt(2, lh.getId());
			
			pre.executeUpdate();
		
		close(pre, null);
	}
	
	public void deleteLoaiHang(int idLoaiHang) throws SQLException{
		
		PreparedStatement pre = null;
		
			String sql = "delete from loai_hang where id = " + idLoaiHang;
			pre = con.prepareStatement(sql);
			pre.executeUpdate();
		
		close(pre, null);
	}
}
