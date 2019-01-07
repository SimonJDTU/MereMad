package com.johansen.dk.meremad.model;

import java.io.Serializable;
import java.util.ArrayList;

public class foodItem implements Serializable {
    private int dishID;
    private String name;
    private int imageRes;
    private ArrayList<String> ingrediences, howtocook;
    private int counter1=0, counter2=0;

    public foodItem(int dishID, String name, int imageRes, ArrayList<String> options, ArrayList<String> howtocook) {
       this.dishID=dishID;
       this.name=name;
       this.imageRes=imageRes;
       this.ingrediences=new ArrayList<String>(0);
       this.howtocook=new ArrayList<String>(0);
    }

    public foodItem() {
        this.ingrediences=new ArrayList<String>(0);
        this.howtocook=new ArrayList<String>(0);
    }

    public int getID() {
        return dishID;
    }
    public String getName() {
        return name;
    }
    public int getImageRes() {
        return imageRes;
    }
    public ArrayList<String> getIngrediences() {
        return ingrediences;
    }
    public ArrayList<String> getHowtocook() {
        return howtocook;
    }

    public void setID(int id) {
        this.dishID = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImageRes(int res){this.imageRes=res; }
    public void setIngrediences(ArrayList<String> list){this.ingrediences=list;}
    public void setHowtocook(ArrayList<String> list){this.howtocook=list;}

    public void addToIngrediences(String ingredience) {
        ingrediences.add(counter1,ingredience);
        counter1++;
    }
    public void addToHowtocook(String howtocookStep) {
        ingrediences.add(counter2,howtocookStep);
        counter2++;
    }
}
