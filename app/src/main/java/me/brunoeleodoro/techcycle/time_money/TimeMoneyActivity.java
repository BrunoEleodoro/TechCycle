package me.brunoeleodoro.techcycle.time_money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import me.brunoeleodoro.techcycle.R;

import android.os.Bundle;

import com.xw.repo.BubbleSeekBar;

public class TimeMoneyActivity extends AppCompatActivity {

    BubbleSeekBar bubbleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_money);
        bubbleSeekBar = findViewById(R.id.bubbleSeekBar);

        bubbleSeekBar.getConfigBuilder()
                .min(0)
                .max(50)
                .progress(20)
                .sectionCount(5)
                .trackColor(ContextCompat.getColor(this, R.color.color_gray))
                .secondTrackColor(ContextCompat.getColor(this, R.color.color_blue))
                .thumbColor(ContextCompat.getColor(this, R.color.color_blue))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .sectionTextSize(18)
                .showThumbText()
                .thumbTextColor(ContextCompat.getColor(this, R.color.color_red))
                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(this, R.color.color_green))
                .bubbleTextSize(18)
                .showSectionMark()
                .seekBySection()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
    }
}
