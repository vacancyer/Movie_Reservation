package Movie_Reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin_menu_v2 {
public void admin_menu() throws IOException{
	DBconnectMov db = new DBconnectMov();
	Scanner scanner = new Scanner(System.in);
	Add_Movie movie = new Add_Movie();
	Add_Theater theater = new Add_Theater();
	Add_moviecenter moviecenter = new Add_moviecenter();
	Make_file file = new Make_file();
	ArrayList<movies> movies = db.getmovies();
	Add_Screen screen = new Add_Screen();
	
	String prompt = """
			==========================
			=                        =
			=         관리자모드         =
			=                        =
			=        1. 영화 등록       =
			=        2. 영화 수정       =
			=        3. 영화 삭제       =
			=      4. 영화 상영 추가      =
			=      5. 영화 상영 수정      =
			=      6. 영화 상영 삭제      =
			=      7. 영화 목록 조회      =
			=        8. 지점 추가       =
			=        9. 지점 수정       =
			=       10. 지점 삭제       =
			=      13. 영화관 추가       =
			=      14. 영화관 수정       =
			=      15. 영화관 삭제       =
			=   11. 영화 목록 파일 생성    =
			=   12. 예매 내역 파일 생성    = 
			=        16. 끝내기        =
			=                        =
			==========================
			""";
	
	outer:
		while(true) {
			System.out.println(prompt);
			int num = scanner.nextInt();
			switch(num) {
			case 1:
				movie.add_movie();
				break;
			case 2:
				movie.change_movie();
				break;
			case 3:
				movie.delete_movie();
				break;
			case 4:
				screen.addscreen();
				break;
			case 5:
				screen.changescreen();
				break;
			case 6:
				screen.deletescreen();
				break;
			case 7:
			System.out.println("영화코드\t영화제목\t장르\t런타임\t연령가");
			System.out.println("=====================================");
			for (movies movi : movies)
				System.out.printf("%s\t%s\t%s\t%s\t%s\n", movi.getMovcode(), 
						movi.getMovname(), movi.getMovthema(), movi.getRuntime()+"분", movi.getAgegroup()+"세 이상 관람가");
				break;
			case 8:
				moviecenter.addmoviecenter();
				break;
			case 9:
				moviecenter.changemoviecenter();
				break;
			case 10:
				moviecenter.deletemoviecenter();
				break;
			case 11:
				file.movielist();
				break;
			case 12:
				file.reserlist();
				break;
			case 13:
				theater.addtheater();
				break;
			case 14:
				theater.changetheater();
				break;
			case 15:
				theater.deletetheater();
				break;
			case 16:
				break outer;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				break;
			}
		}
}
}
