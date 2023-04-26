package com.stock.data;

import java.util.ArrayList;
import java.util.List;

/**
 * txt파일에 저장된 모든 주식 리스트를 저장하는 배열을 생성하는 클래스
 * @author 6조
 *
 */
public class AllStockList {
   
   private static List<List<String>> result = new ArrayList<List<String>>();
   
   public static List<List<String>> allStockList() {

      searchStockList(TradingDataService.KOSPI_RISE_LIST);
      searchStockList(TradingDataService.KOSPI_FALL_LIST);
      searchStockList(TradingDataService.KOSPI_STEADY_LIST);
      searchStockList(TradingDataService.KOSDAQ_RISE_LIST);
      searchStockList(TradingDataService.KOSDAQ_FALL_LIST);
      searchStockList(TradingDataService.KOSDAQ_STEADY_LIST);
      
      return result;
   }
      
}