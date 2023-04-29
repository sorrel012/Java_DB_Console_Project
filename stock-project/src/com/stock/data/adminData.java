package com.stock.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
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

    /**
     * 관리자 리스트를 생성
     */
    public static void createAdminlist() {
        
        List<List<String>> admins = readAdminData();
        
        for(List<String> tmpAdminList : admins) {

            adminData manager = new adminData(tmpAdminList.get(0), tmpAdminList.get(1), tmpAdminList.get(2), tmpAdminList.get(3), tmpAdminList.get(4), tmpAdminList.get(5));

            list.add(manager);

        }
        
    }


        /**
         * DB에서 관리자 계정을 읽어오는 메소드
         * @return 모든 관리자 계정 정보를 담은 2차원 리스트
         */
        public static List<List<String>> readAdminData() {

            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            List<List<String>> admins = new ArrayList<List<String>>();

            try {

                con = DBUtil.open();
                st = con.createStatement();

                String sql = "SELECT * FROM TBLADMIN ORDER BY ADMIN_SEQ";
                rs = st.executeQuery(sql);

                while(rs.next()) {
                    List<String> tmpAminList = new ArrayList<>();

                    String no = rs.getString("ADMIN_SEQ");
                    String name = rs.getString("NAME");
                    String id = rs.getString("ID");
                    String pw = rs.getString("PW");
                    String tel = rs.getString("TEL");
                    String email = rs.getString("EMAIL");

                    tmpAminList.add(no);
                    tmpAminList.add(name);
                    tmpAminList.add(id);
                    tmpAminList.add(pw);
                    tmpAminList.add(tel);
                    tmpAminList.add(email);

                    admins.add(tmpAminList);
                }

                rs.close();
                st.close();
                con.close();
                
                return admins;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return admins;

        }


    }