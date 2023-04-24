package com.stock.view;

import java.util.Scanner;

import com.stock.service.admin.AdminInfoManage;
import com.stock.service.admin.AdminRegistrationService;
import com.stock.service.admin.UserInfoManage;
import com.stock.service.search.Search;
import com.stock.service.search.StockPriceInfo;

/**
 * 관리자 계정으로 로그인 시 보이는 메뉴
 * @author OWNER
 *
 */
public class Admin {
	
	public static void AdminMenu() {
		
		Menu.adminMenu();
		
		Scanner scan = new Scanner(System.in);
		SystemEndBreak:
		
		while(true) {  
			
	    	//메뉴 번호 입력받기
			System.out.print("\t\t\t\t\t\t\t\t\t\t\t　번호 입력: ");
			String inputNum = scan.next();
		  

			//1. 관리자 계정 추가
			if (inputNum.equals("1")) {
				AdminRegistrationService.add();
				break;
	    
			//2. 관리자 정보 관리
			} else if (inputNum.equals("2")) {
				AdminInfoManage.AdminInfo();
				break;
	    	
			//3. 회원 정보 관리
			} else if (inputNum.equals("3")) {
				UserInfoManage.UserInfo();
				break;
	    	
			//4. 시세정보
			} else if (inputNum.equals("4")) {
				StockPriceInfo.UI();
				break;
	    	
			//5. 주식 종목 검색
			} else if (inputNum.equals("5")) {
				Search s = new Search();
				s.searchStock();
				break;
				
				
			//0. 종료 > 프로그램 종료 안내 문구
			} else if (inputNum.equals("0")) {
	    	
				while (true) {
	    	
					System.out.println("\t\t\t\t\t\t\t\t=================================================================");
					System.out.print("\t\t\t\t\t\t\t\t\t\t　[프로그램을 종료하시겠습니까? (y/n)] ");
	    	
					String inputEnd = scan.next();
	    	
					
					//프로그램 종료 > y
					if (inputEnd.equals("y")) {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t　　　　　프로그램을 종료합니다.\n\n\n\n\n\n\n");
						
						//break label으로 다중 반복문 한번에 종료 
						break SystemEndBreak;

					//프로그램 종료 > n
					} else if (inputEnd.equals("n")) {
						break;
	    			
					//프로그램 종료 > y/n 이외 입력 시
					} else {
						System.out.println("\t\t\t\t\t\t\t\t\t\t　　y/n만 입력 가능합니다.");
						System.out.println();
					}
				}
				System.out.println();
	    
				
			// 0,1,2,3,4,5  이외 입력 시
			} else {
	 
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t잘못 입력하셨습니다. 다시 입력해 주세요.");
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println();

			}

	    
		}//while    
		
	}
	
}
