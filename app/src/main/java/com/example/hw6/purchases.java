package com.example.hw6;

import com.google.gson.annotations.SerializedName;

public class purchases {

    //@SerializedName("")
     String id;
    //@SerializedName("")
     String merchId;
    //@SerializedName("")
     String medium;
    @SerializedName("purchase_date")
     String date;
    //@SerializedName("")
     double amount;
    //@SerializedName("")
     String status;
    //@SerializedName("")
     String desc;
    //@SerializedName("")
     String type;
    //@SerializedName("")
     String payerId;

    public purchases(String id, String merchId, String medium, String date, int amount, String status, String desc, String type, String payerId){
        this.id=id;
        this.merchId=merchId;
        this.medium=medium;
        this.date=date;
        this.amount=amount;
        this.status=status;
        this.desc=desc;
        this.type=type;
        this.payerId=payerId;
    }
}
