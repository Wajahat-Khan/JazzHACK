package com.example.wajahat.jazzhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GetInfo extends AppCompatActivity {
        TextView age_text;
        TextView bp_text;
        Spinner race_spinner;
        CheckBox h_yes;
        int age, bp_s, bp_d;
        String race;
        Boolean heart_disease;
        Button submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info_activity);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Caucasian", "African", "Hispanic/Latino", "American Indian", "Asian", "Native Australian"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        race_spinner=findViewById(R.id.spinner1);

        //Toast.makeText(this, race_selected, Toast.LENGTH_SHORT).show();

        age_text=findViewById(R.id.editText);


        bp_text=findViewById(R.id.editText2);



        h_yes=findViewById(R.id.heart_yes);

        final Intent mIntent = new Intent(this, StatsActivity.class);
        submit=findViewById(R.id.button_submitted);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                race=race_spinner.getSelectedItem().toString();
                age=Integer.parseInt(age_text.getText().toString());
                String temp=bp_text.getText().toString();
                String[] bp_values=temp.split(",");
                bp_s=Integer.parseInt(bp_values[0]);
                bp_d=Integer.parseInt(bp_values[1]);
                if(h_yes.isChecked()){
                    heart_disease=true;
                }
                else{
                    heart_disease=false;
                }
                mIntent.putExtra("race",race);
                mIntent.putExtra("age",age);
                mIntent.putExtra("bp_s",bp_s);
                mIntent.putExtra("bp_d",bp_d);
                mIntent.putExtra("heart_disease",heart_disease);

                startActivity(mIntent);
            }
        });


    }
}
