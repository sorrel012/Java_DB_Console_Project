package com.stock.service.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.db.DBUtil;
import com.stock.data.AllStockList;
import com.stock.data.TradeData;
import com.stock.service.signup.Login;
import com.stock.view.TradeView;
import com.stock.view.UserMenu;

/**
 * 매수 & 매도관련 유효성 검사 클래스
 * @author 6조
 *
 */
public class TradeValidator {

    /**
     * 존재하는 주식명인지 체크하는 메소드
     */
    public static boolean checkStockName(TradeData td) {

        boolean result = false;

        List<List<String>> allStocks = AllStockList.storeAllStockList();

        for (List<String> tmpList : allStocks) {

            if (tmpList.get(1).equals(td.getStockName())) {
                td.setPriceNow(tmpList.get(2));
                result = true;
                break;
            }

        }


        if (!result) {
            System.out.println("\t\t\t\t\t\t\t\t==========================================================");
            System.out.println("\t\t\t\t\t\t\t\t존재하지 않는 주식명을 입력하셨습니다.");
            System.out.println("\t\t\t\t\t\t\t\t회원 메뉴로 이동합니다.");
            UserMenu.UI();
        }

        return result;

    }

    /**
     * 매수 가능한 수량인지 체크하는 메소드
     */
    public static boolean checkOrderQuantity(TradeData td) {

        boolean result = false;

        int moneyNow = Login.loginUser.getAvailableAssets();
        int priceNow = td.getTotalPrice();

        if (moneyNow >= priceNow) {
            result = true;
        } else {
            System.out.println("\t\t\t\t\t\t\t\t==========================================================");
            System.out.println("\t\t\t\t\t\t\t\t보유 가용 자산이 부족합니다.");
            System.out.println("\t\t\t\t\t\t\t\t매수량을 다시 입력해 주세요");
            TradeView.showBuyScreen();
        }

        return result;

    }

    /**
     * 보유중인 주식인지 확인하는 메소드
     */
    public static boolean hasStock(TradeData td) {

        boolean result = false;

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            con = DBUtil.open();
            st = con.createStatement();

            String sql = "SELECT COUNT(*) AS CNT FROM TBLSTOCK WHERE STOCKNAME="+td.getStockName();
            rs = st.executeQuery(sql);

            int cnt = 0;

            if(rs.next()) {
                cnt = rs.getInt("CNT");
            }


            if (cnt != 0) {
                result = true;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t매수 내역이 없습니다.");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t회원 메뉴로 이동합니다.");
                UserMenu.UI();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 판매 수량이 보유 수량을 초과하는지 확인하는 메소드
     */
    public static boolean checkSellQuantity(TradeData td) {

        boolean result = false;

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            con = DBUtil.open();
            st = con.createStatement();

            String sql = "SELECT VOLUME FROM TBLSTOCK WHERE STOCKNAME="+td.getStockName();
            rs = st.executeQuery(sql);

            int volume = 0;

            if(rs.next()) {
                volume = rs.getInt("VOLUME");
            }


            if (volume >= Integer.parseInt(td.getVolume())) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!result) {
            System.out.println("\t\t\t\t\t\t\t\t==========================================================");
            System.out.println("\t\t\t\t\t\t\t\t보유 중인 주식 수량을 초과하였습니다.");
            System.out.println("\t\t\t\t\t\t\t\t매도 수량을 다시 입력해 주세요");
            TradeView.showSellScreen();
        }

        return result;

    }

}
