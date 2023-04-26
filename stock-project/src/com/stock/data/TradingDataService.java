package com.stock.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.db.DBUtil;
/**
 * 국내 증시 사이트에서 시세 정보를 읽어오는 클래스
 * @author 6조
 *
 */
public class TradingDataService {

    private static final String KOSPI = "https://finance.naver.com/sise/sise_market_sum.naver?sosok=0&page="; // 코스피
    private static final String KOSDAQ = "https://finance.naver.com/sise/sise_market_sum.naver?sosok=1&page="; // 코스닥
    public static final String KOSPI_LIST = ".\\dat\\stockprice\\kospiList.txt";
    public static final String KOSDAQ_LIST = ".\\dat\\stockprice\\kosdaqList.txt";

    /**
     * 코스피, 코스닥의 변동률을 읽어오는 매소드
     * @see #createAll(String, String)
     */
    public static void createStockData() {

        try {
            deleteStockData();
            storeStockData(KOSPI, 41);
            storeStockData(KOSDAQ, 33);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 증권 시세를 불러와서 데이터베이스에 저장하는 메소드
     * @param stockUrl 증권 사이트 주소
     * @param pageNum 읽어올 페이지 수
     * @throws SQLException
     * @throws NumberFormatException
     * @throws IOException
     */
    private static void storeStockData(String stockUrl, int pageNum) throws NumberFormatException, SQLException, IOException {
            
            Connection con = DBUtil.open();
            con.setAutoCommit(false);
            PreparedStatement pstat = con.prepareStatement("INSERT INTO tblKospi VALUES (?, ?, ?, ?, ?, ?, ?)");

            if(stockUrl.equals(KOSDAQ)) {
                pstat = con.prepareStatement("INSERT INTO tblKosdaq VALUES (?, ?, ?, ?, ?, ?, ?)");
            }
            
            for (int i = 1; i <= pageNum; i++) {
                Document doc = Jsoup.connect(KOSPI + i).get(); // 웹에서 내용을 가져온다

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
            
                    // 데이터베이스에 저장
                    pstat.setInt(1, Integer.parseInt(seq));
                    pstat.setString(2, stockName);
                    pstat.setInt(3, Integer.parseInt(nowPrice));
                    pstat.setString(4, rate);
                    pstat.setInt(5, Integer.parseInt(volume));
                    pstat.setString(6, per);
                    pstat.setString(7, roe);

                    pstat.executeUpdate();
                    
                } //j
                con.commit();
                
            }//i
            
            
            pstat.close();
            con.close();
        
    }
    
    /**
     * 기존 주식 테이블의 데이터를 지워주는 메소드
     * @param stockUrl
     * @param pageNum
     * @throws SQLException
     */
    private static void deleteStockData() throws SQLException {
        
        Connection con = DBUtil.open();
        
        String sql = "DELETE FROM TBLKOSPI";
        PreparedStatement st = con.prepareStatement(sql);
        
        st.executeUpdate();
        
        sql = "DELETE FROM TBLKOSDAQ";
        st = con.prepareStatement(sql);

        st.executeUpdate();
        
        st.close();
        con.close();
        
    }
    
}