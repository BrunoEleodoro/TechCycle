package me.brunoeleodoro.techcycle.models;

import com.google.android.gms.maps.model.LatLng;

public class Route {
    private LatLng pointA;
    private LatLng pointB;

    public Route(LatLng pointA, LatLng pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Route() {

    }

    public LatLng getPointA() {
        return pointA;
    }

    public void setPointA(LatLng pointA) {
        this.pointA = pointA;
    }

    public LatLng getPointB() {
        return pointB;
    }

    public void setPointB(LatLng pointB) {
        this.pointB = pointB;
    }
}
