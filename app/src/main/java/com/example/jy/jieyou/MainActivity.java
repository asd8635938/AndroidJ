package com.example.jy.jieyou;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.jy.jieyou.base.BaseActivity;
import com.example.jy.jieyou.fragment.FourTabFragment;
import com.example.jy.jieyou.fragment.OneTabFragment;
import com.example.jy.jieyou.fragment.ThreeTabFragment;
import com.example.jy.jieyou.fragment.TwoTabFragment;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    EasyNavigationBar navigationBar;

    private String[] tabText = {"短信群发", "短信模板", "发现", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_un_1, R.mipmap.tab_un_2, R.mipmap.tab_un_3, R.mipmap.tab_un_4};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_on_1, R.mipmap.tab_on_2, R.mipmap.tab_on_3, R.mipmap.tab_on_4};

    private List<android.support.v4.app.Fragment> fragments = new ArrayList<>();

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this).navigationBarColor(R.color.white).statusBarDarkFont(true).init();
        ButterKnife.bind(this);
        mContext = MainActivity.this;

        initPermiss();
        initView();
    }

    private void initView() {
        fragments.add(new OneTabFragment());
        fragments.add(new TwoTabFragment());
        fragments.add(new ThreeTabFragment());
        fragments.add(new FourTabFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .canScroll(true)    //Viewpager能否左右滑动
                .navigationBackground(getResources().getColor(R.color.white))
                .normalTextColor(getResources().getColor(R.color.tab_un_blue))
                .selectTextColor(getResources().getColor(R.color.tab_blue))
                .fragmentManager(getSupportFragmentManager())
                .build();
    }

    private void initPermiss() {
        XXPermissions.with(this)
                .permission(Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_CONTACTS
                )
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
