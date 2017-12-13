package ws.controller;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ws.model.CuaHang;
import ws.model.DonHang;
import ws.model.LoaiHang;
import ws.model.ThongKe;
import ws.service.CuaHangService;
import ws.service.DonHangService;
import ws.service.LoaiHangService;

@Path("/cuahang")
public class CuaHangResource {

	private CuaHangService cuaHangService = new CuaHangService();
	private LoaiHangService loaiHangService = new LoaiHangService();
	private DonHangService donHangService = new DonHangService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response layCuaHangTen(@QueryParam("ten") String ten){
		String newString="";
		try {
			newString = new String(ten.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		List<CuaHang> cuaHangs = cuaHangService.layCuaHangBangTen(new String(ten));
		return Response.status(200).entity(cuaHangs).build();
	}
	
	@GET
	@Path("/gandiadiem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layCuaHangGan(@QueryParam("lat") double lat,@QueryParam("lng") double lng){
		List<CuaHang> cuaHangs = cuaHangService.layCuaHangGanNhat(lat, lng);
		return Response.status(200).entity(cuaHangs).build();
	}
	
	@POST
	@Path("{id}/loaihang")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response themLoaiHangChoCuaHang(@PathParam("id")int idCuaHang,LoaiHang lh){
		LoaiHang lhnew = loaiHangService.themLoaiHang(lh, idCuaHang);
		return Response.status(Status.CREATED).entity(lhnew).build();
	}
	
	@GET
	@Path("/{cuahangId}/loaihang")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layTatCaLoaiHangTuCuaHang(@PathParam("cuahangId") String cuahangId){
		List<LoaiHang> list =  loaiHangService.layTatCaLoaiHang(Integer.parseInt(cuahangId));
		return Response.status(200).entity(list).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response capNhapCuaHang(CuaHang cuaHang){
		cuaHangService.capNhapCuaHang(cuaHang);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/{id}/donhang")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layDonHangCuaCuaHang(@PathParam("id") int id){
		List<DonHang> donHangs = donHangService.layTatCaDonHangTuCuaHang(id);
		return Response.status(200).entity(donHangs).build();
	}
	
	@GET
	@Path("/{id}/thongke")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response thongKeCuaHang(@PathParam("id")int id,@QueryParam("ngay")int ngay,@QueryParam("thang")int thang,@QueryParam("nam")int nam,@QueryParam("chedo")int cheDo){
		Date date = new Date(nam-1900, thang-1, ngay);
		ThongKe tk = cuaHangService.thongKeCuaHang(id, date, cheDo);
		return Response.status(Status.OK).entity(tk).build();
	}
	
}
