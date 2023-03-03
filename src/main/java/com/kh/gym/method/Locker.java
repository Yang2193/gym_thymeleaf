package com.kh.gym.method;

import com.kh.gym.dao.LockerDAO;
import com.kh.gym.vo.LockerVO;

import java.util.List;
import java.util.Scanner;


public class Locker {

    Scanner sc = new Scanner(System.in);
    LockerDAO lDao = new LockerDAO();

    public void locker() {
        while (true) {
            System.out.println("===== 라커 관리 =====");
            System.out.println("[1] 전체 라커 조회, [2] 사용 중 라커 조회, [3] 특정 라커 조회, [4] 라커 등록/수정, [5] 라커 초기화 [6] 종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<LockerVO> list= lDao.entireLockerList();
                    lDao.entireLocker(list);
                    break;
                case 2:
                    List<LockerVO> list1 = lDao.occupiedLockerList();
                    lDao.occupiedLocker(list1);
                    break;
                case 3:
                    List<LockerVO> list2 = lDao.specificLockerList();
                    lDao.specificLocker(list2);
                    break;
                case 4:
                    lDao.updateLockerProgram();
                    break;
                case 5:
                    lDao.initLockerProgram();
                    break;
                case 6:
                    System.out.println("라커 관리를 종료합니다.");
                    return;
            }
        }
    }
}
