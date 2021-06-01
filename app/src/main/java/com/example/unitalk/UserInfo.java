package com.example.unitalk;

public class UserInfo {
    String nickname;
    int score;
    int id;
    String partnerList;

    public String getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(String partnerList) {
        this.partnerList = partnerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
