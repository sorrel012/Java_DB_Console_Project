package com.stock.service.store;

import java.util.ArrayList;
import java.util.List;

/**
 * 로그인한 회원의 관심 주식을 저장하는 클래스
 * @author 6조
 *
 */
public class StockStorage {
	
	private String no;
	private List<String> stockList = new ArrayList<String>();
	
	public StockStorage(String no) {
		this.no = no;
	}

	public String getNo() {
		return no;
	}

	public void setStockName(String stockName) {
		this.stockList.add(stockName);
	}

	public List<String> getStockList() {
		return stockList;
	}

	@Override
	public String toString() {
		
		String txt = this.no;
		
		for(String s : this.stockList) {
			txt += ",";
			txt += s;
		}
		
		return txt;
		
	}
	
}
