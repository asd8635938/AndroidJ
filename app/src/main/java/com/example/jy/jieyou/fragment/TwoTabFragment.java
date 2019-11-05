package com.example.jy.jieyou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.jy.jieyou.activity.MessageMouldActivity;
import com.example.jy.jieyou.base.BaseFragment;
import com.example.jy.jieyou.response.TwoListResponse;
import com.example.jy.jieyou.utils.SpaceItemDecoration;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhf on 2019/10/16.
 */

public class TwoTabFragment extends BaseFragment implements SimpleImmersionOwner {

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
        View mView = inflater.inflate(R.layout.two_tab_fragment, null);
        ButterKnife.bind(this, mView);
        titleCenterName.setText("短信模板");
        titleLeft.setVisibility(View.GONE);
        initView();
        return mView;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CircleListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        List<TwoListResponse.Data> data = initContent1();

        mAdapter.setNewData(data);
    }

    private List<TwoListResponse.Data> initContent1() {
        List<TwoListResponse.Data> mList1 = new ArrayList<>();
        List<TwoListResponse.Data.Content> mListContent1 = new ArrayList<>();
        TwoListResponse.Data.Content mContent1 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent2 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent3 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent4 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent5 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent6 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent7 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent8 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent9 = new TwoListResponse.Data.Content();
        mContent1.setText("招生短信");
        mContent2.setText("餐饮短信");
        mContent3.setText("汽车销售");
        mContent4.setText("招生短信");
        mContent5.setText("餐饮短信");
        mContent6.setText("汽车销售");
        mContent7.setText("招生短信");
        mContent8.setText("餐饮短信");
        mContent9.setText("汽车销售");
        mListContent1.add(mContent1);
        mListContent1.add(mContent2);
        mListContent1.add(mContent3);
        mListContent1.add(mContent4);
        mListContent1.add(mContent5);
        mListContent1.add(mContent6);
        mListContent1.add(mContent7);
        mListContent1.add(mContent8);
        mListContent1.add(mContent9);

        List<TwoListResponse.Data.Content> mListContent2 = new ArrayList<>();
        TwoListResponse.Data.Content mContent11 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent22 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent33 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent44 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent55 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent66 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent77 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent88 = new TwoListResponse.Data.Content();
        TwoListResponse.Data.Content mContent99 = new TwoListResponse.Data.Content();
        mContent11.setText("春节");
        mContent22.setText("元旦");
        mContent33.setText("清明");
        mContent44.setText("端午");
        mContent55.setText("七夕");
        mContent66.setText("中元");
        mContent77.setText("中秋");
        mContent88.setText("重阳");
        mContent99.setText("腊八");
        mListContent2.add(mContent11);
        mListContent2.add(mContent22);
        mListContent2.add(mContent33);
        mListContent2.add(mContent44);
        mListContent2.add(mContent55);
        mListContent2.add(mContent66);
        mListContent2.add(mContent77);
        mListContent2.add(mContent88);
        mListContent2.add(mContent99);

        TwoListResponse.Data mData1 = new TwoListResponse.Data();
        mData1.setTitle("广告营销");
        mData1.setContent(mListContent1);
        TwoListResponse.Data mData2 = new TwoListResponse.Data();
        mData2.setTitle("节日祝福");
        mData2.setContent(mListContent2);

        mList1.add(mData1);
        mList1.add(mData2);
        return mList1;
    }

    private class CircleListAdapter extends BaseQuickAdapter<TwoListResponse.Data, BaseViewHolder> {
        private Context mContext;

        public CircleListAdapter(Context mContext) {
            super(R.layout.two_tab_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, final TwoListResponse.Data item) {
            TextView textViewTitle = helper.getView(R.id.textViewTitle);
            TextView textViewMore = helper.getView(R.id.textViewMore);
            RecyclerView adapterRecyclerView = helper.getView(R.id.adapterRecyclerView);

            textViewTitle.setText(item.getTitle());

            textViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageMouldActivity.getInstance(mContext,item);
                }
            });

            adapterRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            adapterRecyclerView.addItemDecoration(new SpaceItemDecoration(10, 10, 10, 0));
            GridAdapter gridAdapter = new GridAdapter(mContext);
            adapterRecyclerView.setAdapter(gridAdapter);
            gridAdapter.setNewData(item.getContent());
        }
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
            textViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
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
