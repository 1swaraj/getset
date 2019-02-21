package com.getset.career.guidance;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;

public class PersonalityTest2 extends AppCompatActivity {
    ImageView question;
    int questionno;
    public static String x="";
    public String data;
    static int score=0;
    ImageButton option1,option2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test2);
        ((TextView)findViewById(R.id.myEditText)).setText(Html.fromHtml("What did you see first?"));
        question=(ImageView) findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
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
                option1.setImageResource(R.drawable.p11);
                option1.setTag(R.drawable.p11);
                option2.setImageResource(R.drawable.p12);
                option2.setTag(R.drawable.p12);
                question.setImageResource(R.drawable.personality1);
                break;

            case 2:
                option1.setImageResource(R.drawable.p21);
                option1.setTag(R.drawable.p21);
                option2.setImageResource(R.drawable.p22);
                option2.setTag(R.drawable.p22);
                question.setImageResource(R.drawable.personality2);
                break;

            case 3:
                option1.setImageResource(R.drawable.p31);
                option1.setTag(R.drawable.p31);
                option2.setImageResource(R.drawable.p32);
                option2.setTag(R.drawable.p32);
                question.setImageResource(R.drawable.personality3);
                break;

            case 4:
                option1.setImageResource(R.drawable.p41);
                option1.setTag(R.drawable.p41);
                option2.setImageResource(R.drawable.p42);
                option2.setTag(R.drawable.p42);
                question.setImageResource(R.drawable.personality4);
                break;

            case 5:
                option1.setImageResource(R.drawable.p51);
                option1.setTag(R.drawable.p51);
                option2.setImageResource(R.drawable.p52);
                option2.setTag(R.drawable.p52);
                question.setImageResource(R.drawable.personality5);
                break;

            default:
                writeToFile("Personality Test : "+x+";",this);
                startActivity(new Intent(this, AptitudeTest.class));
                Bungee.zoom(this);
        }

        option1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x=x+"1,";
                Intent intent = new Intent(PersonalityTest2.this,PersonalityTest2.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                startActivity(intent);
                Bungee.slideLeft(PersonalityTest2.this);

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x=x+"2,";
                Intent intent = new Intent(PersonalityTest2.this,PersonalityTest2.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                startActivity(intent);
                Bungee.slideLeft(PersonalityTest2.this);
            }
        });
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

    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
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
