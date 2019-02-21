package com.getset.career.guidance;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getset.career.guidance.InterestTest;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;

import static android.support.v4.content.ContextCompat.startActivity;

@Layout(R.layout.tinder_card_view)
public class TinderCard {

    static ArrayList<String> list=new ArrayList<String>();//Creating arraylist

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private Profile mProfile;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    public TinderCard(final Context context, Profile profile, SwipePlaceHolderView swipeView) {
        mContext = context;
        mProfile = profile;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved(){
        Glide
                .with(mContext)
                .load(mProfile.getImageUrl())
                .placeholder(R.drawable.loading) // can also be a drawable
                .error(R.drawable.error) // will be displayed if the image cannot be loaded
                .crossFade(50)
                .animate(R.anim.fade_in)
                .into(profileImageView);
        nameAgeTxt.setText(mProfile.getName());
    }

    @SwipeOut
    private void onSwipedOut(){
        InterestTest.clicks++;
        if(InterestTest.clicks==InterestTest.len) {
            String x="";
            for(String s:list)
                x=x+s+",";
            writeToFile("Interest Test : "+x+";",mContext);
            TinderCard.this.mContext.startActivity(new Intent(TinderCard.this.mContext, Selection.class));
            Bungee.zoom(TinderCard.this.mContext);
        }
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
    }

    @SwipeIn
    private void onSwipeIn(){
        list.add(mProfile.getName());
        InterestTest.clicks++;
        if(InterestTest.clicks==InterestTest.len) {
            String x="";
            for(String s:list)
                x=x+s+",";
            writeToFile("Interest Test : "+x+";",mContext);
            TinderCard.this.mContext.startActivity(new Intent(TinderCard.this.mContext, Selection.class));
            Bungee.zoom(TinderCard.this.mContext);
        }
    }

    @SwipeInState
    private void onSwipeInState(){
    }

    @SwipeOutState
    private void onSwipeOutState()
    {
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