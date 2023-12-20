package Movie_Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reserve_Movie {
	
	DAO_Reserve_Movie daoRM = new DAO_Reserve_Movie();
	ResultSet rs = null;
	
	public void speed_reservation(String id) {
		
		try {
			rs = daoRM.movie_list();
			System.out.println("");
			while(rs.next()) {
				System.out.println( rs.getString(1) + "  " + rs.getString(2) );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
