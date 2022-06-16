package com.Generator.server.domain;

public class CoordInfo {

    private float Clat;
    private float Clong;

    private String orientation = "";

    public CoordInfo() {
    }

    public CoordInfo(float coordX,float coordY) {
        this.Clat=coordX;
        this.Clong=coordY;
    }

    public float getClat() {
        return Clat;
    }

    public void setClat(float Clat) {
        this.Clat = Clat;
    }

    public float getClong() {
        return Clong;
    }

    public void setClong(float Clong) {
        this.Clong = Clong;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

}
