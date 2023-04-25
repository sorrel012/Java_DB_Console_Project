package com.stock.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.db.DBUtil;

class Task extends Thread {
    
    private String stockUrl;
    private int pageBegin;
    private int pageEnd;
    private Connection con;
    private PreparedStatement pstat;
    
    public Task(String stockUrl, int pageBegin, int pageEnd, Connection con, PreparedStatement pstat) {
        
        this.stockUrl = stockUrl;
        this.pageBegin = pageBegin;
        this.pageEnd = pageEnd;
        this.con = con;
        this.pstat = pstat;
    }
    
    @Override
    public void run() {
        try {

            for(int i = pageBegin; i <= pageEnd; i++) {
                
                Document doc = Jsoup.connect(this.stockUrl+i).get(); // 웹에서 내용을 가져온다

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

                for(int j = 0; j < name.size(); j+=12) {

                    //쿼리에 넣을 값 준비
                    String seq ="";
                    String stockName ="";
                    String nowPrice ="";
                    String rate ="";
                    String volume ="";
                    String per ="";
                    String roe ="";

                    for(int k = j; k < j+12; k++) {
                        switch(k%12) {
                        case 0:
                            seq = name.get(k);
                            break;
                        case 1:
                            stockName = name.get(k);
                            break;
                        case 2:
                            nowPrice = name.get(k).replace(",", "");
                            break;
                        case 4:
                            rate = name.get(k);
                            break;
                        case 9:
                            volume = name.get(k).replace(",", "");
                            break;
                        case 10:
                            per = name.get(k);
                            break;
                        case 11:
                            roe = name.get(k);
                            break;
                        }

                    }

                  pstat.setInt(1, Integer.parseInt(seq));
                  pstat.setString(2, stockName);
                  pstat.setInt(3, Integer.parseInt(nowPrice));
                  pstat.setString(4, rate);
                  pstat.setInt(5, Integer.parseInt(volume));
                  pstat.setString(6, per);
                  pstat.setString(7, roe);

                  int result = pstat.executeUpdate();
                  System.out.println(result);
                    
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

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
        
        Connection con = null;
        PreparedStatement pstat = null;
        
        try {
            
            con = DBUtil.open();
            String sql = "insert into tblKospi values(?,?,?,?,?,?,?)";
            pstat = con.prepareStatement(sql);
            
            ExecutorService executorService = Executors.newFixedThreadPool(30);
            executorService.execute(new Task(KOSPI, 1, 3, con, pstat));
            executorService.execute(new Task(KOSPI, 4, 6, con, pstat));
            executorService.execute(new Task(KOSPI, 7, 9, con, pstat));
            executorService.execute(new Task(KOSPI, 10, 12, con, pstat));
            executorService.execute(new Task(KOSPI, 13, 15, con, pstat));
            executorService.execute(new Task(KOSPI, 16, 18, con, pstat));
            executorService.execute(new Task(KOSPI, 19, 21, con, pstat));
            executorService.execute(new Task(KOSPI, 22, 24, con, pstat));
            executorService.execute(new Task(KOSPI, 25, 27, con, pstat));
            executorService.execute(new Task(KOSPI, 28, 30, con, pstat));
            executorService.execute(new Task(KOSPI, 31, 33, con, pstat));
            executorService.execute(new Task(KOSPI, 34, 36, con, pstat));
            executorService.execute(new Task(KOSPI, 37, 41, con, pstat));
//            executorService.execute(new Task(KOSDAQ, 1, 6));
//            executorService.execute(new Task(KOSDAQ, 7, 12));
//            executorService.execute(new Task(KOSDAQ, 13, 18));
//            executorService.execute(new Task(KOSDAQ, 19, 24));
//            executorService.execute(new Task(KOSDAQ, 25, 33));
//            readStockData(KOSPI, 41);
//            readStockData(KOSDAQ, 33);

            pstat.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 증권 시세를 불러와서 데이터베이스에 저장하는 메소드
     * @param stockUrl 증권 사이트 주소
     * @param pageNum 읽어올 페이지 수
     */
    private static void readStockData(String stockUrl, int pageBegin, int pageEnd) {
        
        try {

            Connection con = null;
            PreparedStatement pstat = null;

            for(int i = pageBegin; i <= pageEnd; i++) {
                
                Document doc = Jsoup.connect(stockUrl+pageEnd).get(); // 웹에서 내용을 가져온다

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

                for(int j = 0; j < name.size(); j+=12) {

                    //쿼리에 넣을 값 준비
                    String seq ="";
                    String stockName ="";
                    String nowPrice ="";
                    String rate ="";
                    String volume ="";
                    String per ="";
                    String roe ="";

                    for(int k = j; k < j+12; k++) {
                        switch(k%12) {
                        case 0:
                            seq = name.get(k);
                            break;
                        case 1:
                            stockName = name.get(k);
                            break;
                        case 2:
                            nowPrice = name.get(k).replace(",", "");
                            break;
                        case 4:
                            rate = name.get(k);
                            break;
                        case 9:
                            volume = name.get(k).replace(",", "");
                            break;
                        case 10:
                            per = name.get(k);
                            break;
                        case 11:
                            roe = name.get(k);
                            break;
                        }

                    }

                  con = DBUtil.open();

                  String sql = "insert into tblKospi values(?,?,?,?,?,?,?)";
                  pstat = con.prepareStatement(sql);

                  pstat.setInt(1, Integer.parseInt(seq));
                  pstat.setString(2, stockName);
                  pstat.setInt(3, Integer.parseInt(nowPrice));
                  pstat.setString(4, rate);
                  pstat.setInt(5, Integer.parseInt(volume));
                  pstat.setString(6, per);
                  pstat.setString(7, roe);

                  int result = pstat.executeUpdate();
                  System.out.println(result);
                    
                }
                
                
            }
            
            pstat.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}