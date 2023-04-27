package com.stock.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.stock.service.signup.Login;

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
     * 회원 데이터를 불러와서 Member 클래스에 저장하는 메소드
     */
    public static void createMember() {

        List<List<String>> users = createMemberList();
        List<List<String>> accounts =createAccountList();
        
        for (int i = 0; i < users.size(); i++) {
            
            String no = users.get(i).get(0);
            String name = users.get(i).get(1);
            String id = users.get(i).get(2);
            String pw = users.get(i).get(3);
            String tel = users.get(i).get(4);
            String birthday = users.get(i).get(5);
            String email = users.get(i).get(6);
            String totalAcc = accounts.get(i).get(0);
            String availAcc = accounts.get(i).get(1);
            

            Member member = new Member(no, name, id, pw, tel, birthday, email, totalAcc, availAcc);
            members.add(member);
            
        }
    }
    

    /**
     * 데이터베이스에서 회원 개인정보를 불러오는 메소드
     */
    public static List<List<String>> createMemberList() {
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        List<List<String>> users = new ArrayList<List<String>>();
        
        try {
            
            con = DBUtil.open();

            String sql = "SELECT * FROM TBLMEMBER ORDER BY MEMBER_SEQ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                
                List<String> tmpUserList = new ArrayList<String>();
                        
                String no = rs.getString("MEMBER_SEQ");
                String name = rs.getString("NAME");
                String id = rs.getString("ID");
                String pw = rs.getString("PW");
                String tel = rs.getString("TEL");
                String birthday = rs.getString("BIRTH");
                String email = rs.getString("EMAIL");
                
                tmpUserList.add(no);
                tmpUserList.add(name);
                tmpUserList.add(id);
                tmpUserList.add(pw);
                tmpUserList.add(tel);
                tmpUserList.add(birthday);
                tmpUserList.add(email);
                
                users.add(tmpUserList);
                
            }
            
            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return users;
    }
    
    
    /**
     * 데이터베이스에서 회원 자산정보를 불러오는 메소드
     */
    public static List<List<String>> createAccountList() {
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        List<List<String>> accounts = new ArrayList<List<String>>();
        
        try {
            
            con = DBUtil.open();
            
            String sql = "SELECT * FROM TBLACCOUNT ORDER BY MEMBER_SEQ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                
                List<String> tmpAccountList = new ArrayList<String>();
                
                String totalAcc = rs.getString("TOTALACCOUNT");
                String availAcc = rs.getString("AVAILACCOUNT");
                
                tmpAccountList.add(totalAcc);
                tmpAccountList.add(availAcc);
                
                accounts.add(tmpAccountList);
                
            }
            
            return accounts;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return accounts;
    }
        

    /**
     *
     * 계정 정보를 업데이트 시키는 메소드
     */
    public static void updateAccountInfo(TradeData td, String type) {

        Connection con = null;
        PreparedStatement pstat = null;
        
        try {

            String targetId = Login.loginUser.getId(); //멤버 고유번호
            int newAmount = 0; //가용자산
            int totalAmount = 0; //총자산

            if (type.equals("매수")) {
                newAmount = td.getTotalAvailableAssets();
                Login.loginUser.setAvailableAssets(newAmount);
                totalAmount = td.getTotalAssets() + Login.loginUser.getAvailableAssets();

            } else {
                newAmount = Login.loginUser.getAvailableAssets() + td.getTotalPrice();
                Login.loginUser.setAvailableAssets(newAmount);
                totalAmount = td.getTotalAssets() + Login.loginUser.getAvailableAssets();
            }

            con = DBUtil.open();
            
            String sql = "UPDATE TBLACCOUNT SET TOTALACCOUNT=?, AVAILACCOUNT=? WHERE MEMBER_SEQ=?";
            pstat = con.prepareStatement(sql);
            
            pstat.setInt(1, totalAmount);
            pstat.setInt(2, newAmount);
            pstat.setInt(3, Integer.parseInt(targetId));

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
