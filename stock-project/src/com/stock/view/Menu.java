package com.stock.view;

import java.util.Scanner;

import com.stock.data.Member;
/**
 * 주요 메뉴 UI 클래스
 * @author 6조
 *
 */
public class Menu {
	
	/**
	 * 메인 메뉴 UI 메서드
	 */
	public static void mainMenu() {
		
		System.out.println();
		System.out.println();
		  
		System.out.println("\t\t\t\t\t　　░█░█░▀█▀░█▀▄░▀█▀░█░█░█▀█░█░░░░░█▀▀░▀█▀░█▀█░█▀▀░█░█░░░▀█▀░█▀▄░█▀█░█▀▄░▀█▀░█▀█░█▀▀░░░█▀▀░█░█░█▀▀░▀█▀░█▀▀░█▄█\r\n"
				+ "\t\t\t\t\t　　░▀▄▀░░█░░█▀▄░░█░░█░█░█▀█░█░░░░░▀▀█░░█░░█░█░█░░░█▀▄░░░░█░░█▀▄░█▀█░█░█░░█░░█░█░█░█░░░▀▀█░░█░░▀▀█░░█░░█▀▀░█░█\r\n"
				+ "\t\t\t\t\t　　░░▀░░▀▀▀░▀░▀░░▀░░▀▀▀░▀░▀░▀▀▀░░░▀▀▀░░▀░░▀▀▀░▀▀▀░▀░▀░░░░▀░░▀░▀░▀░▀░▀▀░░▀▀▀░▀░▀░▀▀▀░░░▀▀▀░░▀░░▀▀▀░░▀░░▀▀▀░▀░▀\r\n"
				+ "");
		
		
	    System.out.println();
	    System.out.println();
	    
	     
	    System.out.println("\t\t\t\t\t\t\t\t▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");		
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t　　< 메뉴 선택 >\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t　1. 시세정보\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t　2. 주식 종목 검색\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t　3. 로그인\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t　4. 회원가입\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t　0. 종료\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	    
	    System.out.println();
	    
	}
	
