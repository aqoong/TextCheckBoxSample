package com.aqoong.lib.textcheckboxsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aqoong.lib.textcheckbox.TextCheckBox;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextCheckBox textCheckBox = findViewById(R.id.test);
        textCheckBox.setMode(TextCheckBox.MODE_TEXT);
        textCheckBox.setText("12");

        findViewById(R.id.layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textCheckBox.isChecked()){
                    count++;
                }
                textCheckBox.setCheck(!textCheckBox.isChecked(), ""+count);
            }
        });



//        textCheckBox.setColor(ContextCompat.getColor(getApplicationContext(),R.color.textcheckbox_checked_color),
//                                ContextCompat.getColor(getApplicationContext(), R.color.textcheckbox_default_color));
    }
}
