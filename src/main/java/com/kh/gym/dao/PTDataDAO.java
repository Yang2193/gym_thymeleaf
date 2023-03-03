package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.MemberInfoVO;
import com.kh.gym.vo.PTDataVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PTDataDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rSet = null;

    // pt 받는 사람만 조회하는 메소드
    public List<MemberInfoVO> ptMemberList(){
        List<MemberInfoVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBERINFO WHERE SUBSTR(PNAME, 1, 2) = 'PT' ORDER BY MEM_ID";
            rSet = stmt.executeQuery(sql);

            while (rSet.next()) {
                int id = rSet.getInt("MEM_ID");
                String mName = rSet.getString("MNAME");
                String pName = rSet.getString("PNAME");
                int ptRemain = rSet.getInt("PT_REMAIN");
                Date dDate = rSet.getDate("DUE_DATE");
                String gender = rSet.getString("GENDER");
                String pNum = rSet.getString("PHONE_NUM");
                String lNum = rSet.getString("LOCKER");
                Date rDate = rSet.getDate("REG_DATE");

                MemberInfoVO vo = new MemberInfoVO(id, mName, pName, ptRemain, dDate, gender, pNum, lNum, rDate);
                list.add(vo);
            }
            Common.close(rSet); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<PTDataVO> getPTInfoList() {
        List<PTDataVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT PT.MEM_ID, M.MNAME, T.TRAINER_NAME, TO_CHAR( PT.PT_DATE, 'YYYY-MM-DD') AS PT_DATE, PT.PT_REMAIN ");
            sql.append("FROM PT_DATA PT, MEMBERINFO M, TRAINERS T ");
            sql.append("WHERE PT.MEM_ID = M.MEM_ID AND T.TRAINER_ID = PT.TRAINER_ID ");
            sql.append("ORDER BY PT_DATE DESC");
            pstmt = conn.prepareStatement(sql.toString());
            listAddResultSet(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // 회원번호로 조회하는 것은 오버로딩 하겠습니다. 코드가 많이 겹쳐서 listAddResultSet 메서드로 만들겠습니다.
    public List<PTDataVO> getPTInfoList(int memberID) {
        List<PTDataVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT PT.MEM_ID, M.MNAME, T.TRAINER_NAME, TO_CHAR( PT.PT_DATE, 'YYYY-MM-DD') AS PT_DATE, PT.PT_REMAIN ");
            sql.append("FROM PT_DATA PT, MEMBERINFO M, TRAINERS T ");
            sql.append("WHERE PT.MEM_ID = M.MEM_ID AND T.TRAINER_ID = PT.TRAINER_ID AND PT.MEM_ID = ?");
            sql.append("ORDER BY PT_DATE DESC");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, memberID);
            listAddResultSet(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    // list에 ResultSet의 결과물을 추가해주는 메소드 입니다.
    private void listAddResultSet(List<PTDataVO> list) throws SQLException {
        rSet = pstmt.executeQuery();

        while(rSet.next()) {
            PTDataVO pt = new PTDataVO();
            pt.setRowNo(rSet.getRow());
            pt.setMem_ID(rSet.getInt("MEM_ID"));
            pt.setmName(rSet.getString("MNAME"));
            pt.settName(rSet.getString("TRAINER_NAME"));
            pt.setPtDate(rSet.getString("PT_DATE"));
            pt.setPtRemain(rSet.getInt("PT_REMAIN"));

            list.add(pt);
        }
        Common.close(rSet);
        Common.close(pstmt);
        Common.close(conn);
    }

    // 출력문
    public void PTDataView(List<PTDataVO> list) {
        System.out.println("| 번호 | 회원번호 | 회원이름 | 트레이너 | 날짜 | 남은PT횟수 |");
        System.out.println("======================================================");
        for (PTDataVO m : list) {
            StringBuilder out = new StringBuilder();
            out.append("| " + m.getRowNo() + " | ");
            out.append(m.getMem_ID() + " | ");
            out.append(m.getmName() + " | ");
            out.append(m.gettName() + " | ");
            out.append(m.getPtDate() + " | ");
            out.append(m.getPtRemain() + " |");
            System.out.println(out);
            System.out.println("==============================");


        }
    }


    // PT 기록 시 PT_DATA에 INSERT 하고 MEMBERINFO 의 PT_REMAIN을 1 감소시키는 메소드
    // 쿼리문을 2개 써야한다.
    public void PTDataInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.print("회원 번호 입력 : ");
        int memberID = sc.nextInt();
        System.out.print("트레이너 번호 입력 : ");
        int trainerID = sc.nextInt();
        System.out.print("날짜 입력(yyyy-mm-dd) : ");
        String ptDate = sc.next();
        // DATE 는 JAVA.SQL.DATE를 사용하는 것도?
        try {
            conn = Common.getConnection();
            String sql = String.format("SELECT PT_REMAIN FROM MEMBERINFO WHERE MEM_ID = %d", memberID);
            stmt = conn.createStatement();
            rSet = stmt.executeQuery(sql);
            // 에러처리가 힘들어서 PT_REMAIN값을 받아와서 체크합시다. ㅠㅠㅠ
            if (rSet.next() && rSet.getInt("PT_REMAIN") >= 1) {
                // PT_Data 테이블에 INSERT 하는 sql1
                StringBuilder sql1 = new StringBuilder();
                sql1.append("INSERT INTO PT_DATA(MEM_ID, TRAINER_ID, PT_DATE, PT_REMAIN) ");
                sql1.append("VALUES ( ?, ?, ?, (SELECT PT_REMAIN - 1 FROM MEMBERINFO WHERE MEM_ID = ? ))");
                pstmt = conn.prepareStatement(sql1.toString());
                pstmt.setInt(1, memberID);
                pstmt.setInt(2, trainerID);
                pstmt.setString(3, ptDate);
                pstmt.setInt(4, memberID);

                pstmt.executeUpdate();
                pstmt.clearParameters(); // pstmt를 비워줍니다. 재사용 하기 위해서..

                // MEMBERINFO 의 PT_REMAIN을 UPDATE 하는 sql2
                String sql2 = "UPDATE MEMBERINFO SET PT_REMAIN = PT_REMAIN - 1 WHERE MEM_ID = ?";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setInt(1, memberID);
                pstmt.executeUpdate();
                System.out.println("PT 기록이 완료되었습니다.");
            } else {
                System.out.println("남은 PT횟수가 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rSet);
        Common.close(stmt);
        Common.close(pstmt);
        Common.close(conn);
    }

    // pt 기록 삭제: 회원번호 입력하면 PT_DATA 보여줌 rownum입력으로 행을 삭제, MEMBERINFO 테이블의 PT_REMAIN 값 1증가 update
    public void PTDataDelete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("회원 번호 입력 : ");
        int memberID = sc.nextInt();
        // DATE 는 JAVA.SQL.DATE를 사용하는 것도?
        try {
            List<PTDataVO> list = getPTInfoList(memberID);
            PTDataView(list);
            System.out.println("삭제하실 행의 번호를 선택해 주세요 : ");
            int rowNo = sc.nextInt();
            conn = Common.getConnection();

            // 입력받은 rowNo에 해당하는 PT_REMAIN 값을 가져오는 쿼리문입니다.
            String sql1 = String.format("SELECT PT_REMAIN FROM PT_DATA WHERE ROWID = " +
                    "(SELECT rid FROM (SELECT ROWNUM rn, ROWID rid, E.* FROM " +
                    "(SELECT * FROM PT_DATA WHERE MEM_ID = %d ORDER BY PT_DATE DESC) E ) " +
                    "WHERE rn = %d)", memberID, rowNo);
            stmt = conn.createStatement();
            rSet = stmt.executeQuery(sql1);
            int deletePtRemain = 0;
            if (rSet.next()) {
                deletePtRemain = rSet.getInt("PT_REMAIN");
            } else {
                System.out.println("해당 컬럼 없습니다.");
                return;
            }

            // 입력받은 rowNo를 통해 PT_DATA의 해당하는 행을 삭제하는 쿼리문 입니다.
            String sql2 = String.format("DELETE FROM PT_DATA WHERE ROWID = " +
                    "(SELECT rid FROM " +
                    "(SELECT ROWNUM rn, ROWID rid, E.* FROM " +
                    "(SELECT * FROM PT_DATA WHERE MEM_ID = %d ORDER BY PT_DATE DESC) E )" +
                    " WHERE rn = %d)", memberID, rowNo);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql2);

            // stmt 는 재사용이 가능하다
            // MEMBERINFO 테이블의 PT_REMAIN을 1증가시키는 쿼리문
            String sql3 = String.format("UPDATE MEMBERINFO SET PT_REMAIN = PT_REMAIN + 1 WHERE MEM_ID = %d", memberID);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql3);
            // PT_DATA 에서도 1씩 증가시켜야 할까?   5  4  3  2  1 인데 4를 삭제 하면 4보다 작은 값들을 다 1씩 증가 시켜줘야 함
            String sql4 = String.format("UPDATE PT_DATA SET PT_REMAIN = PT_REMAIN + 1 WHERE MEM_ID = %d " +
                    "AND PT_REMAIN < %d", memberID, deletePtRemain);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql4);
            System.out.println("PT 기록 삭제가 완료 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rSet);
        Common.close(stmt);
        Common.close(pstmt);
        Common.close(conn);
    }
}