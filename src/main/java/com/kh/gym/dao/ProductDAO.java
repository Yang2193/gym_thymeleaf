package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.ProductVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Repository
public class ProductDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    PreparedStatement pStmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public List<ProductVO> productSel(){
        List<ProductVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM PRODUCT";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String pName = rs.getString("PNAME");
                int price = rs.getInt("PRICE");
                int term = rs.getInt("TERM");

                ProductVO vo = new ProductVO(pName, price, term);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public void productInsert(ProductVO vo){


        String sql = "INSERT INTO PRODUCT VALUES(?,?,?)";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, vo.getpName());
            pStmt.setInt(2, vo.getPrice());
            pStmt.setInt(3, vo.getTerm());
            int ret = pStmt.executeUpdate();
            if(ret == 0) System.out.println("잘못 된 정보를 입력하셨습니다.");
            else System.out.print("상품 추가 완료");

        } catch(SQLException e){
            System.out.println("잘못 된 정보를 입력하셨습니다.");

        }
        Common.close(pStmt);
        Common.close(conn);

        }




    public void productDelete(String pName){


        String sql = "DELETE FROM PRODUCT WHERE PNAME = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pName);
            int ret = pStmt.executeUpdate();
            if(ret == 0) System.out.println("존재하지 않는 상품이거나 값을 잘못 입력하셨습니다.");
            else System.out.println("삭제 완료");
        }catch(Exception e){
            System.out.println("존재하지 않는 상품이거나 값을 잘못 입력하셨습니다.");
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

}
