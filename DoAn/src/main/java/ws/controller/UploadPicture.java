package ws.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ws.database.CuaHangDAO;
import ws.database.MatHangDAO;
import ws.model.Message;


@Path("/cuahang")
public class UploadPicture {

	private CuaHangDAO cuaHangDAO = new CuaHangDAO();
	private MatHangDAO matHangDAO = new MatHangDAO();
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Message uploadFile(
			@FormDataParam("des")String s,
			@FormDataParam("picture") InputStream uploaded,
			@FormDataParam("picture") FormDataContentDisposition fileDetail) throws IOException, SQLException{
		
		saveToDisk(uploaded,fileDetail);
		System.out.println(s);
		String[] list = s.split(" ");
		int id = Integer.parseInt(list[1]);
		String ten = "";
		if(list[0].equals("cuahang")) ten = cuaHangDAO.updatePicture(fileDetail.getFileName(), id);
		if(list[0].equals("mathang")) ten = matHangDAO.updatePicture(fileDetail.getFileName(), id);

		if(!ten.equals("")){
			String fileLocation = "g://upload/" + ten;
			new File(fileLocation).delete();
		}
		
		return new Message(fileDetail.getFileName());
	}

	private void saveToDisk(InputStream uploaded, FormDataContentDisposition fileDetail) throws IOException {
		// TODO Auto-generated method stub
		String fileLocation = "g://upload/" + fileDetail.getFileName();
		
		try {
			OutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			out = new FileOutputStream(new File(fileLocation));
			while((read = uploaded.read(bytes)) != -1){
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
}
