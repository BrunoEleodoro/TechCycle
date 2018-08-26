package me.brunoeleodoro.techcycle.select_points;

import android.content.Context;

import java.util.List;

import me.brunoeleodoro.techcycle.models.Route;
import me.brunoeleodoro.techcycle.select_points.models.Point;
import me.brunoeleodoro.techcycle.select_points.models.Rota;

public class SelectPointsPresenterImpl implements SelectPointsPresenter{
    SelectPointsView view;
    SelectPointsInteractor interactor;

    public SelectPointsPresenterImpl() {
        interactor = new SelectPointsInteractorImpl(this);
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void setView(SelectPointsView view) {
        this.view = view;
    }

    @Override
    public void getRotasLista(Route route) {
        interactor.getRotasLista(route);
    }

    @Override
    public void click(Point point) {
        view.click(point);
    }

    @Override
    public void error(String msg) {
        view.error(msg);
    }

    @Override
    public void setRotas(List<Rota> rotas) {
        view.setRotas(rotas);
    }
}
