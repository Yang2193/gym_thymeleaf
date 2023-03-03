package com.kh.gym.method;

import com.kh.gym.dao.ProductDAO;
import com.kh.gym.vo.ProductVO;

import java.util.List;
import java.util.Scanner;

public class Product {
    Scanner sc = new Scanner(System.in);
    ProductDAO pDAO = new ProductDAO();

    public void product(){
        while(true){
            System.out.println("===== 상품 관리 =====");
            System.out.println("[1] 전체 상품 조회, [2] 상품 추가, [3] 상품 수정, [4] 상품 삭제, [5] 종료");
            int sel = sc.nextInt();
            switch(sel) {
                case 1:  List<ProductVO> list = pDAO.productSel();
                         pDAO.productView(list);
                         break;
                case 2: pDAO.productInsert();
                        break;
                case 3: pDAO.productUpdate();
                        break;
                case 4: pDAO.productDelete();
                        break;
                case 5:
                    System.out.println("상품 관리를 종료합니다.");
                    return;
            }
        }
    }
}
