package com.stock.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
	
	private static final String KOSPI_RISE = "https://finance.naver.com/sise/sise_rise.naver"; // 코스피 상승
	private static final String KOSPI_FALL = "https://finance.naver.com/sise/sise_fall.naver"; // 코스피 하락
	private static final String KOSPI_STEADY = "https://finance.naver.com/sise/sise_steady.naver"; // 코스피 보합
	private static final String KOSDAQ_RISE = "https://finance.naver.com/sise/sise_rise.naver?sosok=1"; // 코스닥 상승
	private static final String KOSDAQ_FALL = "https://finance.naver.com/sise/sise_fall.naver?sosok=1"; // 코스닥 하락
	private static final String KOSDAQ_STEADY = "https://finance.naver.com/sise/sise_steady.naver?sosok=1"; // 코스닥 보합
	
	public static final String KOSPI_RISE_LIST = ".\\dat\\stockprice\\kospiRiseList.txt";
	public static final String KOSPI_FALL_LIST = ".\\dat\\stockprice\\kospiFallList.txt";
	public static final String KOSPI_STEADY_LIST = ".\\dat\\stockprice\\kospiSteadyList.txt";
	public static final String KOSDAQ_RISE_LIST = ".\\dat\\stockprice\\kosdaqRiseList.txt";
	public static final String KOSDAQ_FALL_LIST = ".\\dat\\stockprice\\kosdaqFallList.txt";
	public static final String KOSDAQ_STEADY_LIST = ".\\dat\\stockprice\\kosdaqSteadyList.txt";
	
	/**
	 * 코스피, 코스닥의 변동률을 읽어오는 매소드
	 * @see #createAll(String, String)
	 */
	public static void createStockData() {
		
		createAll(KOSPI_RISE_LIST, KOSPI_RISE);
		createAll(KOSPI_FALL_LIST, KOSPI_FALL);
		createAll(KOSPI_STEADY_LIST, KOSPI_STEADY);
		createAll(KOSDAQ_RISE_LIST, KOSDAQ_RISE);
		createAll(KOSDAQ_FALL_LIST, KOSDAQ_FALL);
		createAll(KOSDAQ_STEADY_LIST, KOSDAQ_STEADY);
		   
	}
	
	/**
	 * 증권 시세를 불러와서 데이터 파일에 저장하는 메소드
	 * @param fileUrl 증권 사이트 주소
	 * @param stockUrl 저장할 데이터 파일 경로
	 */
	private static void createAll(String fileUrl, String stockUrl) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileUrl));

			Document doc = Jsoup.connect(stockUrl).get(); // 웹에서 내용을 가져온다

			// 코스피 지수 값 추출
			Elements kospiRise = doc.select("td");
			List<String> name = kospiRise.eachText(); // index 30부터 번호 시작

			for (int i = 0; i < 30; i++) { // 목록이 index0이 되도록 함
				name.remove(name.get(0));
			}

			int count = 0;
			String line = "";

			for (int i = 0; i < name.size(); i++) {

				if (count < 11) {

					line = String.format("%s■", name.get(i));
					count++;
				} else if (count == 11) {
					line = String.format("%s\n", name.get(i));
					count = 0;
				}

				writer.write(line);

			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}