package com.seordmoret.app.moveitdrawit.models;


public class PathPoints {
    private float xPercentage;
    private float yPercentage;

    public PathPoints(float xPercentage, float yPercentage) {
        this.xPercentage = xPercentage;
        this.yPercentage = yPercentage;
    }

    public float getxPercentage() {
        return xPercentage;
    }

    public float getyPercentage() {
        return yPercentage;
    }
}
