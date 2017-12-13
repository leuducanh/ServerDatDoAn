package firebase;

import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Notification;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import ws.database.DonHangDAO;
import ws.database.TaiKhoanDAO;
import ws.model.CuaHang;
import ws.model.DonHang;
import ws.model.NguoiVanChuyen;


public class SendNoti {
	
	public static TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	public static DonHangDAO donHangDAO = new DonHangDAO();
	
	public static void main(String[] args) {
		SendNotiService send = new SendNotiService();
		send.guiThongDiep("themdonhang", "Có đơn hàng mới từ " +  " \n"  , new CuaHang(1), new DonHang());
	
	}
}
