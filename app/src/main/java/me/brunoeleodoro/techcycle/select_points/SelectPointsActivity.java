package me.brunoeleodoro.techcycle.select_points;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.brunoeleodoro.techcycle.R;
import me.brunoeleodoro.techcycle.base.BaseActivity;
import me.brunoeleodoro.techcycle.select_points.adapters.PointsAdapter;
import me.brunoeleodoro.techcycle.select_points.models.Point;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class SelectPointsActivity extends BaseActivity implements SelectPointsView, OnCompleteListener<AutocompletePredictionBufferResponse> {

    private RecyclerView recyclerView;
    private Boolean editing_origin = true;
    private EditText txt_origem;
    private EditText txt_destino;
    private int quant = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_points);

        txt_destino = findViewById(R.id.txt_destino);
        txt_origem = findViewById(R.id.txt_origem);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.getLayoutManager().setAutoMeasureEnabled(true);


        txt_origem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(quant > 2)
                {
                    editing_origin = true;
                    GeoDataClient geoDataClient = Places.getGeoDataClient(SelectPointsActivity.this);
                    Task<AutocompletePredictionBufferResponse> results = geoDataClient.getAutocompletePredictions(txt_origem.getText().toString(), null, null);
                    results.addOnCompleteListener(SelectPointsActivity.this);
                    quant = 0;
                }
                else
                {
                    quant++;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        txt_destino.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(quant > 2)
                {
                    editing_origin = false;
                    GeoDataClient geoDataClient = Places.getGeoDataClient(SelectPointsActivity.this);
                    Task<AutocompletePredictionBufferResponse> results = geoDataClient.getAutocompletePredictions(txt_destino.getText().toString(), null, null);
                    results.addOnCompleteListener(SelectPointsActivity.this);
                    quant = 0;
                }
                else
                {
                    quant++;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onComplete(@NonNull Task<AutocompletePredictionBufferResponse> task) {
        int i = 0;
        List<Point> points = new ArrayList<>();
        try
        {
            while(i < task.getResult().getCount())
            {
                AutocompletePrediction autocompletePredictions = task.getResult().get(i);

                Point point = new Point();
                point.setEndereco(autocompletePredictions.getPrimaryText(null).toString());
                point.setSub(autocompletePredictions.getSecondaryText(null).toString());
                point.setFull_endereco(autocompletePredictions.getFullText(null).toString());
                points.add(point);
                i++;
            }

            recyclerView.setAdapter(new PointsAdapter(points, this, this));

        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void click(Point point) {
        if(editing_origin)
        {
            txt_origem.setText(point.getFull_endereco());
        }
        else
        {
            txt_destino.setText(point.getFull_endereco());
        }
        hideKeyboard();
    }
}
