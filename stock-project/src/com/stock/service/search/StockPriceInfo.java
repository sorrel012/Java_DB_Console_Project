package com.stock.service.search;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.db.DBUtil;
import com.stock.service.signup.Login;
import com.stock.view.Admin;
import com.stock.view.Main;
import com.stock.view.UserMenu;

public class StockPriceInfo {


    /**
     * 접근자에 따른 분류 메뉴 호출
     * @param A == 0 비회원 메뉴
     * @param A == 1 회원 메뉴
     * @param A == 2 관리자 메뉴
     */
    private static void definition(int A) {

        if (A == 0) {
            Main.mainMenu();//비회원

        } else if (A == 1) {
            UserMenu.UI();//회원

        } else if (A == 2) {
            Admin.AdminMenu();//관리자
        }

    }

    /**
     * 시세정보 메뉴 UI 출력, 접근자 분류하는 메소드
     * 
     */
    public static void UI() {

        System.out.printf("\n\n%96s\n", "[시세 정보]");
        System.out.println("\t\t\t\t\t\t\t\t=================================================================\n");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t 1. 코스피");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t 2. 코스닥\n");
        System.out.println("\t\t\t\t\t\t\t\t=================================================================");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력: 메인화면으로 이동)");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");

        //접근자에 따른 분류 메뉴
        if (Login.loginUser != null) {
            input(1);//회원

        } else if (Login.loginAdmin != null) {
            input(2);//관리자

        } else {
            input(0);//비회원
        }

    }// UI

    public static void extractRanking() {

        Connection con = null;
        Statement st = null;

        try {
            
            con = DBUtil.open();
            st = con.createStatement();
            
            String sql = "create or replace view vwKospiRise as "
                    + "select * from tblkospi where (SUBSTR(rate, 1, 1) = '+') order by TO_NUMBER(REGEXP_REPLACE(rate, '[^0-9.]', '')) desc";
            st.executeUpdate(sql);
            
            sql = "create or replace view vwKospiFall as "
                    + "select * from tblkospi where (SUBSTR(rate, 1, 1) = '-') order by TO_NUMBER(REGEXP_REPLACE(rate, '[^0-9.]', '')) desc";
            st.executeUpdate(sql);
            
            sql = "create or replace view vwKosdaqRise as "
                    + "select * from tblkosdaq where (SUBSTR(rate, 1, 1) = '+') order by TO_NUMBER(REGEXP_REPLACE(rate, '[^0-9.]', '')) desc";
            st.executeUpdate(sql);
            
            sql = "create or replace view vwKosdaqFall as "
                    + "select * from tblkosdaq where (SUBSTR(rate, 1, 1) = '-') order by TO_NUMBER(REGEXP_REPLACE(rate, '[^0-9.]', '')) desc";
            st.executeUpdate(sql);
            
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

    /**
     * 파일에서 코스피,코스닥의 시세정보의 상승,하락 Top10 추출하는 메소드
     * @param path 코스피,코스닥의 상승,하락 각각의 종류를 입력받는 곳
     */
    private static void output(String stockRankSort) {
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            
            con = DBUtil.open();
            st = con.createStatement();
            
            String sql = "SELECT * FROM VW"+stockRankSort;
            rs = st.executeQuery(sql);

            System.out.println(
                    "\t\t\t\t========================================================================================================================\n");

            System.out.println("\t\t\t\t   [순위]\t[종목명]\t\t\t\t[현재가]\t\t[등락률]\t\t[거래량]\t\t[PER]\t\t[ROE]");

            int rank = 1;
            while (rs.next()) {
                
                String name = "";

                if (rs.getString("STOCKNAME").length() > 12) {
                    name = rs.getString("STOCKNAME").substring(0, 11) + "..";
                } else {
                    name = rs.getString("STOCKNAME");
                }

                System.out.printf("\t\t\t\t   %3s\t\t%-15s  \t\t%-10s\t%-15s\t%-8s\t%-8s\t%-8s\n", rank++ + "", name, rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                if (rank == 11) {
                    break;
                }
                
            } // while

            System.out.println(
                    "\t\t\t\t========================================================================================================================\n");
            
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// output

    /**
     * 사용자에게서 입력받은 입력값에 따라 시세정보 코스피, 코스닥 상승,하락 Top10을 출력하는 메소드
     * @param A 분류된 접근자
     */
    private static void input(int A) {
        Scanner scan = new Scanner(System.in);
        String sel = "";
        boolean loop = true;

        // 시세정보 메뉴에서 입력값받는 곳
        while (loop) {
            
            sel = scan.nextLine();

            if (sel.equals("0")) {

                definition(A);
                loop = false;

            } else if (sel.equals("1")) {

                System.out.printf("\n\n%100s\n", "[코스피 상승Top10 시세 정보]");
                output("KOSPIRISE");
                System.out.println();

                System.out.printf("\n%100s\n", "[코스피 하락Top10 시세 정보]");
                output("KOSPIFALL");
                loop = false;

            } else if (sel.equals("2")) {

                System.out.printf("\n\n%100s\n", "[코스닥 상승Top10 시세 정보]");
                output("KOSDAQRISE");
                System.out.println();

                System.out.printf("\n%100s\n", "[코스닥 하락Top10 시세 정보]");
                output("KOSDAQFALL");
                loop = false;

            } else {
                System.out.println("\t\t\t\t\t\t\t\t=================================================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t  잘못된 번호를 입력하셨습니다.");
                System.out.println("\t\t\t\t\t\t\t\t\t\t   번호를 다시 입력해주세요.");
                System.out.println("\t\t\t\t\t\t\t\t=================================================================");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
            }
        } // while

        // 코스피,코스닥 출력후 메인메뉴로 복귀
        if (!sel.equals("0")) {

            sel = "";
            loop = true;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력: 메인화면으로 이동)");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");

            while (loop) {
                sel = scan.nextLine();

                if (sel.equals("0")) {

                    //접근자에 따른 분류 메뉴
                    definition(A);
                    loop = false;

                } else {
                    System.out.println(
                            "\t\t\t\t\t\t\t\t=================================================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t  잘못된 번호를 입력하셨습니다.");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t   번호를 다시 입력해주세요.");
                    System.out.println(
                            "\t\t\t\t\t\t\t\t=================================================================");
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t번호 입력: ");
                }
            } // while
            scan.close();
        } // if
    }// input
}// class
