package com.stock.service.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.stock.data.AllStockList;
import com.stock.service.signup.Login;

/**
 * My Stock - 자산 확인
 * AssetInfo 클래스를 구성하는 메소드들
 * @author 6조
 *
 */
public class AssetInfoDetail {
	
	private final static String BUYLISTPATH = ".\\dat\\tradinghistory\\" + Login.loginUser.getId() + "\\buyList.txt";
	private final static String HISTORYPATH = ".\\dat\\tradinghistory\\" + Login.loginUser.getId() + "\\history.txt";
	private final static File BUYLIST = new File(BUYLISTPATH);
	private final static File HISTORY = new File(HISTORYPATH);
	
	public static ArrayList<ArrayList<String>> historyList = new ArrayList<ArrayList<String>>();
	
	//static int price = 0;

	/**
	 * [1. 자산 정보 조회] 출력 클래스
	 */
	public static void allPrice() {
		
		int allprice = 0;
		int allreturn = 0;
		int allnowprice = 0;
		double allrateofreturn = 0;
		
		List<List<String>> list = AllStockList.storeAllStockList();
		
		try {
			
			if (BUYLIST.exists()) {
				
				BufferedReader buyReader = new BufferedReader(new FileReader(BUYLIST));
				
				String line = null;

				while((line = buyReader.readLine()) != null) {
					
					String[] tmp = line.split("■");
					
					int a = (Integer.parseInt(rateOfReturn(tmp[0]).replace(",","")) * Integer.parseInt(tmp[1]));	//현재가*보유수량
					int b = checkCategory(tmp[0]);	//매입가
					
					allprice += b;
					allreturn += a-b;
					allnowprice += a;
					allrateofreturn = ((double)allnowprice / (double)allprice) * 100 - 100;

				}
				
				
				System.out.printf("\t\t\t\t\t\t\t\t　　　　　　 총자산 　 　: %,12d \t 가용자산　  : %,12d\n"
						, Login.loginUser.getMoney()	//총자산
						, Login.loginUser.getAvailableAssets());	//가용자산
				
				System.out.printf("\t\t\t\t\t\t\t\t　　　　　　 평가금액(원): %,12d \t 평가수익률  : %11.2f%%\n"
						, Login.loginUser.getMoney() - Login.loginUser.getAvailableAssets()	//평가금액
						, allrateofreturn);	//평가수익률: 현재가격/매수가격 * 100 - 100
				
				System.out.printf("\t\t\t\t\t\t\t\t　　　　　　 평가손익(원): %,12d \t 매입금액(원): %,12d\n"
						, allreturn		//평가손익: 현재가-매입가*수량
						, allprice); 	//매입금액
				
//				System.out.println(allprice);	//매입금액	> 437,500
//				System.out.println(allreturn);	//평가손익 > 1,500
//				System.out.println(allnowprice);	//현재가*보유수량 > 	436,000
//				System.out.println(allrateofreturn);	//수익률
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * [2. 종목별 정보 조회] 출력 클래스
	 */
	public static void price() {
		
		List<List<String>> list = AllStockList.storeAllStockList();
		
		try {
			
			if (BUYLIST.exists()) {
				
				BufferedReader buyReader = new BufferedReader(new FileReader(BUYLIST));
				
				String line = null;

				
				while((line = buyReader.readLine()) != null) {
					
					String[] tmp = line.split("■");
					
					int c = (Integer.parseInt(rateOfReturn(tmp[0]).replace(",",""))*Integer.parseInt(tmp[1]));	//현재가*보유수량
					int d = checkCategory(tmp[0]);	//매입가
					
					System.out.printf("\t\t\t\t\t\t\t%s\t\t%3s\t\t%,7d\t\t%,7d\t\t　　　　%6.2f%%\n"
									, tmp[0]						//종목명
									, tmp[1]						//보유수량
									, d								//매입가
									, c-d							//평가손익 = 현재가 * 보유수량 - 매입가
									, (double)c/d*100-100);			//수익률 = (현재가*보유수량 / 매입가) * 100 - 100
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 매도/매수를 저장해둔 txt파일을 배열로 저장하는 메소드
	 */
	private static void historyList() {
		
		List<List<String>> history = new ArrayList<List<String>>();
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(HISTORY));
			String line = null;
			
			while((line = br.readLine()) != null) {
				
				String[] temp = line.split("■");
				List<String> list = new ArrayList<String>();
				
				list.add(temp[0]);
				list.add(temp[1]);
				list.add(temp[2]);
				list.add(temp[3]);
				list.add(temp[4]);
				list.add(temp[5]);
				
				history.add(list);
			}
			
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 매도/매수 거래내역을 저장해둔 txt파일에서 주식명, 매입가, 매도/매수를 저장하는 메소드
	 * @param name
	 */
	public static void history(String name) {
		

		try {
			
			BufferedReader hisReader = new BufferedReader(new FileReader(HISTORY));
			
			String line = null;
			
			while((line = hisReader.readLine()) != null) {
				
				ArrayList<String> price = new ArrayList<String>();
				
				String[] tmp = line.split("■");
				
				if(name.equals(tmp[1]))	 {
					price.add(tmp[1]); //주식명
					price.add(tmp[3]); //매매가
					price.add(tmp[5]); //매매종류
					
					historyList.add(price);
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 각 보유 종목의 매입가를 계산하는 메소드
	 * @param stockName
	 * @return
	 */
	public static int checkCategory(String stockName) {
		
		historyList = new ArrayList<ArrayList<String>>();
		history(stockName);
		
		int price = 0;

		
		for(ArrayList<String> l: historyList) {
			if(l.get(0).equals(stockName)) {

				if(l.get(2).equals("매수")) {
					price += Integer.parseInt(l.get(1).replace(",", ""));
				} else {
					price -= Integer.parseInt(l.get(1).replace(",", ""));

				}
			}
		}

		return price;
			
	}
	
	/**
	 * 각 보유 종목의 현재가를 계산하는 메소드
	 * @param stockName
	 * @return
	 */
	public static String rateOfReturn(String stockName) {
		
		List<List<String>> list = AllStockList.storeAllStockList();
		
		String nowPrice = null;
		
		try {
			
	         nowPrice = null;
	         
	         for(List<String> l : list) {
	            
	            if(l.get(1).equals(stockName)) {
	               nowPrice = l.get(2);
	               break;
	            }

	         }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nowPrice;
	}
		
		
}






