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
import com.mkurfeyiz.kelimeoyunu.firstlevelactivities.EndOf5Activity;
import com.mkurfeyiz.kelimeoyunu.firstlevelactivities.ScorePageActivity;

import java.util.ArrayList;
import java.util.Random;

public class SecondLevel6Activity extends AppCompatActivity {
    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"Y","Ö","K","B"};

    TextView Y,Y2,Y3,letterY;
    TextView K,K2,K3,letterK;
    TextView O,U,letterO,letterU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level6);

        score = 0;
        time = 0;
        multiplier = 7;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore21);
        timeText = findViewById(R.id.textViewTime21);
        answer = findViewById(R.id.editTextAnswerEntry21);

        //Y ler
        Y = findViewById(R.id.textView21Y);
        Y2 = findViewById(R.id.textView21Y2);
        Y3 = findViewById(R.id.textView21Y3);
        letterY = findViewById(R.id.textViewLetterY21);
        //O ve U lar
        O = findViewById(R.id.textView21O);
        U = findViewById(R.id.textView21U);
        letterO = findViewById(R.id.textViewLetterO21);
        letterU = findViewById(R.id.textViewLetterU21);
        //K lar duzelt
        K = findViewById(R.id.textView21K);
        K2 = findViewById(R.id.textView21K2);
        K3 = findViewById(R.id.textView21K3);
        letterK = findViewById(R.id.textViewLetterK21);

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
            toNext = new Intent(getApplicationContext(), EndOf26Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //2.1 bilgisi
            toNext.putExtra("2.1Score",score);

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
        letterY.setText(letters[0]);
        letterK.setText(letters[1]);
        letterU.setText(letters[2]);
        letterO.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("KÖY")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KÖY Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                O.setText("Ö");
                O.setBackgroundColor(Color.WHITE);
                Y.setText("Y");
                Y.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("ÖYKÜ")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ÖYKÜ Doğru Cevap",Toast.LENGTH_SHORT).show();

                O.setText("Ö");
                O.setBackgroundColor(Color.WHITE);
                Y2.setText("Y");
                Y2.setBackgroundColor(Color.WHITE);
                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                U.setText("Ü");
                U.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("YÜK")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"YÜK Doğru Cevap",Toast.LENGTH_SHORT).show();

                Y3.setText("Y");
                Y3.setBackgroundColor(Color.WHITE);
                U.setText("Ü");
                U.setBackgroundColor(Color.WHITE);
                K3.setText("K");
                K3.setBackgroundColor(Color.WHITE);

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
            menuIntent.putExtra("2.1Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
