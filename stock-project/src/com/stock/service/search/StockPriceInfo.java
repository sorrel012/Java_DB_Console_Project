package com.stock.service.search;
/**
 * 코스피,코스닥 하락,상승 Top10을 출력하기 위한 클래스
 * @author 6조
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.stock.service.signup.Login;
import com.stock.view.Admin;
import com.stock.view.Main;
import com.stock.view.UserMenu;

public class StockPriceInfo {
	
	
	/**
	 * 접근자에 따른 분류 메뉴 호출
	 * @param A == 0 비회원 메뉴
	 * @param A == 1 회원 메뉴
	 * @param A == 2 관리자 메뉴
	 */
	private static void definition(int A) {
		
		if (A == 0) {
			Main.mainMenu();//비회원

		} else if (A == 1) {
			UserMenu.UI();//회원

		} else if (A == 2) {
			Admin.AdminMenu();//관리자
		}
	}
	
	/**
	 * 시세정보 메뉴 UI 출력, 접근자 분류하는 메소드
	 * 
	 */
	public static void UI() {

		System.out.printf("\n\n%96s\n", "[시세 정보]");
		System.out.println("\t\t\t\t\t\t\t\t=================================================================\n");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t 1. 코스피");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t 2. 코스닥\n");
		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력: 메인화면으로 이동)");
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");

		//접근자에 따른 분류 메뉴
		if (Login.loginUser != null) {
			input(1);//회원
		
		} else if (Login.loginAdmin != null) {
			input(2);//관리자
		
		} else {
			input(0);//비회원
		}

	}// UI

	/**
	 * 파일에서 코스피,코스닥의 시세정보의 상승,하락 Top10 추출하는 메소드
	 * @param path 코스피,코스닥의 상승,하락 각각의 파일의 경로를 입력받는 곳
	 */
	private static void output(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = null;

			System.out.println(
					"\t\t\t\t========================================================================================================================\n");

			System.out.println("\t\t\t\t[순위]\t[종목명]\t\t\t[현재가]\t\t[전일비]\t\t[등락률]\t\t[거래량]\t\t[매수호가]\t\t[매도호가]");

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				String name = "";

				if (temp[1].length() > 12) {
					name = temp[1].substring(0, 11) + "..";
				} else {
					name = temp[1];
				}

				System.out.printf("\t\t\t\t%3s\t%-18s\t%-8s\t%-10s\t%-8s\t%-8s\t%-8s\t%-9s\n", temp[0], name, temp[2],
						temp[3], temp[4], temp[5], temp[6], temp[7]);

				if (temp[0].equals("10")) {
					break;
				}
			} // while

			System.out.println(
					"\t\t\t\t========================================================================================================================\n");
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// output

	/**
	 * 사용자에게서 입력받은 입력값에 따라 시세정보 코스피, 코스닥 상승,하락 Top10을 출력하는 메소드
	 * @param A 분류된 접근자
	 */
	private static void input(int A) {
		Scanner scan = new Scanner(System.in);
		String sel = "";
		boolean loop = true;

		// 시세정보 메뉴에서 입력값받는 곳
		while (loop) {
			sel = scan.nextLine();

			if (sel.equals("0")) {

				definition(A);
				loop = false;

			} else if (sel.equals("1")) {

				System.out.printf("\n\n%100s\n", "[코스피 상승Top10 시세 정보]");
				output(".\\dat\\stockprice\\kospiRiseList.txt");
				System.out.println();

				System.out.printf("\n%100s\n", "[코스피 하락Top10 시세 정보]");
				output(".\\dat\\stockprice\\kospiFallList.txt");
				loop = false;

			} else if (sel.equals("2")) {

				System.out.printf("\n\n%100s\n", "[코스닥 상승Top10 시세 정보]");
				output(".\\dat\\stockprice\\kosdaqRiseList.txt");
				System.out.println();

				System.out.printf("\n%100s\n", "[코스닥 하락Top10 시세 정보]");
				output(".\\dat\\stockprice\\kosdaqFallList.txt");
				loop = false;

			} else {
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t  잘못된 번호를 입력하셨습니다.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t   번호를 다시 입력해주세요.");
				System.out.println("\t\t\t\t\t\t\t\t=================================================================");
				System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
			}
		} // while

		// 코스피,코스닥 출력후 메인메뉴로 복귀
		if (!sel.equals("0")) {

			sel = "";
			loop = true;
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력: 메인화면으로 이동)");
			System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");

			while (loop) {
				sel = scan.nextLine();

				if (sel.equals("0")) {
					
					//접근자에 따른 분류 메뉴
					definition(A);
					loop = false;

				} else {
					System.out.println(
							"\t\t\t\t\t\t\t\t=================================================================");
					System.out.println("\t\t\t\t\t\t\t\t\t\t  잘못된 번호를 입력하셨습니다.");
					System.out.println("\t\t\t\t\t\t\t\t\t\t   번호를 다시 입력해주세요.");
					System.out.println(
							"\t\t\t\t\t\t\t\t=================================================================");
					System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
				}
			} // while
			scan.close();
		} // if
	}// input
}// class
