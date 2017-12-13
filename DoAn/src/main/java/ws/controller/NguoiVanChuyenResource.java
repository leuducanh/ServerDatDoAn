package ws.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ws.model.MatHang;
import ws.service.NguoiVanChuyenService;

@Path("/nguoivanchuyen")
public class NguoiVanChuyenResource {

	private NguoiVanChuyenService nguoiVanChuyenService = new NguoiVanChuyenService();
	
	
	
	@PUT
	@Path("/{id}/trangthai")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response capNhapTrangThai(@PathParam("id")int id,@QueryParam("trangthai")int trangThai) throws SQLException{
		nguoiVanChuyenService.capNhapTrangThai(id, trangThai);
		return Response.status(Status.OK).build();
	}
	@PUT
	@Path("/{id}/toado")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response capNhapViTri(@PathParam("id")int id,@QueryParam("lat")double lat,@QueryParam("lng")double lng) throws SQLException{
		nguoiVanChuyenService.capNhapToaDo(id, lat, lng);
		return Response.status(Status.OK).build();
	}
}
