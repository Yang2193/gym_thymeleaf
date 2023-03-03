package com.kh.gym.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
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

    public MemberInfoVO(){}

}
