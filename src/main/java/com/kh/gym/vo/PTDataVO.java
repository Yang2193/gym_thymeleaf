package com.kh.gym.vo;

public class PTDataVO {
    private int rowNo;
    private int mem_ID;
    private String mName;
    private String tName;
    private String ptDate;
    private int ptRemain;

    public PTDataVO() {
        super();
    }
    public PTDataVO(int rowNo, int mem_ID, String mName, String tName, String ptDate, int ptRemain) {
        this.rowNo = rowNo;
        this.mem_ID = mem_ID;
        this.mName = mName;
        this.tName = tName;
        this.ptDate = ptDate;
        this.ptRemain = ptRemain;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getMem_ID() {
        return mem_ID;
    }

    public void setMem_ID(int mem_ID) {
        this.mem_ID = mem_ID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getPtDate() {
        return ptDate;
    }

    public void setPtDate(String ptDate) {
        this.ptDate = ptDate;
    }

    public int getPtRemain() {
        return ptRemain;
    }

    public void setPtRemain(int ptRemain) {
        this.ptRemain = ptRemain;
    }
}
