package com.example.jy.jieyou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseActivity;
import com.example.jy.jieyou.response.TwoListResponse;
import com.example.jy.jieyou.utils.SpaceItemDecoration;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageMouldActivity extends BaseActivity {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private TwoListResponse.Data mResult;

    public static void getInstance(Context context, TwoListResponse.Data item) {
        Intent intent = new Intent(context, MessageMouldActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BaseActivity.mDATA,item);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_mould_activity);
        ButterKnife.bind(this);
        mContext = this;
        ImmersionBar.with(this).navigationBarColor(R.color.white).statusBarDarkFont(true).init();
        titleCenterName.setText("短信模板");

        mResult = (TwoListResponse.Data) getIntent().getExtras().getSerializable(BaseActivity.mDATA);
        if (mResult == null) {
            return;
        }
        textViewTitle.setText(mResult.getTitle());

        initView();
        initClick();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10, 10, 10, 0));
        GridAdapter mAdapter = new GridAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mResult.getContent());
    }

    public class GridAdapter extends BaseQuickAdapter<TwoListResponse.Data.Content, BaseViewHolder> {
        private Context mContext;

        public GridAdapter(Context mContext) {
            super(R.layout.tab_two_content_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, TwoListResponse.Data.Content item) {
            TextView textViewName = helper.getView(R.id.textViewName);
            textViewName.setText(item.getText());
        }
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
