package com.getset.career.guidance;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

import spencerstudios.com.bungeelib.Bungee;

public class AptitudeTest extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private Button mButton;
    private ViewPager mViewPager;
    private Button button;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();
        String x=readFromFile(this);
        int flag=0;
        if(!x.contains("Logical Aptitude")&&!x.contains("Logical Aptitude 2")) {
            mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
            flag++;
        }
        if(!x.contains("Spatial Aptitude")) {
            mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_2));
            flag++;
        }
        if(!x.contains("Numerical Aptitude")) {
            flag++;
            mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_3));
        }
        if(!x.contains("REA")) {
            mCardAdapter.addCardItem(new CardItem(R.string.title_5, R.string.text_5));
            flag++;
        }
        if(flag==0)
        {
            startActivity(new Intent(this, Selection.class));
            Bungee.zoom(this);
        }
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        }
        else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
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