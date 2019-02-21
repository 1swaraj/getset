package com.getset.career.guidance;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import spencerstudios.com.bungeelib.Bungee;

public class EQTest extends AppCompatActivity {
    ImageView question;
    int questionno;
    public String data;
    static int score=0;
    Button option1,option2,option3,option4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);
        question=(ImageView) findViewById(R.id.question);
        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);
        option4=(Button)findViewById(R.id.option4);
        try
        {
            String value="0";
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                value = bundle.getString("question");
            }
            questionno=Integer.parseInt(value);
        }
        catch (Exception e) {
            questionno = 0;
        }
        switch (questionno+1)
        {
            case 1:
                option1.setText("Embarrasment");
                option2.setText("Fear");
                option3.setText("Sadness");
                option4.setText("Surprise");
                question.setImageResource(R.drawable.q1);
                break;

            case 2:
                option1.setText("Amazement");
                option2.setText("Interest");
                option3.setText("Happiness");
                option4.setText("Politeness");
                question.setImageResource(R.drawable.q2);
                break;

            case 3:
                option1.setText("Sadness");
                option2.setText("Pain");
                option3.setText("Anger");
                option4.setText("Disgust");
                question.setImageResource(R.drawable.q3);
                break;

            case 4:
                option4.setText("Embarrassment");
                option2.setText("Sadness");
                option3.setText("Amusement");
                option1.setText("Shame");
                question.setImageResource(R.drawable.q4);
                break;

            case 5:
                option1.setText("Pride");
                option2.setText("Contempt");
                option3.setText("Excitement");
                option4.setText("Anger");
                question.setImageResource(R.drawable.q5);
                break;

            case 6:
                option1.setText("Fear");
                option2.setText("Interest");
                option3.setText("Surprise");
                option4.setText("Compassion");
                question.setImageResource(R.drawable.q6);
                break;

            case 7:
                option1.setText("Sadness");
                option2.setText("Shame");
                option3.setText("Bored");
                option4.setText("Contempt");
                question.setImageResource(R.drawable.q7);
                break;

            case 8:
                option1.setText("Anger");
                option2.setText("Pain");
                option3.setText("Disgust");
                option4.setText("Sadness");
                question.setImageResource(R.drawable.q8);
                break;

            case 9:
                option3.setText("Desire");
                option2.setText("Embarrassment");
                option1.setText("Flirtatiousness");
                option4.setText("Love");
                question.setImageResource(R.drawable.q9);
                break;

            case 10:
                option1.setText("Shame");
                option2.setText("Anger");
                option3.setText("Sadness");
                option4.setText("Pain");
                question.setImageResource(R.drawable.q10);
                break;

            default:
                writeToFile("EQ Test : "+score+";",this);
                writeToFile2("EQ Test Score : "+score+"\n",this);
                startActivity(new Intent(this, PersonalityTest2.class));
                String x = readFromFile2(this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                Bungee.slideLeft(this);
        }

        option1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EQTest.this,EQTest.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                if(option1.getText().equals("Pride")||option1.getText().equals("Flirtatiousness"))
                    score++;
                startActivity(intent);
                Bungee.slideLeft(EQTest.this);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EQTest.this,EQTest.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                if(option2.getText().equals("Fear"))
                    score++;
                startActivity(intent);
                Bungee.slideLeft(EQTest.this);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EQTest.this,EQTest.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                if(option3.getText().equals("Happiness")||option3.getText().equals("Anger")||option3.getText().equals("Surprise")||option3.getText().equals("Disgust"))
                    score++;
                startActivity(intent);
                Bungee.slideLeft(EQTest.this);

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EQTest.this,EQTest.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                if(option4.getText().equals("Embarrassment")||option4.getText().equals("Contempt")||option4.getText().equals("Pain"))
                    score++;
                startActivity(intent);
                Bungee.slideLeft(EQTest.this);

            }
        });
    }
    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(getApplicationContext(),"Back is not allowed here,",Toast.LENGTH_LONG/2).show();
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
        } catch (IOException e) {
            e.toString();
        }
    }
}
