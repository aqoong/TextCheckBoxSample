package com.aqoong.lib.textcheckboxsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aqoong.lib.textcheckbox.TextCheckBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextCheckBox textCheckBox = findViewById(R.id.test);
        textCheckBox.setMode(TextCheckBox.MODE_TEXT);
        textCheckBox.setText("12");

//        textCheckBox.setColor(ContextCompat.getColor(getApplicationContext(),R.color.textcheckbox_checked_color),
//                                ContextCompat.getColor(getApplicationContext(), R.color.textcheckbox_default_color));
    }
}
