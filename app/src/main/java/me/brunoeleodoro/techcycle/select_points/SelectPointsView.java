package me.brunoeleodoro.techcycle.select_points;

import java.util.List;

import me.brunoeleodoro.techcycle.select_points.models.Point;
import me.brunoeleodoro.techcycle.select_points.models.Rota;

public interface SelectPointsView {
    void click(Point point);
    void error(String msg);
    void setRotas(List<Rota> rotas);
}
