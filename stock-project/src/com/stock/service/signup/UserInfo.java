package com.stock.service.signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.db.DBUtil;
import com.stock.data.Member;
import com.stock.view.UserMenu;

/**
 * 
 * 개인정보를 조회하고 수정하는 클래스
 * @author 6조
 *
 */
public class UserInfo {

    private static final String HISTORY_DIR_PATH = ".\\dat\\tradinghistory\\" + Login.loginUser.getId();
    private static final String HISTORY_BUY_LIST_FILE_PATH = HISTORY_DIR_PATH + "\\buyList.txt";
    private static final String HISTORY_HISTORY_FILE_PATH = HISTORY_DIR_PATH + "\\history.txt";
    private static List<Member> originMemberList = new ArrayList<Member>();

    /**
     * 회원정보를 출력하는 메소드
     */
    private static void printInfo() { //회원정보 출력
        System.out.println("\n\n\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t  [회원정보]");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t이름\t: " + Login.loginUser.getName());
        System.out.println("\t\t\t\t\t\t\t\tID\t: " + Login.loginUser.getId());
        System.out.println("\t\t\t\t\t\t\t\tPW\t: ******");
        System.out.println("\t\t\t\t\t\t\t\t연락처\t: " + Login.loginUser.getTel());
        System.out.println("\t\t\t\t\t\t\t\t생년월일\t: " + Login.loginUser.getBirth());
        System.out.println("\t\t\t\t\t\t\t\t이메일\t: " + Login.loginUser.getEmail());

    }

    /**
     * 회원의 입력을 받아 전달하는 메소드
     * @return 회원이 입력한 번호
     */
    private static String userInput() { //회원 입력 받기

        Scanner scan = new Scanner(System.in);

        System.out.print("\t\t\t\t\t\t\t\t번호 입력: ");
        String input = scan.nextLine();

        return input;

    }

