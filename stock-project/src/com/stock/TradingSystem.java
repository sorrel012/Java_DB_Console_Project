package com.stock;

import com.stock.data.TradingDataService;
import com.stock.service.search.StockPriceInfo;
import com.stock.view.Main;

/**
 * 모의 주식 시스템을 구현한 메인 클래스
 * @author 6조
 *
 */
public class TradingSystem {

  public static void main(String[] args) {

    TradingDataService.createStockData();
    StockPriceInfo.extractRanking();

    Main.mainMenu();

  }

}
