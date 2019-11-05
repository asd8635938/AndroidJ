package com.example.jy.jieyou.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jy.jieyou.R;
import com.example.jy.jieyou.activity.HelpActivity;
import com.example.jy.jieyou.activity.InvestMoney;
import com.example.jy.jieyou.activity.SettingActivity;
import com.example.jy.jieyou.base.BaseFragment;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhf on 2019/10/16.
 */

public class FourTabFragment extends BaseFragment implements SimpleImmersionOwner {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    @BindView(R.id.imageViewHeader)
    ImageView imageViewHeader;

    @BindView(R.id.lineViewInMoney)
    LinearLayout lineViewInMoney;
    @BindView(R.id.lineViewSendMessage)
    LinearLayout lineViewSendMessage;
    @BindView(R.id.lineViewSign)
    LinearLayout lineViewSign;
    @BindView(R.id.lineViewAddressBook)
    LinearLayout lineViewAddressBook;
    @BindView(R.id.lineViewSharDiscount)
    LinearLayout lineViewSharDiscount;
    @BindView(R.id.lineViewAboutApp)
    LinearLayout lineViewAboutApp;
    @BindView(R.id.lineViewHelp)
    LinearLayout lineViewHelp;
    @BindView(R.id.lineViewSetting)
    LinearLayout lineViewSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.four_tab_fragment, null);
        ButterKnife.bind(this,mView);
        titleCenterName.setText("我的");
        titleLeft.setVisibility(View.GONE);

        initView();
        initClick();

        return mView;
    }

    private void initView() {

    }

    private void initClick() {
        imageViewHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        lineViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpActivity.getInstance(getActivity());
            }
        });

        lineViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.getInstance(getActivity());
            }
        });

        lineViewInMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InvestMoney.getInstance(getActivity());
            }
        });
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .navigationBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }
}
