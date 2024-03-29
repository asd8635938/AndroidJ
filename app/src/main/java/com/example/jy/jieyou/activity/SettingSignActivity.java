package com.example.jy.jieyou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseActivity;
import com.example.jy.jieyou.utils.SPUtils;
import com.example.jy.jieyou.view.EditTextWithScrollView;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingSignActivity extends BaseActivity {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    @BindView(R.id.textViewPost)
    Button textViewPost;
    @BindView(R.id.editTextFileContent)
    EditTextWithScrollView editTextFileContent;

    private Context mContext;

    public static void getInstance(Context context) {
        Intent intent = new Intent(context, SettingSignActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_sign_activity);
        ButterKnife.bind(this);
        mContext = this;
        ImmersionBar.with(this).navigationBarColor(R.color.white).statusBarDarkFont(true).init();
        titleCenterName.setText("设置签名");

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

        textViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextFileContent.getText().toString().isEmpty()) {
                    Toast.makeText(mContext,"请输入签名信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                SPUtils.put(mContext,mSpSettingContent,editTextFileContent.getText().toString());
                EventBus.getDefault().post(mEventSettingSign);
                finish();
            }
        });
    }
}
