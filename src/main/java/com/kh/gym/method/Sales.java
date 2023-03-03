package com.kh.gym.method;

import com.kh.gym.dao.SalesDAO;
import com.kh.gym.vo.SalesVO;

import java.util.List;
import java.util.Scanner;

public class Sales {
    Scanner sc = new Scanner(System.in);
    SalesDAO dao = new SalesDAO();

    public void sales() {
        while (true) {
            System.out.println("===== 매출관리 =====");
            System.out.print("[1] 매출 조회, [2] 매출 등록, [3] 매출 수정, [4] 매출 삭제, [5] 종료 : ");
            int sel = sc.nextInt();

            switch (sel) {
                case 1:
                    System.out.println("원하시는 조회를 선택 하세요");
                    System.out.println("[1] 일별 매출 조회, [2] 일별 주문내역 조회, [3] 월별 매출 조회, [4] 월별 주문내역 조회, [5] 년도별 매출 조회 [6] 년도별 주문내역 조회: ");
                    int salSel = sc.nextInt();
                    switch (salSel) {
                        case 1:
                            List<SalesVO> list1 = dao.dailySalSel();
                            dao.dailySalSelPrint(list1);
                            break;
                        case 2:
                            List<SalesVO> list2 = dao.somDaySalSel();
                            dao.SalSelPrint(list2);
                            break;
                        case 3:
                            List<SalesVO> list3 = dao.monthlySalSel();
                            dao.monthlySalSelPrint(list3);
                            break;
                        case 4:
                            List<SalesVO> list4 = dao.somMonthSalSel();
                            dao.SalSelPrint(list4);
                            break;
                        case 5:
                            List<SalesVO> list5 = dao.annualSalSel();
                            dao.annualSalSelPrint(list5);
                            break;
                        case 6:
                            List<SalesVO> list6 = dao.somYearSalSel();
                            dao.SalSelPrint(list6);
                            break;
                    }
                    break;
                case 2:
                    dao.salesInsert();
                    break;
                case 3:
                    dao.salesUpdate();
                    break;
                case 4:
                    dao.salesDelete();
                    break;
                case 5 :
                    System.out.println("메뉴를 종료 합니다.");
                    return;
            }
        }
    }
}

