package com.getset.career.guidance.fragments.main_fragment;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;
import com.cleveroad.fanlayoutmanager.callbacks.FanChildDrawingOrderCallback;

import com.getset.career.guidance.R;
import com.getset.career.guidance.fragments.full_info_fragment.FullInfoTabFragment;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

import static com.getset.career.guidance.SportCardsUtils.sportCardModels;


public class MainFragment extends Fragment {
    TourGuide mTourGuideHandler;
    private FanLayoutManager mFanLayoutManager;
    private SportCardsAdapter mAdapter;
    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvCards);
        FanLayoutManagerSettings fanLayoutManagerSettings = FanLayoutManagerSettings
                .newBuilder(getContext())
                .withFanRadius(true)
                .withAngleItemBounce(5)
                .withViewHeightDp(250)
                .withViewWidthDp(200)
                .build();
        ImageView imageView = (ImageView) view.findViewById(R.id.logo);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.drawable.getset_logo).into(imageViewTarget);
        mTourGuideHandler = TourGuide.init(getActivity()).with(TourGuide.Technique.HorizontalLeft)
                .setPointer(new Pointer())
                .motionType(TourGuide.MotionType.AllowAll)
                .setToolTip(new ToolTip().setTitle("Scroll Horizontally and Click").setDescription("To view and attempt components of career guidance.").setBackgroundColor(R.color.white).setGravity(Gravity.TOP|Gravity.CENTER))
                .setOverlay(new Overlay().setBackgroundColor(R.color.color_silver)).playOn(view.findViewById(R.id.rvCards));
        view.findViewById(R.id.rvCards).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mTourGuideHandler.cleanUp();
                return false;
            }
        });
        mFanLayoutManager = new FanLayoutManager(getContext(), fanLayoutManagerSettings);
        recyclerView.setLayoutManager(mFanLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SportCardsAdapter(getContext());
        mAdapter.addAll(sportCardModels);

        mAdapter.setOnItemClickListener(new SportCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, final View view) {
                if (mFanLayoutManager.getSelectedItemPosition() != itemPosition) {
                    mFanLayoutManager.switchItem(recyclerView, itemPosition);
                } else {
                    mFanLayoutManager.straightenSelectedItem(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            mTourGuideHandler.cleanUp();
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                onClick(view, mFanLayoutManager.getSelectedItemPosition());
                            } else {
                                onClick(mFanLayoutManager.getSelectedItemPosition());
                            }
                            mTourGuideHandler.cleanUp();

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });

        recyclerView.setAdapter(mAdapter);

        recyclerView.setChildDrawingOrderCallback(new FanChildDrawingOrderCallback(mFanLayoutManager));

        (view.findViewById(R.id.logo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFanLayoutManager.collapseViews();

            }
        });

    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View view, int pos) {
        FullInfoTabFragment fragment = FullInfoTabFragment.newInstance(mAdapter.getModelByPos(pos));

        fragment.setSharedElementEnterTransition(new SharedTransitionSet());
        fragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        fragment.setSharedElementReturnTransition(new SharedTransitionSet());

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, "shared")
                .replace(R.id.root, fragment)
                .addToBackStack(null)
                .commit();
        mTourGuideHandler.cleanUp();

    }

    public void onClick(int pos) {
        FullInfoTabFragment fragment = FullInfoTabFragment.newInstance(mAdapter.getModelByPos(pos));
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root, fragment)
                .addToBackStack(null)
                .commit();
    }

    public boolean deselectIfSelected() {
        if (mFanLayoutManager.isItemSelected()) {
            mFanLayoutManager.deselectItem();
            return true;
        } else {
            return false;
        }
    }

}
