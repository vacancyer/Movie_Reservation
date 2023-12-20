package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Add_reser {
public void addreser(String ID, String moviecode, String centercode) {
	DBconnectMov db = new DBconnectMov();
	ArrayList<MOVIE> movies = new ArrayList<>();
	ArrayList<MOVIECENTER> moviecenters = new ArrayList<>();
	ArrayList<USERINFO> userinfos = new ArrayList<>();
	ArrayList<THEATER> theaters = new ArrayList<>();
	Look_tables look = new Look_tables();
	reservation_seat reser = new reservation_seat();
	Scanner scanner = new Scanner(System.in);
	String screencode = "";
	String theatercode = "";
	
	int count = 0;
	int num = 0;
	
	if (ID.equals("")&&moviecode.equals("")&&centercode.equals(""))
		num = 1; 
	else if (ID.equals("")&&moviecode.equals(""))
		num = 2;
	else if (ID.equals("")&&centercode.equals(""))
		num = 3;
	else if (ID.equals(""))
		num = 4;
	else if (moviecode.equals("")&&centercode.equals(""))
		num = 5;
	else if (moviecode.equals(""))
		num = 6;
	else if (centercode.equals(""))
		num = 7;
	else
		num = 8;
	
	switch(num) {
	case 1:
	look.look_movie();
	System.out.println("위 영화 중 선택하실 영화의 코드를 입력해주세요");
	moviecode = scanner.next();
	look.look_moviecenter();
	System.out.println("위 영화 중 선택하실 영화관의 코드를 입력해주세요.");
	centercode = scanner.next();
	System.out.println("선택하신 영화 및 영화관이 포함 된 목록입니다.");
	for (THEATER theater : theaters) {
		if (theater.getCentercode().equals(centercode))
			theatercode = theater.getTheatercode();
	}
	look.look_screen(moviecode, theatercode);
	System.out.println("위 상영 정보들 중 선택하실 상영 정보의 코드를 입력해주세요.");
	screencode = scanner.next();
	System.out.println("해당 상영의 정보입니다.");
	System.out.println("먼저 예약하실 분들의 인원수를 입력해주세요.");
	count = scanner.nextInt();
	ArrayList<String> seats = reser.reservationseat(count, screencode);
	
		break;
	case 2:
		
		break;
	case 3:
		
		break;
	case 4:
		
		break;
	case 5:
		
		break;
	case 6:
		
		break;
	case 7:
		
		break;
	case 8:
		
		break;
	
	}
	
	
}
}
