package com.example.jy.jieyou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseFragment;
import com.example.jy.jieyou.response.ThreeListResponse;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhf on 2019/10/16.
 */

public class ThreeTabFragment extends BaseFragment implements SimpleImmersionOwner {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private CircleListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.three_tab_fragment, null);
        ButterKnife.bind(this, mView);
        titleCenterName.setText("发现");
        titleLeft.setVisibility(View.GONE);

        initView();

        return mView;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CircleListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        View mHeaderView = getLayoutInflater().inflate(R.layout.three_tab_header_view, null);
        mAdapter.setHeaderView(mHeaderView);
        mAdapter.setHeaderAndEmpty(true);

        ArrayList<ThreeListResponse.Data> mList = new ArrayList<>();
        ThreeListResponse.Data data1 = new ThreeListResponse.Data();
        data1.setString1("最新优惠分享领取会员了，只要转 发即可领取3天会员！");
        data1.setString2("2019-9-24");
        data1.setImage(R.mipmap.three_tab_image3);
        ThreeListResponse.Data data2 = new ThreeListResponse.Data();
        data2.setString1("最新优惠分享领取会员了，只要转 发即可领取3天会员！");
        data2.setString2("2019-9-24");
        data2.setImage(R.mipmap.three_tab_image4);
        ThreeListResponse.Data data3 = new ThreeListResponse.Data();
        data3.setString1("最新优惠分享领取会员了，只要转 发即可领取3天会员！");
        data3.setString2("2019-9-24");
        data3.setImage(R.mipmap.three_tab_image5);
        ThreeListResponse.Data data4 = new ThreeListResponse.Data();
        data4.setString1("最新优惠分享领取会员了，只要转 发即可领取3天会员！");
        data4.setString2("2019-9-24");
        data4.setImage(R.mipmap.three_tab_image6);
        mList.add(data1);
        mList.add(data2);
        mList.add(data3);
        mList.add(data4);
        mAdapter.setNewData(mList);
    }

    private class CircleListAdapter extends BaseQuickAdapter<ThreeListResponse.Data, BaseViewHolder> {
        private Context mContext;

        public CircleListAdapter(Context mContext) {
            super(R.layout.three_tab_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, ThreeListResponse.Data item) {
            TextView mTextView1 = helper.getView(R.id.mTextView1);
            ImageView mImageView = helper.getView(R.id.mImageView);
            TextView mTextView2 = helper.getView(R.id.mTextView2);

            mTextView1.setText(item.getString1());
            mTextView2.setText(item.getString2());
            mImageView.setImageResource(item.getImage());

        }
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
