package com.chitingwang.listviewcustomadapter;

/**
 * Created by ettbe on 2/21/2018.
 */

public class Planet {
    public int logo;
    public String name;
    public String foodtype;
    public int cost;
    public String address;

    public Planet() {
        super();
    }

    public Planet(int logo, String name, String foodtype, int cost, String address) {
        super();
        this.logo = logo;
        this.name = name;
        this.foodtype = foodtype;
        this.cost = cost;
        this.address = address;
    }
}
