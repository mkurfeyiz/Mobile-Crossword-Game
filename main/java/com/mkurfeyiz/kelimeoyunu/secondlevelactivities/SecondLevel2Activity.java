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

public class SecondLevel2Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"K","U","Ü","P","L"};

    TextView L,L2,letterL;
    TextView K,K2,letterK;
    TextView U,U2,U3,letterU,letterU1;
    TextView P,letterP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level2);

        score = 0;
        time = 0;
        multiplier = 7;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore22);
        timeText = findViewById(R.id.textViewTime22);
        answer = findViewById(R.id.editTextAnswerEntry22);

        //U lar
        U = findViewById(R.id.textView22U);
        U2 = findViewById(R.id.textView22U2);
        U3 = findViewById(R.id.textView22U3);
        letterU = findViewById(R.id.textViewLetterU22);
        letterU1 = findViewById(R.id.textViewLetterU122);
        //L ler
        L = findViewById(R.id.textView22L);
        L2 = findViewById(R.id.textView22L2);
        letterL = findViewById(R.id.textViewLetterL22);
        //K lar
        K = findViewById(R.id.textView22K);
        K2 = findViewById(R.id.textView22K2);
        letterK = findViewById(R.id.textViewLetterK22);
        //P
        P = findViewById(R.id.textView22P);
        letterP = findViewById(R.id.textViewLetterP22);

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
            toNext = new Intent(getApplicationContext(), EndOf22Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //2.1 bilgisi
            toNext.putExtra("2.1Score",intent.getIntExtra("2.1Score",0));
            //2.2 bilgisi
            toNext.putExtra("2.2Score",score);

            startActivity(toNext);
        }
    }

    public void help(View view) {
        String temp;
        Random random =new Random();
        int index1,index2;
        for (int i=0;i<letters.length;i++){
            index1 = random.nextInt(5);
            index2 = random.nextInt(5);
            temp = letters[index1];
            letters[index1] = letters[index2];
            letters[index2] = temp;
        }
        letterP.setText(letters[0]);
        letterK.setText(letters[1]);
        letterU.setText(letters[2]);
        letterU1.setText(letters[3]);
        letterL.setText(letters[4]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("KULP")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KULP Doğru Cevap",Toast.LENGTH_SHORT).show();

                K.setText("K");
                K.setBackgroundColor(Color.WHITE);
                U.setText("U");
                U.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                P.setText("P");
                P.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KÜP")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KÜP Doğru Cevap",Toast.LENGTH_SHORT).show();

                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                U2.setText("Ü");
                U2.setBackgroundColor(Color.WHITE);
                P.setText("P");
                P.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("KÜL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"KÜL Doğru Cevap",Toast.LENGTH_SHORT).show();

                K2.setText("K");
                K2.setBackgroundColor(Color.WHITE);
                U3.setText("Ü");
                U3.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);

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
            menuIntent.putExtra("2.2Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
