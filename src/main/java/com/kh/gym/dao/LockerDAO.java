package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.LockerVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void entireLocker(List<LockerVO> list){
        System.out.println("라커번호         만료일         회원번호         회원성함");
        for(LockerVO e : list) {
            System.out.print("  " + e.getLockNum());
            if(e.getdDate() == null) System.out.print("              현재 이용자 없음");
            else {
                System.out.print("         " + e.getdDate());
                System.out.print("         " + e.getId());
                System.out.print("         " + e.getName());
            }
            System.out.println();

        }
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

    public void occupiedLocker(List<LockerVO> list){
        System.out.println("라커번호         만료일         회원번호         회원성함");
        for(LockerVO e : list) {
            System.out.print("  " + e.getLockNum());
            System.out.print("         " + e.getdDate());
            System.out.print("         " + e.getId());
            System.out.print("         " + e.getName());
            System.out.println();
        }
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

    public void specificLocker(List<LockerVO> list){
        try {
        System.out.println("라커번호         만료일         회원번호         회원성함");

            for (LockerVO e : list) {
                System.out.print("  " + e.getLockNum());
                System.out.print("         " + e.getdDate());
                System.out.print("         " + e.getId());
                System.out.print("         " + e.getName());
                System.out.println();
            }
        }catch(Exception e){
            System.out.println("존재하지 않는 라커번호거나 잘못된 값을 입력하셨습니다.");
        }
    }

    public void updateLockerProgram(){
        Scanner sc = new Scanner(System.in);
        System.out.print("등록/수정할 라커 번호 입력 : ");
        String num = sc.next();
        updateMILocker(num);
        updateLocker(num);
    }

    public void updateLocker(String num){
        Scanner sc = new Scanner(System.in);

        System.out.print("이용하실 기간 입력 : ");
        int term = sc.nextInt();
        String sql = "UPDATE LOCKER SET DUE_DATE = SYSDATE + ? WHERE LOCKNUM = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, term);
            pStmt.setString(2, num);
            int ret = pStmt.executeUpdate();
            if(ret == 0) System.out.println("그 라커번호는 존재하지 않습니다.");
            else System.out.println("등록/수정 완료");

        }catch(SQLException e){
            System.out.println("그 라커번호는 존재하지 않습니다.");

        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void updateMILocker(String num){
        Scanner sc = new Scanner(System.in);
        System.out.println("라커를 이용하시는 회원님의 회원번호를 입력해주세요.");
        int id = sc.nextInt();

        String sql = "UPDATE MEMBERINFO SET LOCKER = ? WHERE MEM_ID = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, num);
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

    public void initLocker(String num){ // 다음엔 걍 라커테이블에 mem_id 넣고 회원정보에 라커테이블의 번호를 붙이는게 낫겠음
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
            pStmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }



}
