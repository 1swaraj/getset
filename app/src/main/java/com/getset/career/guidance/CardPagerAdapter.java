package com.getset.career.guidance;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.bungeelib.Bungee;

import static android.support.v4.content.ContextCompat.startActivity;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        Button btn=view.findViewById(R.id.apti_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv =view.findViewById(R.id.titleTextView);
                String x= tv.getText().toString();
                Context context=view.getContext();
                if(x.equalsIgnoreCase("Logical and Reasoning Aptitude")) {
                    context.startActivity(new Intent(context, TOH.class));
                    Bungee.zoom(context);  //fire the zoom animation
                }
                else if(x.equalsIgnoreCase("Spatial Aptitude")) {
                    context.startActivity(new Intent(context, SpatialAptitude.class));
                    Bungee.zoom(context);  //fire the zoom animation
                }
                else if(x.equalsIgnoreCase("Numerical Aptitude")) {
                    context.startActivity(new Intent(context, NumericalAbility.class));
                    Bungee.zoom(context);  //fire the zoom animation
                }
                else if(x.equalsIgnoreCase("REA"))
                {
                    context.startActivity(new Intent(context, RapidEvaluationAbility.class));
                    Bungee.zoom(context);  //fire the zoom animation
                }
                else if(x.equalsIgnoreCase("Reasoning Ability"))
                {
                    context.startActivity(new Intent(context, ReasoningAbility.class));
                    Bungee.zoom(context);  //fire the zoom animation
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view) {
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView contentTextView = (TextView) view.findViewById(R.id.contentTextView);
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getText());
    }

}