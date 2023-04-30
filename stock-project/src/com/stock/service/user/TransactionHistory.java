package com.stock.service.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.db.DBUtil;
import com.stock.service.signup.Login;

/**
 * 거래내역 정보를 생성하는 메소드
 * @author 6조
 *
 */
public class TransactionHistory {

    /**
     * 거래 내역 정보를 DB에서 읽어와서 출력하는 메소드
     */
    public static void addTransaction() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            con = DBUtil.open();
            st = con.createStatement();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String sql = "SELECT TRADDATE, STOCKNAME, SORT, QUANTITY, TOTALPRICE FROM TBLTRADING WHERE USER_SEQ="+Login.loginUser.getNo();
            rs = st.executeQuery(sql);

            while(rs.next()) {

                Date dateSQL = null;
                String date = "";
                String stockName = "";
                String sort = "";
                int volume = 0;
                int totalPrice = 0;

                dateSQL = rs.getDate("TRADDATE");
                date = sdf.format(dateSQL);
                stockName = rs.getString("STOCKNAME");
                sort = rs.getString("SORT");
                volume = rs.getInt("QUANTITY");
                totalPrice = rs.getInt("TOTALPRICE");

                System.out.println("\t\t\t\t\t\t\t\t\t" + date + "\t" + stockName + "\t\t" + sort + "\t\t" + volume + "\t\t" + totalPrice);

            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}