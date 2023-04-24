package com.stock.data;

/**
 * 로그인한 회원의 정보를 담는 클래스
 * @author 6조
 *
 */
public class UserData {
	private String no;
	private String name;
	private String tel;
	private String id;
	private String pw;
	private String birth;
	private String email;
	private int money;
	private int availableAssets;

	@Override
	public String toString() {
		return "UserData [no=" + no + ", name=" + name + ", tel=" + tel + ", id=" + id + ", pw=" + pw + ", birth="
				+ birth + ", email=" + email + ", money=" + money + "]";
	}

	public UserData(String no, String name, String id, String pw, String tel, String birth, String email, int money, int availableAssets) {

		this.no = no;
		this.name = name;
		this.tel = tel;
		this.id = id;
		this.pw = pw;
		this.birth = birth;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getAvailableAssets() {
		return availableAssets;
	}

	public void setAvailableAssets(int availableAssets) {
		this.availableAssets = availableAssets;
	}
	
}
