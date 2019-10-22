package com.example.skusamzas.model;

public class Recipes {


    private String servings;
    private String time;


    public Recipes(String servings, String time) {

        this.servings = servings;
        this.time = time;

    }



    public String getServings() {
        return servings;
    }

    public String getTime() {
        return time;
    }


}
