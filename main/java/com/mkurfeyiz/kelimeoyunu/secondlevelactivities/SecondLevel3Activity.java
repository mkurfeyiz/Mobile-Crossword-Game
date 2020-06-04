package com.mkurfeyiz.kelimeoyunu.secondlevelactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mkurfeyiz.kelimeoyunu.R;

import java.util.ArrayList;
import java.util.Random;

public class SecondLevel3Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"K","U","Ş","O"};

    TextView S,S2,letterS;
    TextView K,K2,letterK;
    TextView U,U2,letterU;
    TextView O,O2,letterO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level3);

        score = 0;
        time = 0;
        multiplier = 7;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore23);
        timeText = findViewById(R.id.textViewTime23);
        answer = findViewById(R.id.editTextAnswerEntry23);

        //U lar
        U = findViewById(R.id.textView23U);
        U2 = findViewById(R.id.textView23U2);
        letterU = findViewById(R.id.textViewLetterU23);
        //L ler
        S = findViewById(R.id.textView23S);
        S2 = findViewById(R.id.textView23S2);
        letterS = findViewById(R.id.textViewLetterS23);
        //K lar
        K = findViewById(R.id.textView23K);
        K2 = findViewById(R.id.textView23K2);
        letterK = findViewById(R.id.textViewLetterK23);
        //O lar
        O = findViewById(R.id.textView23O);
        O2 = findViewById(R.id.textView23O2);
        letterO = findViewById(R.id.textViewLetterO23);

        time();
    }

    public void time() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                timeText.setText("Geçen Süre : "+time);
                time++;
                timeText.setText("Geçen Süre : "+time);
                if(time%60 == 0 && multiplier>0)
                {
                    multiplier--;  //puan azaltma kontrolu
                }
                if(multiplier<0)
                {
                    multiplier = 1; // negatif olursa 1 e esitle
                }
                handler.postDelayed(runnable,1000);
            }
        };
        handler.post(runnable);
    }

    public void search(View view) {
        String input=answer.getText().toString();
        answerCheck(input);
        if(correctCount == 3){
            //Intentle yeni aktiviteye gec ve oyuncuya ilk seviyeyi bitirdigini belirt.
            toNext = new Intent(getApplicationContext(), EndOf23Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //2.1 bilgisi
            toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
            //2.2 bilgisi
            toNext.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
            //2.3
            toNext.putExtra("2.3Score",score);

            startActivity(toNext);
        }
    }

    public void help(View view) {
        String temp;
        Random random =new Random();
        int index1,index2;
        for (int i=0;i<letters.length;i++){
            index1 = random.nextInt(4);
            index2 = random.nextInt(4);
            temp = letters[index1];
            letters[index1] = letters[index2];
            letters[index2] = temp;
        }
        letterO.setText(letters[0]);
        letterK.setText(letters[1]);
        letterU.setText(letters[2]);
        letterS.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("SOK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ŞOK Doğru Cevap",Toast.LENGTH_SHORT).show();

                S.setText("Ş");
                S.setBackgroundColor(Color.WHITE);
                O.setText("O");
                O.setBackgroundColor(Color.WHITE);
                K.setText("K");
                K.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KOSU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KOŞU Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                O2.setText("O");
                O2.setBackgroundColor(Color.WHITE);
                S2.setText("Ş");
                S2.setBackgroundColor(Color.WHITE);
                U.setText("U");
                U.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KUS")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KUŞ Doğru Cevap",Toast.LENGTH_SHORT).show();

                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                U2.setText("U");
                U2.setBackgroundColor(Color.WHITE);
                S2.setText("Ş");
                S2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else {
                Toast.makeText(getApplicationContext(),"Aradığınız kelime bulmacada bulunmamaktadır.",Toast.LENGTH_LONG).show();
                multiplier -= 1;
                if(multiplier<0)
                {
                    multiplier = 1; // negatif olursa 1 e esitle
                }
            }
        }
        answer.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.get_to_scorepage,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.scorepage){
            Intent menuIntent = new Intent(getApplicationContext(), ScorePage2Activity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //2.1 seviye puani
            menuIntent.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
            //2.2
            menuIntent.putExtra("2.2Score",intent.getIntExtra("2.2Score",0));
            //2.3
            menuIntent.putExtra("2.3Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
