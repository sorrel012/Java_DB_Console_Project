package com.stock.view;

import java.util.Scanner;

import com.stock.service.search.Search;
import com.stock.service.search.StockPriceInfo;
import com.stock.service.signup.UserInfo;
import com.stock.service.user.RankingPrintService;

/**
 * 로그인시 회원의 메인메뉴 클래스
 * @author 6조
 */
public class UserMenu {
	
	/**
	 * 회원에게서 입력받은 값을 토대로 다른 메뉴로의 이동하는 메소드
	 */
	public static void UI() {
		Scanner scan = new Scanner(System.in);
		String sel = "";
		boolean loop = true;

		Menu.userMenu();

		while (loop) {
			sel = scan.nextLine();

			if (sel.equals("1")) {
				UserInfo.info();
				loop = false;

			} else if (sel.equals("2")) {
				TradeView.mainView();
				loop = false;

			} else if (sel.equals("3")) {
				RankingPrintService.printRanking();
				loop = false;

			} else if (sel.equals("4")) {
				MyStock.UI();
				loop = false;

			} else if (sel.equals("5")) {
				StockPriceInfo.UI();
				loop = false;

			} else if (sel.equals("6")) {
				Search s = new Search();

				s.searchStock();
				loop = false;

			} else if (sel.equals("0")) {
				end();
				loop = false;

			} else {
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t  잘못된 번호를 입력하셨습니다.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t   번호를 다시 입력해주세요.");
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
			}
			scan.close();
		} // while

	}// UI
	
	//종료
	private static void end() {
		Scanner scan = new Scanner(System.in);

		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		System.out.print("\t\t\t\t\t\t\t\t\t\t　[프로그램을 종료하시겠습니까? (y/n)] ");

		while (true) {

			String input = scan.next();

			if (input.equals("y")) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t　　　　　프로그램을 종료합니다.\n\n\n\n\n\n\n");
				break;
				
			} else if (input.equals("n")) {
				UserMenu.UI();
				break;

			} else {
				System.out.println("\t\t\t\t\t\t\t\t\t\t　　y/n만 입력 가능합니다.");
				System.out.println();
			}
		}
		scan.close();
	}
}
