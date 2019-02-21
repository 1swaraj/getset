package com.getset.career.guidance;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.getset.career.guidance.R;

import com.getset.career.guidance.fragments.main_fragment.MainFragment;

import java.io.File;
import java.io.FileInputStream;

import spencerstudios.com.bungeelib.Bungee;

public class Selection extends AppCompatActivity {

    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String str=readFromFile(this);
        int a=0,p=0,i=0,s=0;
        if(str.contains("Interest"))
            i=1;
        if(str.contains("Personality"))
            p=1;
        if(str.contains("Study Habits"))
            s=1;
        if(str.contains("Logical Aptitude")&&str.contains("Logical Aptitude 2")&&str.contains("Spatial Aptitude")&&str.contains("REA")&&str.contains("Numerical Aptitude")&&str.contains("Logical Aptitude 3"))
            a=1;
        if(a+p+i+s==4) {
            startActivity(new Intent(this, Result.class));
            Bungee.zoom(this);  //fire the zoom animation
        }

        SportCardsUtils.generateSportCards(a,p,i,s);


        setContentView(R.layout.activity_selection);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root, mMainFragment = MainFragment.newInstance())
                    .commit();
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.root);
            if (fragment instanceof MainFragment)
            {
                mMainFragment = (MainFragment) fragment;
            }
        }

    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.root);
        if (fragment instanceof MainFragment) {
            mMainFragment = (MainFragment) fragment;
        }
        if (mMainFragment == null || !mMainFragment.isAdded() || !mMainFragment.deselectIfSelected()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
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
