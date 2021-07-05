package com.wordpress.naispace.myfirstapp;

import android.support.annotation.NonNull;

import java.util.Locale;

public class ClosePair {

    private Double closeNumber;
    private String closeTxts;

    public ClosePair (Double num, String txt){
        //Constructor
        this.closeNumber = num;
        this.closeTxts = txt;
    }

    ClosePair (){
        //Constructor
        this.closeNumber = 0.0;
        this.closeTxts = "";
    }

    void setClosePair (Double val, String txt){
        this.closeNumber = val;
        this.closeTxts = txt;
    }

    Double getNum (){
        return this.closeNumber;
    }

    String getTxt (){
        return this.closeTxts;
    }

    @NonNull
    public String toString(){
        return this.closeNumber.toString() + " , " + this.closeTxts;
    }

    String toString(Double myDiam){
        Double diff = this.closeNumber - myDiam;
        String plus = "";

        if (diff >= 0.0)
            plus = "+";

        return String.format(Locale.US, "%s (%.4f, %s%.4f)",this.closeTxts, this.closeNumber, plus, diff);

        //return this.closeTxts + " (" + this.closeNumber.toString() + ", " + String.format("{0:0.0000}", diff) + ")";
    }

}
