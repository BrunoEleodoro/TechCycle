package me.brunoeleodoro.techcycle.main;

import android.content.Context;

import me.brunoeleodoro.techcycle.models.Route;

public class MainPresenterImpl implements MainPresenter {

    MainView view;
    MainInteractor interactor;

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void findRoute(Route route) {
        interactor.findRoute(route);
    }

    @Override
    public void error(String msg) {
        view.error(msg);
    }
}
