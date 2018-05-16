package com.client.weeat.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.client.weeat.R;
import com.client.weeat.user.bean.UserBeanDate;
import com.client.weeat.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExitActivity extends AppCompatActivity {

    @Bind(R.id.iv_exit_icon)
    ImageView mIvExitIcon;
    @Bind(R.id.tv_user_change)
    TextView mTvUserChange;
    @Bind(R.id.btn_user_logout)
    Button mBtnUserLogout;
    @Bind(R.id.ib_EXit_back)
    ImageButton mIbEXitBack;
    private String user_info_json;
    private String userID;
    private UserBeanDate.ResultBean.UserInfoBean userInfoBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            Log.e("user_info_json", user_info_json);
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json, UserBeanDate.class);
            userInfoBean = userBeanDate.getResult().getUser_info();
            userID = userBeanDate.getResult().getUser_info().getUserID().toString();  //获取用户信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        mIbEXitBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Glide.with(this).load(Constants.BASIC_URL_USERICON + userInfoBean.getUserIcon()).asBitmap()
                .error(R.drawable.icon_callserver_unpressed).into(new BitmapImageViewTarget(mIvExitIcon) {
            @Override
            protected void setResource(Bitmap resource) {
                mIvExitIcon.setImageBitmap(resource);
            }
        });
        mBtnUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExitActivity.this, MainActivity.class);
                ExitActivity.this.startActivity(intent);
                //调用记录的标签
                HomeActivity.instance.finish();
                finish();
            }

        });
        mTvUserChange.setText(userInfoBean.getUserName());
    }
}
