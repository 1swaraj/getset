package com.getset.career.guidance;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

import spencerstudios.com.bungeelib.Bungee;

public class PersonalityReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_report);
        String x=readFromFile(this);
        String[] arrOfStr = x.split(";");
        String personality="",eq="",studyhabits="",la="";
        String disp="",disp2="";
        for (String a : arrOfStr) {
            if(a.contains("Personality Test :"))
                personality=a.substring("Personality Test :".length());
            if(a.contains("EQ Test :"))
                eq=a.substring("EQ Test :".length()).trim();
            if(a.contains("Study Habits :"))
                studyhabits=a.substring("Study Habits :".length()).trim();
            if(a.contains("Logical Aptitude :"))
                la=a.substring("Logical Aptitude :".length()).trim()+" ";
            if(a.contains("Logical Aptitude 2 :"))
                la+=a.substring("Logical Aptitude 2 :".length()).trim()+ " ";
            if(a.contains("Logical Aptitude 3 :"))
                la+=a.substring("Logical Aptitude 3 :".length()).trim();
        }

        if(Integer.parseInt(eq)>5)
            disp2+="Self-Awareness is your ability to accurately perceive your emotions and stay aware of them as they happen." +
                    "Self-Management is your ability to use awareness of your emotions to stay flexible and positively direct your behavior.Social Awareness is your ability to accurately pick up on emotions in other people and understand what is really going on." +
                    "Relationship Management is your ability to use awareness of your emotions and the others’ emotions to manage interactions successfully.";
        else
            disp2+="You are not very good at understanding people. You are often the sufferer in relationships";

        if(Integer.parseInt(studyhabits)>9)
            disp2+="Your study habits show signs of academic excellence.";
        else
            disp2+="Take down class notes and study regularly. These two advices will take you a long way in your career.";
        int iq=1;
        if(la.substring(la.indexOf(" ")+1).equals("true"))
            iq+=10;
        iq+=Integer.parseInt(la.substring(la.indexOf(" ")+1,la.lastIndexOf(" ")));
        if(la.substring(la.lastIndexOf(" ")).trim().equals("good"))
            iq+=10;
        if((iq>15&&Integer.parseInt(la.substring(la.lastIndexOf(" ")).trim())==2)||iq>30)
        {
            disp2+="You are an intellectual person and your intellect is the most important asset for you";
        }
        else
        {
            disp2+="Hardwork will be the key to your success.";
        }
        int i=0;
        for(String y:personality.split(","))
        {
            if(y.equals("1")&&i==0)
            {
                disp+="You are practical, and you don’t like to take risks in life. You leave a cautious life, and you tend to focus more on the negative aspects instead on the positive. Try to relax and enjoy yourself and your life more.";
            }
            else if(i==1)
            {
                disp+="You have an eye for details and nothing can run past you without you noticing it. You have creative side and also a unique way to find a solution to any problem or situation.";
            }
            if(y.equals("1")&&i==1)
            {
                disp+="You are deep into your comfort zone and this may be the reason why you can’t achieve all that you want in life. You need to step out of your comfort zone and see the world differently to gain new perspectives. You are a dreamy and romantic person and you tend to miss out important details.";
            }
            else if(i==1)
            {
                disp+="You haven’t got any constraints in life. You live your life full and effortlessly and it is easy for you to make new friends and connect with people. Also, you are a sensitive and kind person, and you like to help those in need.";
            }
            if(y.equals("2")&&i==2)
            {
                disp+="You are a socially oriented and communicative person, and you’re curious about people’s lives. Moreover, it’s in your nature to use your intellectual abilities to solve everyday tasks.";
            }
            else if (i==2)
            {
                disp+="You’re happy with your life, and you also believe in luck for all future endeavors. Even if something isn’t going according to your plan, you’re sure you’ll manage to cope with any situation.";
            }
            if(y.equals("1")&&i==3)
            {
                disp+="You’re curious about your life in general. You might even have the feeling that soon you’ll have to face the unknown, and you like it.";
            }
            else if(i==3)
            {
                disp+="You are not completely aware of your current emotional state. For example, it can be sadness, disappointment, or loneliness. You need to pay more attention to your own emotions.";
            }
            if(y.equals("2")&&i==4)
            {
                disp+="You’re ready for changes, and you feel you’re moving in the right direction. You’re aware of what’s waiting for you in the future.";
            }
            else if(i==4)
            {
                disp+="It’s very important for you to express your creativity. You’ve got something you want to share with this world. If you work hard, you’ll use your potential to the fullest.";
            }
            i++;
        }
        ((TextView)findViewById(R.id.abc)).setText(disp);
        ((TextView)findViewById(R.id.abcd)).setText(disp2);

        findViewById(R.id.colleges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlString="http://meridiensystems.com/gs/index.html";
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    PersonalityReport.this.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    PersonalityReport.this.startActivity(intent);
                }
            }
        });

        findViewById(R.id.explore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalityReport.this, CareerExploration.class));
                Bungee.zoom(PersonalityReport.this);  //fire the zoom animation
            }
        });
    }

    private String readFromFile(Context context) {
        String contents="";
        try {
            File file = new File(context.getFilesDir(), "config.txt");
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
        {}
        return contents;
    }
}
