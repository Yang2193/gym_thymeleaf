package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.MemberInfoVO;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
@Repository

public class MemberInfoDAO {

    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    PreparedStatement pStmt = null;

    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public List<MemberInfoVO> M_InfoSelect() {
        List<MemberInfoVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBERINFO";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("MEM_ID");
                String mName = rs.getString("MNAME");
                String pName = rs.getString("PNAME");
                int ptRemain = rs.getInt("PT_REMAIN");
                Date dDate = rs.getDate("DUE_DATE");
                String gender = rs.getString("GENDER");
                String pNum = rs.getString("PHONE_NUM");
                String lNum = rs.getString("LOCKER");
                Date rDate = rs.getDate("REG_DATE");

                MemberInfoVO vo = new MemberInfoVO();
                vo.setMem_Id(id);
                vo.setMname(mName);
                vo.setPname(pName);
                vo.setPtRemain(ptRemain);
                vo.setDue_Date(dDate);
                vo.setGender(gender);
                vo.setPhoneNum(pNum);
                vo.setLockNum(lNum);
                vo.setReg_Date(rDate);

                list.add(vo);
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    //특정 회원 조회 쿼리
    public List<MemberInfoVO> M_SelectSomeone(int mem_id) {
        List<MemberInfoVO> list = new ArrayList<>();

        int num = mem_id;


        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBERINFO WHERE MEM_ID =" + num;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("MEM_ID");
                String mName = rs.getString("MNAME");
                String pName = rs.getString("PNAME");
                int ptRemain = rs.getInt("PT_REMAIN");
                Date dDate = rs.getDate("DUE_DATE");
                String gender = rs.getString("GENDER");
                String pNum = rs.getString("PHONE_NUM");
                String lNum = rs.getString("LOCKER");
                Date rDate = rs.getDate("REG_DATE");

                MemberInfoVO vo = new MemberInfoVO();
                vo.setMem_Id(id);
                vo.setMname(mName);
                vo.setPname(pName);
                vo.setPtRemain(ptRemain);
                vo.setDue_Date(dDate);
                vo.setGender(gender);
                vo.setPhoneNum(pNum);
                vo.setLockNum(lNum);
                vo.setReg_Date(rDate);

                list.add(vo);
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public MemberInfoVO M_SelectSomeone2(int mem_id) {

        MemberInfoVO vo = null;
        int num = mem_id;


        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBERINFO WHERE MEM_ID =" + num;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("MEM_ID");
                String mName = rs.getString("MNAME");
                String pName = rs.getString("PNAME");
                int ptRemain = rs.getInt("PT_REMAIN");
                Date dDate = rs.getDate("DUE_DATE");
                String gender = rs.getString("GENDER");
                String pNum = rs.getString("PHONE_NUM");
                String lNum = rs.getString("LOCKER");
                Date rDate = rs.getDate("REG_DATE");

                vo = new MemberInfoVO();
                vo.setMem_Id(id);
                vo.setMname(mName);
                vo.setPname(pName);
                vo.setPtRemain(ptRemain);
                vo.setDue_Date(dDate);
                vo.setGender(gender);
                vo.setPhoneNum(pNum);
                vo.setLockNum(lNum);
                vo.setReg_Date(rDate);

            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;

    }


    public void M_InfoInsert(MemberInfoVO vo) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = null;
        try {
            Date date = vo.getReg_Date();
            cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, vo.getTerm());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Date dDate = Date.valueOf(sdf.format(cal.getTime()));
        vo.setDue_Date(dDate);

        String sql = "INSERT INTO MEMBERINFO VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            //  stmt = conn.createStatement();

            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, vo.getMem_Id());
            pStmt.setString(2, vo.getMname());
            pStmt.setString(3, vo.getPname());
            pStmt.setInt(4, vo.getPtRemain());
            pStmt.setDate(5, vo.getDue_Date());
            pStmt.setString(6, vo.getGender());
            pStmt.setString(7, vo.getPhoneNum());
            pStmt.setString(8, vo.getLockNum());
            pStmt.setDate(9, vo.getReg_Date());
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("중복된 회원번호거나 값을 잘못 입력하셨습니다.");
        }
        Common.close(pStmt);
        Common.close(conn);

    }

    public void m_infoModify(MemberInfoVO vo) {

        String sql = "UPDATE MEMBERINFO SET MNAME = ?, PNAME = ?, PT_REMAIN = ?, DUE_DATE = DUE_DATE + ?, GENDER = ?, PHONE_NUM = ?, LOCKER = ?  WHERE MEM_ID = ?";
                try {
                    conn = Common.getConnection();
                    pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, vo.getMname());
                    pStmt.setString(2, vo.getPname());
                    pStmt.setInt(3, vo.getPtRemain());
                    pStmt.setInt(4, vo.getTerm());
                    pStmt.setString(5, vo.getGender());
                    pStmt.setString(6, vo.getPhoneNum());
                    pStmt.setString(7, vo.getLockNum());
                    pStmt.setInt(8, vo.getMem_Id());
                    int ret = pStmt.executeUpdate();
                    if(ret == 0) System.out.println("등록되지 않았거나 잘못된 정보를 입력하였습니다.");
                    else System.out.println("수정 완료");
                }catch(Exception e){
                    e.printStackTrace();
                }
                Common.close(pStmt);
                Common.close(conn);

        }


    public void m_InfoDelete(int mem_Id){

        try {
            String sql = "DELETE FROM MEMBERINFO WHERE MEM_ID = " + mem_Id;
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                int ret = stmt.executeUpdate(sql);
                if (ret == 0) System.out.println("등록되지 않은 회원번호입니다.");
                else System.out.println("삭제 완료");

            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(stmt);
            Common.close(conn);
        }catch(InputMismatchException e){
            System.out.println("잘못된 값을 입력하셨습니다.");
        }
    }


    public List<MemberInfoVO> expiredDateList(){
        List<MemberInfoVO> list = new ArrayList<>();

        String sql = "SELECT * FROM MEMBERINFO WHERE SYSDATE-DUE_DATE > 0";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("MEM_ID");
                String mName = rs.getString("MNAME");
                String pName = rs.getString("PNAME");
                int ptRemain = rs.getInt("PT_REMAIN");
                Date dDate = rs.getDate("DUE_DATE");
                String gender = rs.getString("GENDER");
                String pNum = rs.getString("PHONE_NUM");
                String lNum = rs.getString("LOCKER");
                Date rDate = rs.getDate("REG_DATE");
                MemberInfoVO vo = new MemberInfoVO();
                vo.setMem_Id(id);
                vo.setMname(mName);
                vo.setPname(pName);
                vo.setPtRemain(ptRemain);
                vo.setDue_Date(dDate);
                vo.setGender(gender);
                vo.setPhoneNum(pNum);
                vo.setLockNum(lNum);
                vo.setReg_Date(rDate);

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

}


