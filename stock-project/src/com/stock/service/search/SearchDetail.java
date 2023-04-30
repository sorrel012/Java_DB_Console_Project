package com.stock.service.search;

import java.util.List;
import java.util.Scanner;

import com.stock.service.signup.Login;
import com.stock.service.store.UserStockStorage;
import com.stock.view.Admin;
import com.stock.view.Main;
import com.stock.view.TradeView;
import com.stock.view.UserMenu;

/**
 * 사용자가 검색한 주식의 상세 정보를 제공하는 클래스
 * @author 6조
 *
 */
public class SearchDetail {
	
	private static int userNum;
	private static String stockName;

	/**
	 * 사용자의 입력을 받는 메소드
	 * @return 사용자가 입력한 종목의 번호
	 */
	private static String userInputNum() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\t\t\t\t\t\t\t\t원하시는 종목의 번호를 입력해 주세요 : ");
		String num = scan.nextLine();
		
		return num;
		
	}
	
	/**
	 * 사용자가 입력한 종목 번호의 유효성을 검증하는 메소드
	 * @param result 사용자가 입력한 값이 포함된 모든 주식명이 저장된 리스트
	 * @return 사용자가 입력한 종목 번호의 유효성 검사 결과
	 */
	private static int checkSearchNum(List<List<String>> result) {
		
		SearchDetail.userNum = Integer.parseInt(userInputNum());
		
		if(SearchDetail.userNum == 0) {
			return 0;
		} else if((SearchDetail.userNum > 0) && (SearchDetail.userNum <= result.size())) {
			return 1;
		} else {
			return 2;
		}
		
	}
	
	/**
	 * 사용자가 선택한 주식의 상세 정보를 출력하는 메소드
	 * @param info 사용자가 선택한 주식의 정보를 저장하고 있는 리스트
	 */
	private static void printDetailInfo(List<String> info) {

		System.out.println("\n\t\t\t\t\t\t\t==================================================================================");
		
		SearchDetail.stockName = info.get(1);
		
		if(SearchDetail.stockName.length() < 6 ) {
			System.out.printf("\t\t\t\t\t\t\t\t\t\t선택하신 [%s]의 시세 정보입니다.\n", SearchDetail.stockName);
		} else {
			System.out.printf("\t\t\t\t\t\t\t\t   선택하신 [%s]의 시세 정보입니다.\n", SearchDetail.stockName);
		}
		System.out.println("\t\t\t\t\t\t\t==================================================================================\n");
		
		System.out.printf("\t\t\t\t\t\t\t\t%8s : %s\t\t\t%7s : %s\n", "[현재가]", info.get(2), "[PER]", info.get(5));
		System.out.printf("\t\t\t\t\t\t\t\t%8s : %s\t\t\t%6s : %s\n", "[등락률(%)]", info.get(3), "[거래량]",info.get(4));
		System.out.println("\n\t\t\t\t\t\t\t\t*: 관심 종목 등록\t\t  #: 매수\t\t(0번 입력: 메인화면으로 이동)");
		System.out.println("\t\t\t\t\t\t\t==================================================================================");
				
	}
	
	/**
	 * 사용자가 선택한 주식의 상세 정보를 출력한 후 추가 작업 여부를 입력받는 메소드
	 */
	public static void userAdditionalInput() {

		Scanner scan = new Scanner(System.in);
		
		System.out.print("\t\t\t\t\t\t\t\t선택 : ");
		String additionalInput = scan.nextLine();
		
		try {
			
			if(additionalInput.equals("*")) {
				isUser();
				storeStock();
			} else if(additionalInput.equals("#")) {
				isUser();
				TradeView.showBuyScreen();
			} else if(additionalInput.equals("0")) {
				chooseUI();
				
			} else {
				System.out.println("\t\t\t\t\t\t\t\t잘못 입력하셨습니다.\n");
				userAdditionalInput();
			}
				
		} catch (NullPointerException e) {
			System.out.println("\t\t\t\t\t\t\t\t회원 로그인 후 사용할 수 있는 기능입니다.\n");
			userAdditionalInput();
		}
			
	}
	
	/**
	 * 비회원일 경우 에러를 발생하는 메소드
	 */
	private static void isUser() {
		
		if(Login.loginUser.getNo() == null) {
			throw new NullPointerException();
		}
		 
	}
	
	/**
	 * 사용자가 선택한 주식을 관심 종목에 추가하는 메소드
	 */
	private static void storeStock() {
				
		String no = Login.loginUser.getNo();
		
		UserStockStorage storage = new UserStockStorage();
		storage.addUser(no, stockName);
		
		System.out.printf("\n\t\t\t\t\t\t\t\t[%s]을/를 관심 종목으로 등록하였습니다.\n", SearchDetail.stockName);
		UserMenu.UI();
		
	}
	
	/**
	 * 비회원, 회원, 관리자에 따라 다른 메인화면으로 이동
	 */
	private static void chooseUI() {
		
		Search s = new Search();
		if(s.checkUser() == 0) {
			Main.mainMenu();
		} else if(s.checkUser() == 1) {
			UserMenu.UI();
		} else {
			Admin.AdminMenu();
		}
	}
	
	/**
	 * 사용자의 입력값이 포함된 모든 주식명을 출력 후 상세 작업을 다시 입력받아 처리하는 메소드
	 * @param result 사용자가 선택한 상세 작업 결과
	 */
	public static void searchResultDetail(List<List<String>> result) {
		
		int check = checkSearchNum(result);
		
		if(check == 0) {
			chooseUI();
			
		} else if(check == 1) {
			List<String> info = result.get(SearchDetail.userNum - 1);
			printDetailInfo(info);
			userAdditionalInput();
			
		} else {
			System.out.println("\n\t\t\t\t\t\t\t\t잘못된 번호입니다.");
			searchResultDetail(result);
		}
		
	}
	
}