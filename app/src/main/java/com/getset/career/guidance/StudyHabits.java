package com.getset.career.guidance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import spencerstudios.com.bungeelib.Bungee;

public class StudyHabits extends AppCompatActivity {
    static int score=0;
    static int i=0;
    private String [] que={"Like to study daily","Do my home work daily","Study at fixed hours of the day","Don't get disturbed by TV/Radio noise while studying","Take rest in between long hours of study","Develop interest in the subject after start studying it","I know the importance of my study for my future","No stray thoughts come to mind, while studying","I take down the notes while reading","I take down notes while my teacher is teaching","I read very carefully so as to understand every point","I combine my class notes with notes from the book after I return home","I study whenever I get free time in school/college","I study figures and graphs very carefully while reading","I take help of my teacher or friend if I do not follow anything","If a matter is to be learnt by heart,use to do it part by part","I sleep as usual on the night before examination"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i=0;
        setContentView(R.layout.activity_study_habits);
        final TextView tv = findViewById(R.id.studyquestions);
        tv.setText(que[i++]);
        final Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        findViewById(R.id.rejectBtn2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i<que.length) {
                        a.reset();
                        tv.clearAnimation();

                        tv.setText(que[i++]);
                        tv.startAnimation(a);
                    }
                    else
                    {
                        writeToFile("Study Habits : "+score+";",StudyHabits.this);
                        startActivity(new Intent(StudyHabits.this, Selection.class));
                        Bungee.zoom(StudyHabits.this);  //fire the zoom animation
                    }
                }
            });

            findViewById(R.id.acceptBtn2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i<que.length) {
                        a.reset();
                        tv.clearAnimation();
                        score++;
                        tv.setText(que[i++]);
                        tv.startAnimation(a);
                    }
                    else
                    {
                        writeToFile("Study Habits : "+score+";",StudyHabits.this);
                        startActivity(new Intent(StudyHabits.this, Selection.class));
                        Bungee.zoom(StudyHabits.this);  //fire the zoom animation
                    }
                }
            });
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.acceptBtn2), "Always", "Click Here if you do the given activity always/often")
                        // All options below are optional
                        .outerCircleColor(R.color.colorAccent)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(30)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(20)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.white)  // Specify the color of the description text
                        .textColor(R.color.white)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.DEFAULT_BOLD)  // Specify a typeface for the text
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        TapTargetView.showFor(StudyHabits.this,                 // `this` is an Activity
                                TapTarget.forView(findViewById(R.id.rejectBtn2), "Rarely", "Click Here if you don't the given activity rarely/never")
                                        // All options below are optional
                                        .outerCircleColor(R.color.red)      // Specify a color for the outer circle
                                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                        .titleTextSize(30)                  // Specify the size (in sp) of the title text
                                        .titleTextColor(R.color.white)      // Specify the color of the title text
                                        .descriptionTextSize(20)            // Specify the size (in sp) of the description text
                                        .descriptionTextColor(R.color.white)  // Specify the color of the description text
                                        .textColor(R.color.white)            // Specify a color for both the title and description text
                                        .textTypeface(Typeface.DEFAULT_BOLD)  // Specify a typeface for the text
                                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                        .tintTarget(true)                   // Whether to tint the target view's color
                                        .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
                                        .targetRadius(60),                  // Specify the target radius (in dp)
                                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                                    @Override
                                    public void onTargetClick(TapTargetView view) {
                                        super.onTargetClick(view);      // This call is optional
                                    }
                                });
                    }
                });

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
