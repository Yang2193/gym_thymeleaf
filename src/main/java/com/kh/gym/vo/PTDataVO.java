package com.kh.gym.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PTDataVO {
    private int rowNo;
    private int mem_ID;
    private int trainer_ID;
    private String mName;
    private String tName;
    private String ptDate;
    private int ptRemain;

    public PTDataVO(int mem_ID, int trainer_ID, String ptDate) {
        this.mem_ID = mem_ID;
        this.trainer_ID = trainer_ID;
        this.ptDate = ptDate;
    }
}
