package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Add_Screen {
public void addscreen() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<screen> screens = db.getscreen();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> sccodes = new ArrayList<>();
	ArrayList<movies> moviess = new ArrayList<>();
	ArrayList<Theater> theaters = new ArrayList<>();
	ArrayList<moviecenter> moviecenters = new ArrayList<>();
	ArrayList<String> movcenters = new ArrayList<>();
	ArrayList<String> movcodes = new ArrayList<>();
	ArrayList<String> thecodes = new ArrayList<>();
	
	for (int i=0; i<screens.size();i++)
		sccodes.add(screens.get(i).getScreencode());
	for (moviecenter moviecente : moviecenters)
		movcenters.add(moviecente.getMovcenter());
	for (Theater theater : theaters)
		thecodes.add(theater.getThecode());
	
	System.out.println("상영 등록 메뉴입니다.");
	
	String sccode = "";
	if (sccodes.size() == 0)
		sccode = "S001";
	else {
		int sccod = Integer.parseInt(sccodes.get(sccodes.size()-1).substring(1, 4));
		sccod ++;
		if (sccod<10)
			sccode = "S00" + sccod;
		else if (sccod>=10&&sccod<100)
			sccode = "S0" + sccod;
		else
			sccode = "S" + sccod;
	}
	System.out.printf("지금 저장하는 상영 정보의 CODE는 %s로 등록됩니다\n", sccode);
	
	System.out.println("영화관코드\t지점명\t주소");
	System.out.println("=======================================");
	for (moviecenter moviecenter : moviecenters)
		System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
	
	System.out.println("먼저 상영정보를 등록하실 영화관의 코드를 입력해주세요.");
	String movcenter = scanner.next();
	if (!movcenters.contains(movcenter))
		System.out.println("올바른 영화관 코드를 입력해주세요.");
	else {
		String movpoint = "";
		for (moviecenter moviecenter : moviecenters) {
			if (moviecenter.getMovcenter().equals(movcenter))
		movpoint = moviecenter.getMovpoint();
		}
		System.out.printf("%s 지점에 등록됩니다\n", movpoint);
		
		System.out.printf("현재 %s지점에 있는 상영관 목록입니다.\n", movpoint);
		System.out.println("상영관 코드\t상영관 이름\t상영관 가격\t청소시간");
		System.out.println("====================================================");
		for (Theater theater : theaters) {
			if (theater.getMovcenter().equals(movcenter))
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", theater.getThecode(), theater.getThename(), 
						theater.getPrice()+"원", theater.getCleantime()+"분");
		}
		System.out.println("상영정보를 등록하실 상영관의 코드를 입력해주세요.");
		String thecode = scanner.next();
		if (!thecodes.contains(thecode))
			System.out.println("등록된 상영관 코드가 아닙니다.");
		else {
		String thename = "";
		for (Theater theater : theaters) {
			if (theater.getThecode().equals(thecode))
				thename = theater.getThename();
		}
		System.out.printf("%s 지점 %s에 등록됩니다.\n", movpoint, thename);
		
		System.out.println("영화코드\t영화제목\t장르\t런타임\t연령가");
		System.out.println("=====================================");
		for (movies movie : moviess)
			System.out.printf("%s\t%s\t%s\t%s\t%s\n", movie.getMovcode(), 
					movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세 이상 관람가");
		System.out.println("상영정보를 등록하실 영화의 '영화코드'를 입력해주세요.");
		String movcode = scanner.next();
		if (!movcodes.contains(movcode))
			System.out.println("올바른 영화코드를 입력해주세요.");
		else {
			String moviename = "";
			for (movies movie : moviess) {
				if (movie.getMovcode().equals(movcode))
					moviename = movie.getMovname();
			}
			int seat = 0;
		
		}	
		}
	}
	
}
public void changescreen() {
	
}
public void deletescreen() {
	
}
}
