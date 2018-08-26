package me.brunoeleodoro.techcycle.select_points;

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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.brunoeleodoro.techcycle.models.MinhaRota;
import me.brunoeleodoro.techcycle.models.RotaEscolhida;
import me.brunoeleodoro.techcycle.models.Route;
import me.brunoeleodoro.techcycle.select_points.models.Rota;
import me.brunoeleodoro.techcycle.select_points.models.Step;

public class SelectPointsInteractorImpl implements SelectPointsInteractor {
    SelectPointsPresenter presenter;

    public SelectPointsInteractorImpl(SelectPointsPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void getRotasLista(Route route) {
        DecimalFormat numberFormat = new DecimalFormat("#.00000");

        String origin = numberFormat.format(route.getPointA().latitude).replace(",",".") + "," + numberFormat.format(route.getPointA().longitude).replace(",",".");
        String destiny = numberFormat.format(route.getPointB().latitude).replace(",",".") + "," + numberFormat.format(route.getPointB().longitude).replace(",",".");
        String url = "https://tuba.work/directions?origin="+origin+"&destination="+destiny;
        Log.i("script","url= "+url);
        RequestQueue queue = Volley.newRequestQueue(presenter.getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("script", "respoonse="+response);
                try
                {
                    List<Rota> rotas = new ArrayList<>();
                    JSONArray array_pai = new JSONArray(response);
                    int i = 0;
                    while(i < array_pai.length())
                    {
                        JSONObject object_filho = array_pai.getJSONObject(i);

                        Rota rota = new Rota();
                        rota.setPreco_total(object_filho.optString("preco_total"));
                        rota.setTempo_total(object_filho.optString("tempo_total"));
                        rota.setTipo_rota(object_filho.optString("tipo_rota"));


                        JSONArray array_steps = object_filho.getJSONArray("array");
                        List<Step> steps = new ArrayList<>();
                        int k = 0;

                        while(k < array_steps.length())
                        {
                            JSONObject object_step = array_steps.getJSONObject(k);
                            Step step = new Step();
                            step.setTipo(object_step.optString("tipo"));
                            step.setCor(object_step.optString("cor"));
                            //pegando as polylines
                            List<LatLng> lats_lngs = new ArrayList<>();
                            int j = 0;
                            JSONArray polyline = object_step.getJSONArray("polyline");
                            while(j < polyline.length())
                            {
                                JSONObject object_polyline = polyline.getJSONObject(j);
                                LatLng latLng = new LatLng(
                                        Double.parseDouble(object_polyline.optString("lat")),
                                        Double.parseDouble(object_polyline.optString("lng"))
                                );
                                lats_lngs.add(latLng);
                                j++;
                            }
                            step.setLats_lngs(lats_lngs);
                            step.setDistance("");
                            step.setDuration("");
                            try
                            {
                                step.setDistance(object_step.optJSONObject("distance").optString("text"));
                                step.setDuration(object_step.optJSONObject("duration").optString("text"));
                            }
                            catch (Exception e)
                            {

                            }

                            steps.add(step);

                            k++;
                        }
                        rota.setSteps(steps);
                        rotas.add(rota);

                        i++;
                    }

                    presenter.setRotas(rotas);
                    MinhaRota.setRotas(rotas);
                }
                catch (Exception e)
                {
                    presenter.error("erro json= "+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                presenter.error("volley error="+error);
            }
        });
        queue.add(request);
    }
}
