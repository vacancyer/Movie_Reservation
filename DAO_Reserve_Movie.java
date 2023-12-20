package Movie_Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO_Reserve_Movie {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ResultSet movie_list(){
		
		conn = new DBconnectMov().conn;
		try {
			String query = "SELECT * FROM MOVIE ORDER BY salesrate";
			pstmt = conn.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return rs;
	}

}
