package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Add_Theater {
public void addtheater() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<Theater> theaters = db.getTheater();
	ArrayList<moviecenter> moviecenters = db.getmoviecenter();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> thecodes = new ArrayList<>();
	ArrayList<String> movcenters = new ArrayList<>();
	ArrayList<String> thenames = new ArrayList<>();
	
	for (int i=0; i<theaters.size();i++) 
		thecodes.add(theaters.get(i).getThecode());
	
	
	for (int i=0; i<moviecenters.size();i++)
		movcenters.add(moviecenters.get(i).getMovcenter());
	
System.out.println("상영 등록 메뉴입니다.");
	
	String thecode = "";
	if (thecodes.size() == 0)
		thecode = "T001";
	else {
		int thecod = Integer.parseInt(thecodes.get(thecodes.size()-1).substring(1, 4));
		thecod ++;
		if (thecod<10)
			thecode = "T00" + thecod;
		else if (thecod>=10&&thecod<100)
			thecode = "T0" + thecod;
		else
			thecode = "T" + thecod;
	}
	
	System.out.printf("지금 저장하는 관의 CODE는 %s로 등록됩니다\n", thecode);
	
	System.out.println("영화관코드\t지점명\t주소");
	System.out.println("=======================================");
	for (moviecenter moviecenter : moviecenters)
		System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
	System.out.println("관 추가하실 지점의 코드를 입력해주세요.");
	String movcenter = scanner.next();
	if (!movcenters.contains(movcenter))
System.out.println("올바른 지점의 코드르르 입력해주세요.");
	else {
		for (int i=0; i<moviecenters.size();i++) {
			if (moviecenters.get(i).getMovcenter().equals(movcenter)) {
				String movpoint = moviecenters.get(i).getMovpoint();
				for (int j=0; j<theaters.size();j++) {
				if (theaters.get(i).getMovcenter().equals(movcenter)) 
					thenames.add(theaters.get(i).getThename());
				}
				if (thenames.size() == 0)
					System.out.printf("현재 %s지점에 등록된 관이 없습니다.\n", moviecenters.get(i).getMovpoint());
				else {
				System.out.printf("현재 %s지점에 등록된 관입니다.\n", moviecenters.get(i).getMovpoint());
					for (String thename : thenames) 
						System.out.println(thename);
				}
				System.out.println("추가할 관의 이름을 입력해주세요.\nex)IMAX관, 1관");
				String thename = scanner.next();
				System.out.printf("%s의 가격을 입력해주세요.\n", thename);
				int price = scanner.nextInt();
				System.out.printf("%s의 청소시간을 입력해주세요.\n", thename);
				int cleantime = scanner.nextInt();
				System.out.printf("%s의 맨 끝 좌석을 입력해주세요.\nex)I09", thename);
				String seat = scanner.next();
				System.out.printf("지점 : %s\n상영관 이름 : %s\n금액 : %s\n청소시간 : %s\n맨 끝좌석 : %s\n",
						movpoint, thename, price, cleantime, seat);
				System.out.println("위 정보로 등록하시려면 '1'번\n취소하시려면 '2'번을 눌러주세요.\n");
				int num = scanner.nextInt();
				switch(num) {
				case 1:
					db.insertTheater(thecode, thename, movpoint, price, cleantime, movcenter);
					break;
				case 2:
					System.out.println("상영관 등록이 취소되었습니다.");
					break;
					default:
						System.out.println("올바른 번호를 입력해주세요.");
						break;
				}
				}
		}
	}
}
public void changetheater() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<Theater> theaters = db.getTheater();
	ArrayList<moviecenter> moviecenters = db.getmoviecenter();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> thecodes = new ArrayList<>();
	ArrayList<String> movcenters = new ArrayList<>();
	ArrayList<String> thenames = new ArrayList<>();
	
	for (int i=0; i<theaters.size();i++) 
		thecodes.add(theaters.get(i).getThecode());
	
	
	for (int i=0; i<moviecenters.size();i++)
		movcenters.add(moviecenters.get(i).getMovcenter());
	
	System.out.println("영화관코드\t지점명\t주소");
	System.out.println("=======================================");
	for (moviecenter moviecenter : moviecenters)
		System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
	System.out.println("관 삭제하실 지점의 코드를 입력해주세요.");
	String movcenter = scanner.next();
	if (!movcenters.contains(movcenter))
		System.out.println("올바른 코드를 입력해주세요.");
	else {
		String movpoint = ""; 
		for (moviecenter moviecenter : moviecenters) {
			if (moviecenter.getMovcenter().equals(movcenter))
				movpoint = moviecenter.getMovpoint();
		}
		
		System.out.printf("현재 %s지점에 있는 상영관 목록입니다.\n", movpoint);
		System.out.println("상영관 코드\t상영관 이름\t상영관 가격\t청소시간");
		System.out.println("====================================================");
		for (Theater theater : theaters) {
			if (theater.getMovcenter().equals(movcenter))
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", theater.getThecode(), theater.getThename(), 
						theater.getPrice()+"원", theater.getCleantime()+"분");
		}
		System.out.println("수정하실 상영관의 코드를 입력해주세요.");
		String thecode = scanner.next();
		if (!thecodes.contains(thecode))
			System.out.println("등록된 상영관 코드가 아닙니다.");
		else {
			String thename = "";
			int price = 0;
			int cleantime = 0;
			
			for (Theater theater : theaters) {
				if (theater.getThecode().equals(thecode)) {
					System.out.printf("상영관 이름 : %s\n지점명 : %s\n상영관 가격 : %s\n청소시간 : %s\n", theater.getThename(), movpoint, 
							theater.getPrice(), theater.getCleantime());
				thename = theater.getThename();
				price = theater.getPrice();
				cleantime = theater.getCleantime();
				}
				
			}
			System.out.printf("%s을 수정하시려면 수정 하실 상영관의 이름을 입력해주세요.\n건너뛰려면 '501'을 입력해주세요.\n", thename);
			String a = scanner.next();
			if (!a.equals("501"))
				thename = a;
			System.out.printf("%s의 가격을 수정하시려면 수정 하실 가격을 입력해주세요.\n건너뛰려면 '501'을 입력해주세요.\n", thename);
			int b= scanner.nextInt();
			if (b!=501)
				price = b;
			System.out.printf("%s의 청소시간을 수정하시려면 수정 하실 청소시간을 입력해주세요.\n건너뛰려면 '501'을 입력해주세요.\n", thename);
			int c = scanner.nextInt();
			if (c!=501)
				cleantime = c;
			
			System.out.printf("상영관 이름 : %s\n지점명 : %s\n상영관 가격 : %s\n청소시간 : %s\n", thename, movpoint, 
					price, cleantime);
			System.out.println("위 정보로 수정하시려면 '1'번\n취소하시려면 '2'번을 입력해주세요.");
			int num = scanner.nextInt();
			switch(num) {
			case 1:
				db.updateTheater(thecode, thename, movcenter, price, cleantime, movpoint);
				break;
			case 2:
				System.out.println("상영관 삭제가 취소되었습니다.");
				break;
				default:
					System.out.println("올바른 번호를 입력해주세요.");
					break;
			}
			
		}
	}
}
public void deletetheater() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<Theater> theaters = db.getTheater();
	ArrayList<moviecenter> moviecenters = db.getmoviecenter();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> thecodes = new ArrayList<>();
	ArrayList<String> movcenters = new ArrayList<>();
	
	for (int i=0; i<theaters.size();i++) 
		thecodes.add(theaters.get(i).getThecode());
	
	
	for (int i=0; i<moviecenters.size();i++)
		movcenters.add(moviecenters.get(i).getMovcenter());
	
	System.out.println("영화관코드\t지점명\t주소");
	System.out.println("=======================================");
	for (moviecenter moviecenter : moviecenters)
		System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
	System.out.println("관 삭제하실 지점의 코드를 입력해주세요.");
	String movcenter = scanner.next();
	if (!movcenters.contains(movcenter))
		System.out.println("올바른 코드를 입력해주세요.");
	else {
		String movpoint = ""; 
		for (moviecenter moviecenter : moviecenters) {
			if (moviecenter.getMovcenter().equals(movcenter))
				movpoint = moviecenter.getMovpoint();
		}
		
		System.out.printf("현재 %s지점에 있는 상영관 목록입니다.\n", movpoint);
		System.out.println("상영관 코드\t상영관 이름\t상영관 가격\t청소시간");
		System.out.println("====================================================");
		for (Theater theater : theaters) {
			if (theater.getMovcenter().equals(movcenter))
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n", theater.getThecode(), theater.getThename(), 
						theater.getPrice()+"원", theater.getCleantime()+"분");
		}
		System.out.println("수정하실 상영관의 코드를 입력해주세요.");
		String thecode = scanner.next();
		if (!thecodes.contains(thecode))
			System.out.println("등록된 상영관 코드가 아닙니다.");
		else {
			String thename = "";
			for (Theater theater : theaters) {
				if (theater.getThecode().equals(thecode)) {
					System.out.printf("상영관 이름 : %s\n지점명 : %s\n상영관 가격 : %s\n청소시간 : %s\n", theater.getThename(), movpoint, 
							theater.getPrice(), theater.getCleantime());
				thename = theater.getThename();
				}
			}
			System.out.println("위 상영관을 지우시려면 '1'번\n취소하시려면 '2'번을 입력해주세요.");
			int num = scanner.nextInt();
			switch(num) {
			case 1:
				db.deleteTheater(movpoint, thecode, thename);
				break;
			case 2:
				System.out.println("상영관 삭제가 취소되었습니다.");
				break;
				default:
					System.out.println("올바른 번호를 입력해주세요.");
					break;
			}
			
		}
	}
	
}
}
