package com.stock.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;

/**
 * txt파일에 저장된 모든 주식 리스트를 저장하는 배열을 생성하는 클래스
 * @author 6조
 *
 */
public class AllStockList {
   
   private static List<List<String>> result;

   private static void readAllStockList(String stockSort) {
       
      Connection con = null;
      Statement st = null;
      ResultSet rs = null;
      
      try {
          
          con = DBUtil.open();
          
          String sql = null;
          
          if(stockSort.equals("kospi")) {
              sql = "SELECT * FROM TBLKOSPI";
          } else if(stockSort.equals("kosdaq")) {
              sql = "SELECT * FROM TBLKOSDAQ";
          }
          
          st = con.createStatement();
          rs = st.executeQuery(sql);
          
          while(rs.next()) {
              
              List<String> tmp = new ArrayList<>();
              
              String seq ="";
              if(stockSort.equals("kospi")) {
                  seq = rs.getString("KOSPI_SEQ");
              } else if(stockSort.equals("kosdaq")) {
                  seq = rs.getString("KOSDAQ_SEQ");
              }
              String stockName = rs.getString("STOCKNAME");
              String nowPrice = rs.getString("NOWPRICE");
              String rate = rs.getString("RATE");
              String volume = rs.getString("VOLUME");
              String per = rs.getString("PER");
              String roe = rs.getString("ROE");
              
              tmp.add(seq);
              tmp.add(stockName);
              tmp.add(nowPrice);
              tmp.add(rate);
              tmp.add(volume);
              tmp.add(per);
              tmp.add(roe);
              
              result.add(tmp);
          }
          
          rs.close();
          st.close();
          con.close();

      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   
   public static List<List<String>> storeAllStockList() {
       
       result = new ArrayList<List<String>>();
      
       readAllStockList("kospi");
       readAllStockList("kosdaq");

       
       return result;
   }
      
}