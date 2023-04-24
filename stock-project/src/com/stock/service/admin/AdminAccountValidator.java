package com.stock.service.admin;

import com.stock.data.adminData;
/**
 * 관리자 계정 추가시 정보의 유효성 검사를 하는 클래스
 * @author 6조
 */
public class AdminAccountValidator {
	
	
	/**
	 * 아이디 형식체크 메소드
	 * @param manager 입력받은 관리자 데이터값을 불러오기 위한 변수
	 * @return  저장된 아이디 값이 형식과 맞지 많으면 false를 반환
	 */
	public static boolean checkIdFormat(adminData manager) {
		
		boolean result = true;
		
		if (!manager.getId().matches("^[a-zA-Z][a-zA-Z0-9]{7,15}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[아이디 항목이 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**비밀번호 형식 체크 메소드
	 * @param manager 입력받은 관리자 데이터값을 불러오기 위한 변수
	 * @return  저장된 비밀번호 값이 형식과 맞지 많으면 false를 반환
	 */
	public static boolean checkPasswordFormat(adminData manager) {
		
		boolean result = true;
		
		if (!manager.getPw().matches("^[a-zA-Z0-9]*$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[패스워드 항목이 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**이름 형식 체크 메소드
	 * @param manager 입력받은 관리자 데이터값을 불러오기 위한 변수
	 * @return  저장된 이름이 형식과 맞지 많으면 false를 반환
	 */
	public static boolean checkName(adminData manager) {
		
		boolean result = true;
		
		if (!manager.getName().matches("^[가-힣]{2,5}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[이름 항목이 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	

	
	/**이메일 형식 체크
	 * @param manager 입력받은 관리자 데이터값을 불러오기 위한 변수
	 * @return  저장된 이메일이 형식과 맞지 많으면 false를 반환
	 */
	public static boolean checkEmailFormat(adminData manager) {
		
		boolean result = true;
		
		if (!manager.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[이메일 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**연락처 형식 체크
	 * @param manager 입력받은 관리자 데이터값을 불러오기 위한 변수
	 * @return  저장된 연락처가 형식과 맞지 많으면 false를 반환
	 */
	public static boolean checkPhoneFormat(adminData manager) {
		
		boolean result = true;
		
		if (!manager.getTel().matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[연락처 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}

}