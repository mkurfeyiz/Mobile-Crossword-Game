package com.mkurfeyiz.kelimeoyunu.firstlevelactivities;

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

public class FirstLevel1Activity extends AppCompatActivity {
    Intent intent,toNext;
    TextView A,A2,A3,letterA;
    TextView U,U2,U3,letterU;
    TextView L,L2,letterL;
    TextView Z,Z2,letterZ;
    TextView B,B2,letterB;
    TextView M,letterM;
    TextView firstLevelScoreText,firstLevelTimeText;
    EditText answer;
    int firstLevelScore,firstLevelTime,firstLevelMultiplier,firstLevelCorrectsCount,totalScore;
    Runnable firstLvlRunnable;
    Handler firstLvlHandler;
    ArrayList<String> correctAnswers;
    String[] letters = {"M","U","B","L","Z","A"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level1);


        correctAnswers = new ArrayList<>(6);

        firstLevelScore = 0;
        firstLevelTime = 0;
        firstLevelMultiplier = 10;
        firstLevelCorrectsCount = 0;

        intent = getIntent();

        //A lar
        A = findViewById(R.id.textViewA);
        A2 = findViewById(R.id.textViewA2);
        A3 = findViewById(R.id.textViewA3);
        letterA = findViewById(R.id.textViewLetterA);
        //U lar
        U = findViewById(R.id.textViewU);
        U2 = findViewById(R.id.textViewU2);
        U3 = findViewById(R.id.textViewU3);
        letterU = findViewById(R.id.textViewLetterU);
        //L ler
        L = findViewById(R.id.textViewL);
        L2= findViewById(R.id.textViewL2);
        letterL = findViewById(R.id.textViewLetterL);
        //Z ler
        Z = findViewById(R.id.textViewZ);
        Z2= findViewById(R.id.textViewZ2);
        letterZ = findViewById(R.id.textViewLetterZ);
        //B ler
        B = findViewById(R.id.textViewB);
        B2 = findViewById(R.id.textViewB2);
        letterB = findViewById(R.id.textViewLetterB);
        //M
        M = findViewById(R.id.textViewM);
        letterM = findViewById(R.id.textViewLetterM);
        //Skor ve zaman
        firstLevelScoreText = findViewById(R.id.textViewScore);
        firstLevelTimeText = findViewById(R.id.textViewTime);

