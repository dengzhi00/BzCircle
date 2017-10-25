package com.dzm.prize;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dzm.prize.fm.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> list = new ArrayList<>();
        list.add(MyFragment.newInstance());
        list.add(MyFragment.newInstance());
        list.add(MyFragment.newInstance());
        BzaiCircleView bcv_test = (BzaiCircleView) findViewById(R.id.bcv_test);
        ViewPager vp_tst = (ViewPager) findViewById(R.id.vp_tst);
        vp_tst.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),list));
        bcv_test.setViewPager(vp_tst);
    }



    private class MyFragmentAdapter extends FragmentPagerAdapter{

        private List<Fragment> list;

        public MyFragmentAdapter(FragmentManager fm,List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
