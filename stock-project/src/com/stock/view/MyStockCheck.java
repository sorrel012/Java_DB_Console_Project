package com.stock.view;

import java.util.Scanner;

import com.stock.service.user.AssetInfo;
import com.stock.service.user.AssetReset;
/**
 * My Stock-자산확인의 메뉴 클래스
 * @author 6조
 *
 */
public class MyStockCheck {
	
	public static void mystockcheck() {
		
		Menu.stockCheck();
		
		Scanner scan = new Scanner(System.in);
		
		//break label: 다중 반복문 한번에 종료
		SystemEndBreak:
		
			
		while(true) {
			
			//메뉴 번호 입력받기
			System.out.print("\t\t\t\t\t\t\t\t\t\t\t　번호 입력: ");
			String inputNum = scan.next();
			
			//1. 자산 정보 조회
			if (inputNum.equals("1")) {
				AssetInfo.assetInfo();
				break;
	    
			//2. 종목별 정보 조회
			} else if (inputNum.equals("2")) {
				AssetInfo.assetInfoDetail();
				break;
	    	
			//3. 자산 초기화
			} else if (inputNum.equals("3")) {
				AssetReset.assetsReset();
				break;
	    	
			//4. 뒤로가기
			} else if (inputNum.equals("4")) {
				System.out.println("\n\n");
				MyStock.UI();
				break;
				
		
			//0. 종료 > 프로그램 종료 안내 문구
			} else if (inputNum.equals("0")) {
	    	
				while (true) {
	    	
					System.out.println("\t\t\t\t\t\t\t\t=================================================================");
					System.out.print("\t\t\t\t\t\t\t\t\t\t　[프로그램을 종료하시겠습니까? (y/n)] ");
	    	
					String inputEnd = scan.next();
	    	
					
					//프로그램 종료 > y
					if (inputEnd.equals("y")) {
						System.out.println();
						System.out.println("\t\t\t\t\t\t\t\t\t\t　　　　　프로그램을 종료합니다.\n\n\n\n\n\n\n");
	    			
						//break label으로 다중 반복문 한번에 종료 
						break SystemEndBreak;

					//프로그램 종료 > n
					} else if (inputEnd.equals("n")) {
						break;
	    			
					//프로그램 종료 > y/n 이외 입력 시
					} else {
						System.out.println("\t\t\t\t\t\t\t\t\t\t\ty/n만 입력 가능합니다.");
						System.out.println();
					}
				}//while
				System.out.println();
			
			}//else if
			
		}//while
	}

}
