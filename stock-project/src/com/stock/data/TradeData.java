package com.stock.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import com.stock.service.signup.Login;
import com.stock.view.Main;
import com.stock.view.Menu;
import com.stock.view.UserMenu;

/**
 * 주식 정보를 저장하는 클래스
 * @author 6조
 *
 */
public class TradeData {
	
	public final static String HISTORYDIR = ".\\dat\\tradinghistory\\";
	public final static String FILEPATH = HISTORYDIR + Login.loginUser.getId() + "\\" + "buyList.txt";
	public final static String HISTORYFILE = HISTORYDIR + Login.loginUser.getId() + "\\" + "history.txt";

	private String stockName;				//주식명
	private String volume;					//매수량
	private String priceNow;				//현재가
	private int totalPrice;					//총 매수금액 / 매도금액
	private int totalAssets;				//총 자산
	private int totalAvailableAssets;		//총 가용자산
	
	private ArrayList<String> list = new ArrayList<String>(5);
	
	public TradeData(String stockName, String volume) {
		this.stockName = stockName;
		this.volume = volume;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(String priceNow) {
		this.priceNow = priceNow;
	}

	public int getTotalPrice() {
		return Integer.parseInt(priceNow.replaceAll(",", "")) * Integer.parseInt(volume.replaceAll(",", ""));
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * 보유한 모든 주식의 총 가격 정보를 만드는 메소드 
	 */
	public int getTotalAssets() {

		int sum = 0;
		
		for (int i = 0; i < list.size(); i++) {
			
			try {
				
				BufferedReader listReader = new BufferedReader(new FileReader(getList().get(i)));
				BufferedReader buyReader = new BufferedReader(new FileReader(TradeData.FILEPATH));
				
				ArrayList<String> buyName = new ArrayList<String>();
				ArrayList<String> volume = new ArrayList<String>();
				
				String line = null;
				
				while ((line = buyReader.readLine()) != null) {
					
					String[] temp = line.split("■");
					
					buyName.add(temp[0]);
					volume.add(temp[1]);
					
				}
				
				line = null;
				
				while ((line = listReader.readLine()) != null) {
					
					String[] temp = line.split("■");
					
					for (int j = 0; j < buyName.size(); j++) {
						
						int stockPrice = 0;
						
						if (temp[1].equals(buyName.get(j))) {
							stockPrice = Integer.parseInt(temp[2].replaceAll(",", ""));
							sum += stockPrice * Integer.parseInt(volume.get(j));
							break;
						}
						
					}
					
				}
				
				listReader.close();
				buyReader.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} //for

		return sum;

	}

	public void setTotalAssets(int totalAssets) {
		this.totalAssets = totalAssets;
	}

	public int getTotalAvailableAssets() {
		return Login.loginUser.getAvailableAssets() - getTotalPrice();
	}

	public void setTotalAvailableAssets(int totalAvailableAssets) {
		this.totalAvailableAssets = totalAvailableAssets;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public void readAll() {

		list.add(TradingDataService.KOSPI_RISE_LIST);
		list.add(TradingDataService.KOSPI_FALL_LIST);
		list.add(TradingDataService.KOSPI_STEADY_LIST);
		list.add(TradingDataService.KOSDAQ_RISE_LIST);
		list.add(TradingDataService.KOSDAQ_FALL_LIST);
		list.add(TradingDataService.KOSDAQ_STEADY_LIST);

	}
	
	/**
	 * 매수 & 매도 히스토리를 텍스트 파일로 생성하는 메소드 
	 */
	public static void createHistory(TradeData td, String type) {
		
		try {
			
			Calendar cal = Calendar.getInstance();
			String date = String.format("%tF", cal);
			
			File dir = new File(HISTORYDIR + Login.loginUser.getId());
			dir.mkdir();
			
			File file = new File(HISTORYDIR + Login.loginUser.getId() + "\\" + "history.txt");
			file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			
			if (file.exists()) {
				
				String content = String.format("%s■%s■%s■%,d■%s■%s■%s\n", Login.loginUser.getId(), td.getStockName(), td.getPriceNow(), td.getTotalPrice(), date, type, td.getVolume());
				bw.write(content);
				System.out.printf("\t\t\t\t\t\t\t\t%s가 완료되었습니다.\n", type);
				bw.close();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 매수한 주식의 정보를 텍스트 파일로 생성하는 메소드 
	 */
	public static void createBuyList(TradeData td, String type) {
		
		try {
			
			File file = new File(TradeData.FILEPATH);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = null;
			
			String content = "";
			
			while ((line = br.readLine()) != null) {
				
				String[] temp = line.split("■");
				
				if (temp[0].equals(td.getStockName())) {
					int volume = Integer.parseInt(temp[1]) + Integer.parseInt(td.getVolume());
					content += td.getStockName() + "■" + volume + "\n";
					
				} else {
					content += line + "\n";
				}
				
			}
			br.close();
			
			if (!content.contains(td.getStockName())) {
				content += String.format("%s■%s\n", td.getStockName(), td.getVolume());
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.close();
			
			Member.updateAccountInfo(td, type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 매도한 주식정보를 매수 정보가 들어있는 텍스트파일에서 지우는 메소드 
	 */
	public static void removeBuyList(TradeData td, String type) {

		try {

			File file = new File(TradeData.FILEPATH);

			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = null;

			String content = "";

			while ((line = br.readLine()) != null) {

				String[] temp = line.split("■");

				if (temp[0].equals(td.getStockName())) {
					int volume = Integer.parseInt(temp[1]) - Integer.parseInt(td.getVolume());
					content += td.getStockName() + "■" + volume + "\n";

				} else {
					content += line + "\n";
				}

			}
			br.close();

			if (!content.contains(td.getStockName())) {
				content += String.format("%s■%s\n", td.getStockName(), td.getVolume());
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.close();

			Member.updateAccountInfo(td, type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
