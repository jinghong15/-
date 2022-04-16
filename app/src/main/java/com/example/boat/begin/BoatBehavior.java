package com.example.boat.begin;


import com.example.boat.elude.Elude;

public class BoatBehavior {
    private Boat boat;
    private Elude elude;

    public final static Double v1 = 0.25;
    public final static Double v2 = 0.5;
    public final static Double v3 = 0.75;
    public final static Double v4 = 1.00;
    public final static Double v5 = 1.25;

    //private Double distance;

    public BoatBehavior(){
        boat = new Boat();
        elude = new Elude(boat.getDirection());
    }
    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Elude getElude() {
        return elude;
    }

    public void setElude(Elude elude) {
        this.elude = elude;
    }


}
