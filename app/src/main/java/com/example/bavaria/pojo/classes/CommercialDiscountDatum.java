package com.example.bavaria.pojo.classes;


public class CommercialDiscountDatum {


    public double amount= 0.0;


    public String description="itmdiscount";

    final String COTATION = "\"";

    public String getString(){
        String result=COTATION+"COMMERCIALDISCOUNTDATA"+COTATION+COTATION+"AMOUNT"+COTATION+COTATION+amount+COTATION;
        result+=COTATION+"DESCRIPTION"+COTATION+COTATION+description+COTATION;
        return  result;
    }
}
