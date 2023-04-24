package com.stock.service.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.stock.service.search.SearchDetail;

/**
 * 모든 회원의 관심 주식 목록을 관리하는 클래스
 * @author 6조
 *
 */
public class UserStockStorage {

	private List<StockStorage> userStorageList = new ArrayList<StockStorage>();
	private final String PATH = ".\\dat\\storage\\stockList.txt";
	private StockStorage myStockList;
	
	/**
	 * 로그인한 회원이 추가한 관심 주식을 저장하는 메소드
	 * @param no 로그인한 회원의 고유 넘버
	 * @param stockName 로그인한 회원이 추가한 관심 주식명
	 */
	public void addUser(String no, String stockName) {
		
		load();
		StockStorage s = new StockStorage(no);
		
		if(!checkContains(no)) {	//처음 찜하는 유저
			s.setStockName(stockName);
			userStorageList.add(s);
			
		} else {					//이전에 찜한 기록이 있는 유저
			
			if(checkSameStock(stockName)) {
				myStockList.setStockName(stockName);
				
			} else {
				System.out.println("\t\t\t\t\t\t\t\t이미 저장되어 있는 주식입니다.\n");
				SearchDetail.userAdditionalInput();
			}
			
		}
		
		save();

	}
	
	/**
	 * 이전에 저장한 관심 주식이 있는 회원의 관심 주식 목록에 새로 추가하고자 하는 주식이 이미 저장되어 있는지 확인하는 메소드
	 * @param stockName 새로 추가하고자 하는 주식 종목명
	 * @return 이미 저장되어 있다면 false / 저장되어 있지 않다면 true
	 */
	private boolean checkSameStock(String stockName) {
		
		List<String> userStockList = myStockList.getStockList();
		
		for(String s : userStockList) {
			
			if(s.equals(stockName)) {
				return false;
			}
			
		}

		return true;
	}

	/**
	 * 로그인한 회원이 이전에 저장한 관심 주식이 있는지 확인하는 메소드
	 * @param no 로그인한 회원의 고유 넘버
	 * @return 이전에 저장한 관심 주식이 있을 경우 true / 없을 경우 false
	 */
	private boolean checkContains(String no) {

		for(int i = 0; i < userStorageList.size(); i++) {

			if(this.userStorageList.get(i).getNo().equals(no)) {
				myStockList = userStorageList.get(i);
				return true;
			}

		}

		return false;
	}

	/**
	 * 모든 회원의 관심 주식 목록을 불러오는 메소드
	 */
	private void load() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(this.PATH));

			String line = null;

			while((line = reader.readLine()) != null) {

				String[] tmp = line.split(",");

				StockStorage s = new StockStorage(tmp[0]);
				
				for(int i = 1; i < tmp.length; i++) {
					s.setStockName(tmp[i]);
				}
				
				this.userStorageList.add(s);

			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 로그인한 회원의 관심 주식 목록을 업데이트하여 데이터 파일에 저장하는 메소드
	 */
	public void save() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(".\\dat\\storage\\stockList.txt"));

			for(StockStorage s : userStorageList) {
				String line = String.format("%s\n", s.toString());
				writer.write(line);
			}

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
