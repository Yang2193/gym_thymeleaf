package com.kh.gym.vo;

import java.sql.Date;
public class SalesVO {
    private int order_No;
    private int mem_ID;
    private String mName;
    private String purchase;
    private int sales;
    private Date p_Date;
    private String p_DateStr;


    public SalesVO(int order_No, int mem_ID, String mName, String purchase, int sales, Date p_Date) {
        this.order_No = order_No;
        this.mem_ID = mem_ID;
        this.mName = mName;
        this.purchase = purchase;
        this.sales = sales;
        this.p_Date = p_Date;
    }

    public SalesVO() {

    }

    public String getP_DateStr() {
        return p_DateStr;
    }

    public void setP_DateStr(String p_DateStr) {
        this.p_DateStr = p_DateStr;
    }

    public int getOrder_No() {
        return order_No;
    }

    public void setOrder_No(int order_No) {
        this.order_No = order_No;
    }

    public int getMem_ID() {
        return mem_ID;
    }

    public void setMem_ID(int mem_ID) {
        this.mem_ID = mem_ID;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Date getP_Date() {
        return p_Date;
    }

    public void setP_Date(Date p_Date) {
        this.p_Date = p_Date;
    }


}