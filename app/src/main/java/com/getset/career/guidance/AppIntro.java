package com.getset.career.guidance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

public class AppIntro extends FancyWalkthroughActivity implements View.OnClickListener {

    int flag=0;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_app_intro);
        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Find Where Your Passion Lies", "Bored with tests.\nGetSet - a gamified approach to career guidance.", R.drawable.getset_logo);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Aptitude Test", "Used to determine an individual's propensity to succeed in a given activity.", R.drawable.aptitude_test);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Personality Test", "Used for assessing human personality constructs.", R.drawable.personality);
        FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("Interest Test", "Interests tell more on who you are and what suits you and also provides information necessary for making the right career choice.", R.drawable.interest);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels / 2;
        int height = displayMetrics.heightPixels / 2;
        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(width, height, -50, 0, 0, 100);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(width, height, -50, 0, 0, 100);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(width, height, -50, 0, 0, 100);
        fancywalkthroughCard4.setIconLayoutParams(width, height, -50, 0, 0, 100);
        fancywalkthroughCard4.setBackgroundColor(R.color.orange);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        pages.add(fancywalkthroughCard4);
        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.blue);
            page.setBackgroundColor(R.color.orange);
            fancywalkthroughCard4.setBackgroundColor(R.color.orange);
            page.setDescriptionColor(R.color.blue);
        }setFinishButtonTitle("Sign In");
        showNavigationControls(true);
        setColorBackground(R.color.green);
        //setImageBackground(R.drawable.ic_arrow_foward_black_24dp);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorAccent);
        setOnboardPages(pages);
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.ivNext), "Click Here", "To go to the next page")
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
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                    }
                });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(this, Login.class));
        Bungee.zoom(this);  //fire the zoom animation

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Bungee.slideLeft(this); //fire the slide left animation
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
