package ws.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataParam;

import ws.model.LoaiHang;
import ws.model.MatHang;
import ws.service.LoaiHangService;
import ws.service.MatHangService;


@Path("/loaihang")
public class LoaiHangResource {

	private LoaiHangService loaiHangService = new LoaiHangService();
	private MatHangService matHangService = new MatHangService();
		
//	@Path("/1/loaihang/{loaihangid}/mathang")
//	public MatHangResource getMatHangResource(){
//		return new MatHangResource();
//	}

	
	@PUT
	@Path("/{idLoaiHang}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response suaLoaiHang(@PathParam("idLoaiHang")int id,LoaiHang lh) throws SQLException{
		lh.setId(id);
		loaiHangService.suaLoaiHang(lh);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path("/{idLoaiHang}")
	public Response xoaLoaiHang(@PathParam("idLoaiHang")int idLoaiHang) throws SQLException{
		loaiHangService.xoaLoaiHang(idLoaiHang);
		
		return Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/{idLoaiHang}/mathang")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response themMatHang( MatHang mh  ,@PathParam("idLoaiHang") int idLoaiHang){
		MatHang mh1 = matHangService.themMatHang(mh, idLoaiHang);
		return Response.status(201).entity(mh1).build();
	}
}
