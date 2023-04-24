package com.stock.service.signup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.stock.data.UserData;
import com.stock.data.adminData;
import com.stock.view.Admin;
import com.stock.view.LoginUI;
import com.stock.view.Main;
import com.stock.view.Menu;
import com.stock.view.UserMenu;
/**
 * 로그인 기능을 담당하는 클래스
 * @author 
 *
 */
public class Login {
	
	static Scanner scan;
	
	public static UserData loginUser;	// 로그인 완료한 회원 정보를 담기 위한 변수
	public static adminData loginAdmin;	// 로그인 완료한 관리자 정보를 담기 위한 변수
	
	static {
		
		scan = new Scanner(System.in);
	}
	
	/**
	 * 입력받은 정보를 확인해서 로그인을 하는 메소드
	 */
	public static void loginMenu() {
		
			boolean loop = true;
			
			while(loop) {
				
					
				LoginUI.loginBoard();
				
				System.out.print("\t\t\t\t\t\t\t\t\t\t\t아이디: ");
				String id = scan.nextLine();
				
				
				System.out.print("\t\t\t\t\t\t\t\t\t\t\t비밀번호: ");
				String pw = scan.nextLine();
				System.out.println();
				
				//회원 아이디와 패스워드 검사
				try {
					
					BufferedReader userReader = new BufferedReader(new FileReader(".\\dat\\account\\user.txt"));  
					
					String line = null;
					
					while((line = userReader.readLine()) != null) {
						
						String[] temp = line.split(",");
						
						if(id.equals(temp[2]) && pw.equals(temp[3])) {
							System.out.printf("\t\t\t\t\t\t\t\t\t\t%s님 로그인에 성공하셨습니다.", id);
							
							loginUser = new UserData(temp[0]
												   , temp[1]
												   , temp[2]
												   , temp[3]
												   , temp[4]
												   , temp[5]
												   , temp[6]
												   , Integer.parseInt(temp[7])
												   , Integer.parseInt(temp[8]));
							
							loop = false;
							UserMenu.UI(); //회원 메뉴 출력
							break;
						}
							
						
					}//while	
					
					userReader.close();
					
				}catch (Exception e) {
						e.printStackTrace();
					
				}//try
						
				
				//관리자 아이디와 패스워드 검사
				try {
					
					BufferedReader adminReader = new BufferedReader(new FileReader(".\\dat\\account\\admin.txt"));  
					
					String line = null;
					
					while((line = adminReader.readLine()) != null) {
						
						String[] temp = line.split(",");
						
						if(id.equals(temp[2]) && pw.equals(temp[3])) {
							System.out.println("\t\t\t\t\t\t\t\t\t\t\t관리자로 로그인합니다.");
							
							loginAdmin = new adminData(temp[0]
									   , temp[1]
									   , temp[2]
									   , temp[3]
									   , temp[4]
									   , temp[5]);
							
							
							loop = false;
							Admin.AdminMenu();
							break;
						} 	
				
					}//while	
		
					adminReader.close();
					
				}catch (Exception e) {
						e.printStackTrace();
					
				}//try
				
				//회원과 관리자에서 일치하는 아이디가 없을때
				if(loop) {
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t로그인에 실패하셨습니다.");
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t메인메뉴로 돌아갑니다.");
					Main.mainMenu();
					break;
				}
				
		
			
			
		}//바깥쪽 while
			
			
	}//loginMenu
					


}//class


