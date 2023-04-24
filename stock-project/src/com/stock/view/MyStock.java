package com.stock.view;

import java.util.Scanner;

import com.stock.service.store.FavoriteStock;

/**
 * 회원이 가진 주식,자산에 관련된 메뉴 클래스
 * @author 6조
 *
 */
public class MyStock {
	
	/**
	 * 회원이 가진 주식,자산에 관련된 메뉴 UI
	 */
	public static void UI(){
		
		System.out.printf("\n\n%100s\n","[My Stock]");
		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t     1. 자산 확인");
		System.out.println("\t\t\t\t\t\t\t\t\t\t     2. 주식 거래 내역 확인");
		System.out.println("\t\t\t\t\t\t\t\t\t\t     3. 관심 종목 목록");
		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력: 메인화면으로 이동)");
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
		input();

		
	}//UI
	
	/**
	 * 회원에게서 입력받은 값을 토대로 다른 메뉴로의 이동하는 메소드
	 */
	private static void input() {
		Scanner scan = new Scanner(System.in);
		String sel = "";
		boolean loop = true;
		
		while(loop) {
			sel = scan.nextLine();
			
			if(sel.equals("1")) {
				MyStockCheck.mystockcheck();
				loop = false;
			
			}else if(sel.equals("2")) {
				TradeView.printTransactionHistory();
				loop = false;
			
			}else if(sel.equals("3")) {
				FavoriteStock.favorite();
				loop = false;
			
			}else if(sel.equals("0")) {
				UserMenu.UI();
				loop = false;
			
			}else {
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t  잘못된 번호를 입력하셨습니다.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t   번호를 다시 입력해주세요.");
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
			}
		}//while
		scan.close();
	}//input
}
