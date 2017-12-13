package ws.service.sendsms;

import java.io.IOException;


public class SpeedSMS {
	
	private String accessToken = "lNKToCqSg60jSXZtE_vt4rkgsmXAOJJc";
	
	public void getUserInfo() {
		SpeedSMSAPI api = new SpeedSMSAPI(accessToken);
		try {
			String info = api.getUserInfo();
			System.out.println(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendSMS(String sdt,String maXacNhan) {
		SpeedSMSAPI api = new SpeedSMSAPI(accessToken);
		String json = "{\"to\": [\""+ sdt +"\"], \"content\": \"Ma xac nhan app dat hang:" + maXacNhan +" \", \"sms_type\": 2, \"brandname\":\"\"}";
		try {
			String result = api.sendSMS(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getSMSStatus(int tranId) {
		SpeedSMSAPI api = new SpeedSMSAPI(accessToken);
		try {
			String result = api.getStatus(tranId);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
