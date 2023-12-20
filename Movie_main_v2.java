package Movie_Reservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Movie_main_v2 {
public static void main(String[] args) throws IOException{
	String id = "";
	Scanner scanner = new Scanner(System.in);
	DBconnectMov db = new DBconnectMov();
	Account_Main account = new Account_Main();
	Admin_menu_v2 admin = new Admin_menu_v2();
	String prompt = """
			==========================
			=                        =
			=      영화 예매 프로그램     =
			=                        =
			=        1. 영화별 조회      =
			=      2. 극장별 조회       =
			=        3. 빠른 예매       =
			=    4. 예매 조회 및 취소    =
			""";

	outer:
		while (true) {
			System.out.print(prompt);
			if(id.equals("")) {
				System.out.println(
						"""
						=    5. 로그인 및 회원 가입   =
						=    6. 프로그램 종료      =
						==========================
						"""
				);
			}else {
				System.out.println(
						"""
						=    5. 마이페이지       =
						=    6. 로그아웃         =
						=    7. 프로그램 종료     =
						==========================
						"""
				);
			}
			String num = scanner.nextLine();
			switch(num) {
			case "1":
				//영화별 조회
				break;
			case "2":
				//극장별 조회
				break;
			case "3":
				//빠른 예매
				break;
			case "4":
				//예매 조회 및 취소
				break;
			case "5":
				if(id.equals("")) {//로그인 및 회원가입
					id = account.account_main();
				}else {//마이페이지
					//마이페이지
				}
				break;
			case "6":
				if(id.equals("")) {//프로그램 종료
					break outer;
				}else {//로그아웃
					//로그아웃
					break;
				}
			case "7":
				break outer; //프로그램 종료
			case "501": //관리자
				admin.admin_menu();
				break;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
				break;
			}
			
		}
}
}
