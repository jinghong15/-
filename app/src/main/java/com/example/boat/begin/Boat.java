package com.example.boat.begin;


import com.example.boat.elude.Elude;

public class Boat {

    //石油浓度
    private Double concentration;
    //行驶速度
    private Double speed;

    //起始经纬度
    private Double position_x;
    private Double position_y;
    //经度
    private Double longitude;
    //维度
    private Double latitude;
    //避障
    private Elude elude;
    //时间
    private Double time;
    //方向
    private int direction;
    //电量
    private int electric;
    //离岸距离
    private Double distance;
    public Double getSpeed() {
        return speed;
    }


    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Boat(){
        speed = 0.0;
        direction = 0;
        concentration = 1.0;
        electric = 100;
        position_x = position_y = 0.0;
        distance = 0.0;
    }
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Elude getElude() {
        return elude;
    }

    public void setElude(Elude elude) {
        this.elude = elude;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Double getConcentration() {
        return concentration;
    }

    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public int getElectric() {
        return electric;
    }

    public void setElectric(int electric) {
        this.electric = electric;
    }

    public Double getPosition_x() {
        return position_x;
    }

    public void setPosition_x(Double position_x) {
        this.position_x = position_x;
    }

    public Double getPosition_y() {
        return position_y;
    }

    public void setPosition_y(Double position_y) {
        this.position_y = position_y;
    }
}
