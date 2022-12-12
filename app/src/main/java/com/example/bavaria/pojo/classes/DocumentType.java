package com.example.bavaria.pojo.classes;


public class DocumentType {
    final String COTATION = "\"";

    public String receiptType="S";

    public String typeVersion="1.1";




    public String getString(){
        String result=COTATION+"RECEIPTTYPE"+COTATION+COTATION+receiptType+COTATION;
        result+=COTATION+"TYPEVERSION"+COTATION+COTATION+typeVersion+COTATION;
        return  result;
    }
}
