package com.stock.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 관리자 데이터 파일을 저장하기 위한 클래스
 * @author 6조
 */
public class adminData {
	
	
	public static List<adminData> list = new ArrayList<adminData>();
	
	private String no;
	private String id;
	private String pw;
	private String name;
	private String tel;
	private String email;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "adminData [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", tel=" + tel + ", email="
				+ email + "]";
	}
	
	public adminData(String no, String name, String id, String pw, String tel, String email) {
		
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.email = email;
	}

	
	public adminData() {
	
	}
	
	/**
	 * 관리자 리스트를 생성
	 * @param adminFile 관리자 데이터 파일 경로가 저장된 변수
	 */
	public static void createAdminlist(File adminFile) {
		
		/**
		 * @throws ArrayIndexOutOfBounds
		 * 		   배열의 요소의 범위를 벗어나면 예외를 던짐
		 */
		try {
			BufferedReader adminReader = new BufferedReader(new FileReader(adminFile));
			
			String line = null;
			
			while((line = adminReader.readLine()) != null) {
				
				String[] admin = line.split(",");
				
				adminData manager = new adminData(admin[0], admin[1], admin[2], admin[3], admin[4], admin[5]);
				
				list.add(manager);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}