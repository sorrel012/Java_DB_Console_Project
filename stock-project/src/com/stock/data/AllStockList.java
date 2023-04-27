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
   
   private static List<List<String>> result = new ArrayList<List<String>>();

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
              String seq ="";
              if(stockSort.equals("kospi")) {
                  
              } else if(stockSort.equals("kosdaq")) {
                  
              }
              String stockName ="";
              String nowPrice ="";
              String rate ="";
              String volume ="";
              String per ="";
              String roe ="";
          }
         

      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   
   public static List<List<String>> storeAllStockList() {

      searchStockList("kospi");
      searchStockList("kosdaq");
      
      return result;
   }
      
}