package me.brunoeleodoro.techcycle.main;

import android.graphics.Path;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import me.brunoeleodoro.techcycle.models.Directions;
import me.brunoeleodoro.techcycle.models.HoraChegada;
import me.brunoeleodoro.techcycle.models.HoraSaida;
import me.brunoeleodoro.techcycle.models.Route;

public class MainInteractorImpl implements MainInteractor {
    MainPresenter presenter;

    public MainInteractorImpl(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void findRoute(Route route) {
        RequestQueue queue = Volley.newRequestQueue(presenter.getContext());
        String origin = route.getPointA().latitude + "," + route.getPointA().longitude;
        String destiny = route.getPointB().latitude + "," + route.getPointB().longitude;

        StringRequest request = new StringRequest(Request.Method.GET, "https://tuba.work/directions?origin="+origin+"&destination="+destiny, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONArray array = new JSONArray(response);
                    List<Directions> direcoes = new ArrayList<>();
                    int i = 0;
                    while(i < array.length())
                    {
                        JSONObject object = array.getJSONObject(i);
                        JSONObject object_hora_saida = object.getJSONObject("hora_saida");
                        JSONObject object_hora_chegada = object.getJSONObject("hora_chegada");

                        HoraSaida horaSaida = new HoraSaida();
                        horaSaida.setText(object_hora_saida.optString("text"));
                        horaSaida.setTime_zone(object_hora_saida.optString("time_zone"));
                        horaSaida.setValue(object_hora_saida.optString("value"));

                        HoraChegada horaChegada = new HoraChegada();
                        horaChegada.setText(object_hora_chegada.optString("text"));
                        horaChegada.setTime_zone(object_hora_chegada.optString("time_zone"));
                        horaChegada.setValue(object_hora_chegada.optString("value"));

                        Directions direction = new Directions(
                            object.optString("tipo"),
                            object.optString("nome"),
                            object.optString("numero"),
                            object.optString("cor"),
                            object.optString("paradas"),
                            object.optString("polyline"),
                                    horaSaida,
                                    horaChegada
                            );

                        direcoes.add(direction);

                        i++;
                    }
                }
                catch (Exception e)
                {
                    presenter.error("erro json="+e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.error("error = "+error);
            }
        });
        queue.add(request);
    }
}
