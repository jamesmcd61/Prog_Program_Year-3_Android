package com.example.opsc;
//class for the diet information
public class DietInfo {
    //creating of variables
    private int fat;
    private int carbs;
    private int weight;
    private int protein;
    private  String imageuri;
    private String username;

    public DietInfo(){

    }
    //constructer
    public  DietInfo(int fat, int carbs, int weight, int protein, String imageuri,String username) {
        this.fat = fat;
        this.carbs = carbs;
        this.weight = weight;
        this.protein = protein;
        this.imageuri = imageuri;
        this.username = username;
    }
    //gets and sets
    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getWeight() {
        return weight;
    }

    public int getProtein() {
        return protein;
    }

    public String getImageuri(){
        return imageuri;
    }

    public String getUsername(){
        return username;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setImageuri(String imageuri){
        this.imageuri = imageuri;
    }

    public void setUsername(String username){
        this.username = username;
    }
}