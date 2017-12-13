package ws.controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ws.model.MatHang;
import ws.service.MatHangService;

@Path("/mathang")
public class MatHangResource {
	
	
	private MatHangService matHangService = new MatHangService();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response suaMatHang(MatHang mh) throws SQLException{
		matHangService.suaMatHang(mh);
		return Response.status(Status.OK).entity(new MatHang()).build();
	}
	
	@DELETE
	@Path("/{mathangId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaMatHang(@PathParam("mathangId") int mathangId) throws SQLException{
		matHangService.xoaMatHang(mathangId);
		return Response.status(Status.OK).build();
	}
}
