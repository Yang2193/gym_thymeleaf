package com.kh.gym.method;

import com.kh.gym.dao.MemberInfoDAO;
import com.kh.gym.vo.MemberInfoVO;

import java.util.List;
import java.util.Scanner;

public class Member {
    Scanner sc = new Scanner(System.in);
    MemberInfoDAO mIDao = new MemberInfoDAO();

    public void management() {
        while (true) {
            System.out.println("===== 회원정보 관리 =====");
            System.out.println("[1] 전체 회원 조회, [2] 회원 조회, [3] 기간 만료 회원 조회, [4] 회원 추가, [5] 회원 정보 수정, [6] 회원 삭제, [7] 종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                        List<MemberInfoVO> list = mIDao.M_InfoSelect();
                        mIDao.M_InfoView(list);
                        break;
                case 2:
                        mIDao.selSomeoneInfo(mIDao.selSomeone());
//                    List<MemberInfoVO> list1 = mIDao.M_SelectSomeone();
//                    mIDao.M_SomeoneView(list1);
                        break;
                case 3: List<MemberInfoVO> list2 = mIDao.expiredDateList();
                        mIDao.expiredListShow(list2);
                        break;
                case 4: mIDao.M_InfoInsert();
                        break;
                case 5: mIDao.m_infoModify();
                        break;
                case 6: mIDao.m_InfoDelete();
                        break;
                case 7:
                    System.out.println("회원정보 관리를 종료합니다.");
                    return;
            }
        }
    }
}
