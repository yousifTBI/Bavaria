package com.example.bavaria.pojo.classes;

public class DocumentType2 {
    final String COTATION = "\"";

    public String receiptType="R";

    //        "TypeVersion": "1.1",
    public String typeVersion="1.2";




    public String getString(){
        String result=COTATION+"RECEIPTTYPE"+COTATION+COTATION+receiptType+COTATION;
        result+=COTATION+"TYPEVERSION"+COTATION+COTATION+typeVersion+COTATION;
        return  result;
    }

    public String getTypeVersion() {
        return typeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }
}


