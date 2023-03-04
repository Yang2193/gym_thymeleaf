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
    private int term;
    private Date due_Date;
    private String gender;
    private String phoneNum;
    private String lockNum;
    private Date reg_Date;


    public MemberInfoVO(){}

}
