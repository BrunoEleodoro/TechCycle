package me.brunoeleodoro.techcycle.select_points;

import android.content.Context;

public interface SelectPointsPresenter extends SelectPointsView, SelectPointsInteractor {

    Context getContext();
    void setView(SelectPointsView view);
}
