package com.example.bavaria.pojo.classes;

public class Header2 {

        final String COTATION = "\"";

        public String dateTimeIssued;

        public String receiptNumber;

        public String uuid;

        public String previousUUID;

        public String referenceOldUUID;

        public String referenceUUID;

        public String currency="EGP";

        public double exchangeRate= 0.0;

        public String sOrderNameCode="";

        public String orderdeliveryMode= "FC";

        public double grossWeight= 0.0;

        public double netWeight= 0.0;

        public Header2(String dateTimeIssued, String receiptNumber, String uuid, String previousUUID, String referenceOldUUID,String referenceUUID) {
            this.dateTimeIssued = dateTimeIssued;
            this.receiptNumber = receiptNumber;
            this.uuid = uuid;
            this.referenceUUID = referenceUUID;
            this.previousUUID = previousUUID;
            this.referenceOldUUID = referenceOldUUID;
        }

        public String getString(){
            String result=COTATION+"DATETIMEISSUED"+COTATION+COTATION+dateTimeIssued+COTATION;
            result+=COTATION+"RECEIPTNUMBER"+COTATION+COTATION+receiptNumber+COTATION;
            result+=COTATION+"UUID"+COTATION+COTATION+uuid+COTATION;
            result+=COTATION+"PREVIOUSUUID"+COTATION+COTATION+previousUUID+COTATION;
            result+=COTATION+"REFERENCEOLDUUID"+COTATION+COTATION+referenceOldUUID+COTATION;
            result+=COTATION+"CURRENCY"+COTATION+COTATION+currency+COTATION;
            result+=COTATION+"EXCHANGERATE"+COTATION+COTATION+exchangeRate+COTATION;
            result+=COTATION+"SORDERNAMECODE"+COTATION+COTATION+sOrderNameCode+COTATION;
            result+=COTATION+"ORDERDELIVERYMODE"+COTATION+COTATION+orderdeliveryMode+COTATION;
            result+=COTATION+"GROSSWEIGHT"+COTATION+COTATION+grossWeight+COTATION;
            result+=COTATION+"NETWEIGHT"+COTATION+COTATION+netWeight+COTATION;
            return  result;
        }
    }

