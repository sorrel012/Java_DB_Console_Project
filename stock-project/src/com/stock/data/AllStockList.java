package com.stock.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * txt파일에 저장된 모든 주식 리스트를 저장하는 배열을 생성하는 클래스
 * @author 6조
 *
 */
public class AllStockList {
   
   private static List<List<String>> result = new ArrayList<List<String>>();

   private static void readAllStockList(String stockPath) {

      try {

         BufferedReader reader = new BufferedReader(new FileReader(stockPath));

         String line = "";

         while((line = reader.readLine()) != null) {

            String[] tmp = line.split("■");
            List<String> info = new ArrayList<String>();
              
              info.add(tmp[0]);
              info.add(tmp[1]);
              info.add(tmp[2]);
              info.add(tmp[3]);
              info.add(tmp[4]);
              info.add(tmp[5]);
              info.add(tmp[6]);
              info.add(tmp[7]);
              info.add(tmp[8]);
              info.add(tmp[9]);
              info.add(tmp[10]);
              info.add(tmp[11]);
            
              result.add(info);
         }

         reader.close();

      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   
   public static List<List<String>> storeAllStockList() {

      searchStockList(TradingDataService.KOSPI_RISE_LIST);
      searchStockList(TradingDataService.KOSPI_FALL_LIST);
      searchStockList(TradingDataService.KOSPI_STEADY_LIST);
      searchStockList(TradingDataService.KOSDAQ_RISE_LIST);
      searchStockList(TradingDataService.KOSDAQ_FALL_LIST);
      searchStockList(TradingDataService.KOSDAQ_STEADY_LIST);
      
      return result;
   }
      
}