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

public class SpatialAptitude extends AppCompatActivity {
    ImageView question;
    int questionno;
    public String data;
    static int score=0;
    ImageButton option1,option2,option3,option4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spatial_aptitude);
        ((TextView)findViewById(R.id.myEditText)).setText(Html.fromHtml("Which cube cannot be made"+"<br>"+"based on the unfolded cube?"));
        question=(ImageView) findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
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
                option1.setImageResource(R.drawable.qq11);
                option1.setTag(R.drawable.qq11);
                option2.setImageResource(R.drawable.qq12);
                option2.setTag(R.drawable.qq12);

                option3.setTag(R.drawable.qq13);
                option4.setTag(R.drawable.qq14);
                option3.setImageResource(R.drawable.qq13);
                option4.setImageResource(R.drawable.qq14);
                question.setImageResource(R.drawable.qq1);
                break;

            case 2:
                option1.setImageResource(R.drawable.qq21);
                option1.setTag(R.drawable.qq21);
                option2.setImageResource(R.drawable.qq22);
                option2.setTag(R.drawable.qq22);

                option3.setTag(R.drawable.qq23);
                option4.setTag(R.drawable.qq24);
                option3.setImageResource(R.drawable.qq23);
                option4.setImageResource(R.drawable.qq24);
                question.setImageResource(R.drawable.qq2);
                break;

            case 3:
                option1.setImageResource(R.drawable.qq31);
                option1.setTag(R.drawable.qq31);
                option2.setImageResource(R.drawable.qq32);
                option2.setTag(R.drawable.qq32);

                option3.setTag(R.drawable.qq33);
                option4.setTag(R.drawable.qq34);
                option3.setImageResource(R.drawable.qq33);
                option4.setImageResource(R.drawable.qq34);
                question.setImageResource(R.drawable.qq3);
                break;

            case 4:
                option1.setImageResource(R.drawable.qq41);
                option1.setTag(R.drawable.qq41);
                option2.setImageResource(R.drawable.qq42);
                option2.setTag(R.drawable.qq42);

                option3.setTag(R.drawable.qq43);
                option4.setTag(R.drawable.qq44);
                option3.setImageResource(R.drawable.qq43);
                option4.setImageResource(R.drawable.qq44);
                question.setImageResource(R.drawable.qq4);
                break;

            case 5:
                option1.setImageResource(R.drawable.qq51);
                option1.setTag(R.drawable.qq51);
                option2.setImageResource(R.drawable.qq52);
                option2.setTag(R.drawable.qq52);

                option3.setTag(R.drawable.qq53);
                option4.setTag(R.drawable.qq54);
                option3.setImageResource(R.drawable.qq53);
                option4.setImageResource(R.drawable.qq54);
                question.setImageResource(R.drawable.qq5);
                break;

            case 6:
                option1.setImageResource(R.drawable.qq61);
                option1.setTag(R.drawable.qq61);
                option2.setImageResource(R.drawable.qq62);
                option2.setTag(R.drawable.qq62);

                option3.setTag(R.drawable.qq63);
                option4.setTag(R.drawable.qq64);
                option3.setImageResource(R.drawable.qq63);
                option4.setImageResource(R.drawable.qq64);
                question.setImageResource(R.drawable.qq6);
                break;

            case 7:
                option1.setImageResource(R.drawable.qq71);
                option1.setTag(R.drawable.qq71);
                option2.setImageResource(R.drawable.qq72);
                option2.setTag(R.drawable.qq72);

                option3.setTag(R.drawable.qq73);
                option4.setTag(R.drawable.qq74);
                option3.setImageResource(R.drawable.qq73);
                option4.setImageResource(R.drawable.qq74);
                question.setImageResource(R.drawable.qq7);
                break;

            case 8:
                option1.setImageResource(R.drawable.qq81);
                option1.setTag(R.drawable.qq81);
                option2.setImageResource(R.drawable.qq82);
                option2.setTag(R.drawable.qq82);

                option3.setTag(R.drawable.qq83);
                option4.setTag(R.drawable.qq84);
                option3.setImageResource(R.drawable.qq83);
                option4.setImageResource(R.drawable.qq84);
                question.setImageResource(R.drawable.qq8);
                break;

            case 9:
                option1.setImageResource(R.drawable.qq91);
                option1.setTag(R.drawable.qq91);
                option2.setImageResource(R.drawable.qq92);
                option2.setTag(R.drawable.qq92);
                option3.setTag(R.drawable.qq93);
                option4.setTag(R.drawable.qq94);
                option3.setImageResource(R.drawable.qq93);
                option4.setImageResource(R.drawable.qq94);
                question.setImageResource(R.drawable.qq9);
                break;

            case 10:
                option1.setImageResource(R.drawable.qq101);
                option1.setTag(R.drawable.qq101);
                option2.setImageResource(R.drawable.qq102);
                option2.setTag(R.drawable.qq102);
                option3.setImageResource(R.drawable.qq103);
                option3.setTag(R.drawable.qq103);
                option4.setTag(R.drawable.qq104);
                option4.setImageResource(R.drawable.qq104);
                question.setImageResource(R.drawable.qq10);
                break;

            default:
                writeToFile("Spatial Aptitude Test : "+score+";",this);
                writeToFile2("Spatial Aptitude Test : "+score+"\n",this);
                startActivity(new Intent(this, AptitudeTest.class));
                String x = readFromFile2(SpatialAptitude.this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                Bungee.zoom(this);
        }

        option1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((Integer) option1.getTag()==R.drawable.qq51)
                    score++;
                Intent intent = new Intent(SpatialAptitude.this,SpatialAptitude.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                startActivity(intent);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((Integer) option2.getTag()==R.drawable.qq12||(Integer) option2.getTag()==R.drawable.qq22||(Integer) option2.getTag()==R.drawable.qq92||(Integer) option2.getTag()==R.drawable.qq62)
                    score++;

                Intent intent = new Intent(SpatialAptitude.this,SpatialAptitude.class);
                intent.putExtra("question", String.valueOf(questionno +1));

                startActivity(intent);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((Integer) option3.getTag()==R.drawable.qq83)
                    score++;
                Intent intent = new Intent(SpatialAptitude.this,SpatialAptitude.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                startActivity(intent);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((Integer) option4.getTag()==R.drawable.qq34||(Integer) option4.getTag()==R.drawable.qq44||(Integer) option4.getTag()==R.drawable.qq74||(Integer) option4.getTag()==R.drawable.qq104)
                    score++;
                Intent intent = new Intent(SpatialAptitude.this,SpatialAptitude.class);
                intent.putExtra("question", String.valueOf(questionno +1));
                startActivity(intent);
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
        }
        catch (IOException e) {
            e.toString();
        }
    }

}
