package com.example.boat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn_begin;
//    private Button mBtn_simulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        mBtn_begin = findViewById(R.id.btn_begin);
        mBtn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BeginActivity.class);
                startActivity(intent);
            }
        });
//        mBtn_simulation = findViewById(R.id.btn_simulation);
//        mBtn_simulation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SimulationActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
