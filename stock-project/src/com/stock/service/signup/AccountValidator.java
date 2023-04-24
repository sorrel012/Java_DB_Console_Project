package com.stock.service.signup;

import com.stock.data.Member;
/**
 * 회원가입시 사용자가 입력하는 값의 유효성 검사 클래스
 * @author 6조
 *
 */
public class AccountValidator {
	
	/**
	 * 사용자가 입력한 아이디를 중복 여부 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkDuplicateAccount(Member member) {
		
		boolean result = true;
		
		for (Member m : Member.members) {
			
			if (member.getId().equals(m.getId())) {
				System.out.println("\t\t\t\t\t\t\t\t\t\t[중복된 아이디입니다]");
				result = false;
				break;
			}
			
		}
		
		return result;
		
	}
	
	/**
	 * 사용자가 입력한 아이디의 형식을 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkIdFormat(Member member) {
		
		boolean result = true;
		
		if (!member.getId().matches("^[a-zA-Z][a-zA-Z0-9]{7,15}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[아이디 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**
	 * 사용자가 입력한 비밀번호의 형식을 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkPasswordFormat(Member member) {
		
		boolean result = true;
		
		if (!member.getPw().matches("^[a-zA-Z0-9]*$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[패스워드 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**
	 * 사용자가 입력한 이름의 형식을 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkName(Member member) {
		
		boolean result = true;
		
		if (!member.getName().matches("^[가-힣]{2,5}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[이름 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**
	 * 사용자가 입력한 생년월일 형식을 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkDateFormat(Member member) {
		
		boolean result = true;
		
		if (!member.getBirthday().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[생년월일 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**
	 * 사용자가 입력한 이메일 형식을 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkEmailFormat(Member member) {
		
		boolean result = true;
		
		if (!member.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[이메일 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}
	
	/**
	 * 사용자가 입력한 연락처 형식을 검사하는 메서드
	 * @param member
	 * @return
	 */
	public static boolean checkPhoneFormat(Member member) {
		
		boolean result = true;
		
		if (!member.getTel().matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t[연락처 항목이 회원가입 형식과 일치하지 않습니다.]");
			result = false;
		}
		
		return result;
		
	}

}
