package com.example.unitalk.bean;

public class MatchResult {
    int id;
    String username;
    String gender;
    String school;
    String mother_tongue;
    String target_language1;
    String target_language2;
    String target_language3;
    String intention;

    public MatchResult(int id) {
        this.id = id;
    }

    public MatchResult(int id, String username, String gender, String school, String mother_tongue, String target_language1, String target_language2, String target_language3, String intention) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.school = school;
        this.mother_tongue = mother_tongue;
        this.target_language1 = target_language1;
        this.target_language2 = target_language2;
        this.target_language3 = target_language3;
        this.intention = intention;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget_language1() {
        return target_language1;
    }

    public void setTarget_language1(String target_language1) {
        this.target_language1 = target_language1;
    }

    public String getTarget_language2() {
        return target_language2;
    }

    public void setTarget_language2(String target_language2) {
        this.target_language2 = target_language2;
    }

    public String getTarget_language3() {
        return target_language3;
    }

    public void setTarget_language3(String target_language3) {
        this.target_language3 = target_language3;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMother_tongue() {
        return mother_tongue;
    }

    public void setMother_tongue(String mother_tongue) {
        this.mother_tongue = mother_tongue;
    }
}
