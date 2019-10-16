package com.example.jy.jieyou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseFragment;
import com.example.jy.jieyou.manager.Image;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.one_tab_fragment, null);
        ButterKnife.bind(this, mView);
        titleCenterName.setText("短信群发");
        titleLeft.setVisibility(View.GONE);
        initView();
        return mView;
    }

    private void initView() {
        initViewPager(new ArrayList<String>());
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
    public boolean immersionBarEnabled() {
        return true;
    }
}
