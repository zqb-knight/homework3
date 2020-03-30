package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;
    private RecyclerView mNumbersListView;
    private CardAdapter mAdapter;
    private AnimatorSet animatorSet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件


        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animationView = getView().findViewById(R.id.animation_view);

//        /*
//         * Use this setting to improve performance if you know that changes in content do not
//         * change the child layout size in the RecyclerView
//         */
//        mNumbersListView.setHasFixedSize(true);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行

                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                //淡出
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(animationView,"alpha",1,0f);

                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(500);

                //淡入
                mNumbersListView  = getView().findViewById(R.id.rv_numbers);
                mNumbersListView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                mAdapter = new CardAdapter(5);

                mNumbersListView.setAdapter(mAdapter);
                ObjectAnimator alphaAnimatorOpen = ObjectAnimator.ofFloat(mNumbersListView,"alpha",0,1f);

                alphaAnimatorOpen.setInterpolator(new LinearInterpolator());
                alphaAnimatorOpen.setDuration(500);

                animatorSet = new AnimatorSet();
                animatorSet.playTogether(alphaAnimator,alphaAnimatorOpen);
                animatorSet.start();


            }
        }, 5000);
    }
}
