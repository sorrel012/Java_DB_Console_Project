package com.stock.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.stock.service.signup.Login;
import com.stock.service.signup.RegistrationService;

/**
 * 회원 데이터를 만들기 위한 클래스
 * @author 6조
 *
 */
public class Member {

	public static ArrayList<Member> members = new ArrayList<Member>();
	
	private String no;
	private String name;
	private String id;
	private String pw;
	private String tel;
	private String birthday;
	private String email;
	private String money;
	private String availableAssets;
	
	public Member(String no, String name, String id, String pw, String tel, String birthday, String email, String money) {

		this.no = no;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.birthday = birthday;
		this.email = email;
		this.money = money;
		
	}
	
	public Member(String no, String name, String id, String pw, String tel, String birthday, String email, String money, String availableAssets) {

		this.no = no;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.birthday = birthday;
		this.email = email;
		this.money = money;
		this.availableAssets = availableAssets;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	public String getAvailableAssets() {
		return availableAssets;
	}

	public void setAvailableAssets(String availableAssets) {
		this.availableAssets = availableAssets;
	}

	/**
	 * 회원 데이터 리스트를 만드는 메서드 
	 */
	public static void createMemberList(File userFile) {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(userFile));
			
			String line = null;
			
			while ((line = br.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				Member member = new Member(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]);
				
				members.add(member);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *
	 * 계정 정보를 업데이트 시키는 메소드
	 */
	public static void updateAccountInfo(TradeData td, String type) {
		
		try {
			
			String targetId = Login.loginUser.getId();
			int newAmount = 0;
			int totalAmount = 0;
			
			if (type.equals("매수")) {
				newAmount = td.getTotalAvailableAssets();
				Login.loginUser.setAvailableAssets(newAmount);
				totalAmount = td.getTotalAssets() + Login.loginUser.getAvailableAssets();
				
			} else {
				newAmount = Login.loginUser.getAvailableAssets() + td.getTotalPrice();
				Login.loginUser.setAvailableAssets(newAmount);
				totalAmount = td.getTotalAssets() + Login.loginUser.getAvailableAssets();
			}
			
			File file = new File(RegistrationService.USERDIR);
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = null;
			
			StringBuilder sb = new StringBuilder();
			
			while ((line = br.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				if (temp[2].equals(targetId)) {
					temp[7] = String.valueOf(totalAmount);
					temp[8] = String.valueOf(newAmount);
					line = String.join(",", temp);
				}
				
				sb.append(line).append("\n");
			}
			
			br.close();
			
			FileWriter fw = new FileWriter(file);
			fw.write(sb.toString());
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		return String.format("Member [no=%s, name=%s, id=%s, pw=%s, tel=%s, birthday=%s, email=%s, money=%s]", no, name,
				id, pw, tel, birthday, email, money);
	}

}
