package Movie_Reservation;

import java.util.ArrayList;

public class Look_tables {
	
	public void look_movie() {
		DBconnectMov db = new DBconnectMov();
ArrayList<MOVIE> movies = db.getmovie();

System.out.println("영화코드\t영화제목\t장르\t런타임\t연령가\t개봉일");
System.out.println("=====================================");
for (MOVIE movie : movies)
	System.out.printf("%s\t%s\t%s\t%s\t%s\n", movie.getMoviecode(), 
			movie.getMoviename(), movie.getThema(), movie.getRuntime()+"분", movie.getAgegroup()+"세 이상 관람가", movie.getOpendate());

	}
	public void look_moviecenter() {
		DBconnectMov db = new DBconnectMov();
ArrayList<MOVIECENTER> moviecenters = db.getmoviecenter();

System.out.println("영화관코드\t지점명\t주소");
System.out.println("=======================================");
for (MOVIECENTER moviecenter : moviecenters)
	System.out.printf("%s\t%s\t%s\n", moviecenter.getCentercode(), moviecenter.getCentername(), moviecenter.getAddress());

	}
	public void look_theater(String centercode) {
		DBconnectMov db = new DBconnectMov();
ArrayList<THEATER> theaters = db.gettheater();
		
System.out.println("상영관 코드\t상영관 이름\t상영관 가격\t청소시간\t맨끝좌석");
System.out.println("====================================================");
for (THEATER theater : theaters) {
	if (theater.getCentercode().equals(centercode))
		System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", theater.getTheatercode(), theater.getTheatername(), 
				theater.getSeatprice()+"원", theater.getCleantime()+"분", theater.getTotalseats());
}
	}
	public void look_screen(String moviecode, String theatercode) {
		DBconnectMov db = new DBconnectMov();
		ArrayList<SCREEN> screens = db.getscreen();
		ArrayList<MOVIE> movies = db.getmovie();
		ArrayList<THEATER> theaters = db.gettheater();
		String moviename = "";
		String theatername = "";
		
		for (MOVIE movie : movies) {
			if (moviecode.equals(movie.getMoviecode()))
				moviename = movie.getMoviename();
		}
		
		for (THEATER theater : theaters) {
			if (theatercode.equals(theater.getTheatercode()))
				theatername = theater.getTheatername();
		}
		
		System.out.println("상영 코드\t상영 영화 이름\t상영관 이름\t시작 시간\t종료 시작");
		System.out.println("====================================================");
		for (SCREEN screen : screens) {
			if (screen.getMoviecode().equals(moviecode)&&screen.getTheatercode().equals(theatercode))
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", screen.getScreencode(), moviename, 
						theatername, screen.getStarttime(), screen.getEndtime());
		}
	}

}
