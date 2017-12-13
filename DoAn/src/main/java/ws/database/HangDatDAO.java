package ws.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ws.model.HangDat;
import ws.model.LoaiHang;
import ws.model.MatHang;

public class HangDatDAO extends DAO{

	private MatHangDAO matHangDAO;
	
	
	
	public HangDatDAO() {
		super();
		// TODO Auto-generated constructor stub
		matHangDAO = new MatHangDAO();
	}

	public List<HangDat> getTatCaHangDat(int idHoaDon){
		ArrayList<HangDat> list = new ArrayList<>();
		
		String sql = "Select * from hang_dat where id_donhang = ?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, idHoaDon);
			rs = pre.executeQuery();
			
			int id;
			MatHang mh;
			while(rs.next()){
				id = rs.getInt(3);
				mh = matHangDAO.getMatHangBangId(id);
				list.add(new HangDat(
						rs.getInt(1),
						mh,
						rs.getInt(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return list;
	}
	
public void xoaHangDat(int idDonHang) throws SQLException{
		
		String sql = "delete from hang_dat where id_donhang = " + idDonHang;
		PreparedStatement pre = con.prepareStatement(sql);
		pre.executeUpdate();
	}
	
	
	public HangDat addHangDat(int idDonHang,HangDat hd){
		String sql = "Insert into hang_dat (soluong,id_mathang,id_donhang) values(?,?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, hd.getSoluong());
			pre.setInt(2, hd.getMatHang().getId());
			pre.setInt(3, idDonHang);

			pre.executeUpdate();
			
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM hang_dat");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);
			hd.setId(lastid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);
		return hd;
	}
}
