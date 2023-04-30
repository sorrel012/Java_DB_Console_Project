package com.stock.service.signup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.db.DBUtil;
import com.stock.data.Member;
import com.stock.data.UserData;
import com.stock.data.adminData;
import com.stock.view.Admin;
import com.stock.view.LoginUI;
import com.stock.view.Main;
import com.stock.view.UserMenu;
/**
 * 로그인 기능을 담당하는 클래스
 * @author
 *
 */
public class Login {

    static Scanner scan = new Scanner(System.in);

    public static UserData loginUser;	// 로그인 완료한 회원 정보를 담기 위한 변수
    public static adminData loginAdmin;	// 로그인 완료한 관리자 정보를 담기 위한 변수

    /**
     * 입력받은 정보를 확인해서 로그인을 하는 메소드
     */
    public static void loginMenu() {

        boolean loop = true;

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            while(loop) {

                con = DBUtil.open();
                st = con.createStatement();

                LoginUI.loginBoard();

                System.out.print("\t\t\t\t\t\t\t\t\t\t\t아이디: ");
                String id = scan.nextLine();


                System.out.print("\t\t\t\t\t\t\t\t\t\t\t비밀번호: ");
                String pw = scan.nextLine();
                System.out.println();

                //회원 아이디와 패스워드 검사

                List<List<String>> users = Member.createMemberList();

                for(List<String> tmpUserList : users) {

                    if(id.equals(tmpUserList.get(2)) && pw.equals(tmpUserList.get(3))) {
                        System.out.printf("\t\t\t\t\t\t\t\t\t\t%s님 로그인에 성공하셨습니다.", id);

                        String sql = "SELECT TOTALACCOUNT, AVAILACCOUNT FROM TBLACCOUNT WHERE USER_SEQ="+tmpUserList.get(0);
                        rs = st.executeQuery(sql);
                        
                        int totalAccount = 0;
                        int availAccount = 0;
                        
                        if(rs.next()) {
                            totalAccount = rs.getInt("TOTALACCOUNT");
                            availAccount = rs.getInt("AVAILACCOUNT");
                        }

                        loginUser = new UserData(tmpUserList.get(0), tmpUserList.get(1), tmpUserList.get(2), tmpUserList.get(3), tmpUserList.get(4), tmpUserList.get(5), tmpUserList.get(6),
                                totalAccount, availAccount);

                        loop = false;
                        UserMenu.UI(); //회원 메뉴 출력
                        break;
                    }
                }

                //관리자 아이디와 패스워드 검사

                List<List<String>> admins = adminData.readAdminData();

                for(List<String> tmpdAminList : admins) {

                    if(id.equals(tmpdAminList.get(2)) && pw.equals(tmpdAminList.get(3))) {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t관리자로 로그인합니다.");

                        loginAdmin = new adminData(tmpdAminList.get(0), tmpdAminList.get(1), tmpdAminList.get(2), tmpdAminList.get(3), tmpdAminList.get(4), tmpdAminList.get(5));

                        loop = false;
                        Admin.AdminMenu();
                        break;
                    }

                }

                //회원과 관리자에서 일치하는 아이디가 없을때
                if(loop) {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t로그인에 실패하셨습니다.");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t메인메뉴로 돌아갑니다.");
                    Main.mainMenu();
                    break;
                }
                
            }//while

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//loginMenu

}//class


