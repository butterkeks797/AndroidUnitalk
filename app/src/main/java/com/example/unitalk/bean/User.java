package com.example.unitalk.bean;

public class User implements Comparable<User> {
    int id;
    String email;
    String region;
    String targetLanguage1;
    int targetScore1;
    String targetLanguage2;
    int targetScore2;
    String targetLanguage3;
    int targetScore3;
    String intention;
    String major;
    String nationality;
    String school;
    String userName;
    String gender;
    String grade;
    String studentNumber;
    String motherTongue;

    // for friend matching

    String partnerList;


    // for friendlist

    String chatDate;
    String briefIntro;
    String dot;
    int iconId;
    private String pinyin; // 姓名对应的拼音
    private String firstLetter; // 拼音的首字母
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTargetLanguage1() {
        return targetLanguage1;
    }

    public void setTargetLanguage1(String targetLanguage1) {
        this.targetLanguage1 = targetLanguage1;
    }

    public String getTargetLanguage2() {
        return targetLanguage2;
    }

    public void setTargetLanguage2(String targetLanguage2) {
        this.targetLanguage2 = targetLanguage2;
    }

    public String getTargetLanguage3() {
        return targetLanguage3;
    }

    public void setTargetLanguage3(String targetLanguage3) {
        this.targetLanguage3 = targetLanguage3;
    }

    public int getTargetScore1() {
        return targetScore1;
    }

    public void setTargetScore1(int targetScore1) {
        this.targetScore1 = targetScore1;
    }

    public int getTargetScore2() {
        return targetScore2;
    }

    public void setTargetScore2(int targetScore2) {
        this.targetScore2 = targetScore2;
    }

    public int getTargetScore3() {
        return targetScore3;
    }

    public void setTargetScore3(int targetScore3) {
        this.targetScore3 = targetScore3;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(String partnerList) {
        this.partnerList = partnerList;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getChatDate() {
        return chatDate;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public User() {

    }
    public User(String name, String chatDate, String briefIntro, String pinyin) {
        this.userName = name;
        this.chatDate = chatDate;
        this.briefIntro = briefIntro;
        this.pinyin = pinyin; // 根据姓名获取拼音
        firstLetter = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
        if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
        }
    }
    public User(String name, String motherTongue, String targetLanguage1, String targetLanguage2, String targetLanguage3 , String intention) {
        this.userName = name;
        this.motherTongue = motherTongue;
        this.targetLanguage1 = targetLanguage1;
        this.targetLanguage2 = targetLanguage2;
        this.targetLanguage3 = targetLanguage3;
        this.intention = intention;

    }
    @Override
    public int compareTo(User another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")) {
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }
}

