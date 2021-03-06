package com.ppandroid.chartdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ppandroid.app.home.FG_OverView;
import com.ppandroid.app.home.FG_SecurityCenter;
import com.ppandroid.chartdemo.base.AC_ContentFG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AC_ContentFG.createIntent(MainActivity.this, FG_SecurityCenter.class.getName()));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AC_ContentFG.createIntent(MainActivity.this, FG_OverView.class.getName()));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AC_ContentFG.createIntent(MainActivity.this, FG_Wave.class.getName()));
            }
        });



    }
}
