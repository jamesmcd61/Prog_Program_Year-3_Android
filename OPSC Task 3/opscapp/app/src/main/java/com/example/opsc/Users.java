package com.example.opsc;
//user classs informantion
public class Users {
    private String name;
    private String surname;
    private String password;
    private String age;
    private String weight;
    private String height;
    private String goaldis;
    private String email;
    private String weightgoal;
    private String caloriegoal;
    public Users(){

    }
    //constuctor
    public Users( String name,String surname,String password,String age,String weight,String height,String goaldis,String email,String weightgoal,String caloriegoal){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goaldis = goaldis;
        this.email = email;
        this.weightgoal = weightgoal;
        this.caloriegoal = caloriegoal;
    }
    //getters and setters
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getGoaldis() {
        return goaldis;
    }
    public String getEmail(){
        return email;
    }

    public String getWeightgoal()
    {
        return weightgoal;
    }
    public String getCaloriegoal()
    {
        return caloriegoal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setGoaldis(String goaldis) {
        this.goaldis = goaldis;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setWeightgoal(String weightgoal){
        this.weightgoal = weightgoal;
    }
    public void setCaloriegoal(String caloriegoal){
        this.caloriegoal = caloriegoal;
    }
}

