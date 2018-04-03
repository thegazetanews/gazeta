package com.andnet.gazeta.Models;


public class Currency {

    private String curencyName;
    private String curencySale;
    private String curenctBaught;


    public Currency(String curencyName, String curencySale, String curenctBaught) {
        this.curencyName = curencyName;
        this.curencySale = curencySale;
        this.curenctBaught = curenctBaught;
    }



    public String getCurencyName() {
        return curencyName;
    }

    public void setCurencyName(String curencyName) {
        this.curencyName = curencyName;
    }

    public String getCurencySale() {
        return curencySale;
    }

    public void setCurencySale(String curencySale) {
        this.curencySale = curencySale;
    }

    public String getCurenctBaught() {
        return curenctBaught;
    }

    public void setCurenctBaught(String curenctBaught) {
        this.curenctBaught = curenctBaught;
    }
}
