package Movie_Reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Make_file {
public void movielist() throws IOException{
	Scanner scanner = new Scanner(System.in);
	DBconnectMov db = new DBconnectMov();
	ArrayList<movies> movies = db.getmovies();
	PrintWriter pw = new PrintWriter("c://works//movie_list.csv", "euc-kr");
	String prompt = """
			==========================
			=                        =
			=        영화 목록 출력       =
			=                        =
			=    1. 영화 제목순(오름차순)    =
			=    2. 영화 제목순(내림차순)    =
			=    3. 영화 코드 순(오름차순)   =
			=    4. 영화 코드 순(내림차순)   =
			=    5. 예매율 순(오름차순)     =
			=    6. 예매율 순(내림차순)     =
			=       7. 끝내기          =
			=                        =
			==========================
			
			""";
	ArrayList<String> movienames = new ArrayList<>();
	ArrayList<String> moviecodes = new ArrayList<>();
	ArrayList<String> resrates = new ArrayList<>();
	outer:
		while(true) {
			System.out.println(prompt);
			int num = scanner.nextInt();
			switch(num) {
			case 1:
				System.out.println("영화 제목순(오름차순)으로 파일이 생성됩니다.");
				for (movies movie : movies)
					movienames.add(movie.getMovname()+movie.getMovcode());
				movienames.sort(Comparator.naturalOrder());
				pw.println("영화코드,영화이름,영화장르,런타임,연령가,예매율");
				for (String moviename : movienames) {
				for (movies movie : movies) {
					 String moviecode = moviename.substring(moviename.length()-4,moviename.length());	
					if (moviecode.equals(movie.getMovcode()))
							pw.printf("%s,%s,%s,%s,%s,%s\n",
									movie.getMovcode(),movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세",
									movie.getResrate()+"%");
					}
				}
				pw.close();
				System.out.println("'c://works'에 'movie_list.csv'파일이 생성되었습니다.");
				break;
			case 2:
				System.out.println("영화 제목순(내림차순)으로 파일이 생성됩니다.");
				for (movies movie : movies)
					movienames.add(movie.getMovname()+movie.getMovcode());
				movienames.sort(Comparator.reverseOrder());
				pw.println("영화코드,영화이름,영화장르,런타임,연령가,예매율");
				for (String moviename : movienames) {
				for (movies movie : movies) {
					 String moviecode = moviename.substring(moviename.length()-4,moviename.length());	
					if (moviecode.equals(movie.getMovcode()))
							pw.printf("%s,%s,%s,%s,%s,%s\n",
									movie.getMovcode(),movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세",
									movie.getResrate()+"%");
					}
				}
				pw.close();
				System.out.println("'c://works'에 'movie_list.csv'파일이 생성되었습니다.");
				break;
			case 3:
				System.out.println("영화 코드순(오름차순)으로 파일이 생성됩니다.");
				for (movies movie : movies)
					moviecodes.add(movie.getMovcode());
				moviecodes.sort(Comparator.naturalOrder());
				pw.println("영화코드,영화이름,영화장르,런타임,연령가,예매율");
				for (String moviecode : moviecodes) {
				for (movies movie : movies) {	
					if (moviecode.equals(movie.getMovcode()))
							pw.printf("%s,%s,%s,%s,%s,%s\n",
									movie.getMovcode(),movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세",
									movie.getResrate()+"%");
					}
				}
				pw.close();
				System.out.println("'c://works'에 'movie_list.csv'파일이 생성되었습니다.");
				break;
			case 4:
				System.out.println("영화 코드순(내림차순)으로 파일이 생성됩니다.");
				for (movies movie : movies)
					moviecodes.add(movie.getMovcode());
				moviecodes.sort(Comparator.reverseOrder());
				pw.println("영화코드,영화이름,영화장르,런타임,연령가,예매율");
				for (String moviecode : moviecodes) {
				for (movies movie : movies) {	
					if (moviecode.equals(movie.getMovcode()))
							pw.printf("%s,%s,%s,%s,%s,%s\n",
									movie.getMovcode(),movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세",
									movie.getResrate()+"%");
					}
				}
				pw.close();
				System.out.println("'c://works'에 'movie_list.csv'파일이 생성되었습니다.");
				break;
			case 5:
				System.out.println("에매율순(오름차순)으로 파일이 생성됩니다.");
				for (movies movie : movies)
					resrates.add(movie.getResrate()+movie.getMovcode());
				resrates.sort(Comparator.naturalOrder());
				pw.println("영화코드,영화이름,영화장르,런타임,연령가,예매율");
				for (String resrate : resrates) {
				for (movies movie : movies) {
					 String moviecode = resrate.substring(resrate.length()-4,resrate.length());	
					if (moviecode.equals(movie.getMovcode()))
							pw.printf("%s,%s,%s,%s,%s,%s\n",
									movie.getMovcode(),movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세",
									movie.getResrate()+"%");
					}
				}
				pw.close();
				System.out.println("'c://works'에 'movie_list.csv'파일이 생성되었습니다.");
				break;
			case 6:
				System.out.println("에매율순(내림차순)으로 파일이 생성됩니다.");
				for (movies movie : movies)
					resrates.add(movie.getResrate()+movie.getMovcode());
				resrates.sort(Comparator.reverseOrder());
				pw.println("영화코드,영화이름,영화장르,런타임,연령가,예매율");
				for (String resrate : resrates) {
				for (movies movie : movies) {
					 String moviecode = resrate.substring(resrate.length()-4,resrate.length());	
					if (moviecode.equals(movie.getMovcode()))
							pw.printf("%s,%s,%s,%s,%s,%s\n",
									movie.getMovcode(),movie.getMovname(), movie.getMovthema(), movie.getRuntime()+"분", movie.getAgegroup()+"세",
									movie.getResrate()+"%");
					}
				}
				pw.close();
				System.out.println("'c://works'에 'movie_list.csv'파일이 생성되었습니다.");
				break;
			case 7:
				System.out.println("파일 생성이 종료됩니다.");
				break outer;
			default:
				System.out.println("올바른 번호를 입력해 주세요.");
				break;
			}
		}
}
public void reserlist() throws IOException{
	Scanner scanner = new Scanner(System.in);
	DBconnectMov db = new DBconnectMov();
	ArrayList<movies> movies = db.getmovies();
	PrintWriter pw = new PrintWriter("c://works//movie_list.csv", "euc-kr");
	
	System.out.println("영화/영화관/상영 날짜");
}
}
