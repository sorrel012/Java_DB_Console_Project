package com.stock.service.admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.stock.data.Member;
import com.stock.data.adminData;
import com.stock.view.Admin;

/**
 * 관리자 계정 정보를 확인하는 클래스
 * @author 6조
 *
 */
public class AdminInfoManage {
	
	private static Scanner scan;
	static {
		scan = new Scanner(System.in);
	}
	
	/**
	 * 관리자 계정 정보를 출력하는 메소드
	 */
public static void AdminInfo() {
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		String input = "";
		
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t[관리자 목록]");
		System.out.println("\t\t\t\t\t\t\t======================================================================");
		System.out.println("\t\t\t\t\t\t\t[이름]\t\t[아이디]\t\t[연락처]\t\t\t[이메일]");
		System.out.println("\t\t\t\t\t\t\t======================================================================");
		
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(".\\dat\\account\\admin.txt"));
			
			String line = null;
			
			while((line = reader.readLine()) != null){
				
				String [] admin  = line.split(",");	
				
				System.out.printf("\t\t\t\t\t\t\t%s\t%10s\t%15s\t%30s\n", admin[1], admin[2], admin[4], admin[5]);
				
			}
			
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t뒤로 가시려면 [엔터]를 눌러주세요.");
		
		input = scan.nextLine();
		if(input.equals("")) {
			Admin.AdminMenu();
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}//AdminInfo

}//class


	

