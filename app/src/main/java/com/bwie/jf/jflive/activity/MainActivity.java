package com.bwie.jf.jflive.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.bwie.jf.jflive.R;
import com.bwie.jf.jflive.fragment.HomePageFragment;
import com.bwie.jf.jflive.fragment.MineFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private RadioButton rb_1,rb_2;
    private RelativeLayout relout;
    private HomePageFragment homepage;
    private MineFragment mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        init();
    }

    private void init() {
        rb_1= (RadioButton) findViewById(R.id.main_rb_1);
        rb_2= (RadioButton) findViewById(R.id.main_rb_2);
        relout= (RelativeLayout) findViewById(R.id.main_relout);

        rb_1.setOnClickListener(this);
        rb_2.setOnClickListener(this);
        //显示隐藏fragment并提交
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        homepage=new HomePageFragment();
        mine=new MineFragment();
        ft.add(R.id.main_relout,homepage,"homepage").show(homepage).add(R.id.main_relout,mine,"mine").hide(mine);
        ft.commit();
    }

    //radiogroup监听事件
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.main_rb_1:
                FragmentManager fm1=getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.show(homepage).hide(mine).commit();
                rb_1.setTextColor(Color.RED);
                rb_1.setChecked(true);
                rb_2.setChecked(false);
                rb_2.setTextColor(Color.BLACK);
                break;
            case R.id.main_rb_2:
                FragmentManager fm2=getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();
                ft2.show(mine).hide(homepage).commit();
                rb_2.setTextColor(Color.RED);
                rb_2.setChecked(true);
                rb_1.setChecked(false);
                rb_1.setTextColor(Color.BLACK);
                break;
            default :

                break;
        }
    }
}
