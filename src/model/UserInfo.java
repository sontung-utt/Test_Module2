package model;

import java.time.LocalDate;

public class UserInfo {
    private int id;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private LocalDate birth;
    private String email;
    private String facebook;
    private static int idIncrement = 1;

    public UserInfo(){

    }

    public UserInfo(String name, String gender, String phone, String address, LocalDate birth, String email, String facebook){
        this.id = idIncrement;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.email = email;
        this.facebook = facebook;
        idIncrement++;
    }

    public UserInfo(int id, String name, String gender, String phone, String address, LocalDate birth, String email, String facebook){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.birth = birth;
        this.facebook = facebook;
        if (id >= idIncrement){
            idIncrement = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String toString() {
        return "===== THÔNG TIN NGƯỜI DÙNG =====" +
                "\nMã người dùng: " + id +
                "\nTên người dùng: " + name +
                "\nGiới tính: " + gender +
                "\nSố điện thoại: " + phone +
                "\nĐịa chỉ: " + address +
                "\nNgày sinh: " + birth +
                "\nEmail: " + email +
                "\nfacebook: " + facebook;
    }
}
