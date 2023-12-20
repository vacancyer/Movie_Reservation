package Movie_Reservation;

import java.sql.ResultSet;

public class Reserve_Movie {
	
	DAO_Reserve_Movie daoRM = new DAO_Reserve_Movie();
	ResultSet rs = null;
	
	public void speed_reservation(String id) {
		
		rs = daoRM.movie_list();
		
	}

}
