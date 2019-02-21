package com.getset.career.guidance;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SportCardsUtils {
    public static List<SportCardModel> sportCardModels;
    public static void generateSportCards(int a,int p,int i,int s) {

        sportCardModels= new ArrayList<>(4-a-p-i-s);
        if(a==0)
        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Aptitude")
                    .withImageResId(R.drawable.abc)
                    .withBackgroundColorResId(R.color.holo_red_dark)
                    .build());
        }
        if(p==0)
        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Personality")
                    .withSportSubtitle("")
                    .withSportRound("")
                    .withImageResId(R.drawable.index)
                    .withBackgroundColorResId(R.color.color_belize_hole)
                    .build());

        }
        if(s==0)
        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Study Habits")
                    .withImageResId(R.drawable.study)

                    .withBackgroundColorResId(R.color.holo_purple_dark)
                    .build());

        }
        if(i==0)
        {
            sportCardModels.add(SportCardModel
                    .newBuilder()
                    .withSportTitle("Interest")
                    .withImageResId(R.drawable.images)
                    .withBackgroundColorResId(R.color.blue)
                    .build());

        }
    }
}
