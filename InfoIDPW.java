package Movie_Reservation;

import java.util.ArrayList;
import java.util.Scanner;

public class InfoIDPW {
	public static void infoidpassword() {
		DBconnectMov db = new DBconnectMov();
		ArrayList<userinfo> account = db.getuserinfo();
		
		String infodkdlel = """
				====================================================
				=                                                  =
				=  아이디/비밀번호 조회 메뉴입니다. 원하시는 메뉴를 선택해 주세요.   =
				=                                                  =
				=                   1. 아이디 찾기                    =
				=                  2. 비밀번호 찾기                    =
				=                    3. 끝내기                       =
				=                                                  =
				====================================================
				""";
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> Phonenum = new ArrayList<>();
		ArrayList<String> Birth = new ArrayList<>();
		ArrayList<String> Id = new ArrayList<>();
		
		int k = account.size();
		for (int i=0; i<k; i++) {
			Phonenum.add(account.get(i).getCellphone());
			Birth.add(account.get(i).getBirth());
		    Id.add(account.get(i).getId());
		}
		
		outer:
		while(true) {
			System.out.println(infodkdlel);
			int num = scanner.nextInt();
			switch (num) {
			case 1:
				System.out.println("아이디 찾기는 전화번호, 생년월일로 찾을 수 있습니다.\n예시에 맞게 정확하게 입력해주세요.");
				System.out.println("먼저 전화번호를 입력해주세요.");
				System.out.println("(Ex : 010-8251-6374) : ");
				String phonenum = scanner.next();
				if (Phonenum.contains(phonenum)) {
					for(int i=0; i<k; i++) {
						if(phonenum.equals(account.get(i).getCellphone())){
							System.out.println("다음으로 생년월일을 입력해주세요.");
							System.out.println("(Ex : 2000-05-01) : ");
							String birth = scanner.next();
							if(birth.equals(account.get(i).getBirth())) {
								System.out.printf("당신의 아이디는 %s입니다.\n",account.get(i).getId());
							}else {
								System.out.println("등록된 생년월일이 아닙니다.");
							}
						}
					}
				}else {
					System.out.println("등록되지 않은 전화번호 입니다.");
					break;
				}
				break;
			case 2:
				System.out.println("비밀번호 찾기는 아이디, 전화번호, 생년월일로 찾을 수 있습니다.\n예시에 맞게 정확하게 입력해주세요.");
				System.out.println("먼저 아이디를 입력해주세요.");
				String id = scanner.next();
				if (Id.contains(id)) {
					for(int i=0; i<k; i++) {
						if(id.equals(account.get(i).getId())){
							System.out.println("다음으로 전화번호를 입력해주세요.");
							System.out.println("(Ex : 010-8251-6374) : ");
							String phonenumber = scanner.next();
							if(phonenumber.equals(account.get(i).getCellphone())) {
								System.out.println("다음으로 생년월일을 입력해주세요.");
								System.out.println("(Ex : 2000-05-01) : ");
								String birth = scanner.next();
								if (birth.equals(account.get(i).getBirth())) {
									System.out.printf("%s님의 비밀번호는 %s입니다.\n", account.get(i).getName_(),account.get(i).getPass_word());
								}else {
									System.out.println("등록된 생년월일이 아닙니다.");
								}
							}else{
								System.out.println("등록된 전화번호가 아닙니다.");
							}
							
							}
						}
					}
				else {
					System.out.println("등록되지 않은 아이디입니다.");
					break;
				}
				break;
			case 3:
				break outer;
				default:
					System.out.println("올바른 번호가 아닙니다.");
			}
		}
	}
}
