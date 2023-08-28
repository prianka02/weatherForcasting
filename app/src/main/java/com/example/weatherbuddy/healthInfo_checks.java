package com.example.weatherbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class healthInfo_checks extends AppCompatActivity {
    TextView  submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info_checks);
        submitBtn = findViewById(R.id.submit);


//        View checkboxLayout = LayoutInflater.from(this).inflate(R.layout.activity_health_info_checks, null);
//        CheckBox checkbox1 = checkboxLayout.findViewById(R.id.checkbox1);
//        CheckBox checkbox2 = checkboxLayout.findViewById(R.id.checkbox2);
//        CheckBox checkBox3 = checkboxLayout.findViewById(R.id.checkbox3);
//        CheckBox checkBox4 = checkboxLayout.findViewById(R.id.checkbox4);
//        CheckBox checkBox5 = checkboxLayout.findViewById(R.id.checkbox5);
//        CheckBox checkBox6 = checkboxLayout.findViewById(R.id.checkbox6);




        // Create a custom toast
        final Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
     //   toast.setView(checkboxLayout);

        // Show the toast when any checkbox is clicked
//        View.OnClickListener checkboxClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Check the state of each checkbox and show the toast accordingly
//                if ((checkbox1.isChecked() || checkbox2.isChecked()) && (checkBox3.isChecked() || checkBox4.isChecked()) && (checkBox5.isChecked() || checkBox6.isChecked())) {
//                    toast.show();
//                } else {
//                    toast.cancel();
//                }
//            }
//        };

//        checkbox1.setOnClickListener(checkboxClickListener);
//        checkbox2.setOnClickListener(checkboxClickListener);
//        checkBox3.setOnClickListener(checkboxClickListener);
//        checkBox4.setOnClickListener(checkboxClickListener);
//        checkBox5.setOnClickListener(checkboxClickListener);
//        checkBox6.setOnClickListener(checkboxClickListener);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(healthInfo_checks.this, mainHome.class);
                startActivity(intent);
            }
        });


    }

}