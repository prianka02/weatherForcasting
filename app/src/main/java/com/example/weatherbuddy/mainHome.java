package com.example.weatherbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class mainHome extends AppCompatActivity {
    TextView logoutBtn;
    TextView temparatureValue, wthertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        logoutBtn = findViewById(R.id.logout);
        temparatureValue = findViewById(R.id.Temperature);
        wthertype = findViewById(R.id.weatherType);
        forcastWeather f = new forcastWeather();
        temparatureValue.setText((CharSequence) f.temparatureValue);
        wthertype.setText((CharSequence) f.wthertype);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainHome.this, forcastWeather.class);
                startActivity(intent);
            }
        });
    }
}