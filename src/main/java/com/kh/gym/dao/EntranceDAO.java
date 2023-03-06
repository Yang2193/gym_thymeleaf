package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.EntranceVO;
import com.kh.gym.vo.MemberEntranceVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Repository
public class EntranceDAO {

    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    PreparedStatement pStmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public List<EntranceVO> showTodayList(){
        List<EntranceVO> list = new ArrayList<>();

        String sql = "SELECT ROWNUM, MEM_ID, MNAME, TO_CHAR(E_DATE, 'HH24:MI') AS E_DATE FROM (SELECT ED.MEM_ID, MNAME, E_DATE FROM ENTRANCE_DATA ED JOIN MEMBERINFO M \n" +
                "ON ED.MEM_ID = M.MEM_ID WHERE TO_CHAR(E_DATE, 'YYYY-MM-DD') = TO_CHAR(SYSDATE, 'YYYY-MM-DD')\n" +
                "ORDER BY E_DATE)";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){
                int rownum = rs.getInt("ROWNUM");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                String date = rs.getString("E_DATE");

                EntranceVO vo = new EntranceVO();
                vo.setRownum(rownum);
                vo.setId(id);
                vo.setName(name);
                vo.setDateStr(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<EntranceVO> showDayList(String dDate){
        List<EntranceVO> list = new ArrayList<>();

        String sql = "SELECT ROWNUM, MEM_ID, MNAME, TO_CHAR(E_DATE, 'HH24:MI') AS E_DATE FROM (SELECT ED.MEM_ID, MNAME, E_DATE FROM ENTRANCE_DATA ED JOIN MEMBERINFO M \n" +
                "ON ED.MEM_ID = M.MEM_ID WHERE TO_CHAR(E_DATE, 'YYYY-MM-DD') = ?\n" +
                "ORDER BY E_DATE)";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, dDate);
            pStmt.executeUpdate();
            rs = pStmt.executeQuery();
            while(rs.next()){
                int rownum = rs.getInt("ROWNUM");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                String date = rs.getString("E_DATE");

                EntranceVO vo = new EntranceVO();
                vo.setRownum(rownum);
                vo.setId(id);
                vo.setName(name);
                vo.setDateStr(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }



    public List<EntranceVO> showMemberAttendanceList(int num){
        List<EntranceVO> list = new ArrayList<>();

        String sql = "SELECT ROWNUM, MEM_ID, MNAME, E_DATE FROM (SELECT ED.MEM_ID, MNAME, E_DATE FROM ENTRANCE_DATA ED JOIN MEMBERINFO M \n" +
                "ON ED.MEM_ID = M.MEM_ID WHERE ED.MEM_ID = ?\n" +
                "ORDER BY E_DATE)";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, num);
            pStmt.executeUpdate();
            rs = pStmt.executeQuery();
            while(rs.next()){
                int rownum = rs.getInt("ROWNUM");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                Date date = rs.getDate("E_DATE");

                EntranceVO vo = new EntranceVO();
                vo.setRownum(rownum);
                vo.setId(id);
                vo.setName(name);
                vo.setDate(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }



    public void entranceInsert(int mem_id) {
        String sql = "INSERT INTO ENTRANCE_DATA VALUES(?, SYSDATE)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, mem_id);
            int ret = pStmt.executeUpdate();
        } catch(Exception e){

            }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void entranceDelete(int mem_Id){
        String sql = "DELETE FROM ENTRANCE_DATA WHERE MEM_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, mem_Id);
            pStmt.executeUpdate();
        } catch(Exception e){

        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public List<MemberEntranceVO> enterMember(int num){
        List<MemberEntranceVO> list = new ArrayList<>();

        String sql = "SELECT MEM_ID, MNAME, PNAME, DUE_DATE FROM MEMBERINFO WHERE MEM_ID = ?"
                +"AND DUE_DATE - SYSDATE > 0";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, num);
            rs = pStmt.executeQuery();

                entranceInsert(num);
                while (rs.next()) {
                        int id = rs.getInt("MEM_ID");
                    String name = rs.getString("MNAME");
                    String pName = rs.getString("PNAME");
                    Date date = rs.getDate("DUE_DATE");

                    MemberEntranceVO vo = new MemberEntranceVO();
                    vo.setId(id);
                    vo.setName(name);
                    vo.setpName(pName);
                    vo.setDate(date);

                    list.add(vo);


                    System.out.println("입장 완료");


            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("등록되지 않은 회원번호거나 기간이 만료되었습니다.");
        }
        return list;
    }
    public void enterMemberInfo(List<MemberEntranceVO> list){
        for(MemberEntranceVO e : list){
            System.out.println(e.getName() + "님 입장을 환영합니다.");
            System.out.println("회원번호 : " + e.getId());
            System.out.println("이용 중인 상품 : " + e.getpName());
            System.out.println("만료일 : " + e.getDate());
        }
    }

}
