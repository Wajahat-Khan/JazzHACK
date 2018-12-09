package com.example.wajahat.jazzhackathon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    double th1,th2,hbt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        th1=getIntent().getDoubleExtra("th1",33.0);
        th2=getIntent().getDoubleExtra("th2",34.0);
        hbt=getIntent().getDoubleExtra("hbt",78);

        

    }
}
