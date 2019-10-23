package com.example.jy.jieyou.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;
import com.example.jy.jieyou.R;
import com.example.jy.jieyou.VideoWebActivity;
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
import com.example.jy.jieyou.utils.SoftKeyboardUtils;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

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
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.imageViewFile)
    ImageView imageViewFile;

    private String mTime = ""; // 定时时间
    private int mStringLength = 0; // 收件人联系电话长度
    private List<SortModel> mSortModels = new ArrayList<>();

    private static final int REQUEST_CODE_CHOOSE = 23;

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

        imageViewFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                VideoWebActivity.mUrlString = editextFilePeople.getText().toString();
//                VideoWebActivity.getInstance(getActivity());
                FilePicker.from(OneTabFragment.this)
                        .chooseForMimeType()
                        .setMaxCount(1)
                        .setFileTypes("vcf", "txt")
                        .requestCode(REQUEST_CODE_CHOOSE)
                        .start();
            }
        });

        if (!editTextFileContent.getText().toString().isEmpty()) {
            textViewYiXuan.setText(editTextFileContent.length() + "");
            textViewShengYu.setText(70 - editTextFileContent.length() + "");
        }

        editTextFileContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                initScoll();
            }
        });

        editextFilePeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    if (editable.toString().contains(",")) {
                        String[] split = editable.toString().split(",");
                        mStringLength = split.length;
                        textViewFilePeopleAll.setText("已选" + split.length + "位");
                    } else {
                        textViewFilePeopleAll.setText("已选1位");
                    }
                } else {
                    textViewFilePeopleAll.setText("已选0位");
                }
            }
        });

        editTextFileContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
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
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH), startDate.get(Calendar.HOUR_OF_DAY), startDate.get(Calendar.MINUTE), startDate.get(Calendar.SECOND));
        endDate.set(2100, 1, 1);

        linearTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mTime = DateUtils.getDateStr(date, "");
                        mCheckBox.setChecked(true);
                    }
                }).addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTime = "";
                        mCheckBox.setChecked(false);
                    }
                }).setType(new boolean[]{true, true, true, true, true, true}).setLabel("年", "月", "日", "时", "分", "秒").setRangDate(startDate, endDate).build();
                pvTime.show();
            }
        });

        textViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editextFilePeople.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "请选择收件人", Toast.LENGTH_SHORT).show();
                    return;
                } else if (editTextFileContent.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "请输入发送内容", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, Object> mHashMap = new HashMap<>();
                Request request = new Request();
                mHashMap.put("action", "send");
                mHashMap.put("mobile", editextFilePeople.getText().toString());
                mHashMap.put("userid", "51");
                mHashMap.put("timestamp", request.getTimestamp());
                mHashMap.put("sendTime", mTime);
                mHashMap.put("content", editTextFileContent.getText().toString());
                mHashMap.put("sign", request.getSign());

                NetManager.http_post_map(URL.actionSend, mHashMap, "", "", new onNetCallbackListener() {
                    @Override
                    public void onSuccess(String requestStr, String result) {
                        super.onSuccess(requestStr, result);
                        Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
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

        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHOOSE) {
            ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
            StringBuilder builder = new StringBuilder();
            for (EssFile file : essFileList) {
                builder.append(file.getMimeType()).append(" | ").append(file.getName()).append("\n\n");
            }
            if (builder.toString().contains(".vcf")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(builder.toString())),"text/x-vcard");
                startActivity(intent);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent mResult) {
        mSortModels = mResult.getmResult();
        String mString = "";
        for (int i = 0; i < mSortModels.size(); i++) {
            if (mSortModels.get(i).isClick()) {
                mString = mString + mSortModels.get(i).getTelPhone() + ",";
            }
        }

        if (mString.isEmpty()) {
            if (editextFilePeople.getText().toString().isEmpty()) {
                editextFilePeople.setText("");
                textViewFilePeopleAll.setText("已选0位");
            } else {
                String mReplace = "";
                for (int i = 0; i < PhonePeopleActivity.mSortModel.size(); i++) {
                    if (editextFilePeople.getText().toString().contains(PhonePeopleActivity.mSortModel.get(i).getTelPhone() + ",")) {
                        mReplace = editextFilePeople.getText().toString().replace(PhonePeopleActivity.mSortModel.get(i).getTelPhone() + ",", "");
                        editextFilePeople.setText(mReplace);
                    } else if (editextFilePeople.getText().toString().contains(PhonePeopleActivity.mSortModel.get(i).getTelPhone())) {
                        mReplace = editextFilePeople.getText().toString().replace(PhonePeopleActivity.mSortModel.get(i).getTelPhone(), "");
                        editextFilePeople.setText(mReplace);
                    }
                }
                if (!mReplace.isEmpty() && mReplace.endsWith(",")) {
                    editextFilePeople.setText(mReplace.substring(0, mReplace.length() - 1));
                } else {
                    editextFilePeople.setText(mReplace);
                }
            }
        } else {
            if (editextFilePeople.getText().toString().isEmpty()) {
                editextFilePeople.setText(mString.trim().substring(0, mString.length() - 1));
            } else {
                if (editextFilePeople.getText().toString().endsWith(",")) {
                    editextFilePeople.setText(editextFilePeople.getText().toString() + mString.trim().substring(0, mString.length() - 1));
                } else {
                    editextFilePeople.setText(editextFilePeople.getText().toString() + "," + mString.trim().substring(0, mString.length() - 1));
                }
            }
            textViewFilePeopleAll.setText("已选" + mStringLength + "位");
        }
    }

    private void initView() {
        initViewPager(new ArrayList<String>());
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhonePeopleActivity.getInstance(getActivity(), mSortModels);
            }
        });
    }

    private void initViewPager(ArrayList<String> bannerList) {
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
                SoftKeyboardUtils.hideSoftKeyboard(getActivity());
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

    private void initScoll() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    @Override
    public boolean immersionBarEnabled() {
        return true;
    }
}
