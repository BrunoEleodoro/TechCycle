package me.brunoeleodoro.techcycle.time_money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import me.brunoeleodoro.techcycle.R;
import me.brunoeleodoro.techcycle.models.MinhaRota;
import me.brunoeleodoro.techcycle.select_points.models.Rota;

import android.os.Bundle;

import com.xw.repo.BubbleSeekBar;

public class TimeMoneyActivity extends AppCompatActivity {

    BubbleSeekBar bubbleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_money);
        bubbleSeekBar = findViewById(R.id.bubbleSeekBar);

        double maior_valor = 0;
        int i = 0;
        while(i < MinhaRota.getRotas().size())
        {
            Rota rota = MinhaRota.getRotas().get(i);
            double preco_total = Double.parseDouble(rota.getPreco_total());
            if(maior_valor < preco_total)
            {
                maior_valor = preco_total;
            }
            i++;
        }

        bubbleSeekBar.getConfigBuilder()
                .min(0)
                .max((float) maior_valor)
                .progress(0)
                .sectionCount(MinhaRota.getRotas().size())
                .build();
    }
}
