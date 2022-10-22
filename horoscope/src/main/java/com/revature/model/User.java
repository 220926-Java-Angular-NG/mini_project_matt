package com.revature.model;

public class User {
    String first_name;
    String last_name;
    String email;
    String password;
    int id;
    String birthdate;

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", birthdate='" + birthdate + '\'' +
                ", wzodiac_Display='" + wzodiac_Display + '\'' +
                ", czodiac_Display='" + czodiac_Display + '\'' +
                ", wzodiac=" + wzodiac +
                ", czodiac=" + czodiac +
                ", mood='" + mood + '\'' +
                ", czDescription='" + czDescription + '\'' +
                '}';
    }

    String wzodiac_Display;
    String czodiac_Display;
    int wzodiac;
    int czodiac;
    String mood;
    String czDescription;

    public String getCzDescription() {
        return czDescription;
    }

    public void setCzDescription(String czDescription) {
        this.czDescription = czDescription;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String first_name, String last_name, String email, String password, int id, String birthdate,
                int wzodiac, int czodiac, String mood) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.birthdate = birthdate;
        this.wzodiac = wzodiac;
        this.czodiac = czodiac;
        this.mood = mood;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getWzodiac_Display() {
        return wzodiac_Display;
    }

    public void setWzodiac_Display(String wzodiac_Display) {
        this.wzodiac_Display = wzodiac_Display;
    }

    public String getCzodiac_Display() {
        return czodiac_Display;
    }

    public void setCzodiac_Display(String czodiac_Display) {
        this.czodiac_Display = czodiac_Display;
    }

    public int getWzodiac() {
        return wzodiac;
    }

    public void setWzodiac(int wzodiac) {
        this.wzodiac = wzodiac;
    }

    public int getCzodiac() {
        return czodiac;
    }

    public void setCzodiac(int czodiac) {
        this.czodiac = czodiac;
    }
}
