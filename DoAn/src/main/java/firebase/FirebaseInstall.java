package firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

public class FirebaseInstall  {

	public static void main(String[] args) {

		System.out.println("123");
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("G:/SpringCode/DoAn/doanleuducanh-1509441396022-firebase-adminsdk-frywo-d571a6ee67.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				FirebaseOptions options = null;
				try {
					options = new FirebaseOptions.Builder()
					  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
					  .setDatabaseUrl("https://doanleuducanh-1509441396022.firebaseio.com/")
					  .build();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ok1");
				FirebaseApp.initializeApp(options);
				System.out.println("ok");
	}
}
