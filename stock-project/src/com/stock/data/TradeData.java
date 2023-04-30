package com.stock.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.db.DBUtil;
import com.stock.service.signup.Login;

/**
 * 주식 정보를 저장하는 클래스
 * @author 6조
 *
 */
public class TradeData {

    private String stockName;				//주식명
    private String volume;					//매수량
    private String priceNow;				//현재가
    private int totalPrice;					//총 매수금액 / 매도금액
    private int totalAssets;				//총 자산
    private int totalAvailableAssets;		//총 가용자산

    private List<String> stockNameList;
    private List<String> volumeList;

    private List<List<String>> allStocks = AllStockList.storeAllStockList();

    public TradeData(String stockName, String volume) {
        this.stockName = stockName;
        this.volume = volume;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(String priceNow) {
        this.priceNow = priceNow;
    }

    public int getTotalPrice() {
        return Integer.parseInt(priceNow.replaceAll(",", "")) * Integer.parseInt(volume.replaceAll(",", ""));
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 보유한 모든 주식의 총 가격 정보를 만드는 메소드
     */
    public int getTotalAssets() {

        int sum = 0;

        allStocks = AllStockList.storeAllStockList();
        readUserStocks();

        for (List<String> tmpList : allStocks) {

            for (int j = 0; j < this.stockNameList.size(); j++) {

                int stockPrice = 0;

                if (tmpList.get(1).equals(this.stockNameList.get(j))) {
                    stockPrice = Integer.parseInt(tmpList.get(2).replaceAll(",", ""));
                    sum += stockPrice * Integer.parseInt(this.volumeList.get(j));
                    break;
                }

            }

        }


        return sum;
    }


    /**
     * 로그인한 회원이 보유한 주식명과 주수를 DB에서 읽어오는 메소드
     */
    private void readUserStocks() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        this.stockNameList = new ArrayList<String>();
        this.volumeList = new ArrayList<String>();

        try {

            String userNo = Login.loginUser.getNo();

            con = DBUtil.open();
            st = con.createStatement();

            String sql = "SELECT STOCKNAME, VOLUME FROM TBLSTOCK WHERE USER_SEQ=" + userNo;
            rs = st.executeQuery(sql);

            while(rs.next()) {

                this.stockNameList.add(rs.getString("STOCKNAME"));
                this.volumeList.add(rs.getString("VOLUME"));

            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTotalAssets(int totalAssets) {
        this.totalAssets = totalAssets;
    }

    public int getTotalAvailableAssets() {
        return Login.loginUser.getAvailableAssets() - getTotalPrice();
    }

    public void setTotalAvailableAssets(int totalAvailableAssets) {
        this.totalAvailableAssets = totalAvailableAssets;
    }


    /**
     * 매수 & 매도 히스토리를 DB에 저장하는 메소드
     */
    public static void createHistory(TradeData td, String type) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstat = null;

        try {
            
            con = DBUtil.open();
            st = con.createStatement();

            Calendar cal = Calendar.getInstance();
            String date = String.format("%tF", cal);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


            String sql = "SELECT NVL(MAX(HISTORY_SEQ),0) AS MAX FROM TBLTRADING";
            rs = st.executeQuery(sql);

            int historySeq = 0;

            if(rs.next()) {
                historySeq = rs.getInt("MAX");
            }
            
            sql = "INSERT INTO TBLTRADING VALUES(?,?,?,?,?,?,?,?)";
            pstat = con.prepareStatement(sql);
            
            pstat.setInt(1, historySeq+1);
            pstat.setInt(2, Integer.parseInt(Login.loginUser.getNo()));
            pstat.setString(3, td.getStockName());
            pstat.setInt(4, Integer.parseInt(td.getPriceNow()));
            pstat.setInt(5, Integer.parseInt(td.getVolume()));
            pstat.setInt(6, td.getTotalPrice());
            pstat.setDate(7, sqlDate);
            pstat.setString(8, type);
            
            pstat.executeUpdate();

            System.out.printf("\t\t\t\t\t\t\t\t%s가 완료되었습니다.\n", type);
            
            rs.close();
            st.close();
            pstat.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 매수한 주식의 정보를 DB에 저장하는 메소드
     * @param td 매수 주식 이름, 수량의 정보를 담은 TradeData
     */
    public static void createBuyList(TradeData td, String type) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstat = null;

        try {

            con = DBUtil.open();
            st = con.createStatement();

            String sql = "SELECT NVL(MAX(STOCK_SEQ),0) AS MAX FROM TBLSTOCK";
            rs = st.executeQuery(sql);

            int stockSeq = 0;

            if(rs.next()) {
                stockSeq = rs.getInt("MAX");
            }

            sql = "SELECT COUNT(*) AS CNT FROM TBLSTOCK WHERE STOCKNAME='"+td.getStockName()+"'";
            rs = st.executeQuery(sql);

            int cnt = -1;

            if(rs.next()) {
                cnt = rs.getInt("CNT");
            }

            if(cnt != 0) {
                sql = "SELECT VOLUME FROM TBLSTOCK WHERE STOCKNAME='"+td.getStockName()+"'";
                rs = st.executeQuery(sql);

                int orgVolume = 0;
                if(rs.next()) {
                    orgVolume = rs.getInt("VOLUME");
                }

                int volumeSum = orgVolume + Integer.parseInt(td.getVolume());

                sql = "UPDATE TBLSTOCK SET VOLUME=? WHERE STOCKNAME=?";
                pstat = con.prepareStatement(sql);

                pstat.setInt(1, volumeSum);
                pstat.setString(2, td.getStockName());

                pstat.executeUpdate();

            } else {
                sql = "INSERT INTO TBLSTOCK VALUES(?,?,?,?)";
                pstat = con.prepareStatement(sql);

                pstat.setInt(1, stockSeq+1);
                pstat.setInt(2, Integer.parseInt(Login.loginUser.getNo()));
                pstat.setString(3, td.getStockName());
                pstat.setInt(4, Integer.parseInt(td.getVolume()));

                pstat.executeUpdate();

            }


            rs.close();
            st.close();
            pstat.close();
            con.close();

            Member.updateAccountInfo(td, type);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 매도한 주식정보를 매수 정보가 들어있는 DB에서 지우는 메소드
     */
    public static void removeBuyList(TradeData td, String type) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstat = null;

        try {

            con = DBUtil.open();
            st = con.createStatement();

            //보유 수량 확인
            String sql = "SELECT VOLUME FROM TBLSTOCK WHERE STOCKNAME='"+td.getStockName()+"'";
            rs = st.executeQuery(sql);

            int orgVolume = -1;

            if(rs.next()) {
                orgVolume = rs.getInt("VOLUME");
            }

            //매도할 주식 레코드 삭제
            sql = "DELETE FROM TBLSTOCK WHERE STOCKNAME='"+td.getStockName()+"'";
            pstat = con.prepareStatement(sql);

            pstat.executeUpdate();

            //남은 수량이 0이 아니면(0 초과) 남은 수량만큼 다시 insert
            int remains = orgVolume - Integer.parseInt(td.getVolume());

            if(remains > 0) {

                sql = "SELECT NVL(MAX(STOCK_SEQ),0) AS MAX FROM TBLSTOCK";
                rs = st.executeQuery(sql);

                int stockSeq = 0;

                if(rs.next()) {
                    stockSeq = rs.getInt("MAX");
                }

                sql = "INSERT INTO TBLSTOCK VALUES(?,?,?,?)";
                pstat = con.prepareStatement(sql);

                pstat.setInt(1, stockSeq+1);
                pstat.setInt(2, Integer.parseInt(Login.loginUser.getNo()));
                pstat.setString(3, td.getStockName());
                pstat.setInt(4, remains);

            }

            rs.close();
            st.close();
            pstat.close();
            con.close();

            Member.updateAccountInfo(td, type);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
