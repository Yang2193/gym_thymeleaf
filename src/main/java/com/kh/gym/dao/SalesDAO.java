package com.kh.gym.dao;
import com.kh.gym.util.Common;
import com.kh.gym.vo.SalesVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class SalesDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;

    public List<SalesVO> dailySalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE,'YYYY/MM/DD') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY/MM/DD') ORDER BY TO_CHAR(P_DATE,'YYYY/MM/DD')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDate = rs.getString("TO_CHAR(P_DATE,'YYYY/MM/DD')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr(pDate);
                vo.setSales(sumSales);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SalesVO> monthlySalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE,'YYYY/MM') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY/MM') ORDER BY TO_CHAR(P_DATE,'YYYY/MM')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDateStr = rs.getString("TO_CHAR(P_DATE,'YYYY/MM')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr(pDateStr);
                vo.setSales(sumSales);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SalesVO> annualSalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES),TO_CHAR(P_DATE,'YYYY') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY') ORDER BY TO_CHAR(P_DATE,'YYYY')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDate = rs.getString("TO_CHAR(P_DATE,'YYYY')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr((pDate));
                vo.setSales(sumSales);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void salesInsert(SalesVO salesVO) {
        String sql = "INSERT INTO SALES_STATEMENT(ORDER_NO,MEM_ID,MNAME,PURCHASE,SALES,P_DATE) VALUES (SEQ_ORDER_NO.NEXTVAL,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, salesVO.getMem_ID());
            pStmt.setString(2, salesVO.getMName());
            pStmt.setString(3, salesVO.getPurchase());
            pStmt.setInt(4, salesVO.getSales());
            pStmt.setDate(5, salesVO.getP_Date());
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void salesUpdate(SalesVO salesVO) {

        String sql = "UPDATE SALES_STATEMENT SET MEM_ID = ?,MNAME = ?,PURCHASE = ?,SALES = ?,P_DATE = ? WHERE ORDER_NO = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, salesVO.getMem_ID());
            pStmt.setString(2, salesVO.getMName());
            pStmt.setString(3, salesVO.getPurchase());
            pStmt.setInt(4, salesVO.getSales());
            pStmt.setDate(5, salesVO.getP_Date());
            pStmt.setInt(6, salesVO.getOrder_No());
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void salesDelete(SalesVO salesVO) {

        String sql = "DELETE FROM SALES_STATEMENT WHERE ORDER_NO = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, salesVO.getOrder_No());
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public List<SalesVO> oneDaySalSel(SalesVO salesVO) {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT * FROM SALES_STATEMENT WHERE TO_CHAR(P_DATE,'YYYY/MM/DD') = ? ORDER BY ORDER_NO";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,salesVO.getP_DateStr());
            rs = pStmt.executeQuery();
        while (rs.next()){
            int no = rs.getInt("ORDER_NO");
            int id = rs.getInt("MEM_ID");
            String name = rs.getString("MNAME");
            String purchase = rs.getString("PURCHASE");
            int sales = rs.getInt("SALES");
            String pDate = rs.getString("P_DATE");

            SalesVO vo = new SalesVO();
            vo.setOrder_No(no);
            vo.setMem_ID(id);
            vo.setMName(name);
            vo.setPurchase(purchase);
            vo.setSales(sales);
            vo.setP_DateStr(pDate);
            list.add(vo);
        }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SalesVO> oneMonthSalSel(SalesVO salesVO) {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT * FROM SALES_STATEMENT WHERE TO_CHAR(P_DATE,'YYYY/MM') = ? ORDER BY ORDER_NO";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,salesVO.getP_DateStr());
            rs = pStmt.executeQuery();
            while (rs.next()) {
                    int no = rs.getInt("ORDER_NO");
                    int id = rs.getInt("MEM_ID");
                    String name = rs.getString("MNAME");
                    String purchase = rs.getString("PURCHASE");
                    int sales = rs.getInt("SALES");
                    String pDate = rs.getString("P_DATE");

                    SalesVO vo = new SalesVO();
                    vo.setOrder_No(no);
                    vo.setMem_ID(id);
                    vo.setMName(name);
                    vo.setPurchase(purchase);
                    vo.setSales(sales);
                    vo.setP_DateStr(pDate);
                    list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

        public List<SalesVO> oneYearSalSel(SalesVO salesVO) {
        List<SalesVO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SALES_STATEMENT WHERE TO_CHAR(P_DATE,'YYYY') = ? ORDER BY ORDER_NO";
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, salesVO.getP_DateStr());
                rs = pStmt.executeQuery();
                while (rs.next()) {
                    int no = rs.getInt("ORDER_NO");
                    int id = rs.getInt("MEM_ID");
                    String name = rs.getString("MNAME");
                    String purchase = rs.getString("PURCHASE");
                    int sales = rs.getInt("SALES");
                    String pDate = rs.getString("P_DATE");

                    SalesVO vo = new SalesVO();
                    vo.setOrder_No(no);
                    vo.setMem_ID(id);
                    vo.setMName(name);
                    vo.setPurchase(purchase);
                    vo.setSales(sales);
                    vo.setP_DateStr((pDate));
                    list.add(vo);
                }
                Common.close(rs);
                Common.close(pStmt);
                Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}






