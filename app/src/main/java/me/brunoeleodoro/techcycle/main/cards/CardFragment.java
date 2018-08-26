package me.brunoeleodoro.techcycle.main.cards;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import me.brunoeleodoro.techcycle.R;
import me.brunoeleodoro.techcycle.select_points.models.Rota;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class CardFragment extends Fragment {

    ImageView img;
    TextView txt_tempo;
    TextView txt_valor;
    CardView cardView;
    Rota rota;
    TextView txt_nome;

    public CardFragment() {
        // Required empty public constructor
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        cardView = rootView.findViewById(R.id.cardView);
        img = rootView.findViewById(R.id.img);
        txt_tempo = rootView.findViewById(R.id.txt_tempo);
        txt_valor = rootView.findViewById(R.id.txt_valor);
        txt_nome = rootView.findViewById(R.id.txt_nome);


        int tempo = Integer.parseInt(rota.getTempo_total());
        double tempo_certo = (tempo - Math.ceil(tempo / 60)) * 60;

        txt_nome.setText(rota.getTipo_rota().toUpperCase());
        txt_tempo.setText(Math.ceil((tempo / 60 )) + "h e "+tempo_certo + " minutos");
        txt_valor.setText("R$ "+rota.getPreco_total());


        return rootView;
    }

    public void elevate()
    {
        cardView.setCardElevation(2f);
    }
    public void reduce()
    {
        cardView.setCardElevation(15f);
    }

}
