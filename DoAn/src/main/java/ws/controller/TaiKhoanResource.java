package ws.controller;

import java.sql.SQLException;

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
import ws.model.KhachHang;
import ws.model.Message;
import ws.model.NguoiVanChuyen;
import ws.model.TaiKhoan;
import ws.service.CuaHangService;
import ws.service.KhachHangService;
import ws.service.NguoiVanChuyenService;
import ws.service.TaiKhoanService;

@Path("/taikhoan")
public class TaiKhoanResource {

	private CuaHangService cuaHangService = new CuaHangService();
	private TaiKhoanService taiKhoanService = new TaiKhoanService();
	private KhachHangService khachHangService = new KhachHangService();
	private NguoiVanChuyenService nguoiVanChuyenService = new NguoiVanChuyenService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response taoTaiKhoan(TaiKhoan tk,@QueryParam("token_firebase")String token,@QueryParam("loainguoidung")String loainguoidung) throws SQLException{
		TaiKhoan tknew = taiKhoanService.taoTaiKhoan(tk,token,loainguoidung);
		
		if(tknew == null){
			return Response.status(Status.OK).entity(new Message("Tồn tại tài khoản!")).build();
		}
		
		return Response.status(Status.OK).entity(tknew).build();
	
	}
	
	@POST
	@Path("{id}/nguoivanchuyen")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response taNguoiVanChuyenChoTaiKhoan(NguoiVanChuyen nvc,@PathParam("id")int idTaiKhoan){
		NguoiVanChuyen nvc1 = nguoiVanChuyenService.themNguoiVanChuyen(idTaiKhoan, nvc);
		return  Response.status(Status.OK).entity(nvc1).build();
	}
	
	@POST
	@Path("{id}/cuahang")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response taoCuaHangChoTaiKhoan(CuaHang ch,@PathParam("id")int idTaiKhoan){
		CuaHang  ch1 = cuaHangService.themCuaHang(ch, idTaiKhoan);
		return  Response.status(Status.OK).entity(ch1).build();
	}

	@POST
	@Path("{id}/khachhang")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response taoKhachHangChoTaiKhoan(KhachHang kh,@PathParam("id")int idTaiKhoan){
		KhachHang  kh1 = khachHangService.themKhachHang(kh, idTaiKhoan);
		return  Response.status(Status.OK).entity(kh1).build();
	}
	
	@GET
	@Path("{id}/guimaxacnhan")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guiSmsMaXacNhan(@QueryParam("sdt") String sdt,@PathParam("id") int id) throws SQLException{
		String thongDiep = taiKhoanService.guiSmsMaXacNhan(id, sdt);
		return Response.status(Status.OK).entity(new Message(thongDiep)).build();	
	}
	
	@GET
	@Path("{id}/xacnhanma")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response xacNhanMa(@PathParam("id")int idTaiKhoan,@QueryParam("maxacnhan")String maXacNhan) throws SQLException{
		String thongDiep = taiKhoanService.xacNhanMa(idTaiKhoan, maXacNhan);
		return Response.status(Status.OK).entity(new Message(thongDiep)).build();
	}
	
	@PUT
	@Path("/kiemtrataikhoan")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response kiemTraTaiKhoan(TaiKhoan tk) throws SQLException{
		String thongDiep = taiKhoanService.kiemTraTaiKhoan(tk);
		return Response.status(Status.OK).entity(new Message(thongDiep)).build();
	}
	
	@GET
	@Path("{id}/cuahang")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layCuaHangBangIdTaiKhoan(@PathParam("id") int id) throws SQLException{
		CuaHang ch = taiKhoanService.layCuaHangBangTaiKhoanId(id);
		return Response.status(Status.OK).entity(ch).build();	
	}
	
	@GET
	@Path("{id}/khachhang")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layKhachHangBangIdTaiKhoan(@PathParam("id") int id) throws SQLException{
		KhachHang kh = khachHangService.layKhachHangTuTaiKhoanId(id);
		return Response.status(Status.OK).entity(kh).build();	
	}
	
	@PUT
	@Path("{id}/tokenfirebase")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response capNhapToken(@PathParam("id")int id,@QueryParam("token")String token){
		System.out.println("123â");
		taiKhoanService.capNhapTokenFirebase(id,token);
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("{id}/nvc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layNVCBangIdTaiKhoan(@PathParam("id") int id) throws SQLException{
		NguoiVanChuyen nvc = taiKhoanService.layNguoiVanChuyenBangIdTaiKhoan(id);
		return Response.status(Status.OK).entity(nvc).build();	
	}
}
