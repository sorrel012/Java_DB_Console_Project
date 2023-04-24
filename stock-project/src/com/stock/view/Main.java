package com.stock.view;

import java.util.Scanner;

import com.stock.service.search.Search;
import com.stock.service.search.StockPriceInfo;
import com.stock.service.signup.Login;
import com.stock.service.signup.RegistrationService;

/**
 * 메인 화면을 구성하는 클래스
 * @author 6조
 *
 */
public class Main {
	/**
	 * 회원에게서 입력받은 값을 토대로 다른 메뉴로의 이동하는 메소드
	 */
	public static void mainMenu() {
		
		Menu.mainMenu();
		
		Scanner scan = new Scanner(System.in);
		
		//break label: 다중 반복문 한번에 종료
		SystemEndBreak:
		  
		  
		while(true) {
			
	    	//메뉴 번호 입력받기
			System.out.print("\t\t\t\t\t\t\t\t\t\t\t　번호 입력: ");
			String inputNum = scan.next();
		  

			//1. 시세 정보
			if (inputNum.equals("1")) {
				StockPriceInfo.UI();
				break;
	    
			//2. 주식 종목 검색
			} else if (inputNum.equals("2")) {
				Search s = new Search();
				s.searchStock();
				break;
	    	
			//3. 로그인
			} else if (inputNum.equals("3")) {
				Login.loginMenu();
				break;
	    	
			//4. 회원가입
			} else if (inputNum.equals("4")) {
				RegistrationService.signUp();
				break;
	    	
	    	
			//0. 종료 > 프로그램 종료 안내 문구
			} else if (inputNum.equals("0")) {
	    	
				while (true) {
	    	
					System.out.println("\t\t\t\t\t\t\t\t=================================================================");
					System.out.print("\t\t\t\t\t\t\t\t\t\t　[프로그램을 종료하시겠습니까? (y/n)] ");
	    	
					String inputEnd = scan.next();
	    	
					
					//프로그램 종료 > y
					if (inputEnd.equalsIgnoreCase("y")) {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t　　　　　프로그램을 종료합니다.\n\n\n\n\n\n\n");
	    			
						//break label으로 다중 반복문 한번에 종료
						break SystemEndBreak;

					//프로그램 종료 > n
					} else if (inputEnd.equalsIgnoreCase("n")) {
						break;
	    			
					//프로그램 종료 > y/n 이외 입력 시
					} else {
						System.out.println("\t\t\t\t\t\t\t\t\t\t　　y/n만 입력 가능합니다.");
						System.out.println();
					}
				}
				System.out.println();
	    
				
			// 0,1,2,3,4 이외 입력 시
			} else {
	 
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t잘못 입력하셨습니다. 다시 입력해 주세요.");
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println();

			}
	    
		}//while

	}

}
