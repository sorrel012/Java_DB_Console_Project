package com.stock.service.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stock.data.AllStockList;
import com.stock.service.signup.Login;

/**
 * 주식명을 입력하여 정보를 검색하는 메소드
 * @author 6조
 *
 */
public class Search {

    private String inputName;
    private String editedName;
    private List<List<String>> result = new ArrayList<List<String>>();

    /**
     * 검색창 로고를 출력하는 메소드
     */
    private void printLogo() {

        try {
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t    ░█▀▀░█▀▀░█▀█░█▀▄░█▀▀░█░█\r\n"
                    + "\t\t\t\t\t\t\t\t\t\t    ░▀▀█░█▀▀░█▀█░█▀▄░█░░░█▀█\r\n"
                    + "\t\t\t\t\t\t\t\t\t\t    ░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀▀░▀░▀\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 사용자의 입력을 받는 메소드
     */
    private void userInput() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\n\t\t\t\t\t\t\t\t원하시는 종목의 이름을 입력해 주세요 : ");
        this.inputName = scan.nextLine();

        this.editedName = inputName.replace(" ", "").toUpperCase();
    }

    /**
     * 저장된 주식 데이터 파일에서 사용자가 입력한 내용이 포함된 주식들을 추출하여 리스트에 저장하는 메소드
     */
    private void searchStockList() {

        List<List<String>> allStocks = AllStockList.storeAllStockList();

        for (List<String> tmpList : allStocks) {

            if(tmpList.get(1).replace(" ", "").toUpperCase().contains(this.editedName)) {
                this.result.add(tmpList);
            }

        }

    }

    /**
     * 입력한 주식명이 포함된 모든 주식명을 출력하는 메소드
     */
    private void printSearchResult() {

        System.out.println("\n\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t  [검색 결과]");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t종목 검색: " + this.inputName);
        System.out.println("\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\n\t\t\t\t\t\t\t\t[종목 번호] \t\t\t [종목명]");

        int num = 1;
        for(List<String> list : result) {
            System.out.printf("\t\t\t\t\t\t\t\t %d \t\t\t\t %s\n", num, list.get(1));
            num++;

        }

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t   (0번 입력: 메인화면으로 이동)");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");

    }

    /**
     * 입력한 주식명이 포함된 주식명이 없을 경우 출력하는 메소드
     */
    private void printNoSearchResult() {

        System.out.println("\n\t\t\t\t\t\t\t==================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t종목 검색: " + this.inputName);
        System.out.println("\t\t\t\t\t\t\t==================================================================================");

        System.out.println("\t\t\t\t\t\t\t\t일치하는 종목이 없습니다.");
        System.out.println("\t\t\t\t\t\t\t==================================================================================");

    }

    /**
     * 사용자의 입력과 일치하는 검색 결과를 출력하는 메소드
     * @see #searchStockList(String)
     */
    private void searchResult() {

        userInput();
        searchStockList();

        if(!this.result.isEmpty()) {
            printSearchResult();
            SearchDetail.searchResultDetail(result);

        } else {
            printNoSearchResult();
            searchResult();
        }

    }

    /**
     * 현재 사용자가 비회원인지, 회원인지, 관리자인지 확인
     */
    public int checkUser() {
        if (Login.loginUser != null) {
            return 1; //회원

        } else if (Login.loginAdmin != null) {
            return 2; //관리자

        } else {
            return 0; //비회원
        }
    }

    /**
     * 검색창과 사용자 검색 결과를 함께 출력하는 메소드
     */
    public void searchStock() {
        printLogo();
        searchResult();
    }

}