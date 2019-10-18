package com.example.jy.jieyou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.jy.jieyou.R;
import com.example.jy.jieyou.activity.PhonePeopleActivity;
import com.example.jy.jieyou.base.BaseFragment;
import com.example.jy.jieyou.bean.MessageEvent;
import com.example.jy.jieyou.manager.Image;
import com.example.jy.jieyou.manager.NetManager;
import com.example.jy.jieyou.manager.URL;
import com.example.jy.jieyou.manager.onNetCallbackListener;
import com.example.jy.jieyou.phone.SortModel;
import com.example.jy.jieyou.request.Request;
import com.example.jy.jieyou.utils.DateUtils;
import com.example.jy.jieyou.view.EditTextWithScrollView;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhf on 2019/10/16.
 */

public class OneTabFragment extends BaseFragment implements SimpleImmersionOwner {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.imageViewAdd)
    ImageView imageViewAdd;
    @BindView(R.id.editextFilePeople)
    EditText editextFilePeople;
    @BindView(R.id.textViewPost)
    TextView textViewPost;
    @BindView(R.id.mCheckBox)
    CheckBox mCheckBox;
    @BindView(R.id.editTextFileContent)
    EditTextWithScrollView editTextFileContent;
    @BindView(R.id.textViewFilePeopleAll)
    TextView textViewFilePeopleAll;
    @BindView(R.id.textViewYiXuan)
    TextView textViewYiXuan;
    @BindView(R.id.textViewShengYu)
    TextView textViewShengYu;
    @BindView(R.id.linearTime)
    LinearLayout linearTime;

    private String mTime; // 定时时间

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.one_tab_fragment, null);
        ButterKnife.bind(this, mView);
        EventBus.getDefault().register(this);
        titleCenterName.setText("短信群发");
        titleLeft.setVisibility(View.GONE);
        initView();
        initClick();
        return mView;
    }

    private void initClick() {
        editTextFileContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                textViewYiXuan.setText(count + start + "");
                textViewShengYu.setText(70 - start - count + "");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        final Calendar startDate = Calendar.getInstance();
        final Calendar endDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),startDate.get(Calendar.MONTH),startDate.get(Calendar.DAY_OF_MONTH),startDate.get(Calendar.HOUR_OF_DAY),startDate.get(Calendar.MINUTE),startDate.get(Calendar.SECOND));
        endDate.set(2030,1,1);

        linearTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTime = DateUtils.getDateStr(date,"");
                        mCheckBox.setChecked(true);
                    }
                }).addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTime = "";
                        mCheckBox.setChecked(false);
                    }
                }).setType(new boolean[]{true, true, true, true, true, true}).setLabel("年","月","日","时","分","秒").setRangDate(startDate,endDate).build();
                pvTime.show();
            }
        });

        textViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editextFilePeople.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "请选择收件人", Toast.LENGTH_SHORT).show();
                } else if (editTextFileContent.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "请输入发送内容", Toast.LENGTH_SHORT).show();
                }

                Request request = new Request();
                request.setMobile("18701134427,18101134427");
                request.setContent("测试发送");

                NetManager.http_post(URL.actionSend, "", request, new onNetCallbackListener() {
                    @Override
                    public void onSuccess(String requestStr, String result) {
                        super.onSuccess(requestStr, result);
                        System.out.println("result = " + result);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        System.out.println("throwable = " + throwable);
                    }
                });
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent mResult) {
        List<SortModel> mSortModels = mResult.getmResult();
        String mString = "";
        int mInt = 0;
        for (int i = 0; i < mSortModels.size(); i++) {
            if (mSortModels.get(i).isClick()) {
                mInt++;
                mString = mString + mSortModels.get(i).getTelPhone() + ",";
            }
        }
        if (mString.isEmpty()) {
            editextFilePeople.setText("");
            textViewFilePeopleAll.setText("已选0位");
        } else {
            editextFilePeople.setText(mString.trim().substring(0, mString.length() - 1));
            textViewFilePeopleAll.setText("已选" + mInt + "位");
        }
    }

    private void initView() {
        initViewPager(new ArrayList<String>());
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhonePeopleActivity.getInstance(getActivity());
            }
        });
    }

    private void initViewPager(ArrayList<String> bannerList) {
        bannerList.clear();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571216113084&di=18bc1d5bc366d02fcc85f1aac33426be&imgtype=0&src=http%3A%2F%2Fn4.cmsfile.pg0.cn%2Fgroup1%2FM00%2F06%2FD3%2FCgqg2FWkYlaABioBACROPiWwOKM327.jpg");
        bannerList.add("http://img1.qunarzz.com/travel/d3/1609/19/681801e832a9f0b5.jpg");
        bannerList.add("http://e.hiphotos.baidu.com/zhidao/pic/item/1e30e924b899a901e0621e251c950a7b0208f54a.jpg");
        bannerList.add("http://g.hiphotos.baidu.com/zhidao/pic/item/1f178a82b9014a90b02fcd13a9773912b31bee0f.jpg");
        banner.setImages(bannerList);
        // 设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titleList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        // item点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Image.errImage(context, (String) path, R.mipmap.test_1, imageView);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    public boolean immersionBarEnabled() {
        return true;
    }
}