	/**
	 * 관리자 메인 메뉴 UI 메서드
	 */
	public static void adminMenu() {
		  
		System.out.println();
		System.out.println();
		
	    System.out.println("\t\t\t\t\t\t\t\t\t    ░█▀█░█▀▄░█▄█░▀█▀░█▀█░░░█▄█░█▀▀░█▀█░█░█\r\n"
	    		+ "\t\t\t\t\t\t\t\t\t    ░█▀█░█░█░█░█░░█░░█░█░░░█░█░█▀▀░█░█░█░█\r\n"
	    		+ "\t\t\t\t\t\t\t\t\t    ░▀░▀░▀▀░░▀░▀░▀▀▀░▀░▀░░░▀░▀░▀▀▀░▀░▀░▀▀▀\r\n");
	    
	    System.out.println();
	        
	    System.out.println("\t\t\t\t\t\t\t\t▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t  < 관리자 메뉴 >\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t1. 관리자 계정 추가\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t2. 관리자 정보 관리\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t3. 회원 정보 관리\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t4. 시세정보\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t5. 주식 종목 검색\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t(0번 입력: 종료)\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	    //System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
	    System.out.println();
		
		
	}
	
	/**
	 * My Stock-자산확인 메뉴 UI 메서드
	 */
	public static void stockCheck() {
		
		System.out.println();
		System.out.println();
		
	    System.out.println("\t\t\t\t\t\t\t\t▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t　　　　< My Stock - 자산 확인 >\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t1. 자산 정보 조회\t\t3. 자산 초기화\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t2. 종목별 정보 조회\t\t4. 뒤로가기\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t(0번 입력: 종료)\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	    System.out.println();
	    
	}
	
	/**
	 * 메인 메뉴 UI 메서드
	 */
	public static void stockMenu() {

		  System.out.println();
		  System.out.println();

		  System.out.println("\t\t\t\t\t\t\t\t\t\t░█▄█░█░█░░░█▀▀░▀█▀░█▀█░█▀▀░█░█\r\n"
				  + "\t\t\t\t\t\t\t\t\t\t░█░█░░█░░░░▀▀█░░█░░█░█░█░░░█▀▄\r\n"
				  + "\t\t\t\t\t\t\t\t\t\t░▀░▀░░▀░░░░▀▀▀░░▀░░▀▀▀░▀▀▀░▀░▀");

		  System.out.println();
		  System.out.println();

		  System.out.println("\t\t\t\t\t\t\t\t▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t< My Stock >\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t1. 자산 확인\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t2. 주식 거래 내역 확인\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t3. 관심 종목 목록\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t(0번 입력: 메인화면으로 이동)\t█");
		  System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		  System.out.println("\t\t\t\t\t\t\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		  System.out.println("\t\t\t\t\t\t\t\t\t\t\t 번호 입력: ");

	  }
	
	/**
	 * 시세정보 메뉴 UI 메서드
	 */
	public static void priceMenu() {

		System.out.println();
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t░█▀█░█▀▄░▀█▀░█▀▀░█▀▀░░░▀█▀░█▀█░█▀▀░█▀█\r\n"
				+ "\t\t\t\t\t\t\t\t\t\t░█▀▀░█▀▄░░█░░█░░░█▀▀░░░░█░░█░█░█▀▀░█░█\r\n"
				+ "\t\t\t\t\t\t\t\t\t\t░▀░░░▀░▀░▀▀▀░▀▀▀░▀▀▀░░░▀▀▀░▀░▀░▀░░░▀▀▀");

		System.out.println();
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t< 시세 정보 >\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t1. 코스피 (상승률 / 하락률 Top 10 출력)\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t2. 코스피 (상승률 / 하락률 Top 10 출력)\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t(0번 입력: 메인화면으로 이동)\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력 :");

	}
	
	/**
	 * 회원 메인 메뉴 UI 메서드
	 */
	public static void userMenu() {

		System.out.println();
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t\t\t░█░█░█▀▀░█▀▀░█▀▄░░░█▄█░█▀▀░█▀█░█░█\r\n"
				+ "\t\t\t\t\t\t\t\t\t\t░█░█░▀▀█░█▀▀░█▀▄░░░█░█░█▀▀░█░█░█░█\r\n"
				+ "\t\t\t\t\t\t\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░▀░▀░░░▀░▀░▀▀▀░▀░▀░▀▀▀");

		System.out.println();
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t   < 회원 메뉴 >\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t1. 개인정보 조회\t\t4. My Stock\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t2. 매수 및 매도\t\t5. 시세 정보\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t3. 랭킹 보기\t\t6. 주식 종목 검색\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
	    System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t(0번 입력: 종료)\t█");
		System.out.println("\t\t\t\t\t\t\t\t█\t\t\t\t\t\t\t\t█");
		System.out.println("\t\t\t\t\t\t\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력 : ");

	}
	
	/**
	 * 회원가입 UI
	 * @return 사용자가 회원가입을 위해 입력한 항목에 대한 값
	 */
	public static Member signUpMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t░█▀▀░▀█▀░█▀▀░█▀█░█░█░█▀█\r\n"
				+ "\t\t\t\t\t\t\t\t\t\t░▀▀█░░█░░█░█░█░█░█░█░█▀▀\r\n"
				+ "\t\t\t\t\t\t\t\t\t\t░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░░\r"
				+ "");
		
		System.out.println();

		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t< 회원 가입 >");
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t[회원가입을 위한 정보를 입력해주세요]");
		System.out.println("\t\t\t\t\t\t\t\t----------------------------------------------------------");
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t이름 \t: ");
		String name = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t\t\t아이디 \t: ");
		String id = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t\t\t비밀번호 \t: ");
		String pw = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t\t\t연락처 \t: ");
		String tel = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t\t\t생년월일 \t: ");
		String birthday = sc.nextLine();
		
		System.out.print("\t\t\t\t\t\t\t\t\t\t이메일 \t: ");
		String email = sc.nextLine();
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t==========================================================");
		System.out.println();
		
		String no = Member.members.stream()
				  .mapToLong(m -> Long.parseLong(m.getNo()))
				  .max().getAsLong() + 1 + "";
		
		Member member = new Member(no, name, id, pw, tel, birthday, email, "10000000", "10000000");
		
		return member;
		
	}
	
}
