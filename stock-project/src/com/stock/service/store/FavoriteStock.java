package com.stock.service.store;

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
        System.out.println("\t\t\t\t\t\t　[종목명]\t\t[현재가]\t\t[등락률]\t\t[거래량]\t\t[ PER ]\t\t[ ROE ]");

        printMyStockList();

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

        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.print("\t\t\t\t\t\t\t\t\t\t 　　　계속하시려면 엔터를 입력해주세요.");
        String enterLine = scan.nextLine();
        MyStock.UI();

        scan.close();
    }


    /**
     * 로그인 한 회원의 관심 주식 목록을 출력하는 메소드 오는 메소드
     */
    private static void printMyStockList() {

        List<List<String>> userStorageList = UserStockStorage.load();

        for(List<String> tmpUserStorage : userStorageList) {

            if (Login.loginUser.getNo().equals(tmpUserStorage.get(0))) {
                myStockList(tmpUserStorage.get(1));
            }
        }

    }


    /**
     * 관심 종목으로 등록한 종목의 정보를 탐색하는 메소드
     * (종목명, 현재가, 등락률, 거래량, PER, ROE)
     */
    private static void myStockList(String stockName) {

        List<List<String>> list = AllStockList.storeAllStockList();

        for(List<String> tmpList : list) {
            if(tmpList.get(1).equals(stockName)) {
                System.out.printf("\t\t\t\t\t\t　%-10s\t\t%6s\t\t%5s\t%15s\t\t%7s\t\t%7s\n"
                        , tmpList.get(1)
                        , tmpList.get(2)
                        , tmpList.get(3)
                        , tmpList.get(4)
                        , tmpList.get(5)
                        , tmpList.get(6));
                break;
            }
        }
    }

}





