package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;

import edu.wlu.cs.levy.CG.KDTree;
import edu.wlu.cs.levy.CG.KeyDuplicateException;
import edu.wlu.cs.levy.CG.KeySizeException;
import ws.database.CuaHangDAO;
import ws.database.MatHangDAO;
import ws.model.CuaHang;

public class Test {

	
	public static void main(String[] args) {

		KDTree<CuaHang> c = new KDTree<>(2);
		CuaHangDAO dao = new CuaHangDAO();
		
		List<CuaHang> l = dao.layToaDo();
		for(int i = 0;i<l.size();i++ ){
			double[] d = {l.get(i).getLat(),l.get(i).getLng()};
			try {
				c.insert(d, l.get(i));
			} catch (KeySizeException | KeyDuplicateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<CuaHang> chs = new ArrayList<>();
		CuaHang a = null ;
		double[] d = {21.046575,105.791971};
		try {
			a = c.nearest(d);	
		} catch (KeySizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("123");
		for(int i = 0;i < l.size();i++){
			System.out.println(l.get(i).getId());
		}
		System.out.println("-----------------------");
		for(int i = 0;i < chs.size();i++){
			System.out.println(chs.get(i).getId());
		}
	}
}
