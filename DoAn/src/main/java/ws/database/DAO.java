package ws.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO {
	
	protected Connection con;
	
	public DAO() {
		super();
		con = MySQLConUtils.getMySQLConnection();
	}
	
	public void close(PreparedStatement pre,ResultSet rs){
		try {
			if(pre != null)pre.close();
			if(rs != null)rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
