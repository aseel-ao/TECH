package com.example.tech_shop.modules;

public class UserModule {
    String Name;
    String Email;
    String Password;
    String Profile_img;

    public UserModule() {
    }

    public UserModule(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    public String getProfile_img() {
        return Profile_img;
    }

    public void setProfile_img(String profile_img) {
        Profile_img = profile_img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
