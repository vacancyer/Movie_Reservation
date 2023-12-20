package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Add_moviecenter {
public void addmoviecenter() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<moviecenter> moviecenter = db.getmoviecenter();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> movcenters = new ArrayList<>();
	
	for (int i=0; i<moviecenter.size();i++)
		movcenters.add(moviecenter.get(i).getMovcenter());
	
	System.out.println("영화 등록 메뉴입니다.");
	
	String movcenter = "";
	if (movcenters.size() == 0)
		movcenter = "C001";
	else {
		int movcent = Integer.parseInt(movcenters.get(movcenters.size()-1).substring(1, 4));
		movcent ++;
		if (movcent<10)
			movcenter = "C00" + movcent;
		else if (movcent>=10&&movcent<100)
			movcenter = "C0" + movcent;
		else
			movcenter = "C" + movcent;
	}
	System.out.printf("지금 저장하는 영화관의 CODE는 %s로 등록됩니다\n", movcenter);
	
	System.out.println("등록하실 영화관의 지점 명을 입력해주세요.");
	String movpoint = scanner.nextLine();
	System.out.printf("%s의 주소를 입력해 주세요.\n", movpoint);
	String movaddress = scanner.nextLine();
	System.out.printf("지점명 : %s\n주소 : %s\n", movpoint, movaddress);
	System.out.println("위 정보로 영화관 등록하시려면 '1'번\n취소하시려면 '2'번을 입력해주세요.\n");
	int num = scanner.nextInt();
	switch(num) {
	case 1:
		db.insertMoviecenter(movcenter, movpoint, movaddress);
		break;
	case 2:
		System.out.println("영화관 등록이 취소되었습니다.");
		break;
		default :
			System.out.println("올바른 번호를 입력해주세요.");
			break;
	}
}
public void changemoviecenter() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<moviecenter> moviecenters = db.getmoviecenter();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> movcenters = new ArrayList<>();
	
	for (int i=0; i<moviecenters.size();i++)
		movcenters.add(moviecenters.get(i).getMovcenter());
	
	System.out.println("영화관코드\t지점명\t주소");
	System.out.println("=======================================");
	for (moviecenter moviecenter : moviecenters)
		System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
	
	System.out.println("수정하실 영화관의 코드를 입력해주세요.");
	String movcenter = scanner.next();
	scanner.nextLine();
	if (!movcenters.contains(movcenter))
		System.out.println("등록된 영화관 코드가 아닙니다.");
	else {
		System.out.println("영화관코드\t지점명\t주소");
		System.out.println("=======================================");
		for (moviecenter moviecenter : moviecenters) {
		if (movcenter.equals(moviecenter.getMovcenter())) {
			System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
		String movpoint = moviecenter.getMovpoint();
		String movaddress = moviecenter.getMovaddress();
		System.out.printf("지점명을 수정하시겠습니까?\n수정하시려면 <%s> 영화관의 지점명을 입력해 주세요.\n건너뛰려면 '501'을 입력해주세요.\n", movpoint);
		String a = scanner.nextLine();
		if (!a.equals("501"))
			movpoint = a;
		System.out.printf("주소를 수정하시겠습니까?\n수정하시려면 <%s> 영화관의 주소를 입력해 주세요.\n건너뛰려면 '501'을 입력해주세요.\n", movpoint);
		String b = scanner.nextLine();
		if (!b.equals("501"))
			movaddress = b;
		System.out.println("수정하신 정보입니다.\n입력하신 정보로 수정하시려면 '1'번\n취소하시려면 '2'번을 입력해주세요.");
		System.out.printf("지점명 : %s\n주소 : %s\n", movpoint, movaddress);
		int num = scanner.nextInt();
		switch(num) {
		case 1:
			db.updateMoviecenter(movcenter, movpoint, movaddress);
			break;
		case 2:
			System.out.println("영화관 수정이 취소되었습니다.");
			break;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				break;
		}
		}
		}
		
		}
}
public void deletemoviecenter() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<moviecenter> moviecenters = db.getmoviecenter();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> movcenters = new ArrayList<>();
	
	for (int i=0; i<moviecenters.size();i++)
		movcenters.add(moviecenters.get(i).getMovcenter());
	
	System.out.println("영화관코드\t지점명\t주소");
	System.out.println("=======================================");
	for (moviecenter moviecenter : moviecenters)
		System.out.printf("%s\t%s\t%s\n", moviecenter.getMovcenter(), moviecenter.getMovpoint(), moviecenter.getMovaddress());
	
	System.out.println("삭제하실 영화관의 코드를 입력해주세요.");
	String movcenter = scanner.next();
	scanner.nextLine();
	if (!movcenters.contains(movcenter))
		System.out.println("등록된 영화관 코드가 아닙니다.");
	else {
		for (int i=0; i<moviecenters.size();i++) {
			if (movcenter.equals(moviecenters.get(i).getMovcenter())) {
				System.out.printf("정말 <%s>영화관을 삭제하시겠습니까?\n삭제하시려면 '1'번\n취소하시려면 '2'번을 눌러주세요.\n", moviecenters.get(i).getMovpoint());
				int num = scanner.nextInt();
			 switch(num) {
			 case 1:
				 db.deleteMovie(movcenter, moviecenters.get(i).getMovpoint());
				 break;
			 case 2:
				 System.out.println("영화관 삭제가 취소되었습니다.");
				 break;
				 default:
					 System.out.println("올바른 번호를 입력해주세요.");
					 break;
			 }
			}
		}
	}
}

}
