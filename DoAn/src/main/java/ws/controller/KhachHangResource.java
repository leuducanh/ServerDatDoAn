package ws.controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ws.model.DonHang;
import ws.model.KhachHang;
import ws.model.LoaiHang;
import ws.service.DonHangService;
import ws.service.KhachHangService;

@Path("/khachhang")
public class KhachHangResource {

	private KhachHangService khachHangService = new KhachHangService();
	private DonHangService donHangService = new DonHangService();

	@POST
	@Path("/{id}/themdonhang")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response themDonHangTuKhachHang(@PathParam("id")int id,DonHang donHang) throws SQLException{
		donHangService.themDonHangTuKhachHang(id, donHang);
		System.out.println("1234");
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/{id}/donhang")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layDonHangCuaKhachHang(@PathParam("id") int id){
		List<DonHang> donHangs = donHangService.layDonHangBangIdKhachHang(id);
		return Response.status(200).entity(donHangs).build();
	}
}
