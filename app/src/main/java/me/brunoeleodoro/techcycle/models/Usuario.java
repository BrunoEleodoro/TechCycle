package me.brunoeleodoro.techcycle.models;

import com.google.android.gms.maps.model.LatLng;

public class Usuario {


    private static LatLng pointA;
    private static LatLng pointB;
    private static Double orcamento;


    public static LatLng getPointA() {
        return pointA;
    }

    public static void setPointA(LatLng pointA) {
        Usuario.pointA = pointA;
    }

    public static LatLng getPointB() {
        return pointB;
    }

    public static void setPointB(LatLng pointB) {
        Usuario.pointB = pointB;
    }

    public static Double getOrcamento() {
        return orcamento;
    }

    public static void setOrcamento(Double orcamento) {
        Usuario.orcamento = orcamento;
    }
}
