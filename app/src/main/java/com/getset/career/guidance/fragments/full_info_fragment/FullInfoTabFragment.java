package com.getset.career.guidance.fragments.full_info_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.getset.career.guidance.AptitudeTest;
import com.getset.career.guidance.InterestTest;
import com.getset.career.guidance.EQTest;
import com.getset.career.guidance.R;
import com.getset.career.guidance.SportCardModel;
import com.getset.career.guidance.StudyHabits;

import spencerstudios.com.bungeelib.Bungee;


public class FullInfoTabFragment extends Fragment implements View.OnClickListener {

    private static final String EXTRA_SRORT_CARD_MODEL = "EXTRA_SRORT_CARD_MODEL";
    //    String transitionTag;
    private SportCardModel mSportCardModel;
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private RecyclerView rvAthletics;
    private TextView tv1;
    private Button start;
    public static FullInfoTabFragment newInstance(SportCardModel sportCardModel) {
        FullInfoTabFragment fragment = new FullInfoTabFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_SRORT_CARD_MODEL, sportCardModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSportCardModel = getArguments().getParcelable(EXTRA_SRORT_CARD_MODEL);
        }
        if (savedInstanceState != null) {
            mSportCardModel = savedInstanceState.getParcelable(EXTRA_SRORT_CARD_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_info, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        rvAthletics = (RecyclerView) view.findViewById(R.id.rvAthletics);
        start=view.findViewById(R.id.startact);
        tv1=view.findViewById(R.id.opt1);
        tv1.setOnClickListener(this);
        start.setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        if(v.getId()==R.id.startact)
        {
            String activity= (String) toolbar.getTitle();
            if(activity.equalsIgnoreCase("Interest"))
            {
                startActivity(new Intent(getContext(), InterestTest.class));
                Bungee.zoom(getContext());  //fire the zoom animation

            }
            else if(activity.equalsIgnoreCase("Study Habits"))
            {
                startActivity(new Intent(getContext(), StudyHabits.class));
                Bungee.zoom(getContext());  //fire the zoom animation
            }
            else if(activity.equalsIgnoreCase("Personality"))
            {
                startActivity(new Intent(getContext(), EQTest.class));
                Bungee.zoom(getContext());  //fire the zoom animation
            }
            else if(activity.equalsIgnoreCase("Aptitude"))
            {
                startActivity(new Intent(getContext(), AptitudeTest.class));
                Bungee.zoom(getContext());  //fire the zoom animation
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch(mSportCardModel.getSportTitle().charAt(0))
        {
            case 'A':
                tv1.setText("Aptitude is an innate or inborn capacity for learning. This helps assess an individual’s strengths and weaknesses in six key abilities");
                break;
            case 'S':
                tv1.setText("Evaluates an individual on three key elements i.e. Learning techniques, Examination techniques and Memory.Good study habits are considered essential for acquiring good grades and also help in effective learning to secure a bright career.");
                break;
            case 'P':
                tv1.setText("It is designed to help people identify their natural abilities, personality strengths and their career interests.");
                break;
            case 'I':
                tv1.setText("Interest is the tendency to attend to and be stirred by a certain object, event or process. Interest is always at the core of your career decisions and that is why it is absolutely necessary to identify your interest in a particular area.");
                break;
        }

        toolbar.setTitle(mSportCardModel.getSportTitle());
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), mSportCardModel.getBackgroundColorResId()));
        rvAthletics.setBackgroundColor(ContextCompat.getColor(getContext(), mSportCardModel.getBackgroundColorResId()));
        view.findViewById(R.id.scopeHeader).setBackgroundColor(ContextCompat.getColor(getContext(), mSportCardModel.getBackgroundColorResId()));
        ivPhoto.setImageResource(mSportCardModel.getImageResId());
        ivPhoto.setBackgroundColor(ContextCompat.getColor(getContext(), mSportCardModel.getBackgroundColorResId()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(EXTRA_SRORT_CARD_MODEL, mSportCardModel);
        super.onSaveInstanceState(outState);
    }

}
