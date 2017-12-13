package firebase;

import java.sql.SQLException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Notification;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

import ws.database.CuaHangDAO;
import ws.database.KhachHangDAO;
import ws.database.NguoiVanChuyenDAO;
import ws.database.TaiKhoanDAO;
import ws.model.CuaHang;
import ws.model.DonHang;
import ws.model.KhachHang;
import ws.model.NguoiVanChuyen;

public class SendNotiService {

	private CuaHangDAO cuaHangDAO;
	private KhachHangDAO khachHangDAO;
	private NguoiVanChuyenDAO nguoiVanChuyenDAO;
	private TaiKhoanDAO taiKhoanDAO;
	
	
	
	public SendNotiService() {
		super();
		// TODO Auto-generated constructor stub
		cuaHangDAO = new CuaHangDAO();
		khachHangDAO = new KhachHangDAO();
		nguoiVanChuyenDAO = new NguoiVanChuyenDAO();
		taiKhoanDAO = new TaiKhoanDAO(); 
	}

	String serverKey = "AAAATBAJtLk:APA91bHzfwZOO-Gz2s5K5Rse76jr-8gUVvZe419LVmfx6C_h31EJ4da3T921UC6AUxUNxqetzxZ6Ytsrs38bQHZv7Z7ZpGmLRVRcPea5W-5FwRw5E5E9TcDr2QbaVBgQ-0JpnFiFGHcS";
	
	private String layToken(Object o) throws SQLException{
		if(o instanceof CuaHang){
			int id = cuaHangDAO.layIdTaiKhoanBangIdCuaHang(((CuaHang)o).getId());
			return taiKhoanDAO.layTokenFirebaseBangId(id);
		}
		if(o instanceof KhachHang){
			int id = khachHangDAO.layIdTaiKhoanBangIdKhachHang(((KhachHang)o).getId());
			return taiKhoanDAO.layTokenFirebaseBangId(id);
		}
		return "";
	}
	
	public String layJsonCanGui(Object o){
		Gson g = new Gson();
		if(o instanceof NguoiVanChuyen){
			return g.toJson((NguoiVanChuyen)o);
		}
		
		if(o instanceof DonHang){
			return g.toJson((DonHang)o);
		}
		if(o instanceof CuaHang){
			return g.toJson((CuaHang)o);
		}
		
		return "";
	}
	
	public void guiThongDiep(String chuDe,String thongDiep,Object object,Object body){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub7
				String token = "";
				try {
					token = layToken(object);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
	                Sender sender = new FCMSender(serverKey);
	                Message message = new Message.Builder()
	                                  .collapseKey("message")
	                                  .timeToLive(3)
	                                  .delayWhileIdle(true)
	                                  .notification(new Notification.Builder("ICON").title(chuDe).body(thongDiep).build())
	                                  .addData("message", layJsonCanGui(body)).build();
	                                 
	                // Use the same token(or registration id) that was earlier
	                // used to send the message to the client directly from
	                // Firebase Console's Notification tab.
	                System.out.println(token + " " + chuDe);
	                	if(!token.equals("")){
	                		Result result = sender.send(message,
	        		            	token,
	        		                    1);
	                	}
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
			}
		});
	        t.start();
	        try {
	            t.join();
	        }
	        catch (InterruptedException iex) {
	            iex.printStackTrace();
	        }
	}
}
