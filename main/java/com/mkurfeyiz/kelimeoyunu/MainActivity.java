package com.mkurfeyiz.kelimeoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mkurfeyiz.kelimeoyunu.firstlevelactivities.FirstLevel6Activity;

public class MainActivity extends AppCompatActivity {

    Button begin;
    TextView countdown;
    TextView headline,nameTitle;
    EditText playerName;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        begin = findViewById(R.id.buttonBegin);
        countdown = findViewById(R.id.textViewCountdown);
        headline = findViewById(R.id.textViewHeadline);
        nameTitle = findViewById(R.id.textViewNameEntry);
        playerName = findViewById(R.id.editTextName);
        //Baslik ve geri sayim tusa basilmadigi surece gozukmemeli
        countdown.setVisibility(View.INVISIBLE);
        headline.setVisibility(View.INVISIBLE);
    }

    public void begin(View view)
    {
        if(playerName.getText().toString().matches("")){
            username = "Ali İhsan Varol";
        }
        else{
            username = playerName.getText().toString();
        }
        //Tusa bastiginda hata veriyor.Bunu coz
        countdown.setVisibility(View.VISIBLE);
        headline.setVisibility(View.VISIBLE);
        nameTitle.setVisibility(View.INVISIBLE);
        playerName.setVisibility(View.INVISIBLE);
        begin.setEnabled(false);

        new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdown.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(MainActivity.this, FirstLevel6Activity.class);
                intent.putExtra("playerName",username);
                startActivity(intent);
            }
        }.start();
    }
}
