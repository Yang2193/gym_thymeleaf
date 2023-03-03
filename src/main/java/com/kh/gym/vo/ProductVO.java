package com.kh.gym.vo;

public class ProductVO {
    private String pName;
    private int price;
    private int term;

    public ProductVO(String pName, int price, int term) {
        this.pName = pName;
        this.price = price;
        this.term = term;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
