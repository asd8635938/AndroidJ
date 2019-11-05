package com.example.jy.jieyou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends BaseActivity {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    private Context mContext;

    public static void getInstance(Context context) {
        Intent intent = new Intent(context, HelpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);
        ButterKnife.bind(this);
        mContext = this;
        ImmersionBar.with(this).navigationBarColor(R.color.white).statusBarDarkFont(true).init();
        titleCenterName.setText("帮助及说明");

        initView();
        initClick();
    }

    private void initView() {
    }

    private void initClick() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
