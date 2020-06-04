package com.mkurfeyiz.kelimeoyunu.thirdlevelactivities;

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

public class ThirdLevel3Activity extends AppCompatActivity {

    Intent intent,toNext;
    int score,time,multiplier,correctCount;
    ArrayList<String> correctAnswers;
    TextView scoreText,timeText;
    Handler handler;
    Runnable runnable;
    EditText answer;
    String[] letters = {"M","L","T","U"};

    TextView T,T2,letterT;
    TextView L,L2,letterL;
    TextView M,M2,M3,letterM;
    TextView U,U2,U3,U4,U5,letterU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level3);

        score = 0;
        time = 0;
        multiplier = 10;
        correctCount = 0;
        correctAnswers = new ArrayList<>(6);

        intent = getIntent();

        scoreText = findViewById(R.id.textViewScore33);
        timeText = findViewById(R.id.textViewTime33);
        answer = findViewById(R.id.editTextAnswerEntry33);

        //M ler
        M = findViewById(R.id.textView33M);
        M2 = findViewById(R.id.textView33M2);
        M3 = findViewById(R.id.textView33M3);
        letterM = findViewById(R.id.textViewLetterM33);
        //T ler
        T = findViewById(R.id.textView33T);
        T2 = findViewById(R.id.textView33T2);
        letterT = findViewById(R.id.textViewLetterT33);
        //L ler
        L = findViewById(R.id.textView33L);
        L2 = findViewById(R.id.textView33L2);
        letterL = findViewById(R.id.textViewLetterL33);
        //U lar
        U = findViewById(R.id.textView33U);
        U2 = findViewById(R.id.textView33U2);
        U3 = findViewById(R.id.textView33U3);
        U4 = findViewById(R.id.textView33U4);
        U5 = findViewById(R.id.textView33U5);
        letterU = findViewById(R.id.textViewLetterU33);

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
            toNext = new Intent(getApplicationContext(), EndOf33Activity.class);
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //3.1
            toNext.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
            //3.2
            toNext.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));
            //3.3
            toNext.putExtra("3.3Score",score);

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
        letterM.setText(letters[0]);
        letterL.setText(letters[1]);
        letterT.setText(letters[2]);
        letterU.setText(letters[3]);
    }

    public void answerCheck(String ans) {
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("MUTLU")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"MUTLU Doğru Cevap",Toast.LENGTH_SHORT).show();

                M.setText("M");
                M.setBackgroundColor(Color.WHITE);
                U.setText("U");
                U.setBackgroundColor(Color.WHITE);
                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);
                U4.setText("U");
                U4.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("TULUM")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"TULUM Doğru Cevap",Toast.LENGTH_SHORT).show();

                T.setText("T");
                T.setBackgroundColor(Color.WHITE);
                U2.setText("U");
                U2.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                U3.setText("U");
                U3.setBackgroundColor(Color.WHITE);
                M2.setText("M");
                M2.setBackgroundColor(Color.WHITE);

                score += 5*multiplier;
                scoreText.setText("Puan : "+score);
                correctCount++;

            } else if(ans.toUpperCase().matches("UMUT")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"UMUT Doğru Cevap",Toast.LENGTH_SHORT).show();

                U4.setText("U");
                U4.setBackgroundColor(Color.WHITE);
                M3.setText("M");
                M3.setBackgroundColor(Color.WHITE);
                U5.setText("U");
                U5.setBackgroundColor(Color.WHITE);
                T2.setText("T");
                T2.setBackgroundColor(Color.WHITE);

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
            Intent menuIntent = new Intent(getApplicationContext(), ScorePage3Activity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //3.1
            menuIntent.putExtra("3.1Score",intent.getIntExtra("3.1Score",0));
            //3.2
            menuIntent.putExtra("3.2Score",intent.getIntExtra("3.2Score",0));
            //3.3
            menuIntent.putExtra("3.3Score",score);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
