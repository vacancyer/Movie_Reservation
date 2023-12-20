package Movie_Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO_Reserve_Movie {
	
	Connection conn = new DBconnectMov().conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ResultSet movie_list() throws Exception{
		
		String query = "SELECT * FROM MOVIE ORDER BY SALESRATE";
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		return rs;
	}

}
