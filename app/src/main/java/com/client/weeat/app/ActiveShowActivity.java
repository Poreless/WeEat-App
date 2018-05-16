package com.client.weeat.app;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.home.adapter.IconGridAdapter;
import com.client.weeat.home.bean.ActivesBean;
import com.client.weeat.home.bean.IconGridBean;
import com.client.weeat.user.bean.UserBeanDate;
import com.client.weeat.util.Constants;
import com.longsh.optionframelibrary.OptionMaterialDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 主页面->活动点击后的交互页面
 */
public class ActiveShowActivity extends AppCompatActivity {

    @Bind(R.id.ib_active_info_back)
    ImageButton mIbActiveInfoBack;
    @Bind(R.id.ib_active_info_more)
    ImageButton mIbActiveInfoMore;
    @Bind(R.id.tx_actshow_usericon)
    ImageView mTxActshowUsericon;
    @Bind(R.id.tx_actshow_username)
    TextView mTxActshowUsername;
    @Bind(R.id.tx_actshow_usermore)
    ImageView mTxActshowUsermore;
    @Bind(R.id.tx_actshow_name)
    TextView mTxActshowName;
    @Bind(R.id.tv_actshow_depict)
    TextView mTvActshowDepict;
    @Bind(R.id.tv_actshow_place)
    TextView mTvActshowPlace;
    @Bind(R.id.iv_actshow_placemore)
    ImageView mIvActshowPlacemore;
    @Bind(R.id.tv_actshow_time)
    TextView mTvActshowTime;
    @Bind(R.id.tv_actshow_num)
    TextView mTvActshowNum;
    @Bind(R.id.gv_iconshow)
    GridView mGvIconshow;
    @Bind(R.id.tv_active_info_callback)
    TextView mTvActiveInfoCallback;
    @Bind(R.id.tv_active_info_love)
    TextView mTvActiveInfoLove;
    @Bind(R.id.btn_active_info_ask)
    Button mBtnActiveInfoAsk;
    @Bind(R.id.ll_actives_root)
    LinearLayout mLlActivesRoot;
    @Bind(R.id.activity_actives_info)
    LinearLayout mActivityActivesInfo;
    private ActivesBean activesBean;
    private String user_info_json;
    private String userID;
    private IconGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_info);
        ButterKnife.bind(this);

        //接收数据
        activesBean = (ActivesBean) getIntent().getSerializableExtra("activesBean");
        try {
            user_info_json = ActiveShowActivity.this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID();  //获取用户信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建者不能申请
        Log.e("userID",activesBean.getActshow_userid()+"and"+userID);
       if(activesBean.getActshow_userid().equals(userID)){
            mBtnActiveInfoAsk.setClickable(false);
           mBtnActiveInfoAsk.setBackgroundColor(Color.parseColor("#969696"));
        }else{
            mBtnActiveInfoAsk.setClickable(true);
        }
        if (activesBean != null) {
            Toast.makeText(this, "activesBean==" + activesBean.toString(), Toast.LENGTH_SHORT).show();
            Log.e("ID是：",activesBean.getActive_id()+"");
            setDataForView(activesBean);
        }

        InitUserIconView();
        mIbActiveInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        //服务端建立不能重复申请的逻辑
        mBtnActiveInfoAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //adapter = new ActiveGridViewAdapter(mcontext,active_info);
               // Log.e(TAG,"HomeFragment的数据内容初始化..");
                String url = BASIC_URL+"/servlet/RequestServlet";
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("uid", userID)
                        .addParams("aid", activesBean.getActive_id()+"")
                        .build()
                        .execute(new StringCallback()
                        {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG,"请求失败  "+e.getMessage().toString());
                                Toast.makeText(ActiveShowActivity.this, "数据传输出现了问题，请重试==",
                                        Toast.LENGTH_SHORT).show();
                            }

                            /**
                             * 网络请求回调，异步请求
                             * @param id
                             */
                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG,"请求数据为  "+response.toString());
                                //数据接收成功后，解析数据，创建适配器
                                processDate(response);
                                mBtnActiveInfoAsk.setClickable(false);
                                mBtnActiveInfoAsk.setBackgroundColor(Color.parseColor("#969696"));
                                // OldJSON = response.toString();
                            }

                        });
            }

            private void processDate(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                String  msg = jsonObject.getString("log");
               // if(msg.equals("success")){
                    Log.e("TAG","请求数据为  "+response.toString());

 /*                   Intent intent = new Intent(RegistActivity.this,HomeActivity.class);
                    RegistActivity.this.startActivity(intent);
                    finish();*/
                    final OptionMaterialDialog mMaterialDialog = new OptionMaterialDialog(ActiveShowActivity.this);
                    mMaterialDialog
                            //                .setTitle("这是标题")
                            //                .setTitleTextColor(R.color.colorPrimary)
                            //                .setTitleTextSize((float) 22.5)
                            .setMessage(msg)
                            //                .setMessageTextColor(R.color.colorPrimary)
                            //                .setMessageTextSize((float) 16.5)
                            .setPositiveButtonTextColor(R.color.colorAccent)
                            //                .setNegativeButtonTextColor(R.color.colorPrimary)
                            //                .setPositiveButtonTextSize(15)
                            //                .setNegativeButtonTextSize(15)
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            mMaterialDialog.dismiss();
                                        }
                                    })
                            .setCanceledOnTouchOutside(true)
                            .setOnDismissListener(
                                    new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            //                                Toast.makeText(MainActivity.this, "onDismiss 取消", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .show();

            /*    }else{
                    Toast.makeText(ActiveShowActivity.this,jsonObject.getString("erro"),Toast.LENGTH_SHORT).show();
                }*/
            }


        });

    }

    //这里获取参与者的信息
    private void InitUserIconView() {
        String url = BASIC_URL+"/servlet/JoinIconServlet";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("aid", activesBean.getActive_id()+"")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"请求失败  "+e.getMessage().toString());
                        Toast.makeText(ActiveShowActivity.this, "数据传输出现了问题，请重试==",
                                Toast.LENGTH_SHORT).show();
                    }

                    /**
                     * 网络请求回调，异步请求
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"请求数据为  "+response.toString());
                        //数据接收成功后，解析数据，创建适配器
                        IconGridBean userBean = JSON.parseObject(response, IconGridBean.class);
                        mTvActshowNum.setText("当前人数 "+userBean.getNum()+" 人:");
                        if (userBean.getResult()!= null) {
                            //设置适配器
                            adapter = new IconGridAdapter(ActiveShowActivity.this,userBean.getResult());
                            mGvIconshow.setAdapter(adapter);
                        } else {
                            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

                        }
                    }

                });
    }

    private void setDataForView(ActivesBean activesBean) {
        //设置创建人图片
        //
        Glide.with(this).load(Constants.BASIC_URL_USERICON + activesBean.getActshow_usericon()).into(mTxActshowUsericon);

        //设置创建人姓名
        mTxActshowUsername.setText(activesBean.getActshow_username());

        //设置活动地点
        mTvActshowPlace.setText(activesBean.getActive_place());
        //设置活动标题
        mTxActshowName.setText("#"+activesBean.getActive_name()+"#");
        //设置活动描述
        mTvActshowDepict.setText(activesBean.getActive_depict());

        mTvActshowTime.setText(activesBean.getCreate_time());
    }

}
