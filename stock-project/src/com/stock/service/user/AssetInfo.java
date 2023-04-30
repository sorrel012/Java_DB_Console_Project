package com.stock.service.user;


import java.util.Scanner;

import com.stock.service.signup.Login;
import com.stock.view.MyStockCheck;

/**
 * MyStock - 자산 확인
 * [1. 자산 정보 조회], [2. 종목별 정보 조회] 메뉴를 구성하는 클래스
 * @author 6조
 *
 */
public class AssetInfo {
	
	/**
	 * 1. 자산 정보 조회
	 */
	public static void assetInfo() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t　[자산 정보 조회] ");
		System.out.printf("\t\t\t\t\t\t\t\t\t\t 　%s(%s)님의 자산 정보입니다.\n", Login.loginUser.getName(), Login.loginUser.getId());
		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		System.out.println();
		
		AssetInfoDetail.allPrice();
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t=================================================================");
		
		inputEnter();
		
	}
	
	/**
	 * 보유하고 있는 주식의 종목별 정보를 조회하는 메소드
	 */
	public static void assetInfoDetail(){
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t=============================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t[종목별 정보 조회] ");
		System.out.println("\t\t\t\t\t\t\t=============================================================================");
		System.out.println("\t\t\t\t\t\t\t종목명\t\t보유수량\t\t매입가(원)\t\t평가손익(원)\t　　　　수익률(%)");
		System.out.println("\t\t\t\t\t\t\t=============================================================================");
		
		AssetInfoDetail.price();
		
		
		//평가손익: (현재가 - 매입가)*매입수량 = 현재가*매입수량 - 매입가
		//매입가 = 매입수량*매입가(1개)
		//수익률: (현재가/매수가)*100-100

		inputEnter();
	}
	
	/**
	 * 엔터 입력 시 "계속하시려면 엔터를 입력해주세요" 출력
	 * My Stock - 자산 확인으로 돌아감
	 */
	public static void inputEnter() {
		
		String enterLine = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t 　　　계속하시려면 엔터를 입력해주세요.");
		enterLine = scan.nextLine();
		MyStockCheck.mystockcheck();
		
		scan.close();
	}
}











