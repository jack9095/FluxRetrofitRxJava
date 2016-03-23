package com.retrofit.wangfei.flux_retrofit_rxjava.model;

/**
 * Created by fei.wang on 2016/2/22.
 */
public class User {


    /**
     * id : 1
     * name : 王飞
     * password : 123456
     * sex : 男
     * phone : 13795378745
     * registerTime : 2016-03-16 15:03:56
     * headPicture :
     * logoPicture :
     * diarySum : 0
     * pictureSum : 0
     */

    private String id;
    private String name;
    private String password;
    private String sex;
    private String phone;

    public User() {
    }

    public User(String name, String password, String sex, String phone, String registerTime, String headPicture, String logoPicture, String diarySum, String pictureSum) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.registerTime = registerTime;
        this.headPicture = headPicture;
        this.logoPicture = logoPicture;
        this.diarySum = diarySum;
        this.pictureSum = pictureSum;
    }

    private String registerTime;
    private String headPicture;
    private String logoPicture;
    private String diarySum;
    private String pictureSum;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public void setLogoPicture(String logoPicture) {
        this.logoPicture = logoPicture;
    }

    public void setDiarySum(String diarySum) {
        this.diarySum = diarySum;
    }

    public void setPictureSum(String pictureSum) {
        this.pictureSum = pictureSum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public String getLogoPicture() {
        return logoPicture;
    }

    public String getDiarySum() {
        return diarySum;
    }

    public String getPictureSum() {
        return pictureSum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", headPicture='" + headPicture + '\'' +
                ", logoPicture='" + logoPicture + '\'' +
                ", diarySum='" + diarySum + '\'' +
                ", pictureSum='" + pictureSum + '\'' +
                '}';
    }
}
