package com.stock.data;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 국내 증시 사이트에서 시세 정보를 읽어오는 클래스
 * @author 6조
 *
 */
public class TradingDataService {

    private static final String KOSPI = "https://finance.naver.com/sise/sise_market_sum.naver?sosok=0&page="; // 코스피
    private static final String KOSDAQ = "https://finance.naver.com/sise/sise_market_sum.naver?sosok=1&page="; // 코스닥

    /**
     * 코스피, 코스닥의 변동률을 읽어오는 매소드
     * @see #createAll(String, String)
     */
    public static void createStockData() {
        readStockData(KOSPI, 41);
//        readStockData(KOSDAQ, 33);
    }

    /**
     * 증권 시세를 불러와서 데이터베이스에 저장하는 메소드
     * @param stockUrl 증권 사이트 주소
     * @param pageNum 읽어올 페이지 수
     */
    private static void readStockData(String stockUrl, int pageNum) {

        try {

            for(int i = 1; i <= pageNum; i++) {
                
                Document doc = Jsoup.connect(stockUrl+pageNum).get(); // 웹에서 내용을 가져온다

                // 페이지 전체 읽어오기
                Elements stockData = doc.select("td");
                List<String> name = stockData.eachText(); // index 30부터 번호 시작

                for (int j = 0; j < 30; j++) { // 목록이 index0이 되도록 함
                    name.remove(name.get(0));
                }

                if(i == 1) {
                    int nameSize = name.size();
                    
                    for (int j = nameSize-1; j > nameSize-13; j--) {
                        name.remove(j);
                    }
                    
                } else {
                    int idx = name.indexOf("맨앞");
                    for (int j = name.size()-1; j >= idx; j--) {
                        name.remove(j);
                    }
                    
                }

                for(int j = 0; j < name.size(); j++) {
                    System.out.println(j + ": " + name.get(j));
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}