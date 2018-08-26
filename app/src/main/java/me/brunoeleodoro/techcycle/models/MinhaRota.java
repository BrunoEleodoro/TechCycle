package me.brunoeleodoro.techcycle.models;

import java.util.List;

import me.brunoeleodoro.techcycle.select_points.models.Rota;

public class MinhaRota {

    private static List<Rota> rotas;

    public static List<Rota> getRotas() {
        return rotas;
    }

    public static void setRotas(List<Rota> rotas) {
        MinhaRota.rotas = rotas;
    }
}
