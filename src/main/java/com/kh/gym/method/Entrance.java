package com.kh.gym.method;

import com.kh.gym.dao.EntranceDAO;
import com.kh.gym.vo.EntranceVO;
import com.kh.gym.vo.MemberEntranceVO;

import java.util.List;
import java.util.Scanner;

public class Entrance {
    Scanner sc = new Scanner(System.in);
    EntranceDAO eDao = new EntranceDAO();

    public void entrance(){
        while(true){
            System.out.println("===== 회원 출석 조회 =====");
            System.out.println("[1] 금일 출석 회원 조회, [2] 특정일 출석 회원 조회, [3] 특정 회원 출석 기록 조회, [4] 종료");
            int sel = sc.nextInt();
            switch(sel){
                case 1: List<EntranceVO> list = eDao.showTodayList();
                        eDao.showToday(list);
                        break;
                case 2: List<EntranceVO> list2 = eDao.showDayList();
                        eDao.showDay(list2);
                        break;
                case 3: List<EntranceVO> list3 = eDao.showMemberAttendanceList();
                        eDao.showMemberAttendance(list3);
                        break;
                case 4: System.out.println("출석 조회를 종료합니다.");
                return;
            }
        }
    }

    public void entMemProgram(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("===== 회원 입장 =====");
            System.out.println("이전 메뉴로 돌아가시려면 'X'를 입력하세요.");
            System.out.print("회원 번호 : ");
            String num = sc.next();
            if(num.equalsIgnoreCase("x")) return;
            List<MemberEntranceVO> list = eDao.enterMember(num);
            eDao.enterMemberInfo(list);

        }
    }

}
