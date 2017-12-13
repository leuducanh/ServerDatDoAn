package firebase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.android.gcm.server.Sender;

public class FCMSender extends Sender{

	public FCMSender(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected HttpURLConnection getConnection(String url) throws IOException {
		// TODO Auto-generated method stub
		String fcmUrl = "https://fcm.googleapis.com/fcm/send";
        return (HttpURLConnection) new URL(fcmUrl).openConnection();
	}
}
