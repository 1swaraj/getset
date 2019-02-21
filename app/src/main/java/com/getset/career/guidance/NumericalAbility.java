package com.getset.career.guidance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NumericalAbility extends AppCompatActivity {

    ProgressDialog progressBar;
    Button goButton, button0, button1, button2, button3,playAgainButton;
    ArrayList<Integer> answers;
    ConstraintLayout gameLayout;
    GridLayout gridLayout;
    TextView correctTextView;
    TextView scoreTextView;
    TextView timerTextView;
    int score = 0, total = 0;
    int locationOfAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_numerical_ability);
        WebView webView = findViewById(R.id.toh);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        progressBar = ProgressDialog.show(NumericalAbility.this, "Loading", "Unleash the unique you...");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setInitialScale(1);
        WebViewClient mWebViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                writeToFile("Numerical Aptitude :"+url.substring(url.indexOf("com")+3,url.lastIndexOf('/'))+";",NumericalAbility.this);
                Intent intent = new Intent(NumericalAbility.this, AptitudeTest.class);
                startActivity(intent);
                return true; //don't allow WebView to load url
            }

        };
        webView.setWebViewClient(mWebViewClient);
        webView.loadUrl("file:///android_asset/Maths/index.html");
        Button btn=findViewById(R.id.skip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile("Numerical Aptitude :"+false+";",NumericalAbility.this);
                Intent intent = new Intent(NumericalAbility.this, AptitudeTest.class);
                startActivity(intent);
            }
        });*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_ability);
        correctTextView = findViewById(R.id.answerCheckTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        answers = new ArrayList<Integer>();
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton =findViewById(R.id.playAgainButton);
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);

    }
    private void writeToFile(String data,Context context) {
        try {
            String MYFILE = "config.txt";
            FileOutputStream fos;
            File file = new File(context.getFilesDir(), MYFILE);
            if(file.exists()) {
                FileOutputStream stream = new FileOutputStream(file,true);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
            else {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }
            }
        }
        catch (IOException e) {
            e.toString();
        }
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
    public void playAgain(View view){

        score=0;
        total=0;
        String message = Integer.toString(score) + "/" + Integer.toString(total);
        scoreTextView.setText(message);
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                writeToFile("Numerical Aptitude :"+score+";",NumericalAbility.this);
                writeToFile2("Numerical Aptitude : "+score+"\n",NumericalAbility.this);
                Intent intent = new Intent(NumericalAbility.this, AptitudeTest.class);
                String x = readFromFile2(NumericalAbility.this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }.start();

    }

    private String readFromFile2(Context context) {
        String contents="";
        try {
            File file = new File(context.getFilesDir(), "report.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }
            contents = new String(bytes);
        }
        catch (Exception e)
        {
            Log.e("PDFCreator", "createPDFError:" + e);

        }
        return contents;
    }

    private void writeToFile2(String data,Context context) {
        try {
            String MYFILE = "report.txt";
            FileOutputStream fos;
            File file = new File(context.getFilesDir(), MYFILE);
            if(file.exists()) {
                FileOutputStream stream = new FileOutputStream(file,true);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
            else {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                try {
                    stream.write(data.getBytes());
                } finally {
                    stream.close();
                }

            }
        } catch (IOException e) {
            e.toString();
        }
    }


    public void checkAnswer(View view) {
        if (Integer.toString(locationOfAnswer).equals(view.getTag().toString())) {
            correctTextView.setText("CORRECT :)");
            score += 1;
            total += 1;
            String message = Integer.toString(score) + "/" + Integer.toString(total);
            scoreTextView.setText(message);
        } else {
            score += 0;
            total += 1;
            correctTextView.setText("INCORRECT :(");
            String message = Integer.toString(score) + "/" + Integer.toString(total);
            scoreTextView.setText(message);
        }
        newQuestion();
    }


    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(49);
        int b = random.nextInt(49);

        TextView textView = findViewById(R.id.questionTextView);
        textView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfAnswer = random.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(99);
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(99);
                }
                answers.add(wrongAnswer);
            }
        }
        /* Set the values present in the array list  to the options buttons */
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

}

