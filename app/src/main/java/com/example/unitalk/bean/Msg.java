package com.example.unitalk.bean;

public class Msg {

    public static final int TYPE_BLE = 0;
    public static final int TYPE_PHONE = 1;

    private int id;
    private String content;
    private int type;
    private String time;
    public Msg(int id, String content, int type, String time) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.time = time;
    }
    public Msg() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", time='" + time + '\'' +
                '}';
    }

}

