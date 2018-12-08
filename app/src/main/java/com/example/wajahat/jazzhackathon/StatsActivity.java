package com.example.wajahat.jazzhackathon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class StatsActivity extends AppCompatActivity {
    int age, bp_s, bp_d;
    String race;
    Boolean heart_disease;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        age=getIntent().getIntExtra("age",20);
        bp_s=getIntent().getIntExtra("bp_s",120);
        bp_d=getIntent().getIntExtra("bp_d",80);
        race=getIntent().getStringExtra("race");
        heart_disease=getIntent().getBooleanExtra("heart_disease", false);
/*
        Toast.makeText(this, Integer.toString(age), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.toString(bp_s), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.toString(bp_d), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, race, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Boolean.toString(heart_disease), Toast.LENGTH_SHORT).show();
   */
    }
}
