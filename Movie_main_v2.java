package Movie_Reservation;

import java.io.IOException;
import java.util.Scanner;

public class Movie_main_v2 {
public static void main(String[] args) throws IOException{
	Scanner scanner = new Scanner(System.in);
	DBconnectMov db = new DBconnectMov();
	Account_Main account = new Account_Main();
	Add_reser reser = new Add_reser();
	Admin_menu_v2 admin = new Admin_menu_v2();
	String ID = "";  
	outer:
		while (true) {
			String prompt = """
			영화 예매 관리 시스템
			=====================
			1. 영화별 조회
			2. 극장별 조회
			3. 빠른 예매
			4. 예매 조회 및 취소
			""";
			if (ID.equals("")) {
				prompt += """
				5. 로그인 및 회원 가입
				6. 프로그램 종료
				=====================
				원하는 메뉴를 선택하세요 : 	
						""";
			}
				//비회원예매
			else {
				prompt += """
						5. 마이페이지
						6. 로그아웃
						7. 프로그램 종료
						======================
						원하는 메뉴를 선택하세요 : 	
								""";
			}
			System.out.println(prompt);
			int num = scanner.nextInt();
			switch(num) {
			case 1:
				//영화별 조회
				break;
			case 2:
				//극장별 조회
				break;
			case 3:
				//빠른 예매
				reser.addreser(ID, "", "");
				break;
			case 4:
			//예매 조회 및 취소
				break;
			case 5:
				if (ID.equals("")) {
				ID = account.account_main();
				//break;
				}
				else 
					//마이페이지 함수
				//비회원 - 로그인 및 회원가입
				//회원 - 마이페이지
				break;
			case 6:
				if (ID.equals("")) 
					break outer;
				else {
					ID = "";
				break;
				}
			case 7:
				if (ID.equals("")) {
					System.out.println("올바른 번호를 입력해주세요.");
				break;
				}
				else
					break outer;
			case 501:
				admin.admin_menu();
				break;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				break;
			}
			
		}
}
}
