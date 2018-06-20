package getset.guidance.career.getset.fragments.full_info_fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import getset.guidance.career.getset.Home;
import getset.guidance.career.getset.MemoryGame;
import getset.guidance.career.getset.R;
import getset.guidance.career.getset.AthleticModel;
import getset.guidance.career.getset.Country;
import getset.guidance.career.getset.Selection;
import getset.guidance.career.getset.SpatialAptitude;
import getset.guidance.career.getset.SportCardModel;
import java.util.ArrayList;
import java.util.List;

import static getset.guidance.career.getset.SportCardsUtils.sportCardModels;


public class FullInfoTabFragment extends Fragment implements View.OnClickListener {

    private static final String EXTRA_SRORT_CARD_MODEL = "EXTRA_SRORT_CARD_MODEL";
    //    String transitionTag;
    private SportCardModel mSportCardModel;
    private Toolbar toolbar;
    private ImageView ivPhoto;
    private RecyclerView rvAthletics;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6;
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
        tv1=view.findViewById(R.id.opt1);
        tv2=view.findViewById(R.id.opt2);
        tv3=view.findViewById(R.id.opt3);
        tv4=view.findViewById(R.id.opt4);
        tv5=view.findViewById(R.id.opt5);
        tv6=view.findViewById(R.id.opt6);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        return view;
    }

    public void onClick(View v) {
        if (v.equals(tv4)) {
            if(tv4.getText().equals("Spatial Ability")) {
                Intent intent = new Intent(getActivity(), SpatialAptitude.class);
                this.startActivity(intent);
            }
        }
        else if (v.equals(tv2))
        {
            if(tv2.getText().equals("Memory")) {
                Intent intent = new Intent(getActivity(), MemoryGame.class);
                this.startActivity(intent);
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch(mSportCardModel.getSportTitle().charAt(0))
        {
            case 'A':
                tv1.setText("Numerical Ability");
                tv2.setText("Reasoning Ability");
                tv3.setText("Verbal Ability");
                tv4.setText("Spatial Ability");
                tv5.setText("Rapid Evaluation Ability");
                tv6.setText("Cognitive Ability");
                break;
            case 'S':
                tv1.setText("Learning Techniques");
                tv2.setText("Memory");
                tv3.setText("Examination Techiniques");
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                tv6.setVisibility(View.GONE);
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
        ivPhoto.setImageResource(mSportCardModel.getImageResId());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(EXTRA_SRORT_CARD_MODEL, mSportCardModel);
        super.onSaveInstanceState(outState);
    }

}
