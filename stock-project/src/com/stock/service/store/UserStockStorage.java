package com.stock.service.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.stock.service.search.SearchDetail;

/**
 * 모든 회원의 관심 주식 목록을 관리하는 클래스
 * @author 6조
 *
 */
public class UserStockStorage {

    private List<String> myStockList;

    /**
     * 로그인한 회원이 추가한 관심 주식을 저장하는 메소드
     * @param no 로그인한 회원의 고유 넘버
     * @param stockName 로그인한 회원이 추가한 관심 주식명
     */
    public void addUser(String no, String stockName) {

        List<List<String>> userStorageList = load();

        if(!checkContains(no)) {	//처음 찜하는 유저
            save(no, stockName);

        } else {					//이전에 찜한 기록이 있는 유저

            if(checkSameStock(stockName)) {
                save(no, stockName);

            } else {
                System.out.println("\t\t\t\t\t\t\t\t이미 저장되어 있는 주식입니다.\n");
                SearchDetail.userAdditionalInput();
            }

        }

    }

    /**
     * 이전에 저장한 관심 주식이 있는 회원의 관심 주식 목록에 새로 추가하고자 하는 주식이 이미 저장되어 있는지 확인하는 메소드
     * @param stockName 새로 추가하고자 하는 주식 종목명
     * @return 이미 저장되어 있다면 false / 저장되어 있지 않다면 true
     */
    private boolean checkSameStock(String stockName) {

        for(String s : this.myStockList) {

            if(s.equals(stockName)) {
                return false;
            }

        }

        return true;
    }

    /**
     * 로그인한 회원이 이전에 저장한 관심 주식이 있는지 확인하는 메소드
     * @param no 로그인한 회원의 고유 넘버
     * @return 이전에 저장한 관심 주식이 있을 경우 true / 없을 경우 false
     */
    private boolean checkContains(String no) {

        List<List<String>> userStorageList = load();

        this.myStockList = new ArrayList<String>();
        boolean isContain = false;

        for(List<String> tmpUserStorage : userStorageList) {

            if(tmpUserStorage.get(0).equals(no)) {
                isContain = true;
                this.myStockList.add(tmpUserStorage.get(1));
            }

        }

        return isContain;
    }

    /**
     * 모든 회원의 관심 주식 목록을 불러오는 메소드
     */
    public static List<List<String>> load() {

        List<List<String>> likeStocks = new ArrayList<List<String>>();

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            con = DBUtil.open();
            st = con.createStatement();

            String sql = "SELECT USER_SEQ, LIKESTOCK FROM TBLLIKE";
            rs = st.executeQuery(sql);

            while(rs.next()) {

                List<String> tmpLikeStock = new ArrayList<String>();

                String userNo = rs.getString("USER_SEQ");
                String stockName = rs.getString("LIKESTOCK");

                tmpLikeStock.add(userNo);
                tmpLikeStock.add(stockName);

                likeStocks.add(tmpLikeStock);

            }

            rs.close();
            st.close();
            con.close();

            return likeStocks;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return likeStocks;
    }

    /**
     * 로그인한 회원의 새로운 관심 주식을 데이터베이스에 추가하는 메소드
     */
    public void save(String no, String stockName) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pstat = null;

        try {

            con = DBUtil.open();

            st = con.createStatement();

            String sql = "SELECT NVL(MAX(LIKE_SEQ),0) AS MAX FROM TBLLIKE";
            rs = st.executeQuery(sql);

            int likeSeq = 0;

            if(rs.next()) {
                likeSeq = rs.getInt("MAX");
            }

            sql = "INSERT INTO TBLLIKE VALUES(?,?,?)";
            pstat = con.prepareStatement(sql);

            pstat.setInt(1, likeSeq+1);
            pstat.setInt(2, Integer.parseInt(no));
            pstat.setString(3, stockName);

            pstat.executeUpdate();

            st.close();
            rs.close();
            pstat.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
