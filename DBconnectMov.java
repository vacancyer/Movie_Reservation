package Movie_Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBconnectMov {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private static final String url = "jdbc:mysql://localhost:3306/Tiketing?severTimezone=UTC";
	private static final String user = "root";
	private static final String pass = "1234";
	
	public DBconnectMov() {
		conn=getConnection();
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			//1. 드라이버 세팅
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. Connection 획득
			conn = DriverManager.getConnection(url, user, pass);
			//System.out.println("DB Connection 성공");
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생: " + e.getMessage());
		}
		return conn;
	}
	public void insertMoviecenter(String centercode, String centername, String address) {
	      // ===========DB파일===========
	      // 3. Statement 생성
	      try {
	        stmt = conn.createStatement();

	        String sql = "INSERT INTO MOVIECENTER (centercode, centername, address) VALUES ('"+
	           centercode + "','"+ centername + "','"+ address + "')";
	         stmt.executeUpdate(sql);
	         System.out.printf("%s 영화관 등록이 완료되었습니다.\n", centername);
	      } catch (Exception e) {
	         System.out.println("DB 작업중 문제 발생: " + e.getMessage());
	         e.printStackTrace();
	      }
	   }
	public void updateMoviecenter(String centercode, String centername, String address) {
		//============== DB작업 ================
		try {
			stmt = conn.createStatement();
			String sql = String.format("UPDATE MOVIECENTER SET centername = '%s', address = '%s' WHERE centercode = '%s'", 
					 centername, address, centercode);
		stmt.executeUpdate(sql);
		System.out.printf("%s 영화관 수정이 완료되었습니다.\n", centername);
		} catch (Exception e) {
		System.out.println("DB 작업 중 문제 발생: " + e.getMessage());
		e.printStackTrace();
		}
		//=========================================
	}
	public void deleteMoviecenter(String centercode, String centername) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("DELETE FROM MOVIECENTER WHERE centercode = '%s'", centercode);
			stmt.executeUpdate(sql);
			System.out.printf("%s 영화관 삭제가 완료되었습니다.\n", centername);
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// ===========================================
	}
	public void insertMovie(String moviecode, String moviename, String thema, int runtime, int agegroup, float salesrate, String opendate) {
	      // ===========DB파일===========
	      // 3. Statement 생성
	      try {
	        stmt = conn.createStatement();

	        String sql = String.format("INSERT INTO MOVIE (moviecode, moviename, thema, runtime, agegroup, salesrate, opendate) "
	        		+ "VALUES ('%s','%s','%s','%s','%s','%s','%s')"
	        		, moviecode, moviename, thema, runtime, agegroup, salesrate, opendate);
	         stmt.executeUpdate(sql);
	         System.out.printf("%s 영화 정보 등록이 완료되었습니다.\n", moviename);
	      } catch (Exception e) {
	         System.out.println("DB 작업중 문제 발생: " + e.getMessage());
	         e.printStackTrace();
	      }
	   }
	public void updateMovie(String moviecode, String moviename, String thema, int runtime, int agegroup, float salesrate, String opendate) {
		//============== DB작업 ================
		try {
			stmt = conn.createStatement();
			String sql = String.format("UPDATE MOVIE SET moviename = '%s', thema = '%s', runtime = '%s' , agegroup = '%s', salesrate = '%s',  "
					+ "opendate = '%s' WHERE moviecode = '%s'", 
moviename, thema, runtime, agegroup, salesrate, opendate, moviecode);
		stmt.executeUpdate(sql);
		System.out.printf("%s 영화 정보 수정이 완료되었습니다.\n", moviename);
		} catch (Exception e) {
		System.out.println("DB 작업 중 문제 발생: " + e.getMessage());
		e.printStackTrace();
		}
		//=========================================
	}
	public void deleteMovie(String moviecode, String moviename) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("DELETE FROM MOVIE WHERE moviecode = '%s'",moviecode);
			stmt.executeUpdate(sql);
			System.out.printf("%s 영화 정보 삭제가 완료되었습니다.\n", moviename);
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// ===========================================
	}
	public void insertTheater(String theatercode, String theatername, String centercode, int seatprice, int cleantime, String totalseats) {
	      // ===========DB파일===========
	      // 3. Statement 생성
	      try {
	    	  stmt = conn.createStatement();
	        ArrayList<MOVIECENTER> moviecenters = getmoviecenter();

	    	  String centername = "";
	    	  for (MOVIECENTER moviecenter : moviecenters) {
	    		  if (moviecenter.getCentercode().equals(centercode))
	    			  centername = moviecenter.getCentername();
	    	  }
	    	  
	        String sql =String.format("INSERT INTO THEATER (theatercode, theatername, centercode, seatprice, cleantime, totalseats) VALUES ('%s','%s','%s','%s','%s','%s')"
	        		, theatercode, theatername, centercode, seatprice, cleantime, totalseats);
	         stmt.executeUpdate(sql);
	         System.out.printf("%s 영화관의 %s 상영관 정보 등록이 완료되었습니다.\n", centername, theatername);
	      } catch (Exception e) {
	         System.out.println("DB 작업중 문제 발생: " + e.getMessage());
	         e.printStackTrace();
	      }
	   }
	public void updateTheater(String theatercode, String theatername, String centercode, int seatprice, int cleantime, String totalseats) {
		//============== DB작업 ================
		try {
			stmt = conn.createStatement();
			 ArrayList<MOVIECENTER> moviecenters = getmoviecenter();

	    	  String centername = "";
	    	  for (MOVIECENTER moviecenter : moviecenters) {
	    		  if (moviecenter.getCentercode().equals(centercode))
	    			  centername = moviecenter.getCentername();
	    	  }
			
			String sql = String.format("UPDATE THEATER SET theatername = '%s' , centercode = '%s', seatprice = '%s', cleantime = '%s', totalseats = '%s' WHERE theatercode = '%s'", 
theatername, centercode, seatprice, cleantime, totalseats, theatercode);
		stmt.executeUpdate(sql);
		System.out.printf("%s 영화관의 %s 상영 정보 수정이 완료되었습니다.\n", centername, theatername);
		} catch (Exception e) {
		System.out.println("DB 작업 중 문제 발생: " + e.getMessage());
		e.printStackTrace();
		}
		//=========================================
	}
	public void deleteTheater(String centername, String theatercode, String theatername) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
	    	  
			String sql = String.format("DELETE FROM THEATER WHERE theatercode = '%s'", theatercode);
			stmt.executeUpdate(sql);
			System.out.printf("%s 영화관의 %s 상영 정보 삭제가 완료되었습니다.\n", centername, theatername);
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// ===========================================
	}
	public void insertreservations(String reservenumber, String selectseat, String moviecode, String screencode, String theatercode, String birth,
			String phone, String pw, String usercheck) {
	      // ===========DB파일===========
	      // 3. Statement 생성
	      try {
	        stmt = conn.createStatement();

	        String sql = String.format("INSERT INTO RESERVATION (reservenumber, selectseat, moviecode, screencode, theatercode, birth,\r\n"
	        		+ "			phone, pw, usercheck) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')"
	        		, reservenumber, selectseat, moviecode, screencode, theatercode, birth,
	    			phone, pw, usercheck);
	         stmt.executeUpdate(sql);
	         System.out.println("고객님의 예약이 완료되었습니다.");
	      } catch (Exception e) {
	         System.out.println("DB 작업중 문제 발생: " + e.getMessage());
	         e.printStackTrace();
	      }
	   }
	public void updatereservations(String reservenumber, String selectseat, String moviecode, String screencode, String theatercode, String birth,
			String phone, String pw, String usercheck
			) {
		//============== DB작업 ================
		try {
			stmt = conn.createStatement();
			String sql = String.format("UPDATE RESERVATION SET selectseat = '%s', moviecode = '%s' , screencode = '%s', theatercode = '%s', birth = '%s' "
					+ ", phone = '%s', pw = '%s', usercheck = '%s' WHERE reservenumber = '%s'", 
selectseat, moviecode, screencode, theatercode, birth, phone, pw, usercheck, reservenumber);
		stmt.executeUpdate(sql);
		System.out.println("고객님의 예약 수정이 완료되었습니다.");
		} catch (Exception e) {
		System.out.println("DB 작업 중 문제 발생: " + e.getMessage());
		e.printStackTrace();
		}
		//=========================================
	}
	public void deletereservations(String reservenumber) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("DELETE FROM RESERVATION WHERE reservenumber = '%s'" , reservenumber);
			stmt.executeUpdate(sql);
			System.out.println("고객님의 예약 삭제가 완료되었습니다.");
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// ===========================================
	}
	public void insertuserinfo(String ID, String userpw, String username, String phone, String birth, String ninkname, int userpoint) {
	      // ===========DB파일===========
	      // 3. Statement 생성
	      try {
	        stmt = conn.createStatement();

	        String sql = String.format("INSERT INTO USERINFO (ID, userpw, username, phone, birth, ninkname, userpoint) VALUES ('%s','%s','%s','%s','%s','%s', '%s')"
	        		, ID, userpw, username, phone, birth, ninkname, userpoint);
	         stmt.executeUpdate(sql);
	         System.out.printf("%s님 회원 등록이 완료되었습니다.\n", username);
	      } catch (Exception e) {
	         System.out.println("DB 작업중 문제 발생: " + e.getMessage());
	         e.printStackTrace();
	      }
	   }
	public void updateuserinfo(String ID, String userpw, String username, String phone, String birth, String ninkname, int userpoint) {
		//============== DB작업 ================
		try {
			stmt = conn.createStatement();
			String sql = String.format("UPDATE USERINFO SET userpw = '%s' , username = '%s', phone = '%s', birth = '%s', ninkname = '%s', userpoint = '%s' WHERE ID = '%s'", 
userpw, username, phone, birth, ninkname, userpoint, ID);
		stmt.executeUpdate(sql);
		System.out.printf("%s님 회원 수정이 완료되었습니다.\n", username);
		} catch (Exception e) {
		System.out.println("DB 작업 중 문제 발생: " + e.getMessage());
		e.printStackTrace();
		}
		//=========================================
	}
	public void deleteuserinfo(String username, String ID) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("DELETE FROM USERINFO WHERE ID = '%s'" , ID);
			stmt.executeUpdate(sql);
			System.out.printf("%s님 회원 삭제가 완료되었습니다.\n", username);
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// ===========================================
	}
	public void insertscreen(String screencode, String moviecode, String theatercode, String starttime, String endtime, String soldseats) {
	      // ===========DB파일===========
	      // 3. Statement 생성
	      try {
	        stmt = conn.createStatement();

	        String sql =String.format("INSERT INTO SCREEN (screencode, moviecode, theatercode, starttime, endtime, soldseats) "
	        		+ "VALUES ('%s','%s','%s','%s','%s','%s')"
	        		, screencode, moviecode, theatercode, starttime, endtime, soldseats);
	         stmt.executeUpdate(sql);
	         System.out.printf("%s ~ %s 상영 영화 등록이 완료되었습니다.\n", starttime, endtime);
	      } catch (Exception e) {
	         System.out.println("DB 작업중 문제 발생: " + e.getMessage());
	         e.printStackTrace();
	      }
	   }
	public void updatescreen(String screencode, String moviecode, String theatercode, String starttime, String endtime, String soldseats) {
		//============== DB작업 ================
		try {
			stmt = conn.createStatement();
			String sql = String.format("UPDATE SCREEN SET moviecode = '%s' , theatercode = '%s', starttime = '%s', endtime = '%s', soldseats = '%s' WHERE screencode = '%s'", 
moviecode, theatercode, starttime, endtime, soldseats, screencode);
		stmt.executeUpdate(sql);
		System.out.printf("%s ~ %s 상영 영화 수정이 완료되었습니다.\n", starttime, endtime);
		} catch (Exception e) {
		System.out.println("DB 작업 중 문제 발생: " + e.getMessage());
		e.printStackTrace();
		}
		//=========================================
	}
	public void deletescreen(String screencode) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("DELETE FROM SCREEN WHERE screencode = '%s'" , screencode);
			stmt.executeUpdate(sql);
			System.out.printf("영화 상영 삭제가 완료되었습니다.\n");
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		// ===========================================
	}
	
	public  String lookstartdate(String screencode) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("SELECT DATE_FORMAT(startdate, '%m%d') FROM screen WHERE screencode = '%s'" , screencode);
			rs = stmt.executeQuery(sql);
			String startdate = rs.getString(1);
			return startdate;
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		return "";
		// ===========================================
	}
	public  String lookenddate(String screencode) {
		//================= DB작업 ===============
		try {
			stmt = conn.createStatement();
			String sql = String.format("SELECT DATE_FORMAT(enddate, '%m%d') FROM screen WHERE screencode = '%s'" , screencode);
			rs = stmt.executeQuery(sql);
			String enddate = rs.getString(1);
			return enddate;
		}catch (Exception e) {
			System.out.println("DB 작업중 문제 발생: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "";
		// ===========================================
	}

public ArrayList<ArrayList<String>> jointhmc(String centercode) {
		
		ArrayList<ArrayList<String>> AddrList = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			String sql = String.format("select THEATER.*, MOVIECENTER.* from THEATER inner join MOVIECENTER on "
					+ "THEATER.centercode = MOVIECENTER.centercode where theater.centercode = '%s'", centercode);
			
			rs = stmt.executeQuery(sql);
			
			//System.out.println("============ 주소록 목록 ================");
			while(rs.next()) {
				ArrayList<String> a1 = new ArrayList<>();
				a1.add(rs.getString("theatercode"));
				a1.add(rs.getString("theatername"));
				a1.add(rs.getString("seatprice"));
				a1.add(rs.getString("cleantime"));
				a1.add(rs.getString("totalseats"));
				a1.add(rs.getString("centername"));
				a1.add(rs.getString("address"));
				a1.add(centercode);
				
				AddrList.add(a1);
			}
		}catch (Exception e) {
			System.out.println("list DB 작업중 문제 발생!");
			e.printStackTrace();
		}
		return AddrList;
		
	}

public ArrayList<ArrayList<String>> joinscrmov(String moviecode) {
	
	ArrayList<ArrayList<String>> AddrList = new ArrayList<>();
	
	try {
		stmt = conn.createStatement();
		String sql = String.format("select MOVIE.*, SCREEN.* from MOVIE inner join SCREEN on"
				+ " MOVIE.moviecode = SCREEN.moviecode where MOVIE.moviecode = '%s'", moviecode);
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			ArrayList<String> a1 = new ArrayList<>();
			a1.add(rs.getString("moviename"));
			a1.add(rs.getString("thema"));
			a1.add(rs.getString("runtime"));
			a1.add(rs.getString("agegroup"));
			a1.add(rs.getString("salesrate"));
			a1.add(rs.getString("opendate"));
			a1.add(rs.getString("screencode"));
			a1.add(rs.getString("theatercode"));
			a1.add(rs.getString("starttime"));
			a1.add(rs.getString("endtime"));
			a1.add(rs.getString("soldseats"));
			a1.add(moviecode);
			
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}

public ArrayList<ArrayList<String>> joinscrthe(String theatercode) {
	
	ArrayList<ArrayList<String>> AddrList = new ArrayList<>();
	
	try {
		stmt = conn.createStatement();
		String sql = String.format("select THEATER.*, SCREEN.* from THEATER inner join SCREEN on "
				+ "THEATER.theatercode = SCREEN.theatercode where SCREEN.theatercode = '%s'", theatercode);
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			ArrayList<String> a1 = new ArrayList<>();
			a1.add(rs.getString("theatername"));
			a1.add(rs.getString("centercode"));
			a1.add(rs.getString("seatprice"));
			a1.add(rs.getString("cleantime"));
			a1.add(rs.getString("totalseats"));
			a1.add(rs.getString("screencode"));
			a1.add(rs.getString("moviecode"));
			a1.add(rs.getString("starttime"));
			a1.add(rs.getString("endtime"));
			a1.add(rs.getString("soldseats"));
			a1.add(theatercode);
			
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}

public ArrayList<ArrayList<String>> joinresthe(String theatercode) {
	
	ArrayList<ArrayList<String>> AddrList = new ArrayList<>();
	
	try {
		stmt = conn.createStatement();
		String sql = String.format("select RESERVATION.*, THEATER.* from RESERVATION inner join THEATER on "
				+ "RESERVATION.theatercode = THEATER.theatercode where THEATER.theatercode = '%s'", theatercode);
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			ArrayList<String> a1 = new ArrayList<>();
			a1.add(rs.getString("reservenumber"));
			a1.add(rs.getString("selectseat"));
			a1.add(rs.getString("moviecode"));
			a1.add(rs.getString("screencode"));
			a1.add(rs.getString("birth"));
			a1.add(rs.getString("phone"));
			a1.add(rs.getString("pw"));
			a1.add(rs.getString("usercheck"));
			a1.add(rs.getString("theatername"));
			a1.add(rs.getString("centercode"));
			a1.add(rs.getString("seatprice"));
			a1.add(rs.getString("cleantime"));
			a1.add(rs.getString("totalseats"));
			a1.add(theatercode);
			
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}

public ArrayList<ArrayList<String>> joinresscr(String screencode) {
	
	ArrayList<ArrayList<String>> AddrList = new ArrayList<>();
	
	try {
		stmt = conn.createStatement();
		String sql = String.format("select RESERVATION.*, SCREEN.* from RESERVATION inner join SCREEN on "
				+ "RESERVATION.screencode = SCREEN.screencode where RESERVATION.screencode = '%s'", screencode);
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			ArrayList<String> a1 = new ArrayList<>();
			a1.add(rs.getString("reservenumber"));
			a1.add(rs.getString("selectseat"));
			a1.add(rs.getString(3));
			a1.add(rs.getString("theatercode"));
			a1.add(rs.getString("birth"));
			a1.add(rs.getString("phone"));
			a1.add(rs.getString("pw"));
			a1.add(rs.getString("usercheck"));
			a1.add(rs.getString("theatercode"));
			a1.add(rs.getString("starttime"));
			a1.add(rs.getString("endtime"));
			a1.add(rs.getString("soldseats"));
			a1.add(screencode);
			
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}

public ArrayList<ArrayList<String>> joinresmov(String moviecode) {
	
	ArrayList<ArrayList<String>> AddrList = new ArrayList<>();
	
	try {
		stmt = conn.createStatement();
		String sql = String.format("select RESERVATION.*, MOVIE.* from RESERVATION inner join MOVIE on "
				+ "RESERVATION.moviecode = MOVIE.moviecode where RESERVATION.moviecode = = '%s'", moviecode);
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			ArrayList<String> a1 = new ArrayList<>();
			a1.add(rs.getString("reservenumber"));
			a1.add(rs.getString("selectseat"));
			a1.add(rs.getString("screencode"));
			a1.add(rs.getString("theatercode"));
			a1.add(rs.getString("birth"));
			a1.add(rs.getString("phone"));
			a1.add(rs.getString("pw"));
			a1.add(rs.getString("usercheck"));
			a1.add(rs.getString("moviename"));
			a1.add(rs.getString("thema"));
			a1.add(rs.getString("runtime"));
			a1.add(rs.getString("agegroup"));
			a1.add(rs.getString("salesrate"));
			a1.add(rs.getString("opendate"));
			a1.add(moviecode);
			
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}

	public ArrayList<USERINFO> getuserinfo() {
		
		ArrayList<USERINFO> AddrList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String sql = "select *from USERINFO;\r\n"+"";
			
			rs = stmt.executeQuery(sql);
			
			//System.out.println("============ 주소록 목록 ================");
			while(rs.next()) {
				String ID = rs.getString("ID");
				String userpw = rs.getString("userpw");
				String username = rs.getString("username");
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				String ninkname = rs.getString("ninkname");
				String userpoint = rs.getString("userpoint");
				
				USERINFO a1 = new USERINFO(ID, userpw, username, phone, birth, ninkname);
				AddrList.add(a1);
			}
		}catch (Exception e) {
			System.out.println("list DB 작업중 문제 발생!");
			e.printStackTrace();
		}
		return AddrList;
		
	}
public ArrayList<MOVIE> getmovie() {
		
		ArrayList<MOVIE> AddrList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String sql = "select *from MOVIE;\r\n"+"";
			
			rs = stmt.executeQuery(sql);
			
			//System.out.println("============ 주소록 목록 ================");
			while(rs.next()) {
				String moviecode = rs.getString("moviecode");
				String moviename = rs.getString("moviename");
				String thema = rs.getString("thema");
				int runtime = rs.getInt("runtime");
				int agegroup = rs.getInt("agegroup");
			float salesrate = rs.getFloat("salesrate");
			String opendate = rs.getString("opendate");
				
				MOVIE a1 = new MOVIE(moviecode, moviename, thema, runtime, agegroup, salesrate, opendate);
				AddrList.add(a1);
			}
		}catch (Exception e) {
			System.out.println("list DB 작업중 문제 발생!");
			e.printStackTrace();
		}
		return AddrList;
		
	}
public ArrayList<MOVIECENTER> getmoviecenter() {
	
	ArrayList<MOVIECENTER> AddrList = new ArrayList<>();
	try {
		stmt = conn.createStatement();
		String sql = "select *from MOVIECENTER;\r\n"+"";
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			String centercode = rs.getString("centercode");
			String centername = rs.getString("centername");
			String address = rs.getString("address");
			
			MOVIECENTER a1 = new MOVIECENTER(centercode, centername, address);
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}
public ArrayList<THEATER> gettheater() {
	
	ArrayList<THEATER> AddrList = new ArrayList<>();
	try {
		stmt = conn.createStatement();
		String sql = "select *from THEATER;\r\n"+"";
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			String theatercode = rs.getString("theatercode");
			String theatername = rs.getString("theatername");
			String centercode = rs.getString("centercode");
			int seatprice = rs.getInt("seatprice");
			int cleantime = rs.getInt("cleantime");
			String totalseats = rs.getString("totalseats");
			
			THEATER a1 = new THEATER(theatercode, theatername, centercode, seatprice, cleantime, totalseats);
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}
public ArrayList<SCREEN> getscreen() {
	
	ArrayList<SCREEN> AddrList = new ArrayList<>();
	try {
		stmt = conn.createStatement();
		String sql = "select *from SCREEN;\r\n"+"";
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			String screencode = rs.getString("screencode");
			String moviecode = rs.getString("moviecode");
			String theatercode = rs.getString("theatercode");
			String starttime = rs.getString("starttime");
			String endtime = rs.getString("endtime");
			String soldseats = rs.getString("soldseats");
			
			SCREEN a1 = new SCREEN(screencode, moviecode, theatercode, starttime, endtime, soldseats);
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}
public ArrayList<RESERVATION> getreservations() {
	
	ArrayList<RESERVATION> AddrList = new ArrayList<>();
	try {
		stmt = conn.createStatement();
		String sql = "select *from RESERVATION;\r\n"+"";
		
		rs = stmt.executeQuery(sql);
		
		//System.out.println("============ 주소록 목록 ================");
		while(rs.next()) {
			String reservenumber = rs.getString("reservenumber");
			String selectseat = rs.getString("selectseat");
			String moviecode = rs.getString("moviecode");
			String screencode = rs.getString("screencode");
			String theatercode = rs.getString("theatercode");
			String birth = rs.getString("birth");
			String phone = rs.getString("phone");
			String pw = rs.getString("pw");
			String usercheck = rs.getString("usercheck");
			
			RESERVATION a1 = new RESERVATION(reservenumber, selectseat, moviecode, screencode, theatercode, birth, phone, pw, usercheck);
			AddrList.add(a1);
		}
	}catch (Exception e) {
		System.out.println("list DB 작업중 문제 발생!");
		e.printStackTrace();
	}
	return AddrList;
	
}
	
}
class THEATER{
	private String theatercode;
	private String theatername;
	private String centercode;
	private int seatprice;
	private int cleantime;
	private String totalseats;
	
	public THEATER(String theatercode, String theatername, String centercode, 
			int seatprice, int cleantime, String totalseat){
		this.theatercode = theatercode;
		this.theatername = theatername;
		this.centercode = centercode;
		this.seatprice = seatprice;
		this.cleantime = cleantime;
		this.totalseats = totalseat;
	}

	public String getTheatercode() {
		return theatercode;
	}

	public void setTheatercode(String theatercode) {
		this.theatercode = theatercode;
	}

	public String getTheatername() {
		return theatername;
	}

	public void setTheatername(String theatername) {
		this.theatername = theatername;
	}

	public String getCentercode() {
		return centercode;
	}

	public void setCentercode(String centercode) {
		this.centercode = centercode;
	}

	public int getSeatprice() {
		return seatprice;
	}

	public void setSeatprice(int seatprice) {
		this.seatprice = seatprice;
	}

	public int getCleantime() {
		return cleantime;
	}

	public void setCleantime(int cleantime) {
		this.cleantime = cleantime;
	}

	public String getTotalseats() {
		return totalseats;
	}

	public void setTotalseats(String totalseats) {
		this.totalseats = totalseats;
	}
	
	


}
class MOVIE{
	private String moviecode;
	private String moviename;
	private String thema;
	private int runtime;
	private int agegroup;
	private float salesrate;
	private String opendate;
	
	public MOVIE(String moviecode, String moviename, String thema, int runtime, int agegroup, float salesrate, String opendate) {
		this.moviecode = moviecode;
		this.moviename = moviename;
		this.thema = thema;
		this.runtime = runtime;
		this.agegroup = agegroup;
		this.salesrate = salesrate;
		this.opendate = opendate;
	}

	public String getMoviecode() {
		return moviecode;
	}

	public void setMoviecode(String moviecode) {
		this.moviecode = moviecode;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getAgegroup() {
		return agegroup;
	}

	public void setAgegroup(int agegroup) {
		this.agegroup = agegroup;
	}

	public float getSalesrate() {
		return salesrate;
	}

	public void setSalesrate(float salesrate) {
		this.salesrate = salesrate;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}




}
class SCREEN{
	private String screencode;
	private String moviecode;
	private String theatercode;
	private String starttime;
	private String endtime;
	private String soldseats;
	
	public SCREEN(String screencode, String moviecode, String theatercode, String starttime, String endtime, String soldseats) {
		this.screencode = screencode;
		this.moviecode = moviecode;
		this.theatercode = theatercode;
		this.starttime = starttime;
		this.endtime = endtime;
		this.soldseats = soldseats;
	}

	public String getScreencode() {
		return screencode;
	}

	public void setScreencode(String screencode) {
		this.screencode = screencode;
	}

	public String getMoviecode() {
		return moviecode;
	}

	public void setMoviecode(String moviecode) {
		this.moviecode = moviecode;
	}

	public String getTheatercode() {
		return theatercode;
	}

	public void setTheatercode(String theatercode) {
		this.theatercode = theatercode;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSoldseats() {
		return soldseats;
	}

	public void setSoldseats(String soldseats) {
		this.soldseats = soldseats;
	}

	


}
class RESERVATION{
	private String reservenumber;
	private String selectseat;
	private String moviecode;
	private String screencode;
	private String theatercode;
	private String birth;
	private String phone;
	private String pw;
	private String usercheck;
	
	
	public RESERVATION(String reservenumber, String selectseats, String moviecode, String screencode, 
			String theatercode, String birth, String phone, String pw, String usercheck) {
		this.reservenumber = reservenumber;
		this.selectseat = selectseats;
		this.moviecode = moviecode;
		this.screencode = screencode;
		this.theatercode = theatercode;
		this.birth = birth;
		this.phone = phone;
		this.pw = pw;
		this.usercheck = usercheck;
	}


	public String getReservenumber() {
		return reservenumber;
	}


	public void setReservenumber(String reservenumber) {
		this.reservenumber = reservenumber;
	}


	public String getSelectseat() {
		return selectseat;
	}


	public void setSelectseat(String selectseat) {
		this.selectseat = selectseat;
	}


	public String getMoviecode() {
		return moviecode;
	}


	public void setMoviecode(String moviecode) {
		this.moviecode = moviecode;
	}


	public String getScreencode() {
		return screencode;
	}


	public void setScreencode(String screencode) {
		this.screencode = screencode;
	}


	public String getTheatercode() {
		return theatercode;
	}


	public void setTheatercode(String theatercode) {
		this.theatercode = theatercode;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getUsercheck() {
		return usercheck;
	}


	public void setUsercheck(String usercheck) {
		this.usercheck = usercheck;
	}

	

}
class MOVIECENTER{
	private String centercode;
	private String centername;
	private String address;
	
	public MOVIECENTER(String centercode, String centername, String address) {
		this.centercode = centercode;
		this.centername = centername;
		this.address = address;
	}

	public String getCentercode() {
		return centercode;
	}

	public void setCentercode(String centercode) {
		this.centercode = centercode;
	}

	public String getCentername() {
		return centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}
class USERINFO{
	private String ID;
	private String userpw;
	private String username;
	private String phone;
	private String birth;
	private String ninkname;
	private int userpoint;
	
	public USERINFO(String ID, String userpw, String username, String phone, String birth, String ninkname) {
		this.ID = ID;
		this.userpw = userpw;
		this.username = username;
		this.phone = phone;
		this.birth = birth;
		this.ninkname = ninkname;
		this.userpoint = userpoint;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getNinkname() {
		return ninkname;
	}

	public void setNinkname(String ninkname) {
		this.ninkname = ninkname;
	}

	public int getUserpoint() {
		return userpoint;
	}

	public void setUserpoint(int userpoint) {
		this.userpoint = userpoint;
	}


}
