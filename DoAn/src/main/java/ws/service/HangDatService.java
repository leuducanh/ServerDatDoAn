package ws.service;

import ws.database.HangDatDAO;
import ws.model.HangDat;

public class HangDatService {

	private HangDatDAO hangDatDAO;

	public HangDatService() {
		super();
		// TODO Auto-generated constructor stub
		hangDatDAO = new HangDatDAO();
	}
	
	private void themHangDat(int idDonHang,HangDat hd){
		hangDatDAO.addHangDat(idDonHang, hd);
	}	
}
