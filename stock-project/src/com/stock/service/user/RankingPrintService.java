package com.stock.service.user;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.db.DBUtil;
import com.stock.data.Member;
import com.stock.service.signup.Login;
import com.stock.service.signup.UserInfo;
import com.stock.view.UserMenu;

/**
 * 회원들의 랭킹을 출력하는 클래스
 * @author 6조
 *
 */
public class RankingPrintService {

    private static final String ASSETS_PATH = ".\\dat\\account\\user.txt";
    private static final File ASSETS_FILE = new File(ASSETS_PATH);

    private static HashMap<String, Integer> userAssets = new HashMap<String, Integer>();

    /**
     * 회원 정보가 들어있는 데이터에서 총 자산과 고유 번호를 읽어오는 메소드
     */
    private static void readAssets() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {

            con = DBUtil.open();
            
            String sql = "SELECT MEMBER_SEQ, TOTALACCOUNT FROM TBLACCOUNT";
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()) {
                
                String memberSeq = rs.getString("MEMBER_SEQ");
                int totalAccount = rs.getInt("TOTALACCOUNT");

                RankingPrintService.userAssets.put(memberSeq, totalAccount);

            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 회원들을 총 자산을 기준으로 내림차순 정렬하는 메소드
     * 총 자산이 동일한 경우 고유 넘버를 기준으로 오름차순 정렬
     * @return 고유넘버와 총자산이 저장된 리스트
     */
    private static List<Entry<String, Integer>> sortAssets() {

        readAssets();

        List<Entry<String, Integer>> assetsEntryList = new ArrayList<Entry<String,Integer>>(userAssets.entrySet());

        Collections.sort(assetsEntryList, (o1, o2) -> {

            if(o1.getValue() > o2.getValue()) {
                return -1;

            } else if (o1.getValue() < o2.getValue()) {
                return 1;

            } else {

                int n1 = Integer.parseInt(o1.getKey());
                int n2 = Integer.parseInt(o2.getKey());

                return n1 - n2;
            }
        });

        return assetsEntryList;

    }

    private static String makeUserIdName(String no) {

        ArrayList<Member> userInfoList = UserInfo.readUserAccountFile();

        String id = "";
        String name = "";

        for(Member m : userInfoList) {

            if(m.getNo().equals(no)) {
                id = m.getId();
                name = m.getName();
                break;
            }
        }

        id = protectId(id);
        name = protectName(name);

        String idName = String.format("%s(%s)", id, name);

        return idName;

    }

    /**
     * 아이디 뒤 세자리를 *** 처리하는 메소드
     * @param id 회원 아이디
     * @return 보호 처리된 아이디
     */
    private static String protectId(String id) {

        String protectedId = "";

        for(int i = 0; i < id.length()-3; i++) {
            protectedId += id.charAt(i);
        }

        for(int i = id.length()-3; i < id.length(); i++) {
            protectedId += "*";
        }

        return protectedId;
    }

    /**
     * 이름의 마지막 글자를 X 처리하는 메소드
     * @param name 회원 이름
     * @return 보호 처리된 이름
     */
    private static String protectName(String name) {

        String protectedName = "";

        for(int i = 0; i < name.length()-1; i++) {
            protectedName += name.charAt(i);
        }

        protectedName += "X";

        return protectedName;
    }

    /**
     * 사용자의 입력을 받는 메소드
     */
    private static void userInput() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\n\t\t\t\t\t\t\t\t번호 입력: ");
        String input = scan.nextLine();

        if(input.equals("0")) {
            UserMenu.UI();

        } else {
            System.out.println("\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
            userInput();
        }

    }

    /**
     * 로그인한 회원의 랭킹을 알아내는 메소드
     * @param assetsEntryList 정렬된 전체 회원의 자산 정보
     * @return 로그인한 회원의 랭킹
     */
    private static int findMyRanking(List<Entry<String, Integer>> assetsEntryList) {

        String no = Login.loginUser.getNo();
        int rank = 0;

        for(int i = 0; i < assetsEntryList.size(); i++) {

            String entryListNo = assetsEntryList.get(i).getKey();

            if(entryListNo.equals(no)) {
                rank = i+1;
                break;

            }
        }

        return rank;

    }

    /**
     * 총 자산이 많은 순서대로 10명의 랭킹과 로그인한 회원의 랭킹을 출력하는 메소드
     */
    public static void printRanking() {


        List<Entry<String, Integer>> assetsEntryList = sortAssets();

        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t  [RANKING]");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.printf("\t\t\t\t\t\t\t\t  내 랭킹: %d\n", findMyRanking(assetsEntryList));
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\n\t\t\t\t\t\t\t\t  [랭킹]\t\t\t [아이디(이름)]\t\t   [총 자산]\n");


        int count = 1;

        String no = assetsEntryList.get(0).getKey();
        Integer totalAssets =  assetsEntryList.get(0).getValue();
        System.out.printf("\t\t\t\t\t\t\t\t   %2d\t\t\t%s\t%,20d\n" , 1, makeUserIdName(no), totalAssets);

        for(int i = 1; i < 10; i++) {

            no = assetsEntryList.get(i).getKey();
            totalAssets =  assetsEntryList.get(i).getValue();

            if(!(totalAssets.equals(assetsEntryList.get(i-1).getValue()))) {
                count++;
            }

            System.out.printf("\t\t\t\t\t\t\t\t   %2d\t\t\t%s\t%,20d\n" , count, makeUserIdName(no), totalAssets);

        }

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t(0번 입력 : 메인 화면으로 이동)");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");

        userInput();
    }

}
