package com.impv.comm.pojo;

/**
 * @Description: Created by Admin on 2017/3/10.
 */
public enum FileSaveType {

    product("product"),
    headimg("headimg"),
    otherfile("otherfile"),
    email("email"),
    report("report"),
    recipe("recipe");

    public final String value;

    private FileSaveType(String value){
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
