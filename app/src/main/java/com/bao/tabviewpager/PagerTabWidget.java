package com.bao.tabviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TabWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类可以绑定TabWidget与ViewPager
 */
public class PagerTabWidget extends TabWidget implements View.OnClickListener,ViewPager.OnPageChangeListener{
    protected ViewPager mViewPager;
    protected final List<View> tabViews  = new ArrayList<View>();
    protected Context mContext;
    protected OnTabSelectedListener mOnTabSelectedListener;

    public void setmOnTabSelectedListener(OnTabSelectedListener mOnTabSelectedListener) {
        this.mOnTabSelectedListener = mOnTabSelectedListener;
    }

    public PagerTabWidget(Context context) {
        super(context);
        init(context);
    }

    public PagerTabWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PagerTabWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context){
        this.mContext = context;
        //tabViews = new ArrayList<View>();
    }


    public void setDividerInvisible(){
        setDividerDrawable(R.color.no);
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    public void setmViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        this.mViewPager.setOnPageChangeListener(this);
        if(tabViews.size() > 0) setCurrentTab(0);
    }

    public void addTab(View view){
        //此处需要注意先后顺序，需先addView到Layout再进行事件的绑定
        tabViews.add(view);
        addView(view);
        view.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(mViewPager != null){
            mViewPager.setCurrentItem(tabViews.indexOf(v));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        setCurrentTab(position);
        mOnTabSelectedListener.onSelected(tabViews,position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 该监听器用于监听选中Tab时View的变化
     */
    public static  interface OnTabSelectedListener {

        void onSelected(List<View> tabViews, int position);

    }


}
