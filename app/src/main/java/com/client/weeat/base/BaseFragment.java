package com.client.weeat.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
* 基类BaseFragment
 * ====首页HomeFragment
 * ====通讯录FriendFragment
 * ====消息会话MessageFragment
 * ====话题社区CommunityFragment
 * ====用户中心UserFragment
 * ====
 */
public abstract class BaseFragment extends Fragment{
    protected Context mcontext;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    /**
     *系统创建时回调
     * @paramangji savedInstanceState
     */


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = getActivity();

        if (savedInstanceState != null) {  //解决fragment重叠
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();  //抽象类，由子类实现


    /**
     * 活动创建时回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    /**
     * 子类数据请求重写该方法
     * View函数早于Date函数，生成视图再进行数据请求，防止空指针
     */

    protected void initDate(){

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden()); //解决fragment重叠
    }
}
