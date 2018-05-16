package com.client.weeat.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.client.weeat.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * 社区-话题fragment
 */
public class CommunityFragment extends BaseFragment {
    private TextView textview;
    @Override
    public View initView() {
        Log.e(TAG,"CommunityFragment的UI内容初始化..");
        textview = new TextView(mcontext);
        textview.setGravity(Gravity.CENTER);
        textview.setTextSize(25);

        return textview;
    }

    /**
     * 数据服务
     */
    @Override
    protected void initDate() {
        super.initDate();
        Log.e(TAG,"CommunityFragment的数据内容初始化..");
        textview.setText("社区-话题内容...");
    }
}
