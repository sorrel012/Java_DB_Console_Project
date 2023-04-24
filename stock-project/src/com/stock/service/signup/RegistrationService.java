package com.stock.service.signup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.stock.data.Member;
import com.stock.view.Main;
import com.stock.view.Menu;

/**
 * 계정을 생성하는 클래스 입니다.
 * @author 6조
 * @since 2022.03.08
 */
public class RegistrationService {
	
	public final static String USERDIR = ".\\dat\\account\\user.txt";
	
		/**
		 * 회원가입한 신규 회원의 정보를 저장하는 메소드
		 */
		public static void signUp() {
			
			File userFile = new File(USERDIR);
			
			Member.createMemberList(userFile);
			
			//계정 생성화면 출력하고 member객체를 반환
			Member member = Menu.signUpMenu();
			
			boolean isValid = true;
			isValid = isValid && AccountValidator.checkDuplicateAccount(member);	//아이디 중복 체크
			isValid = isValid && AccountValidator.checkName(member);				//이름 형식 체크
			isValid = isValid && AccountValidator.checkIdFormat(member);			//아이디 형식 체크
			isValid = isValid && AccountValidator.checkPasswordFormat(member);		//패스워드 형식 체크
			isValid = isValid && AccountValidator.checkPhoneFormat(member);			//연락처 형식 체크
			isValid = isValid && AccountValidator.checkDateFormat(member);			//생년월일 형식 체크
			isValid = isValid && AccountValidator.checkEmailFormat(member);			//이메일 형식 체크
			
			if (isValid) {
				
				try {
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true));
					
					bw.write(member.getNo()
							+ "," + member.getName()
							+ "," + member.getId()
							+ "," + member.getPw()
							+ "," + member.getTel()
							+ "," + member.getBirthday()
							+ "," + member.getEmail()
							+ "," + member.getMoney()
							+ "," + member.getAvailableAssets()
							+ "\n");
					
					Member.members.add(member);
					System.out.println("\t\t\t\t\t\t\t\t\t\t[회원가입이 완료되었습니다.]");
					
					bw.close();
					
					Main.mainMenu();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				
				System.out.println("\t\t\t\t\t\t\t\t\t\t[회원가입에 실패했습니다.]");
				
				Main.mainMenu();
				
			}
			
		}//signUp

}