    /**
     * 회원정보 수정 메뉴를 출력하는 메소드
     */
    private static void printModifyInfo() {

        System.out.println("\n");
        printInfo();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t[회원탈퇴]");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t수정하실 정보의 번호를 선택하세요.");
        System.out.println("\t\t\t\t\t\t\t\t(1. pw / 2. 이메일 / 3. 연락처 / 4. 회원탈퇴 / 0. 메인화면으로 이동)\n");

    }

    /**
     * 회원정보를 수정하는 메소드
     * 
     * @see #modifyPw()
     * @see #modifyEmail()
     * @see #modifyTel()
     * @see #withdrawl()
     * 
     */
    private static void modifyInfo() {

        printModifyInfo();
        String user = userInput();

        readUserAccountFile();

        if(user.equals("0")) {
            UserMenu.UI();

        } else if(user.equals("1")) {
            modifyPw();

        } else if(user.equals("2")) {
            modifyEmail();

        } else if(user.equals("3")) {
            modifyTel();

        } else if(user.equals("4")) {
            withdrawl();

        } else {
            System.out.println("\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
            modifyInfo();

        }

    }

    /**
     * 비밀번호를 변경하는 메소드
     */
    private static void modifyPw() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\n\t\t\t\t\t\t\t\t새로운 비밀번호를 입력해 주세요 : ");
        String newPw = scan.nextLine();

        if(!checkSame(Login.loginUser.getPw(), newPw)) {   //이전 비밀번호와 다름
            Login.loginUser.setPw(newPw);
            modifyUserFile("pw", newPw);
            System.out.println("\t\t\t\t\t\t\t\tpw 변경이 완료되었습니다.");
            modifyInfo();

        } else {            //이전 비밀번호와 동일함
            System.out.println("\t\t\t\t\t\t\t\t기존 pw와 동일합니다.");
            modifyPw();
        }

    }

    /**
     * 연락처를 변경하는 메소드
     */
    private static void modifyTel() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\n\t\t\t\t\t\t\t\t새로운 연락처를 입력해 주세요 : ");
        String newTel = scan.nextLine();

        if(!checkSame(Login.loginUser.getTel(), newTel)) {

            if(newTel.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) {
                Login.loginUser.setTel(newTel);
                modifyUserFile("tel", newTel);
                System.out.println("\t\t\t\t\t\t\t\t연락처 변경이 완료되었습니다.");
                modifyInfo();

            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t[연락처 항목이 형식과 일치하지 않습니다.]");
                modifyTel();

            }

        } else {
            System.out.println("\t\t\t\t\t\t\t\t기존 연락처와 동일합니다.");
            modifyTel();
        }

    }

    /**
     * 이메일을 변경하는 메소드
     */
    private static void modifyEmail() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\n\t\t\t\t\t\t\t\t새로운 이메일 주소를 입력해 주세요 : ");
        String newEmail = scan.nextLine();

        if(!checkSame(Login.loginUser.getEmail(), newEmail)) {

            if(newEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                Login.loginUser.setEmail(newEmail);
                modifyUserFile("email", newEmail);
                System.out.println("\t\t\t\t\t\t\t\t이메일 주소 변경이 완료되었습니다.");
                modifyInfo();

            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t[이메일 항목이 형식과 일치하지 않습니다.]");
                modifyEmail();
            }

        } else {
            System.out.println("\t\t\t\t\t\t\t\t기존 이메일 주소와 동일합니다.");
            modifyEmail();
        }

    }

    /**
     * 변경하려는 정보가 기존 정보와 동일한지 확인하는 메소드
     * @param origin 기존 정보
     * @param change 변경 정보
     * @return 기존 정보와 동일하면 true / 동일하지 않다면 false
     */
    private static boolean checkSame(String origin, String change) {

        if(origin.equals(change)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * user 데이터 파일을 읽어오는 메소드
     * @return user 정보를 담은 List
     */
    public static List<Member> readUserAccountFile() {

        originMemberList = Member.createMember();

        return originMemberList;

    }

    /**
     * 회원정보 수정 내용을 저장하는 메소드
     * @param category 변경 종류(pw/이메일/연락처)
     * @param content 변경 내용
     * @see #writeUserAccountFile(ArrayList)
     */
    private static void modifyUserFile(String category, String content) {

        Connection con = null;
        PreparedStatement pstat = null;
        
        try {
            
            con = DBUtil.open();
            String sql = "";
            
            String no = "";

            for(Member mb : originMemberList) {

                if(Login.loginUser.getNo().equals(mb.getNo())) {
                    
                    no = mb.getNo();

                    if(category.equals("pw")) {
                        mb.setPw(content);
                        String pw = mb.getPw();
                        
                        sql = "UPDATE TBLUSER SET PW=? WHERE USER_SEQ=?";
                        pstat = con.prepareStatement(sql);
                        
                        pstat.setString(1, pw);
                        pstat.setInt(2, Integer.parseInt(no));
                        
                        break;

                    } else if(category.equals("tel")) {
                        mb.setTel(content);
                        String tel = mb.getTel();
                        
                        sql = "UPDATE TBLUSER SET TEL=? WHERE USER_SEQ=?";
                        pstat = con.prepareStatement(sql);
                        
                        pstat.setString(1, tel);
                        pstat.setInt(2, Integer.parseInt(no));
                        
                        break;

                    } else {
                        mb.setEmail(content);
                        String email = mb.getEmail();
                        
                        sql = "UPDATE TBLUSER SET EMAIL=? WHERE USER_SEQ=?";
                        pstat = con.prepareStatement(sql);
                        
                        pstat.setString(1, email);
                        pstat.setInt(2, Integer.parseInt(no));
                        
                        break;
                        
                    }
                }

            }
            
            pstat.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 회원을 탈퇴하는 메소드
     * @see #removeUserDataFile()
     */
    private static void withdrawl() {

        Scanner scan = new Scanner(System.in);

        System.out.println("\n\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t회원 탈퇴 시 개인정보 및 모든 데이터가 삭제됩니다.");
        System.out.print("\t\t\t\t\t\t\t\t탈퇴하시겠습니까?(y/n) : ");
        String input = scan.nextLine();

        if(input.equalsIgnoreCase("y")) {
            removeUserData();
            System.out.println("\n\t\t\t\t\t\t\t\t회원 탈퇴가 완료되었습니다.");

        } else if(input.equalsIgnoreCase("n")) {
            modifyInfo();
        } else {
            System.out.println("\n\t\t\t\t\t\t\t\t잘못 입력하셨습니다.");
            withdrawl();

        }

    }

    /**
     * user 정보를 데이터베이스에서 모두 삭제하는 메소드
     */
    public static void deleteDBUserData(String no) {
        
        Connection con = null;
        PreparedStatement pstat = null;
        
        try {
            
            con = DBUtil.open();
            String sql = "";
            
            sql = "DELETE FROM TBLLIKE WHERE USER_SEQ="+no;
            pstat = con.prepareStatement(sql);
            pstat.executeUpdate();
            
            sql = "DELETE FROM TBLSTOCK WHERE USER_SEQ="+no;
            pstat = con.prepareStatement(sql);
            pstat.executeUpdate();
            
            sql = "DELETE FROM TBLACCOUNT WHERE USER_SEQ="+no;
            pstat = con.prepareStatement(sql);
            pstat.executeUpdate();
            
            sql = "DELETE FROM TBLTRADING WHERE USER_SEQ="+no;
            pstat = con.prepareStatement(sql);
            pstat.executeUpdate();
            
            sql = "DELETE FROM TBLUSER WHERE USER_SEQ="+no;
            pstat = con.prepareStatement(sql);
            pstat.executeUpdate();
            
            pstat.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * user 정보를 삭제하는 메소드
     * 개인정보, 거래 내역, 관심 주식 목록, 보유 주식, 보유 자산 삭제
     */
    private static void removeUserData() {
        
        try {
            List<Member> originMemberList = readUserAccountFile();
            
            String no = "";

            for(int i = 0; i < originMemberList.size(); i++) {

                if(Login.loginUser.getNo().equals(originMemberList.get(i).getNo())) {
                    no = originMemberList.get(i).getNo();
                    break;
                }

            }
            
            deleteDBUserData(no);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 개인정보를 조회하고 수정하는 메소드
     */
    public static void info() {

        printInfo();
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t1번 입력: 회원정보 수정 \t\t\t  0번 입력: 메인화면으로 이동\n");

        String user = userInput();
        if(user.equals("1")) {
            modifyInfo();

        } else if(user.equals("0")) {
            UserMenu.UI();

        } else {
            System.out.println("\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
            info();

        }

    }

}