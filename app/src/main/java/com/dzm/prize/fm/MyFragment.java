package com.dzm.prize.fm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dzm.prize.R;

/**
 * Created by 83642 on 2017/10/24.
 */

public class MyFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my,null);
    }

    public static Fragment newInstance(){
        return new MyFragment();
    }
}
