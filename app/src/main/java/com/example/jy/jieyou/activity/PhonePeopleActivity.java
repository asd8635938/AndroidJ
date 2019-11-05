package com.example.jy.jieyou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.jy.jieyou.R;
import com.example.jy.jieyou.base.BaseActivity;
import com.example.jy.jieyou.bean.MessageEvent;
import com.example.jy.jieyou.phone.CharacterParser;
import com.example.jy.jieyou.phone.ClearEditText;
import com.example.jy.jieyou.phone.PinyinComparator;
import com.example.jy.jieyou.phone.SideBar;
import com.example.jy.jieyou.phone.SortModel;
import com.example.jy.jieyou.utils.PhoneUtil;
import com.example.jy.jieyou.utils.SoftKeyboardUtils;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jhf on 2019/10/17.
 */

public class PhonePeopleActivity extends BaseActivity {

    @BindView(R.id.titleCenterName)
    TextView titleCenterName;
    @BindView(R.id.titleLeft)
    ImageView titleLeft;

    @BindView(R.id.sideBar)
    SideBar sideBar;
    @BindView(R.id.sortListView)
    ListView sortListView;
    @BindView(R.id.mClearEditText)
    ClearEditText mClearEditText;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.textViewSure)
    TextView textViewSure;
    @BindView(R.id.linearCheck1)
    LinearLayout linearCheck1;
    @BindView(R.id.linearCheck2)
    LinearLayout linearCheck2;
    @BindView(R.id.mCheckBox1)
    CheckBox mCheckBox1;
    @BindView(R.id.mCheckBox2)
    CheckBox mCheckBox2;

    private SortAdapter adapter;
    private List<SortModel> mResult = new ArrayList<>();

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    // 传递过来的保存的数据
    public static List<SortModel> mSortModel = new ArrayList<>();

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    public static void getInstance(Context context, List<SortModel> mResult) {
        Intent intent = new Intent(context, PhonePeopleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(mDATA, (Serializable) mResult);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_peopke_activity);
        ButterKnife.bind(this);
        ImmersionBar.with(this).navigationBarColor(R.color.white).statusBarDarkFont(true).init();
        titleCenterName.setText("通讯录");

        mSortModel = (List<SortModel>) getIntent().getExtras().getSerializable(mDATA);

        initView();

        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyboardUtils.hideSoftKeyboard(PhonePeopleActivity.this);
                finish();
            }
        });
    }

    private void initView() {
        try {
            PhoneUtil phoneUtil = new PhoneUtil(this);
            List<SortModel> mPhoneDtos = phoneUtil.getPhone();

//            SortModel sortModel = new SortModel();
//            sortModel.setTelPhone("1870000");
//            sortModel.setName("小王");
//            SortModel sortModel1 = new SortModel();
//            sortModel1.setTelPhone("1870111");
//            sortModel1.setName("小王11111");
//            SortModel sortModel2 = new SortModel();
//            sortModel2.setTelPhone("181");
//            sortModel2.setName("小成11111");
//            mPhoneDtos.add(sortModel);
//            mPhoneDtos.add(sortModel1);
//            mPhoneDtos.add(sortModel2);

            // 实例化汉字转拼音类
            characterParser = CharacterParser.getInstance();
            pinyinComparator = new PinyinComparator();

            sideBar.setTextView(dialog);
            // 设置右侧触摸监听
            sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
                @Override
                public void onTouchingLetterChanged(String s) {
                    // 该字母首次出现的位置
                    int position = adapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        sortListView.setSelection(position);
                    }
                }
            });

            SourceDateList = filledData(mPhoneDtos);
            // 根据a-z进行排序源数据
            Collections.sort(SourceDateList, pinyinComparator);
            adapter = new SortAdapter(this, SourceDateList);
            sortListView.setAdapter(adapter);

            // 根据输入框输入值的改变来过滤搜索
            mClearEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                    filterData(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            linearCheck1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCheckBox1.isChecked()) {
                        mCheckBox1.setChecked(false);
                        if (SourceDateList != null && SourceDateList.size() > 0 && adapter != null) {
                            for (int i = 0; i < SourceDateList.size(); i++) {
                                SourceDateList.get(i).setClick(false);
                            }
                            mResult.clear();
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        mCheckBox2.setChecked(false);
                        mCheckBox1.setChecked(true);
                        if (SourceDateList != null && SourceDateList.size() > 0 && adapter != null) {
                            for (int i = 0; i < SourceDateList.size(); i++) {
                                SourceDateList.get(i).setClick(true);
                            }
                            mResult.clear();
                            mResult.addAll(SourceDateList);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            linearCheck2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCheckBox2.isChecked()) {
                        mCheckBox2.setChecked(false);
                    } else {
                        mCheckBox1.setChecked(false);
                        mCheckBox2.setChecked(true);
                        if (SourceDateList != null && SourceDateList.size() > 0 && adapter != null) {
                            for (int i = 0; i < SourceDateList.size(); i++) {
                                SourceDateList.get(i).setClick(false);
                            }
                            mResult.clear();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            textViewSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessageEvent messageEvent = new MessageEvent();
                    messageEvent.setmResult(mResult);
                    EventBus.getDefault().post(messageEvent);
                    finish();
                }
            });

        } catch (Exception e) {
        }
    }

    private void initAdd() {
        mResult.clear();
        mResult.addAll((List<SortModel>) adapter.getItem(0));
        System.out.println("mResult = " + mResult);
    }

    public class SortAdapter extends BaseAdapter implements SectionIndexer {
        private List<SortModel> list;
        private Context mContext;

        final class ViewHolder {
            TextView tvLetter;
            TextView tvTitle;
            CheckBox mCheckBox;
            LinearLayout lineView;
        }

        public SortAdapter(Context mContext, List<SortModel> list) {
            this.mContext = mContext;
            this.list = list;
        }

        /**
         * 当ListView数据发生变化时,调用此方法来更新ListView
         *
         * @param list
         */
        public void updateListView(List<SortModel> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.list.size();
        }

        public Object getItem(int position) {
            return list;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup arg2) {
            final SortAdapter.ViewHolder viewHolder;
            final SortModel mContent = list.get(position);
            if (view == null) {
                viewHolder = new SortAdapter.ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.item_sort_listview, null);
                viewHolder.tvTitle = view.findViewById(R.id.title);
                viewHolder.tvLetter = view.findViewById(R.id.catalog);
                viewHolder.mCheckBox = view.findViewById(R.id.mCheckBox);
                viewHolder.lineView = view.findViewById(R.id.lineView);
                view.setTag(viewHolder);
            } else {
                viewHolder = (SortAdapter.ViewHolder) view.getTag();
            }

            // 根据position获取分类的首字母的Char ascii值
            int section = getSectionForPosition(position);

            // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (position == getPositionForSection(section)) {
                viewHolder.tvLetter.setVisibility(View.VISIBLE);
                viewHolder.tvLetter.setText(mContent.getSortLetters());
            } else {
                viewHolder.tvLetter.setVisibility(View.GONE);
            }

            viewHolder.tvTitle.setText(this.list.get(position).getName());

            if (list.get(position).isClick()) {
                viewHolder.mCheckBox.setChecked(true);
            } else {
                viewHolder.mCheckBox.setChecked(false);
            }

            viewHolder.lineView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SoftKeyboardUtils.hideSoftKeyboard(PhonePeopleActivity.this);
                    if (mContent.isClick()) {
                        viewHolder.mCheckBox.setChecked(false);
                        mContent.setClick(false);
                        initAdd();
                    } else {
                        viewHolder.mCheckBox.setChecked(true);
                        mContent.setClick(true);
                        initAdd();
                    }
                }
            });
            return view;
        }

        /**
         * 根据ListView的当前位置获取分类的首字母的Char ascii值
         */
        public int getSectionForPosition(int position) {
            return list.get(position).getSortLetters().charAt(0);
        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        public int getPositionForSection(int section) {
            for (int i = 0; i < getCount(); i++) {
                String sortStr = list.get(i).getSortLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 提取英文的首字母，非英文字母用#代替。
         *
         * @param str
         * @return
         */
        private String getAlpha(String str) {
            String sortStr = str.trim().substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortStr.matches("[A-Z]")) {
                return sortStr;
            } else {
                return "#";
            }
        }

        @Override
        public Object[] getSections() {
            return null;
        }
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<SortModel> date) {
        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getName());
            sortModel.setNumb(i);
            sortModel.setTelPhone(date.get(i).getTelPhone());

            if (mSortModel.size() > 0) {
                for (int j = 0; j < mSortModel.size(); j++) {
                    if (date.get(i).getTelPhone().equals(mSortModel.get(j).getTelPhone())) {
                        if (mSortModel.get(j).isClick()) {
                            sortModel.setClick(true);
                            break;
                        }
                    }
                }
            }

            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
}
