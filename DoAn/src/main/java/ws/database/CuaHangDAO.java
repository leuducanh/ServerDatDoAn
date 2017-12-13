package ws.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import ws.model.CuaHang;
import ws.model.MatHang;
import ws.model.ThongKeLoaiHang;
import ws.model.ThongKeMatHang;



public class CuaHangDAO extends DAO{

	public List<CuaHang> layCuaHangBangTen(String tenCuaHang){
		ArrayList<CuaHang> list = new ArrayList<>();
		
		String sql = "Select * from cua_hang where LOWER(ten) LIKE LOWER('%" + tenCuaHang + "%')";
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				list.add(new CuaHang(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
						,rs.getString(10)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(pre, rs);
		return list;
	}
	
	public List<CuaHang> layCuaHangGanViTri(double lat,double lng){
		ArrayList<CuaHang> list = new ArrayList<>();
		ArrayList<CuaHang> list1 = new ArrayList<>();
		String sql = "SELECT *, ( 6371 * acos( cos( radians("+lat+") ) * cos( radians( lat ) ) * cos( radians(lng ) - radians("+lng+") ) + sin( radians("+lat+") ) * sin( radians(lat ) ) ) ) AS distance FROM cua_hang HAVING distance < 1 ORDER BY distance LIMIT 0 , 20";
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
			while(rs.next()){
				list.add(new CuaHang(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
						,rs.getString(10)));
				System.out.println(rs.getInt(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return list;
	}
	
	public CuaHang layCuaHangBangTaiKhoanId(int idTaiKhoan){
		String sql = "Select * from cua_hang where id_taikhoan=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		CuaHang ch = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, idTaiKhoan);
			rs = pre.executeQuery();
			
			while(rs.next()){
				ch = new CuaHang(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
						,rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return ch;
	}
	
	public CuaHang layCuaHangBangId(int id){
		String sql = "Select * from cua_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		CuaHang ch = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			
			while(rs.next()){
				ch = new CuaHang(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
						,rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return ch;
	}
	
	public int layIdTaiKhoanBangIdCuaHang(int id){
		String sql = "Select id_taikhoan from cua_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
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
	
	public CuaHang  addCuaHang(CuaHang ch,int idTaiKhoan){
		String sql = "Insert into cua_hang (ten,dia_diem,lat,lng,mota,dienthoai,url,thoigian_mocua,thoigian_dongcua,id_taikhoan) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, ch.getTen());
			pre.setString(2, ch.getDiaDiem());
			pre.setDouble(3, ch.getLat());
			pre.setDouble(4, ch.getLng());
			pre.setString(5, ch.getMota());
			pre.setString(6, ch.getDienthoai());
			pre.setString(7,"");
			pre.setString(8, ch.getThoiGianMoCua());
			pre.setString(9, ch.getThoiGianDongCua());
			pre.setInt(10, idTaiKhoan);

			pre.executeUpdate();
			
			pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM cua_hang");
			
			rs = pre1.executeQuery();
			rs.next();

			int lastid = rs.getInt(1);
			ch.setId(lastid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		close(pre1, null);
		return ch;
	} 
	
	public void updateCuaHang(CuaHang ch){
		String sql = "Update cua_hang set ten=?,dia_diem=?,lat=?,lng=?,mota=?,dienthoai=?,thoigian_mocua=?,thoigian_dongcua=? where id=?";
		PreparedStatement pre = null;
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, ch.getTen());
			pre.setString(2, ch.getDiaDiem());
			pre.setDouble(3, ch.getLat());
			pre.setDouble(4, ch.getLng());
			pre.setString(5, ch.getMota());
			pre.setString(6, ch.getDienthoai());
			pre.setString(7, ch.getThoiGianMoCua());
			pre.setString(8, ch.getThoiGianDongCua());
			pre.setInt(9, ch.getId());

			pre.executeUpdate();
			
			pre = con.prepareStatement(sql);
			
			pre.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, null);
	} 
	
	public String updatePicture(String tenAnh,int id) throws SQLException{
		String sql  =  "select * from cua_hang where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		pre = con.prepareStatement(sql);
		pre.setInt(1, id);
		rs = pre.executeQuery();
		
		String ten = "";
		
		while(rs.next()){
			ten = rs.getString(8);
		}
		
		sql = "Update cua_hang set url=? where id=?";
		
				
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, tenAnh);
			pre.setInt(2, id);

			pre.executeUpdate();
			
			pre = con.prepareStatement(sql);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, rs);
		return ten;
	} 
	
	
	public double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }
	
public void deleteCuaHang(int idCuaHang) throws SQLException{
		
		LoaiHangDAO loaiHangDAO = new LoaiHangDAO();
		PreparedStatement pre = null;
		ResultSet rs = null;
		String sql = "select * from loai_hang where id_cuahang = " + idCuaHang;

		pre = con.prepareStatement(sql);
		rs = pre.executeQuery();
		
		List<Integer> l = new ArrayList<>();
		while(rs.next()){
			l.add(rs.getInt(1));
		}
		
		for(int i = 0;i < l.size();i++){
			loaiHangDAO.deleteLoaiHang(l.get(i));
		}
		
			sql = "delete from cua_hang where id = " + idCuaHang;
			pre = con.prepareStatement(sql);
			pre.executeUpdate();
		
		close(pre, null);
	}



public List<CuaHang> layToaDo(){
	ArrayList<CuaHang> list = new ArrayList<>();
	
	String sql = "Select * from cua_hang";
	PreparedStatement pre = null;
	ResultSet rs = null;
	
	try {
		pre = con.prepareStatement(sql);
		rs = pre.executeQuery();
		
		while(rs.next()){
			list.add(new CuaHang(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),rs.getDouble(4),rs.getDouble(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)
					,rs.getString(10)));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	close(pre, rs);
	return list;
}

public List<ThongKeLoaiHang> thongKeLoaiHang(int id,Date date,int cheDo){
	String sql = "";
	String dk = "";
	PreparedStatement pre = null;
	ResultSet rs = null;
	List<ThongKeLoaiHang> list = new ArrayList<>();
	switch(cheDo){
		case 3:{
			dk = "year(don_hang.ngay_dat) = " + (date.getYear()+1900);
			break;
		}
		case 2:{
			dk ="month(don_hang.ngay_dat) = " + (date.getMonth()+1)+ " and year(don_hang.ngay_dat) = " + (date.getYear()+1900);
			break;
		}
		case 1:{
			dk ="day(don_hang.ngay_dat) = " + date.getDate() +" and month(don_hang.ngay_dat) = " + (date.getMonth()+1)+ " and year(don_hang.ngay_dat) = " + (date.getYear()+1900);
			break;
		}
	}
	
	sql = "SELECT loai_hang.id,loai_hang.ten,SUM(hang_dat.soluong*mat_hang.gia)as tong_gia FROM dat_do_an.mat_hang,dat_do_an.hang_dat,dat_do_an.don_hang,dat_do_an.loai_hang where don_hang.id_cuahang =  " + id +" and don_hang.id = hang_dat.id_donhang and don_hang.trangthai = 'Đã xong' and hang_dat.id_mathang = mat_hang.id and mat_hang.id_loaihang = loai_hang.id and  " + dk +" group by loai_hang.id";
	

	try {
		pre = con.prepareStatement(sql);
		rs = pre.executeQuery();
		
		while(rs.next()){
			list.add(new ThongKeLoaiHang(rs.getString(2),rs.getInt(3)));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	close(pre, rs);
	return list;
	
}
	
public List<ThongKeMatHang> thongKeMatHanng(int id,Date date,int cheDo){
	String sql = "";
	String dk = "";
	PreparedStatement pre = null;
	ResultSet rs = null;
	List<ThongKeMatHang> list = new ArrayList<>();
	switch(cheDo){
		case 3:{
			dk = "year(don_hang.ngay_dat) = " + (date.getYear()+1900);
			break;
		}
		case 2:{
			dk ="month(don_hang.ngay_dat) = " + (date.getMonth()+1)+ " and year(don_hang.ngay_dat) = " + (date.getYear()+1900);
			break;
		}
		case 1:{
			dk ="day(don_hang.ngay_dat) = " + date.getDate() +" and month(don_hang.ngay_dat) = " + (date.getMonth()+1)+ " and year(don_hang.ngay_dat) = " + (date.getYear()+1900);
			break;
		}
	}
	
	sql = "SELECT mat_hang.ten,SUM(hang_dat.soluong)as sl FROM dat_do_an.mat_hang,dat_do_an.hang_dat,dat_do_an.don_hang,dat_do_an.loai_hang where don_hang.id_cuahang = "+ id +" and don_hang.id = hang_dat.id_donhang and hang_dat.id_mathang = mat_hang.id and don_hang.trangthai = 'Đã xong' and mat_hang.id_loaihang = loai_hang.id and  " + dk +" group by mat_hang.id";
	

	try {
		pre = con.prepareStatement(sql);
		rs = pre.executeQuery();
		
		while(rs.next()){
			list.add(new ThongKeMatHang(rs.getString(1),rs.getInt(2)));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	close(pre, rs);
	return list;
}

}
