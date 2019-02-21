package com.getset.career.guidance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import spencerstudios.com.bungeelib.Bungee;

public class InterestTest extends AppCompatActivity{
    static TextView textView;
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    static int clicks=0,len=0;
    private final String FILENAME="config.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_test);
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));
        for(Profile profile : Utils.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));
            len++;
        }
        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSwipeView.doSwipe(true);
            }
        });

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.acceptBtn), "Like", "Click Here if you like doing the activity shown in the picture")
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
                        TapTargetView.showFor(InterestTest.this,                 // `this` is an Activity
                                TapTarget.forView(findViewById(R.id.rejectBtn), "Dislike", "Click Here if don't you like doing the activity shown in the picture")
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



}