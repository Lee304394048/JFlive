package com.bwie.jf.jflive.fragment;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.jf.jflive.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee_peng on 2016/9/26.
 */
public class HomePageFragment extends Fragment {
    private RadioGroup rg;
    private ViewPager vp;
    private List<Fragment> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        rg= (RadioGroup) view.findViewById(R.id.home_rg);
        vp= (ViewPager) view.findViewById(R.id.home_vp);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //franment添加到list集合
        fragmentAdd();
        //viewpager的适配
        viewpagerAdapter();
        //viewpager的监听
        viewpagerListener();
        //RadioButton的监听
        for(int i=0;i<list.size();i++){
            RadioButton rb= (RadioButton) rg.getChildAt(i);
            final int j=i;
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @TargetApi(Build.VERSION_CODES.DONUT)
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    vp.setCurrentItem(j);
                }
            });
        }
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    private void viewpagerListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<list.size();i++){
                    RadioButton rb= (RadioButton) rg.getChildAt(i);
                    if(position==i){
                        rb.setChecked(true);
                        rb.setTextColor(Color.RED);
                    }else{
                        rb.setChecked(false);
                        rb.setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    private void viewpagerAdapter() {
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list!=null?list.size():0;
            }
        });
    }

    private void fragmentAdd() {
        list=new ArrayList<Fragment>();
        FocusFragment focus=new FocusFragment();
        HotFragment hot=new HotFragment();
        NewestFragment news=new NewestFragment();

        list.add(focus);
        list.add(hot);
        list.add(news);
    }
}
