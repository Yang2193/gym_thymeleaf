package com.kh.gym.vo;

import java.sql.Date;

public class MemberInfoVO {
    private int mem_Id;
    private String mname;
    private String pname;
    private int ptRemain;
    private Date due_Date;
    private String gender;
    private String phoneNum;
    private String lockNum;
    private Date reg_Date;

    public MemberInfoVO(int mem_Id, String mname, String pname, int ptRemain, Date due_Date, String gender, String phoneNum, String lockNum, Date reg_Date) {
        this.mem_Id = mem_Id;
        this.mname = mname;
        this.pname = pname;
        this.ptRemain = ptRemain;
        this.due_Date = due_Date;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.lockNum = lockNum;
        this.reg_Date = reg_Date;
    }


    public int getMem_Id() {
        return mem_Id;
    }

    public void setMem_Id(int mem_Id) {
        this.mem_Id = mem_Id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getDue_Date() {
        return due_Date;
    }

    public void setDue_Date(Date due_Date) {
        this.due_Date = due_Date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLockNum() {
        return lockNum;
    }

    public void setLockNum(String lockNum) {
        this.lockNum = lockNum;
    }

    public Date getReg_Date() {
        return reg_Date;
    }

    public void setReg_Date(Date reg_Date) {
        this.reg_Date = reg_Date;
    }

    public int getPtRemain() {
        return ptRemain;
    }

    public void setPtRemain(int ptRemain) {
        this.ptRemain = ptRemain;
    }
}
