package com.stock.service.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.stock.data.TradeData;

/**
 * 거래내역 정보를 생성하는 메소드
 * @author 6조
 *
 */
public class TransactionHistory {
	
	/**
	 * 거래 내역을 정보를 생성하고 출력하는 메소드
	 */
	public static void addTransaction() {
		
		try {
			
			File filePath = new File(TradeData.HISTORYFILE);
			
			if (filePath.exists()) {
				
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				
				String line = null;
				
				while ((line = br.readLine()) != null) {
					
					String[] temp = line.split("■");
					
					System.out.println("\t\t\t\t\t\t\t\t\t" + temp[4] + "\t" + temp[1] + "\t\t" + temp[5] + "\t\t" + temp[6] + "\t\t" + temp[3]);
					
				}
				
				br.close();
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}

}