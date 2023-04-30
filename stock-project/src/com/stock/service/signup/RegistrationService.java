package com.stock.service.signup;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.db.DBUtil;
import com.stock.data.Member;
import com.stock.view.Main;
import com.stock.view.Menu;

/**
 * 계정을 생성하는 클래스 입니다.
 * @author 6조
 * @since 2022.03.08
 */
public class RegistrationService {

    /**
     * 회원가입한 신규 회원의 정보를 저장하는 메소드
     */
    public static void signUp() {

        Member.createMemberList();

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

            Connection con = null;
            PreparedStatement pstat = null;

            try {
                
                con = DBUtil.open();

                int no = Integer.parseInt(member.getNo());
                String name = member.getName();
                String id = member.getId();
                String pw = member.getPw();
                String tel = member.getTel();
                String birth = member.getBirthday();
                String email = member.getEmail();
                int totalAccount = Integer.parseInt(member.getMoney());
                int availAccount = Integer.parseInt(member.getAvailableAssets());
                
                String sql = "INSERT INTO TBLUSER VALUES(?,?,?,?,?,?,?)";
                pstat = con.prepareStatement(sql);
                
                pstat.setInt(1, no);
                pstat.setString(2, name);
                pstat.setString(3, id);
                pstat.setString(4, pw);
                pstat.setString(5, tel);
                pstat.setString(6, birth);
                pstat.setString(7, email);
                
                pstat.executeUpdate();
                
                sql = "INSERT INTO TBLACCOUNT VALUES(?,?,?,?)";
                pstat = con.prepareStatement(sql);

                pstat.setInt(1, no);
                pstat.setInt(2, no);
                pstat.setInt(3, totalAccount);
                pstat.setInt(4, availAccount);
                
                pstat.executeUpdate();

                Member.members.add(member);
                System.out.println("\t\t\t\t\t\t\t\t\t\t[회원가입이 완료되었습니다.]");
                
                pstat.close();
                con.close();

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
