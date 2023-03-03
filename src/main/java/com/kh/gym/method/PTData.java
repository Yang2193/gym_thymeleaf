package com.kh.gym.method;

import com.kh.gym.dao.MemberInfoDAO;
import com.kh.gym.dao.PTDataDAO;
import com.kh.gym.vo.MemberInfoVO;
import com.kh.gym.vo.PTDataVO;

import java.util.List;
import java.util.Scanner;

public class PTData {

    Scanner sc = new Scanner(System.in);
    PTDataDAO dao = new PTDataDAO();
    public void PTDataManagement() {
        while (true) {
            System.out.println("[1]PT 회원 목록, [2]PT 회원 조회, [3] PT 기록 확인, [4] PT 기록 입력, [5] PT 기록 삭제, [6] 종료");
            System.out.print("메뉴를 선택하세요 : ");

            int sel = sc.nextInt();
            List<PTDataVO> ptData = null;
            switch (sel) {
                case 1:
                    System.out.println("[1]PT 회원 목록 (회원번호 순)");
                    List<MemberInfoVO> list = dao.ptMemberList();
                    MemberInfoDAO memberInfoDAO = new MemberInfoDAO();
                    memberInfoDAO.M_InfoView(list);
                    break;
                case 2:
                    System.out.println("[2]PT 회원 조회");
                    System.out.print("회원번호 입력 : ");
                    int memberID = sc.nextInt();
                    ptData = dao.getPTInfoList(memberID);
                    dao.PTDataView(ptData);
                    break;
                case 3:
                    System.out.println("[3]PT 기록 확인 (시간역순)");
                    ptData = dao.getPTInfoList();
                    dao.PTDataView(ptData);
                    break;
                case 4:
                    System.out.println("[4]PT 기록 입력");
                    dao.PTDataInsert();
                    break;
                case 5: //  pt기록 삭제
                    System.out.println("[5]PT 기록 삭제");
                    dao.PTDataDelete();
                    break;
                case 6:
                    System.out.println("PT 기록 관리를 종료합니다.");
                    return;
            }

        }
    }
}
