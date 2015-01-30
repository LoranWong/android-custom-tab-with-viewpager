package com.bao.tabviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {
    PagerTabWidget mTabWidget;
    ViewPager mViewPager;
    HomePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    /**
     * initial tab content and bind it to the pager
     */
    private void initTab() {
        mTabWidget = (PagerTabWidget) findViewById(R.id.home_tabWidget);
        mTabWidget.setDividerInvisible();
        mTabWidget.addTab(LayoutInflater.from(this).inflate(R.layout.tab_home, null));
        mTabWidget.addTab(LayoutInflater.from(this).inflate(R.layout.tab_shop, null));
        mTabWidget.addTab(LayoutInflater.from(this).inflate(R.layout.tab_me, null));

        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mPagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        mTabWidget.setmViewPager(mViewPager);
        mTabWidget.setmOnTabSelectedListener(new PagerTabWidget.OnTabSelectedListener() {
            @Override
            public void onSelected(List<View> tabViews, int position) {
                Toast.makeText(getApplicationContext(), "tab" + (position+1) + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class HomePagerAdapter extends FragmentPagerAdapter {

        private Random random = new Random();
        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override public Fragment getItem(int i) {
            //LogUtils.i(" getitem i="+i);
            return ImageFragment.newInstance(i);
        }

        @Override public int getCount() {
            return 3;
        }
    }

}
