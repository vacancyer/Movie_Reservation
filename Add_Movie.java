package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Add_Movie {
public void add_movie() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<movies> movies = db.getmovies();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> movcodes = new ArrayList<>();
	
	for (int i=0; i<movies.size();i++)
		movcodes.add(movies.get(i).getMovcode());
	
	System.out.println("영화 등록 메뉴입니다.");
	
	String movcode = "";
	if (movcodes.size() == 0)
		movcode = "M001";
	else {
		int movcod = Integer.parseInt(movcodes.get(movcodes.size()-1).substring(1, 4));
		movcod ++;
		if (movcod<10)
			movcode = "M00" + movcod;
		else if (movcod>=10&&movcod<100)
			movcode = "M0" + movcod;
		else
			movcode = "M" + movcod;
	}
	System.out.printf("지금 저장하는 영화의 CODE는 %s로 등록됩니다\n", movcode);
	
	System.out.println("등록하실 영화의 이름을 입력해주세요.");
	String movname = scanner.nextLine();
	System.out.printf("등록하신 영화인 <%s>의 장르를 입력해주세요.\n", movname);
	String movthema = scanner.next();
	System.out.printf("등록하신 영화인 <%s>의 런타임을 입력해주세요.\n", movname);
	int runtime = scanner.nextInt(); 
	System.out.printf("등록하신 영화인 <%s>의 연령가를 입력해주세요.\n", movname);
	int agegroup = scanner.nextInt();
	float resrate = 0;
	System.out.printf("제목 : %s\n장르 : %s\n런타임 : %s분\n연령가 : %s세 이상 관람가\n", movname, movthema, runtime, agegroup);
	System.out.println("위 정보로 영화 추가하시려면 '1'번\n취소하시려면 '2'번을 눌러주세요.");
	int num = scanner.nextInt();
	switch(num) {
	case  1 :
		db.insertMovie(movcode, movname, movthema, runtime, agegroup, resrate);
		System.out.println("영화 등록이 완료되었습니다.");
		break;
	case 2 :
		System.out.println("영화 등록이 취소되었습니다.");
		break;
	default:
		System.out.println("올바른 번호를 입력해주세요.");
		break;
	}
}
public void change_movie() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<movies> movies = db.getmovies();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> movcodes = new ArrayList<>();
		
		for (int i=0; i<movies.size();i++)
			movcodes.add(movies.get(i).getMovcode());
	System.out.println("영화코드\t영화제목\t장르\t런타임\t연령가");
	System.out.println("=====================================");
	for (movies movie : movies)
		System.out.printf("%s\t%s\t%s\t%s\t%s\n", movie.getMovcode(), 
				movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세 이상 관람가");
	System.out.println("수정하실 영화의 '영화코드'를 입력해주세요.");
	String movcode = scanner.next();
	scanner.nextLine();
	if (!movcodes.contains(movcode))
		System.out.println("등록된 영화코드가 아닙니다.");
	else {
	System.out.println("선택하신 영화의 정보입니다.");
	for (int i=0; i<movies.size();i++) {
		if (movcode.equals(movies.get(i).getMovcode())) {
	System.out.printf("제목 : %s\n장르 : %s\n런타임 : %s분\n연령가 : %s세 이상 관람가\n", 
			movies.get(i).getMovname(), movies.get(i).getMovthema(), movies.get(i).getRuntime(), movies.get(i).getAgegroup());
	String movname = movies.get(i).getMovname();
	String movthema = movies.get(i).getMovthema();
	int runtime = movies.get(i).getRuntime();
	int agegroup = movies.get(i).getAgegroup();
	
	System.out.printf("제목을 수정하시겠습니까?\n수정하시려면 <%s> 영화의 제목을 입력해 주세요.\n건너뛰려면 '501'을 입력해주세요.\n", movname);
	String a = scanner.nextLine();
	if (!a.equals("501"))
		movname = a;
	System.out.printf("장르를 수정하시겠습니까?\n수정하시려면 <%s> 영화의 장르를 입력해 주세요.\n건너뛰려면 '501'을 입력해주세요.\n", movname);
	String b = scanner.next();
	if (!b.equals("501"))
		movthema = b;
	System.out.printf("런타임을 수정하시겠습니까?\n수정하시려면 <%s> 영화의 런타임을 입력해 주세요.\n건너뛰려면 '501'을 입력해주세요.\n", movname);
	int c = scanner.nextInt();
	if (c!=501)
		runtime = c;
	System.out.printf("연령가를 수정하시겠습니까?\n수정하시려면 <%s> 영화의 연령가를 입력해 주세요.\n건너뛰려면 '501'을 입력해주세요.\n", movname);
	int d = scanner.nextInt();
	if (d!=501)
		agegroup = d;
	
	System.out.println("수정하신 정보입니다.\n입력하신 정보로 수정하시려면 '1'번\n취소하시려면 '2'번을 입력해주세요.");
	System.out.printf("제목 : %s\n장르 : %s\n런타임 : %s분\n연령가 : %s세 이상 관람가\n", movname, movthema, runtime, agegroup);
	int num = scanner.nextInt();
	switch(num) {
	case 1:
		db.updateMovie(movcode, movname, movthema, runtime, agegroup, movies.get(i).getResrate());
		break;
	case 2:
		System.out.println("영화수정이 취소되었습니다.");
		break;
		default:
			System.out.println("올바른 번호를 입력해주세요.");
			break;
	}
	}
	}
	}
}
public void delete_movie() {
	DBconnectMov db = new DBconnectMov();
	ArrayList<movies> movies = db.getmovies();
	Scanner scanner = new Scanner(System.in);
ArrayList<String> movcodes = new ArrayList<>();
	
	for (int i=0; i<movies.size();i++)
		movcodes.add(movies.get(i).getMovcode());
	
	System.out.println("영화코드\t영화제목\t장르\t런타임\t연령가");
	System.out.println("=====================================");
	for (movies movie : movies)
		System.out.printf("%s\t%s\t%s\t%s\t%s\n", movie.getMovcode(), 
				movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세 이상 관람가");
	
	System.out.println("삭제할 영화의 '영화코드'를 입력해주세요.");
	String movcode = scanner.next();
	if (!movcodes.contains(movcode))
		System.out.println("등록된 영화코드가 아닙니다.");
	else {
		for (int i=0; i<movies.size();i++) {
			if (movcode.equals(movies.get(i).getMovcode())) {
				System.out.printf("정말 <%s>영화를 삭제하시겠습니까?\n삭제하시려면 '1'번\n취소하시려면 '2'번을 눌러주세요.\n", movies.get(i).getMovname());
				int num = scanner.nextInt();
			 switch(num) {
			 case 1:
				 db.deleteMovie(movcode, movies.get(i).getMovname());
				 break;
			 case 2:
				 System.out.println("영화 삭제가 취소되었습니다.");
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
