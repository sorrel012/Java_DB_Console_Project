package com.stock.service.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.db.DBUtil;
import com.stock.data.adminData;
import com.stock.view.Admin;
/**
 * 관리자 계정을 추가하는 클래스
 * @author 6조
 */
public class AdminRegistrationService {

    /**
     * 관리자 게정 추가하는 메소드
     */
    public static void add() {

        Scanner sc = new Scanner(System.in);

        adminData.createAdminlist();

        String no = adminData.list.stream()
                .mapToInt(m -> Integer.parseInt(m.getNo()))
                .max().getAsInt() + 1 + "";


        System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자 계정 추가를 위한 정보를 입력하세요.]");
        System.out.println();

        System.out.print("\t\t\t\t\t\t\t\t\t\t이름 \t: ");
        String name = sc.nextLine();

        System.out.print("\t\t\t\t\t\t\t\t\t\t아이디 \t: ");
        String id = sc.nextLine();

        System.out.print("\t\t\t\t\t\t\t\t\t\t비밀번호 \t: ");
        String pw = sc.nextLine();

        System.out.print("\t\t\t\t\t\t\t\t\t\t연락처 \t: ");
        String tel = sc.nextLine();

        System.out.print("\t\t\t\t\t\t\t\t\t\t이메일 \t: ");
        String email = sc.nextLine();


        adminData manager = new adminData(no, name, id, pw, tel, email);

        boolean isValid = true;
        isValid = isValid && AdminAccountValidator.checkName(manager);				//이름 형식 체크
        isValid = isValid && AdminAccountValidator.checkIdFormat(manager);			//아이디 형식 체크
        isValid = isValid && AdminAccountValidator.checkPasswordFormat(manager);		//패스워드 형식 체크
        isValid = isValid && AdminAccountValidator.checkPhoneFormat(manager);			//연락처 형식 체크
        isValid = isValid && AdminAccountValidator.checkEmailFormat(manager);			//이메일 형식 체크


        if (isValid) {

            Connection con = null;
            PreparedStatement pstat = null;

            try {

                con = DBUtil.open();
                
                String sql = "INSERT INTO TBLADMIN VALUES (?,?,?,?,?,?)";
                pstat = con.prepareStatement(sql);
                
                pstat.setInt(1, Integer.parseInt(no));
                pstat.setString(2, name);
                pstat.setString(3, id);
                pstat.setString(4, pw);
                pstat.setString(5, tel);
                pstat.setString(6, email);
                
                pstat.executeUpdate();
                
                adminData.list.add(manager);
                System.out.println("\t\t\t\t\t\t\t\t\t\t[관리자 계정이 추가되었습니다.]");

                pstat.close();
                con.close();

                Admin.AdminMenu();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("\t\t\t\t\t\t\t\t\t\t[계정 추가에 실패했습니다.]");

            Admin.AdminMenu();

        }



    }
}
