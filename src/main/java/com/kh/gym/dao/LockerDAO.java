package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.LockerVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Repository
public class LockerDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    PreparedStatement pStmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public List<LockerVO> entireLockerList(){
    List<LockerVO> list = new ArrayList<>();
    String sql = "SELECT L.LOCKNUM, L.DUE_DATE, M.MEM_ID, M.MNAME\n" +
            "FROM LOCKER L LEFT OUTER JOIN MEMBERINFO M\n" +
            "ON L.LOCKNUM  = M.LOCKER ORDER BY REGEXP_REPLACE(LOCKNUM, '[0-9]') , TO_NUMBER(REGEXP_REPLACE(LOCKNUM, '[^0-9]'))\n";

    try{
        conn = Common.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            String lNum = rs.getString("LOCKNUM");
            Date date = rs.getDate("DUE_DATE");
            int id = rs.getInt("MEM_ID");
            String name = rs.getString("MNAME");

            LockerVO vo = new LockerVO();
            vo.setId(id);
            vo.setdDate(date);
            vo.setName(name);
            vo.setLockNum(lNum);

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


    public List<LockerVO> occupiedLockerList(){
        List<LockerVO> list = new ArrayList<>();
        String sql = "SELECT L.LOCKNUM , L.DUE_DATE , M.MEM_ID , M.MNAME \n" +
                "FROM LOCKER L JOIN MEMBERINFO M ON L.LOCKNUM  = M.LOCKER ORDER BY REGEXP_REPLACE(LOCKNUM, '[0-9]') , TO_NUMBER(REGEXP_REPLACE(LOCKNUM, '[^0-9]'))";
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String lNum = rs.getString("LOCKNUM");
                Date date = rs.getDate("DUE_DATE");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");

                LockerVO vo = new LockerVO();
                vo.setId(id);
                vo.setdDate(date);
                vo.setName(name);
                vo.setLockNum(lNum);

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


    public List<LockerVO> specificLockerList(){
        List<LockerVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("라커 번호 입력 : ");
        String num = sc.next();
        String sql = "SELECT L.LOCKNUM , L.DUE_DATE , M.MEM_ID , M.MNAME \n" +
                "FROM LOCKER L JOIN MEMBERINFO M ON L.LOCKNUM  = M.LOCKER WHERE L.LOCKNUM = ?";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, num);
            pStmt.executeUpdate();
            rs = pStmt.executeQuery();
            while(rs.next()){
                String lNum = rs.getString("LOCKNUM");
                Date date = rs.getDate("DUE_DATE");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");

                LockerVO vo = new LockerVO();
                vo.setId(id);
                vo.setdDate(date);
                vo.setName(name);
                vo.setLockNum(lNum);

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




    public void updateLocker(String lNum, int term){

        String sql = "UPDATE LOCKER SET DUE_DATE = SYSDATE + ? WHERE LOCKNUM = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, term);
            pStmt.setString(2, lNum);
            int ret = pStmt.executeUpdate();
            if(ret == 0) System.out.println("그 라커번호는 존재하지 않습니다.");
            else System.out.println("등록/수정 완료");

        }catch(SQLException e){
            System.out.println("그 라커번호는 존재하지 않습니다.");

        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void updateMILocker(String lNum, int id){


        String sql = "UPDATE MEMBERINFO SET LOCKER = ? WHERE MEM_ID = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, lNum);
            pStmt.setInt(2, id);
            pStmt.executeUpdate();
        }catch(SQLException e){
            System.out.print("");
        }
        Common.close(pStmt);
        Common.close(conn);

    }

    public void initLockerProgram(){
        Scanner sc = new Scanner(System.in);
        System.out.println("초기화할 라커번호를 입력해주세요.");
        String num = sc.next();
        initMILocker(num);
        initLocker(num);
    }

    public void initLocker(String num){
        String sql = "UPDATE LOCKER SET DUE_DATE = '' WHERE LOCKNUM = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, num);
            int ret = pStmt.executeUpdate();
            if(ret == 0) System.out.println("그 라커번호는 존재하지 않습니다.");
            else System.out.println("라커 초기화 완료.");

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    public void initMILocker(String num){
        String sql = "UPDATE MEMBERINFO SET LOCKER = '' WHERE LOCKER = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, num);
            int ret = pStmt.executeUpdate();
            if(ret == 0) System.out.println("그 라커번호는 존재하지 않습니다.");
            else System.out.println("라커 초기화 완료.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }



    public void updateLocker(String lockNum, int id, int term) {

        String sql1 = "UPDATE MEMBERINFO SET LOCKER = ? WHERE MEM_ID = ?";
        String sql2 = "UPDATE LOCKER SET DUE_DATE = SYSDATE + ? WHERE LOCKNUM = ?";

        try {
            conn = Common.getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            // MEMBERINFO 테이블 업데이트
            pStmt = conn.prepareStatement(sql1);
            pStmt.setString(1, lockNum);
            pStmt.setInt(2, id);
            pStmt.executeUpdate();

            // LOCKER 테이블 업데이트
            pStmt = conn.prepareStatement(sql2);
            pStmt.setInt(1, term);
            pStmt.setString(2, lockNum);
            pStmt.executeUpdate();

            conn.commit(); // 트랜잭션 커밋
            System.out.println("등록/수정 완료");
        } catch (SQLException e) {
            try {
                conn.rollback(); // 트랜잭션 롤백
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("오류 발생");
            e.printStackTrace();
        } finally {
            Common.close(pStmt);
            Common.close(conn);
        }
    }


}
