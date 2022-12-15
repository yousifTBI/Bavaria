package com.example.bavaria.pojo.models;

import ir.mirrajabi.searchdialog.core.Searchable;

public class BranchModel implements Searchable {
    public int State;
    public String Message;
    public String branchID;
    public String Name;





    @Override
    public String getTitle() {
        return Name;
    }
    public String getID() {
        return branchID;
    }
}
