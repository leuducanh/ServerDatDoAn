package ws.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PictureController extends HttpServlet {
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	            doPost(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String pic = request.getParameter("name");
	        ServletOutputStream out;  
	        out = response.getOutputStream();  
	        FileInputStream fin = new FileInputStream("G:\\upload\\" + pic);  
	      
	        BufferedInputStream bin = new BufferedInputStream(fin);  
	        BufferedOutputStream bout = new BufferedOutputStream(out);  
	        int ch =0; ;  
	        while((ch=bin.read())!=-1)  
	        {  
	        bout.write(ch);  
	        }  
	        System.out.println(pic);
	        bin.close();  
	        fin.close();  
	        bout.close();  
	        out.close();  
	    }  
	    


	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>


}
