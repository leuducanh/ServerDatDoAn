package ws.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ws.model.CuaHang;
import ws.model.TaiKhoan;

public class TaiKhoanDAO extends DAO {
	
	 private final char[] symbol={
		        '0','1','2','3','4','5','6','7','8','9'};

	 
	 private final Random random = new Random();

	public static final String LOAICUAHANG = "cuahang";
	public static final String LOAIKHACHHANG = "khachhang";
	
	public TaiKhoan taoTaiKhoan(TaiKhoan tk,String token,String loaiNguoiDung) throws SQLException{
		
		String sql = "select * from tai_khoan where ten=?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;

		pre = con.prepareStatement(sql);
		pre.setString(1, tk.getTen());
		rs = pre.executeQuery();
		
		if(rs.next()){
			return null;
		}else{
			sql = "Insert into tai_khoan (ten,matkhau,ma_xac_nhan,token_firebase,loai_nguoidung) values(?,?,?,?,?)";
			try {
				pre = con.prepareStatement(sql);
				pre.setString(1, tk.getTen());
				pre.setString(2, tk.getMatkhau());
				pre.setString(3, taoChuoiNgauNhien(6));
				if(token == null){
					pre.setString(4, "");
				}else{
					pre.setString(4, token);
				}
				pre.setString(5, loaiNguoiDung);

				pre.executeUpdate();
				
				pre1 = con.prepareStatement("SELECT MAX(id) AS id FROM tai_khoan");
				
				rs = pre1.executeQuery();
				rs.next();

				int lastid = rs.getInt(1);
				tk.setId(lastid);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		close(pre, rs);
		close(pre1, null);
		return tk;
	}
	
	public String kiemTraTaiKhoan(TaiKhoan tk) throws SQLException{
		String sql = "select * from tai_khoan where ten=? and matkhau=?";
		PreparedStatement pre = null;
		ResultSet rs = null;

		pre = con.prepareStatement(sql);
		pre.setString(1, tk.getTen());
		pre.setString(2, tk.getMatkhau());
		rs = pre.executeQuery();
		
		TaiKhoan tknew = null;
		String thongDiep = "Thatbai";
		String ma = "";
		while(rs.next()){
			ma = rs.getString(4);
			if(ma.equals("0")){
				String loai = rs.getString(6);
				int idTaiKhoan = rs.getInt(1);
				boolean flag = false;
				if(loai.equalsIgnoreCase("cuahang")) flag = kiemTraDaTaoCuaHang(idTaiKhoan);
				if(loai.equalsIgnoreCase("khachhang")) flag = kiemTraDaTaoKhachHang(idTaiKhoan);
				if(loai.equalsIgnoreCase("nguoivanchuyen")) flag = kiemTraDaTaoNguoiVanChuyen(idTaiKhoan);

				
				thongDiep = "Thanhcong " + loai + " " + idTaiKhoan + " " + flag;
				
			}else{
				thongDiep = "Canmaxacnhan " + rs.getString(6) + " " + rs.getInt(1);
			}
		}
		return thongDiep;
	}
	
	public boolean kiemTraDaTaoCuaHang(int idTaiKhoan) throws SQLException{
		String sql = "select * from cua_hang where id_taikhoan=?";
		PreparedStatement pre = null;
		ResultSet rs = null;

		boolean flag = false;
		pre = con.prepareStatement(sql);
		pre.setInt(1, idTaiKhoan);
		rs = pre.executeQuery();
		while(rs.next()){
			flag = true;
		}
		
		return flag;
	}
	
	public boolean kiemTraDaTaoKhachHang(int idTaiKhoan) throws SQLException{
		String sql = "select * from khach_hang where id_taikhoan=?";
		PreparedStatement pre = null;
		ResultSet rs = null;

		boolean flag = false;
		pre = con.prepareStatement(sql);
		pre.setInt(1, idTaiKhoan);
		rs = pre.executeQuery();
		while(rs.next()){
			flag = true;
		}
		
		return flag;
	}
	
	public boolean kiemTraDaTaoNguoiVanChuyen(int idTaiKhoan) throws SQLException{
		String sql = "select * from  nguoi_van_chuyen where id_taikhoan=?";
		PreparedStatement pre = null;
		ResultSet rs = null;

		boolean flag = false;
		pre = con.prepareStatement(sql);
		pre.setInt(1, idTaiKhoan);
		rs = pre.executeQuery();
		while(rs.next()){
			flag = true;
		}
		
		return flag;
	}
	
	public String timMaXacNhan(int idTaiKhoan) throws SQLException{
		String sql = "select * from tai_khoan where id=?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;

		pre = con.prepareStatement(sql);
		pre.setInt(1, idTaiKhoan);
		rs = pre.executeQuery();
		
		String ma = "";
		
		while(rs.next()){
			ma = rs.getString(4);
		}
		return ma;
	}
	
	public boolean ktraMaXacNhan(int idTaiKhoan,String maXacNhan) throws SQLException{
		String sql = "select ma_xac_nhan from tai_khoan where id=?";
		PreparedStatement pre = null;
		ResultSet rs = null;

		boolean flag = false;
		
		pre = con.prepareStatement(sql);
		pre.setInt(1, idTaiKhoan);
		rs = pre.executeQuery();
		String maLuu = "";
		while(rs.next()){
			maLuu = rs.getString(1);
		}
		
		flag = maLuu.equals(maXacNhan);
		if(flag){
			sql = "update tai_khoan set ma_xac_nhan=? where id=?";
			pre = con.prepareStatement(sql);
			
			pre.setString(1, 0+"");
			pre.setInt(2, idTaiKhoan);
			
			pre.executeUpdate();
			
			return true;
		}
		
		return false;
	}
	

	public String layTokenFirebaseBangId(int id) throws SQLException{
		String sql = "select token_firebase from tai_khoan where id=?";
		PreparedStatement pre = null;
		PreparedStatement pre1 = null;
		ResultSet rs = null;

		pre = con.prepareStatement(sql);
		pre.setInt(1, id);
		rs = pre.executeQuery();
		
		String token = "";
		while(rs.next()){
			token = rs.getString(1);
		}
		
		return token;
	}
	
	public void capNhapToken(int id,String token){
		String sql = "update tai_khoan set token_firebase = ? where id = ?";
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) con.prepareStatement(sql);
			pre.setString(1, token);
			pre.setInt(2, id);
			
			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(pre, null);
	}
	
	public String taoChuoiNgauNhien(int length){
		char[] randomCharArray = new char[length];
        
        for(int i = 0;i < length;i++){
            randomCharArray[i] = symbol[random.nextInt(10)];
        }
        
        return new String(randomCharArray);
	}

}