        answer = findViewById(R.id.editTextAnswerEntry);
        //letters.setBackgroundColor(Color.WHITE);
        //Sure,puan hesabi burada olmali.Oyun aktivite acilir acilmaz baslayacagi icin oyun onCreate de olmali.
        time();
    }



    public void time()
    {
        firstLvlHandler = new Handler();
        firstLvlRunnable = new Runnable() {
            @Override
            public void run() {
                firstLevelTimeText.setText("Geçen Süre : "+firstLevelTime);
                firstLevelTime++;
                firstLevelTimeText.setText("Geçen Süre : "+firstLevelTime);
                if(firstLevelTime%60 == 0 && firstLevelMultiplier>0)
                {
                    firstLevelMultiplier--;  //puan azaltma kontrolu
                }
                if(firstLevelMultiplier<0)
                {
                    firstLevelMultiplier = 1; // negatif olursa 1 e esitle
                }
                firstLvlHandler.postDelayed(firstLvlRunnable,1000);
            }
        };
        firstLvlHandler.post(firstLvlRunnable);
    }

    public void answerCheck(String ans){
        if(correctAnswers.contains(ans.toUpperCase())) {
            Toast.makeText(getApplicationContext(),"Bu kelimeyi daha önce buldunuz.",Toast.LENGTH_SHORT).show();
        } else {
            if(ans.toUpperCase().matches("BUL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BUL Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                U.setText("U");
                U.setBackgroundColor(Color.WHITE);
                L2.setText("L");
                L2.setBackgroundColor(Color.WHITE);

                firstLevelScore += 5*firstLevelMultiplier;
                firstLevelScoreText.setText("Puan : "+firstLevelScore);
                firstLevelCorrectsCount++;

            } else if(ans.toUpperCase().matches("BAL")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BAL Doğru Cevap",Toast.LENGTH_SHORT).show();

                B.setText("B");
                B.setBackgroundColor(Color.WHITE);
                A.setText("A");
                A.setBackgroundColor(Color.WHITE);
                L.setText("L");
                L.setBackgroundColor(Color.WHITE);

                firstLevelScore += 5*firstLevelMultiplier;
                firstLevelScoreText.setText("Puan : "+firstLevelScore);
                firstLevelCorrectsCount++;

            } else if(ans.toUpperCase().matches("LAZ")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"LAZ Doğru Cevap",Toast.LENGTH_SHORT).show();

                L.setText("L");
                L.setBackgroundColor(Color.WHITE);
                A2.setText("A");
                A2.setBackgroundColor(Color.WHITE);
                Z.setText("Z");
                Z.setBackgroundColor(Color.WHITE);

                firstLevelScore += 5*firstLevelMultiplier;
                firstLevelScoreText.setText("Puan : "+firstLevelScore);
                firstLevelCorrectsCount++;

            } else if(ans.toUpperCase().matches("ZAM")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"ZAM Doğru Cevap",Toast.LENGTH_SHORT).show();

                Z.setText("Z");
                Z.setBackgroundColor(Color.WHITE);
                A3.setText("A");
                A3.setBackgroundColor(Color.WHITE);
                M.setText("M");
                M.setBackgroundColor(Color.WHITE);

                firstLevelScore += 5*firstLevelMultiplier;
                firstLevelScoreText.setText("Puan : "+firstLevelScore);
                firstLevelCorrectsCount++;

            } else if(ans.toUpperCase().matches("MUZ")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"MUZ Doğru Cevap",Toast.LENGTH_SHORT).show();

                M.setText("M");
                M.setBackgroundColor(Color.WHITE);
                U2.setText("U");
                U2.setBackgroundColor(Color.WHITE);
                Z2.setText("Z");
                Z2.setBackgroundColor(Color.WHITE);

                firstLevelScore += 5*firstLevelMultiplier;
                firstLevelScoreText.setText("Puan : "+firstLevelScore);
                firstLevelCorrectsCount++;

            } else if(ans.toUpperCase().matches("BUZ")){
                correctAnswers.add(ans.toUpperCase());
                Toast.makeText(getApplicationContext(),"BUZ Doğru Cevap",Toast.LENGTH_SHORT).show();

                B2.setText("B");
                B2.setBackgroundColor(Color.WHITE);
                U3.setText("U");
                U3.setBackgroundColor(Color.WHITE);
                Z2.setText("Z");
                Z2.setBackgroundColor(Color.WHITE);

                firstLevelScore += 5*firstLevelMultiplier;
                firstLevelScoreText.setText("Puan : "+firstLevelScore);
                firstLevelCorrectsCount++;

            } else {
                Toast.makeText(getApplicationContext(),"Aradığınız kelime bulmacada bulunmamaktadır.",Toast.LENGTH_LONG).show();
                firstLevelMultiplier -= 3;
                if(firstLevelMultiplier<0)
                {
                    firstLevelMultiplier = 1; // negatif olursa 1 e esitle
                }
            }
        }
        answer.setText("");
    }

    public void search(View view)
    {
        //Kelime kontrolu
        String input=answer.getText().toString();
        answerCheck(input);
        if(firstLevelCorrectsCount == 6){
            //Intentle yeni aktiviteye gec ve oyuncuya ilk seviyeyi bitirdigini belirt.
            toNext = new Intent(getApplicationContext(), EndOfFirstLvlActivity.class);
            //Alta onceki aktiviteden gelen bilgileri ekleyerek ilerle
            toNext.putExtra("playerName",intent.getStringExtra("playerName"));
            //1.1 puan bilgisi
            toNext.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 puan bilgisi / NOT : YALNIZCA EN SON BULUNDUGUN SEVİYEDEKI TOPSCORE U ALIRKEN sharedPref KULLAN.HARICI BILGILER ICIN INTENTI KULLAN
            toNext.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
            //1.3 puan bilgisi
            toNext.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));
            //1.4 bilgisi
            toNext.putExtra("1.4Score",intent.getIntExtra("1.4Score",0));
            //1.5
            toNext.putExtra("1.5Score",intent.getIntExtra("1.5Score",0));
            //1.6
            toNext.putExtra("1.6Score",firstLevelScore);
            startActivity(toNext);
        }
    }

    public void help(View view)
    {
        //Harflerin yerini degistir.
        String temp;
        Random random =new Random();
        int index1,index2;
        for (int i=0;i<letters.length;i++){
            index1 = random.nextInt(6);
            index2 = random.nextInt(6);
            temp = letters[index1];
            letters[index1] = letters[index2];
            letters[index2] = temp;
        }
        letterM.setText(letters[0]);
        letterU.setText(letters[1]);
        letterB.setText(letters[2]);
        letterL.setText(letters[3]);
        letterZ.setText(letters[4]);
        letterA.setText(letters[5]);
    }

    //Asagidaki menu icin kullanilan kodlar main haric tum aktivitelerde yazilmak zorunda
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.get_to_scorepage,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.scorepage){
            Intent menuIntent = new Intent(getApplicationContext(), ScorePageActivity.class);
            menuIntent.putExtra("playerName",intent.getStringExtra("playerName"));//nickname i aktariyoruz.
            //1.1 seviye puani
            menuIntent.putExtra("1.1Score",intent.getIntExtra("1.1Score",0));
            //1.2 seviye puani
            menuIntent.putExtra("1.2Score",intent.getIntExtra("1.2Score",0));
            //1.3 seviye bilgileri
            menuIntent.putExtra("1.3Score",intent.getIntExtra("1.3Score",0));
            //1.4 seviye bilgileri
            menuIntent.putExtra("1.4Score",intent.getIntExtra("1.4Score",0));
            //1.5
            menuIntent.putExtra("1.5Score",intent.getIntExtra("1.5Score",0));
            //1.6
            menuIntent.putExtra("1.6Score",firstLevelScore);

            startActivity(menuIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
