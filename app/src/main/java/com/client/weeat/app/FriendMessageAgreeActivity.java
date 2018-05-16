package com.client.weeat.app;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.client.weeat.R;
import com.client.weeat.friend.adapter.FriendEDListAdapter;
import com.client.weeat.friend.bean.FriendMessageBean;
import com.client.weeat.user.adapter.FavoriteAdapter;
import com.client.weeat.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 该活动展示的是已同意的，在好友列表中的用户信息界面
 */

public class FriendMessageAgreeActivity extends AppCompatActivity {

    @Bind(R.id.ib_friend_agree_back)
    ImageButton mIbFriendAgreeBack;
    @Bind(R.id.tx_friend_agree_title)
    TextView mTxFriendAgreeTitle;
    @Bind(R.id.ib_friend_agree_add)
    ImageButton mIbFriendAgreeAdd;
    @Bind(R.id.iv_friend_agree_icon)
    ImageView mIvFriendAgreeIcon;
    @Bind(R.id.rl_friend_agree_icon)
    RelativeLayout mRlFriendAgreeIcon;
    @Bind(R.id.tv_friend_agree_name)
    TextView mTvFriendAgreeName;
    @Bind(R.id.rl_friend_agree)
    RelativeLayout mRlFriendAgree;
    @Bind(R.id.gv_friend_agree_favorite)
    GridView mGvFriendAgreeFavorite;
    @Bind(R.id.friend_agree_expand_list)
    ExpandableListView mFriendAgreeExpandList;
    private FriendEDListAdapter adapter;
    private String user_id;
    private String friend_id;
    private FavoriteAdapter mFavoriteAdapter;  //用的是个人主页的适配器
    private FriendMessageBean.ResultBean resultBean;
    private ArrayList<String> taglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_message_agree);
        ButterKnife.bind(this);
        user_id = (String) getIntent().getSerializableExtra("USERID");
        friend_id = (String) getIntent().getSerializableExtra("FRIENDID");
        Log.e("user_id", user_id);
        Log.e("friend_id", friend_id);
        initDate();
        mIbFriendAgreeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置焦点
        mTxFriendAgreeTitle.setFocusable(true);
        mTxFriendAgreeTitle.setFocusableInTouchMode(true);
        mTxFriendAgreeTitle.requestFocus();
    }
    private void initDate() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String url = BASIC_URL + "/servlet/FriendMessageServlet";
                    OkHttpUtils
                            .post()
                            .url(url)
                            // .addParams("uid", user_id)
                            .addParams("fid", friend_id)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e(TAG, "请求失败  " + e.getMessage().toString());
                                    Toast.makeText(FriendMessageAgreeActivity.this, "数据传输出现了问题，请重试==",
                                            Toast.LENGTH_SHORT).show();
                                }

                                /**
                                 * 网络请求回调，异步请求
                                 *
                                 * @param id
                                 */
                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e(TAG, "请求数据为  " + response.toString());
                                    JSONObject jsonObject = JSON.parseObject(response);
                                    String msg = jsonObject.getString("msg");
                                    if (msg.equals("success")) {
                                        processDate(response);
                                    } else {

                                    }
                                }

                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void processDate(String json) {
        //活动评价的数据绑定
        FriendMessageBean friendBean = JSON.parseObject(json, FriendMessageBean.class);
        resultBean = friendBean.getResult();
        if (resultBean.getActive_comment() != null) {
            //设置适配器
            adapter = new FriendEDListAdapter(FriendMessageAgreeActivity.this, resultBean.getActive_comment());
            mFriendAgreeExpandList.setAdapter(adapter);
        } else {
            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }
        //兴趣标签的数据绑定
        taglist = new ArrayList<>();
        JSONArray tagjson = JSONArray.parseArray(friendBean.getTag());
        //在浏览器中显示为utf-8
        if (tagjson.size() > 0) {
            for (int i = 0; i < tagjson.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject tag = tagjson.getJSONObject(i);
                taglist.add(tag.get("name").toString());
                Log.e("兴趣" + i, tag.get("name").toString());
            }
        }
        //数据源都是列表，无需重新创建适配器
        mFavoriteAdapter = new FavoriteAdapter(FriendMessageAgreeActivity.this, taglist);

        mGvFriendAgreeFavorite.setAdapter(mFavoriteAdapter);
        //其他控件
        mTxFriendAgreeTitle.setText(friendBean.getResult().getFriend_info().getUserName() + "的主页");

        if(friendBean.getResult().getFriend_info().getSchool()!=null) {
            mTvFriendAgreeName.setText(friendBean.getResult().getFriend_info().getUserName()+ "  " + friendBean.getResult().getFriend_info().getSchool());
        }else{
            mTvFriendAgreeName.setText(friendBean.getResult().getFriend_info().getUserName());
        }
        if(friendBean.getResult().getFriend_info().getUserSex()!=null){
            if(friendBean.getResult().getFriend_info().getUserSex().equals("男")){
                Drawable img_on, img_off;
                Resources res = getResources();
                img_off = res.getDrawable(R.drawable.sex_man);
                //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                mTvFriendAgreeName.setCompoundDrawables(null, null, img_off, null); //设置左图标
            }
            if(friendBean.getResult().getFriend_info().getUserSex().equals("女")){
                Drawable img_on, img_off;
                Resources res = getResources();
                img_off = res.getDrawable(R.drawable.sex_woman);
                //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                mTvFriendAgreeName.setCompoundDrawables(null, null, img_off, null); //设置左图标

            }
        }
        Glide.with(this).load(Constants.BASIC_URL_USERICON + friendBean.getResult().getFriend_info().getUserIcon()).asBitmap()
                .error(R.drawable.icon_callserver_unpressed).into(new BitmapImageViewTarget(mIvFriendAgreeIcon) {
            @Override
            protected void setResource(Bitmap resource) {
                mIvFriendAgreeIcon.setImageBitmap(resource);
            }
        });
    }
}
