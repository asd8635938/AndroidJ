package com.example.jy.jieyou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseActivity;
import com.example.jy.jieyou.response.InvestMoneyResponse;
import com.example.jy.jieyou.utils.SpaceItemDecoration;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestMoney extends BaseActivity {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;
    @BindView(R.id.titleLine)
    View titleLine;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private List<InvestMoneyResponse.Data> mList = new ArrayList<>();
    private GridAdapter mAdapter;

    public static void getInstance(Context context) {
        Intent intent = new Intent(context, InvestMoney.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invest_money);
        ButterKnife.bind(this);
        mContext = this;
        ImmersionBar.with(this).navigationBarColor(R.color.white).statusBarDarkFont(true).init();
        titleCenterName.setText("充值");
        titleLine.setVisibility(View.GONE);

        initView();
        initClick();
    }

    private void initView() {
        mList = new ArrayList<>();
        InvestMoneyResponse.Data mData1 = new InvestMoneyResponse.Data();
        InvestMoneyResponse.Data mData2 = new InvestMoneyResponse.Data();
        InvestMoneyResponse.Data mData3 = new InvestMoneyResponse.Data();
        InvestMoneyResponse.Data mData4 = new InvestMoneyResponse.Data();
        InvestMoneyResponse.Data mData5 = new InvestMoneyResponse.Data();
        mData1.setMoney("36元");
        mData1.setMessage("500条短信");
        mData2.setMoney("36元");
        mData2.setMessage("500条短信");
        mData3.setMoney("36元");
        mData3.setMessage("500条短信");
        mData4.setMoney("36元");
        mData4.setMessage("500条短信");
        mData5.setMoney("36元");
        mData5.setMessage("500条短信");
        mList.add(mData1);
        mList.add(mData2);
        mList.add(mData3);
        mList.add(mData4);
        mList.add(mData5);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20, 20, 40, 0));
        mAdapter = new GridAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mList);

        View mFootView = getLayoutInflater().inflate(R.layout.invest_money_foot_view, null);
        TextView textViewMore = mFootView.findViewById(R.id.textViewMore);
        mAdapter.setFooterView(mFootView);
    }

    private void initClick() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public class GridAdapter extends BaseQuickAdapter<InvestMoneyResponse.Data, BaseViewHolder> {
        private Context mContext;

        public GridAdapter(Context mContext) {
            super(R.layout.invest_money_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(final BaseViewHolder helper, final InvestMoneyResponse.Data item) {
            TextView textViewMoney = helper.getView(R.id.textViewMoney);
            TextView textViewMessage = helper.getView(R.id.textViewMessage);
            final RelativeLayout relativeView = helper.getView(R.id.relativeView);

            if (item.isClick()) {
                relativeView.setBackgroundResource(R.drawable.shape_rect_check);
            } else {
                relativeView.setBackgroundResource(R.drawable.shape_rect_no_check);
            }

            textViewMoney.setText(item.getMoney());
            textViewMessage.setText(item.getMessage());

            relativeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.isClick()) {
                        for (int i = 0; i < mAdapter.getData().size();i++) {
                            if (helper.getAdapterPosition() == i) {
                                mAdapter.getData().get(i).setClick(false);
                                break;
                            }
                        }
                        notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < mAdapter.getData().size();i++) {
                            relativeView.setBackgroundResource(R.drawable.shape_rect_check);
                            if (helper.getAdapterPosition() == i) {
                                mAdapter.getData().get(i).setClick(true);
                            } else {
                                mAdapter.getData().get(i).setClick(false);
                            }
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
