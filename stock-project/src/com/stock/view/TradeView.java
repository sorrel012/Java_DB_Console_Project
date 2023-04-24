package com.stock.view;

import java.util.Scanner;

import com.stock.data.Member;
import com.stock.data.TradeData;
import com.stock.service.signup.Login;
import com.stock.service.user.TradeValidator;
import com.stock.service.user.TransactionHistory;

/**
 * 매수 & 매도 화면을 출력하는 클래스
 * @author 6조
 *
 */
public class TradeView {
	
	/**
	 * 매수 & 매도 메인 화면을 출력하는 메소드
	 */
	public static void mainView() {

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t[매수 및 매도]");
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.println("\t\t\t\t\t\t\t\t가용자산: "); // Login.loginUser.getMoney()
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.println("\t\t\t\t\t\t\t\t1. 매수");
		System.out.println("\t\t\t\t\t\t\t\t2. 매도");
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력 : 메인화면으로 이동)");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t번호 입력: ");

		String num = sc.nextLine();
		
		if (num.equals("1")) {
			showBuyScreen();
		} else if (num.equals("2")) {
			showSellScreen();
		} else if (num.equals("0")) {
			UserMenu.UI();
		} else {
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
			TradeView.mainView();
		}

	}
	
	/**
	 * 매수화면을 출력하는 메소드
	 */
	public static void showBuyScreen() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t[주문 - 매수]");
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.print("\t\t\t\t\t\t\t\t구매하실 주식명: ");
		String stockName = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t매수량: ");
		String volume = sc.nextLine();
		
		TradeData td = new TradeData(stockName, volume);
		
		boolean isValid = true;
		isValid = isValid && TradeValidator.checkStockName(td);		//주식 이름 존재여부 확인
		isValid = isValid && TradeValidator.checkOrderQuantity(td);	//매수가능 여부 확인
		
		if (isValid) {
			
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t==========================================================");
			System.out.println("\t\t\t\t\t\t\t\t주식명: " + td.getStockName());
			System.out.println("\t\t\t\t\t\t\t\t매수량: " + td.getVolume());
			System.out.println("\t\t\t\t\t\t\t\t현재가: " + td.getPriceNow());
			System.out.printf("\t\t\t\t\t\t\t\t총 매수금액: %,d\n", td.getTotalPrice());
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t진행하시겠습니까? Y: 1, N: 2 입력");
			System.out.println("\t\t\t\t\t\t\t\t==========================================================");
			System.out.printf("\t\t\t\t\t\t\t\t매수 후 총 자산 금액: %,d\n", Login.loginUser.getMoney());			//7
			System.out.printf("\t\t\t\t\t\t\t\t매수 후 총 가용 금액: %,d\n", td.getTotalAvailableAssets());			//8
			System.out.println("\t\t\t\t\t\t\t\t==========================================================");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력 : 메인화면으로 이동)");
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t번호 입력: ");
			String result = sc.nextLine();
			
			if (result.equals("1")) {
				TradeData.createHistory(td, "매수");
				TradeData.createBuyList(td, "매수");
				UserMenu.UI();
			} else if (result.equals("2")) {
				UserMenu.UI();
			} else if (result.equals("0")) {
				UserMenu.UI();
			} else {
				System.out.println("\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
				System.out.println("\t\t\t\t\t\t\t\t다시 입력해 주세요");
				TradeView.showBuyScreen();
			}
			
		}
		
	}
	
	/**
	 * 매도 화면을 출력하는 메소드
	 */
	public static void showSellScreen() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t[주문 - 매도]");
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.print("\t\t\t\t\t\t\t\t판매하실 주식명: ");
		String stockName = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t매도량: ");
		String volume = sc.nextLine();
		
		TradeData td = new TradeData(stockName, volume);
		
		boolean isValid = true;
		isValid = isValid && TradeValidator.checkStockName(td);		//주식 이름 존재여부 확인
		isValid = isValid && TradeValidator.hasStock(td);			//보유중인 주식이 맞는지 확인
		isValid = isValid && TradeValidator.checkSellQuantity(td);   //주식 보유 수량 체크
		
		if (isValid) {
			
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t==========================================================");
			System.out.println("\t\t\t\t\t\t\t\t주식명: " + td.getStockName());
			System.out.println("\t\t\t\t\t\t\t\t매도량: " + td.getVolume());
			System.out.println("\t\t\t\t\t\t\t\t현재가: " + td.getPriceNow());
			System.out.printf("\t\t\t\t\t\t\t\t총 매도금액: %,d\n", td.getTotalPrice());
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t진행하시겠습니까? Y: 1, N: 2 입력");
			System.out.println("\t\t\t\t\t\t\t\t==========================================================");
			System.out.printf("\t\t\t\t\t\t\t\t매도 후 총 자산 금액: %,d\n", Login.loginUser.getMoney());			
			System.out.printf("\t\t\t\t\t\t\t\t매도 후 총 가용 금액: %,d\n", Login.loginUser.getAvailableAssets() + td.getTotalPrice());
			System.out.println("\t\t\t\t\t\t\t\t==========================================================");
			System.out.println();
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력 : 메인화면으로 이동)");
			System.out.println();
			
			System.out.print("\t\t\t\t\t\t\t\t번호 입력: ");
			String result = sc.nextLine();
			
			if (result.equals("1")) {
				TradeData.createHistory(td, "매도");
				TradeData.removeBuyList(td, "매도");
				UserMenu.UI();
			} else if (result.equals("2")) {
				UserMenu.UI();
			} else if (result.equals("0")) {
				UserMenu.UI();
			} else {
				System.out.println("\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
				System.out.println("\t\t\t\t\t\t\t\t다시 입력해 주세요");
				TradeView.showBuyScreen();
			}
			
		}
		
	}

	/**
	 * 거래내역을 출력하는 메소드
	 */
	public static void printTransactionHistory() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t[My Stock - 주식 거래 내역 확인]");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t=======================================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t거래일자\t\t종목명\t\t종류\t\t거래수량\t\t매매 금액");
		System.out.println("\t\t\t\t\t\t\t\t=======================================================================================");

		//거래내역 출력
		TransactionHistory.addTransaction();
		
		System.out.println("\t\t\t\t\t\t\t\t=======================================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력 : 메인화면으로 이동)");
		System.out.println();
			
		while (true) {
			
			System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
			String num = sc.nextLine();
			
			if (num.equals("0")) {
				UserMenu.UI();
				break;
			} else {
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다");
			}
			
		}
			
	}

}
