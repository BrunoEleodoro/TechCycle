package me.brunoeleodoro.techcycle.models;

import com.google.android.gms.maps.model.LatLng;

public class RotaEscolhida {
    private static LatLng pointA;
    private static LatLng pointB;

    public static LatLng getPointA() {
        return pointA;
    }

    public static void setPointA(LatLng pointA) {
        RotaEscolhida.pointA = pointA;
    }

    public static LatLng getPointB() {
        return pointB;
    }

    public static void setPointB(LatLng pointB) {
        RotaEscolhida.pointB = pointB;
    }
}
