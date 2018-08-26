package me.brunoeleodoro.techcycle.main;

import java.util.List;

import me.brunoeleodoro.techcycle.models.Directions;

public interface MainView {
    void error(String msg);
    void setListDirections(List<Directions> direcoes);
}
