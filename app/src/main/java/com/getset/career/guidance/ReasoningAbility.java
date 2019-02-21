package com.getset.career.guidance;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReasoningAbility extends AppCompatActivity {
    private VideoView mVideoView;
    private static final String VIDEO_SAMPLE = "detective_puzzle";
    ImageButton wife,husband;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reasoning_ability);
        mVideoView = findViewById(R.id.videoViewRelative);
        wife=findViewById(R.id.wife);
        husband=findViewById(R.id.husband);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.setVisibility(View.GONE);
                findViewById(R.id.textView).setVisibility(View.VISIBLE);
                husband.setVisibility(View.VISIBLE);
                wife.setVisibility(View.VISIBLE);
            }
        });
        wife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile("Logical Aptitude 3 : good;",ReasoningAbility.this);
                writeToFile2("Bad Reasoning Ability\n",ReasoningAbility.this);
                Intent intent = new Intent(ReasoningAbility.this, Selection.class);
                String x = readFromFile2(ReasoningAbility.this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });
        husband.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile("Logical Aptitude 3 : bad;",ReasoningAbility.this);
                writeToFile2("Good Reasoning Ability\n",ReasoningAbility.this);
                Intent intent = new Intent(ReasoningAbility.this, AptitudeTest.class);
                String x = readFromFile2(ReasoningAbility.this);
                Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
                startActivity(intent);

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

    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }
    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();

    }
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
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
