package com.stock.service.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.db.DBUtil;
import com.stock.service.signup.Login;

/**
 * My Stock - 자산 확인
 * [3. 자산 초기화] 메뉴를 구성하는 클래스
 * @author 6조
 *
 */
public class AssetReset {

    private final static String BUYLISTPATH = ".\\dat\\tradinghistory\\" + Login.loginUser.getId() + "\\buyList.txt";
    private final static String HISTORYPATH = ".\\dat\\tradinghistory\\" + Login.loginUser.getId() + "\\history.txt";
    private final static File BUYLIST = new File(BUYLISTPATH);
    private final static File HISTORY = new File(HISTORYPATH);

    /**
     * [3. 자산 초기화] 메뉴 UI를 출력하는 클래스
     */
    public static void assetsReset() {

        try {

            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t=================================================================");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t　[자산 초기화] ");
            System.out.println("\t\t\t\t\t\t\t\t=================================================================");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t　⚠ 자산 초기화 시 보유 자산 및 투자 내역이 모두 초기화됩니다 ⚠\n");
            System.out.printf("\t\t\t\t\t\t\t\t\t　　　　　　현재 %s님의 총자산은 %,d원 입니다.\n\n", Login.loginUser.getName(), Login.loginUser.getMoney());
            System.out.println("\t\t\t\t\t\t\t\t=================================================================");

            Scanner scan = new Scanner(System.in);

            while (true) {

                System.out.print("\t\t\t\t\t\t\t\t\t\t　　　　초기화 하시겠습니까?(y/n) ");
                String inputEnd = scan.next();

                //자산 초기화 동의 y
                if (inputEnd.equals("y")) {
                    //resetAsset();
                    printResetAsset();
                    break;

                    //자산 초기화 비동의 n
                } else if (inputEnd.equals("n")) {
                    System.out.println();
                    System.out.println();

                    AssetInfo.inputEnter();
                    break;


                    // y/n 이외 입력 시
                } else {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\ty/n만 입력 가능합니다.");
                    System.out.println();
                }

            }//while

            AssetInfo.inputEnter();


        } catch (Exception e) {//FileNotFoundException e) {

            e.printStackTrace();
        }

    }

    /**
     * 자산 초기화 완료 문구 출력 메소드
     */
    private static void printResetAsset() {

        resetHistory();
        resetBuyList();
        readUserList();

        Login.loginUser.setMoney(10000000);
        Login.loginUser.setAvailableAssets(10000000);

        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t　　　　자산 초기화가 완료되었습니다.");
        System.out.printf("\t\t\t\t\t\t\t\t\t　　　　　현재 %s님의 총자산은 %,d원 입니다.\n\n", Login.loginUser.getName(), Login.loginUser.getMoney());
        System.out.println();
        System.out.println();

    }


    /**
     * 총자산, 가용자산을 초기값으로 되돌리는 메소드
     */
    private static void readUserList() {
        
        Connection con = null;
        PreparedStatement pstat = null;
        
        try {
            
            con = DBUtil.open();
            
            String sql = "UPDATE TBLACCOUNT SET TOTALACCOUNT=10000000, AVAILACCOUNT=10000000 WHERE USER_SEQ = ?";
            pstat = con.prepareStatement(sql);
            
            pstat.setInt(1, Integer.parseInt(Login.loginUser.getNo()));
            
            pstat.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 자산 초기화 시 매수/매도 내역이 적혀있는 txt 파일을 함께 초기화하는 메소드
     */
    private static void resetHistory() {

        try {

            if (HISTORY.exists()) {

                BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY));
                String txt = "";

                bw.write(txt);

                bw.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 자산 초기화 시 보유 종목의 수량이 적혀있는 txt 파일을 함께 초기화하는 메소드
     */
    private static void resetBuyList() {

        try {

            if (BUYLIST.exists()) {

                BufferedWriter bw = new BufferedWriter(new FileWriter(BUYLIST));
                String txt = "";

                bw.write(txt);

                bw.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



