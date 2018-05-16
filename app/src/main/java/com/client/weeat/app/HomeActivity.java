package com.client.weeat.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.client.weeat.R;
import com.client.weeat.base.BaseFragment;
import com.client.weeat.community.fragment.CommunityFragment;
import com.client.weeat.friend.fragment.FriendFragment;
import com.client.weeat.home.fragment.HomeFragment;
import com.client.weeat.message.fragment.MessageFragment;
import com.client.weeat.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout mFrameLayout;

    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    private ArrayList<BaseFragment> fragments;  //多个Fragment的实例集合
    private int  position=0;
    private Fragment tempFragemnt;
    private int fragmentID = 0;
    public static HomeActivity instance;   //记录HomeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);   //使用ButterKnife管理并实例化
        instance = this;
        initFragment();          //初始化Fragment
        initListener();           //设置对RadioGroup底部导航栏的监听
        try {
            fragmentID = (int) getIntent().getSerializableExtra("fragmentID");
            Log.e("fragmentID", fragmentID+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据回传编号选择当前fragment
        switch (fragmentID) {
            case 0:    //主页
                mRgMain.check(R.id.rb_home);
                break;
            case 1:  //通讯录
                mRgMain.check(R.id.rb_friend);
                break;
            case 2:  //会话
                mRgMain.check(R.id.rb_message);
                break;
            case 3:  //社区
                mRgMain.check(R.id.rb_community);
                break;
            case 4:   //个人中心
                mRgMain.check(R.id.rb_user);
                break;
            default:
                mRgMain.check(R.id.rb_home);
                break;

        }
    }
    private void initListener() {
        mRgMain.setOnCheckedChangeListener(new
          RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  switch (checkedId) {
                      case R.id.rb_home:    //主页
                          position = 0;
                          break;
                      case R.id.rb_friend:  //通讯录
                          position = 1;
                          break;
                      case R.id.rb_message:  //会话
                          position = 2;
                          break;
                      case R.id.rb_community:  //社区
                          position = 3;
                          break;
                      case R.id.rb_user:   //个人中心
                          position = 4;
                          break;
                      default:
                          position = 0;
                          break;

                  }
                  //initFragment();
                  //根据位置获取Fragment
                  BaseFragment baseFragment = getFragment(position);
                  switchFragment(tempFragemnt, baseFragment);  //缓存Fragment与当前
              }
          });
            // 默认设置首页
            mRgMain.check(R.id.rb_home);
    }

    /**
     * 根据位置得到Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment逻辑代码
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment
            nextFragment) {
        if (tempFragemnt != nextFragment) {      //点击事件不重复
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                // 判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    // 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    // 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
    /**
     * 初始化布局，在create()中调用
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FriendFragment());
        fragments.add(new MessageFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new UserFragment());
    }
/*    @OnClick({R.id.frameLayout, R.id.rg_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frameLayout:
                break;
            case R.id.rg_main:
                break;
        }
    }*/
}
