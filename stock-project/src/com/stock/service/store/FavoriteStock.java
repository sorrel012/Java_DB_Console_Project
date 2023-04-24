package com.stock.service.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.stock.data.AllStockList;
import com.stock.service.signup.Login;
import com.stock.view.MyStock;

/**
 * 관심 종목으로 등록한 종목 확인
 * @author 6조
 *
 */
public class FavoriteStock {

	/**
	 * 관심 종목 UI 출력 리스트
	 */
	public static void favorite() {
		
			System.out.println();
			System.out.println("\t\t\t\t\t\t===================================================================================================");
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t　　　[My Stock - 관심 종목]");
			System.out.println("\t\t\t\t\t\t===================================================================================================");
			System.out.println();
			System.out.println("\t\t\t\t\t\t　[종목명]\t\t\t[현재가]\t\t[등락률]\t\t[거래량]\t\t[매수호가]\t\t[매도호가]");
			
			StockList();

			
			System.out.println();
			System.out.println("\t\t\t\t\t\t===================================================================================================");
			System.out.println();
			
			inputEnterMyStock();
			
					
	}
	
	/**
	 * "계속하시려면 엔터를 입력해주세요."를 출력하는 메소드
	 * 엔터 입력 시 My Stock UI로 돌아감
	 */
	private static void inputEnterMyStock() {
		
		String enterLine = "";
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("\t\t\t\t\t\t\t\t\t\t 　　　계속하시려면 엔터를 입력해주세요.");
		enterLine = scan.nextLine();
		MyStock.UI();
		
		scan.close();
	}
	
	
	/**
	 * 관심 종목으로 등록되어있는 종목 이름
	 */
	private static void StockList() {
		
		try {
			BufferedReader stockListReader = new BufferedReader(new FileReader(".\\dat\\storage\\stockList.txt"));
			
			//List<String> stocklist = new ArrayList<String>();
			String line = null;

			while((line = stockListReader.readLine())!= null) {
				
				String[] temp = line.split(",");
				if (Login.loginUser.getNo().equals(temp[0])) {
					MyStockList();
				}
			}
			
			stockListReader.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 관심 종목으로 등록한 종목의 내용 출력
	 * (종목명, 현재가, 등락률, 거래량, 매수호가, 매도호가)
	 */
	private static void MyStockList() {
		
		//[종목명=temp1][현재가=temp2][등락률=temp4][거래량=temp5][매수호가=temp6][매도호가=temp7]
		List<List<String>> list = AllStockList.allStockList();
		
		try {
			
			BufferedReader stockListReader = new BufferedReader(new FileReader(".\\dat\\storage\\stockList.txt"));
			String line = null;
			
				while((line = stockListReader.readLine())!= null) {
				
				String[] temp = line.split(",");
					
				for(int i=1; i<temp.length;i++) {
					for(int j=0; j<list.size(); j++) {
						if (temp[i].equals(list.get(j).get(1))) {
							System.out.printf("\t\t\t\t\t\t　%-10s\t\t%6s\t\t%5s\t%15s\t\t%7s\t\t%7s\n"
													, list.get(j).get(1)
													, list.get(j).get(2)
													, list.get(j).get(4)
													, list.get(j).get(5)
													, list.get(j).get(6)
													, list.get(j).get(7));
							break;
						}
					}
					
				}
				
			}
				
			stockListReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}





