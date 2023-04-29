package com.stock.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stock.data.Member;
import com.stock.service.signup.UserInfo;
import com.stock.view.Admin;

/**
 * 회원 정보를 관리하는 클래스
 * @author 6조
 */
public class UserInfoManage {

    private static ArrayList<Member> userFile;
    /**
     * 회원 데이터 파일이 담겨있는 경로 변수
     */

    private static Scanner scan;

    static {
        scan = new Scanner(System.in);
        userFile = new ArrayList<Member>();
    }


    /**
     * 회원 데이터 파일에 저장된 정보를 출력하는 메소드
     */
    public static void UserInfo() {

        String input = "";
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[회원 목록]");
        System.out.println("\t\t\t\t\t\t\t=======================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t[이름]\t\t[아이디]\t\t[연락처]\t\t[이메일]\t\t[총 자산]");
        System.out.println("\t\t\t\t\t\t\t=======================================================================================");

        ArrayList<Member> list = new ArrayList<Member>();

        List<List<String>> users = Member.createMemberList();

        for(List<String> tmpUserList : users) {

            Member m = new Member(tmpUserList.get(0), tmpUserList.get(1), tmpUserList.get(2), tmpUserList.get(3), tmpUserList.get(4), tmpUserList.get(5), tmpUserList.get(6), tmpUserList.get(7), tmpUserList.get(8));

            list.add(m);

            System.out.printf("\t\t\t\t\t\t\t%s\t%s\t%10s\t%10s\t%20s\t%,d\n",tmpUserList.get(0)	//회원 번호
                    , tmpUserList.get(1)	//	  이름
                    , tmpUserList.get(2)	//	  아이디
                    , tmpUserList.get(4)	//	  연락처
                    , tmpUserList.get(6)	//	  이메일
                    , Integer.parseInt(tmpUserList.get(7)));	// 총자산
        }

        userFile = list;

        System.out.println("\t\t\t\t\t\t\t=======================================================================================");
        System.out.println("\t\t\t\t\t\t\t1. 회원삭제");
        System.out.println("\t\t\t\t\t\t\t2. 뒤로가기");
        System.out.println();
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t번호 입력:");

        boolean loop = true;
        while(loop) {
            input = scan.nextLine();

            if (input.equals("1")) {
                deleteUser();
                loop = false;
                Admin.AdminMenu();

            } else if(input.equals("2")) {
                Admin.AdminMenu();
                loop = false;
            } else {
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t잘못된 번호를 입력하셨습니다.");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t다시 입력해주세요.");
                System.out.println();
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t번호 입력:");

            }


        }

    }


    /**
     * 데이터 파일에서 회원 정보를 삭제하는 메소드
     */
    public static void deleteUser() {


        String num = "";
        String input = "";

        System.out.println();
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t삭제하려는 회원의 번호를 입력해주세요: ");
        num = scan.nextLine();


        /**
         * @param members 저장된 값을 넘겨줄 List
         * @param m List값을 넘겨받을 변수
         */
        for(Member m : userFile) {

            if(m.getNo().equals(num)) {
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t삭제 하시겠습니까?(y/n): ");
                input = scan.nextLine();

                if(input.equalsIgnoreCase("y")) {
                    userFile.remove(m);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t삭제가 완료되었습니다.");
                    UserInfo.deleteDBUserData(num);
                    break;
                } else {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t관리자 화면으로 돌아갑니다.");
                    Admin.AdminMenu();

                }


            }//if

        }// for

    }//deleteUser

}//class