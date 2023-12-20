package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class Account_Main {
	public String account_main() {		
		InfoIDPW info = new InfoIDPW();
		DBconnectMov db = new DBconnectMov();
		Scanner scanner = new Scanner(System.in);
		
		String prompt = """
				        ===========================================
					=                                         =
					=          원하시는 메뉴를 선택해 주세요.          =
					=                                         =
					=               1. 회원가입                  =
					=                2. 로그인                  =
					=           3. ID/Password 찾기            =
					=                4. 끝내기                  =
					=                                         =
					===========================================
				""";

		ArrayList<USERINFO> account = db.getuserinfo();
		int k = account.size();
		ArrayList<String> ID = new ArrayList<>();
		for (int i =0; i<k;i++)
			ID.add(account.get(i).getID());
		ArrayList<String> NickName = new ArrayList<String>();
		for (int i =0; i<k;i++)
			NickName.add(account.get(i).getNinkname());
		ArrayList<String> Password = new ArrayList<String>();
		for (int i =0; i<k;i++)
			Password.add(account.get(i).getUserpw());
		outer:
	while (true) {
		System.out.println(prompt);
		int num = scanner.nextInt();
		switch(num) {
		case 1:
			
			System.out.println("이름을 입력해 주세요. : ");
			String name = scanner.next();
			System.out.println("핸드폰 번호를 입력해 주세요. : ");
			System.out.println("ex : 010-8251-6374");
			String phonenum = scanner.next();
			System.out.println("생년월일을 입력해 주세요. : ");
			System.out.println("ex : 000501");
			String birth = scanner.next();
			System.out.println("닉네임을 입력해 주세요. : ");
			System.out.println("닉네임은 영어, 숫자, 특수기호로만 설정해주세요. (다른 언어를 사용하면 닉네임이 제대로 나오지 않을 수 있습니다.)");
			String nickname = scanner.next();
			Boolean nick = NickName.contains(nickname);
			if (nick != true) {
			System.out.println("아이디를 입력해 주세요. : ");
			System.out.println("아이디는 영어, 숫자, 특수기호로만 설정해주세요. (다른 언어를 사용하면 로그인이 되지 않을 수 있습니다.)");
			String id = scanner.next();
			Boolean idd = ID.contains(id);
			if (idd != true) {
			System.out.println("비밀번호를 입력해 주세요. : ");
			System.out.println("비밀번호는 영어, 숫자, 특수기호로만 설정해주세요. (다른 언어를 사용하면 로그인이 되지 않을 수 있습니다.)");
			String password= scanner.next();
			System.out.println("비밀번호를 한번 더 입력해 주세요. : ");
			String password_check = scanner.next();
			if (password.equals(password_check)) {
				db.insertuserinfo(id, password_check, name, phonenum, birth, nickname);
				System.out.println("회원가입이 완료되었습니다.");
				return id;
			}
			else { 
				System.out.println("올바른 비밀번호가 아닙니다. 다시 설정해주세요.");
			} 
			}else {
				System.out.println("이미 등록된 아이디 입니다.");
			}
			} else {
				System.out.println("이미 등록된 닉네임 입니다.");
			}
			return "";
		case 2:
			System.out.println("먼저 아이디를 입력해주세요.");

			String id = scanner.next();
			scanner.nextLine();

			for (int i =0; i<k;i++) {
				if (ID.contains(id)) {
					if(id.equals(account.get(i).getID())) {
						System.out.println("다음으로 비밀번호를 입력해주세요.");
						String password = scanner.next();
						if (password.equals(account.get(i).getUserpw())) {
							System.out.println("로그인이 완료되었습니다.");
							return id;
						}else {
							System.out.println("올바른 비밀번호가 아닙니다.");
						}
					}
					}else {
						System.out.println("등록된 아이디가 아닙니다.");
					}
			}
			return "";
		case 3:
		info.infoidpassword();
			return "";
		case 4:
			System.out.println("회원가입이 종료됩니다.");
			break outer;
		default:
				System.out.println("올바른 번호가 아닙니다.");
				return "";
		}
	}
return "";

	}

}
