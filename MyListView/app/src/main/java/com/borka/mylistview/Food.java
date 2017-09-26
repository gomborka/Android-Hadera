package com.borka.mylistview;

/**
 * Created by Comp14 on 24/08/2017.
 */

public class Food {
    private String name;
    private int imageID;

    public Food(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}

