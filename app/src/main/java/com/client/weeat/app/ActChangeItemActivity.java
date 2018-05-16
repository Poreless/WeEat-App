package com.client.weeat.app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.message.bean.FreshActInfoBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.R.id.et_actadd_place;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 具体某个活动的管理界面
 */
public class ActChangeItemActivity extends AppCompatActivity {

    @Bind(R.id.ib_active_change_back)
    ImageButton mIbActiveChangeBack;
    @Bind(R.id.tv_actchange_title)
    TextView mTvActchangeTitle;
    @Bind(R.id.ib_active_change_more)
    ImageButton mIbActiveChangeMore;
    @Bind(R.id.et_actchange_title)
    EditText mEtActchangeTitle;
    @Bind(R.id.et_actchange_theme)
    EditText mEtActchangeTheme;
    @Bind(R.id.et_actchange_artdcl)
    EditText mEtActchangeArtdcl;
    @Bind(R.id.tx_actchange_time)
    TextView mTxActchangeTime;
    @Bind(et_actadd_place)
    EditText mEtActaddPlace;
    @Bind(R.id.tv_actchange_maxnum)
    TextView mTvActchangeMaxnum;
    @Bind(R.id.btn_actchange_up)
    Button mBtnActchangeUp;
    @Bind(R.id.btn_actchange_finish)
    Button mBtnActchangeFinish;
    private FreshActInfoBean freshbean;
    private static final long msInDay = 1000*60*60*24;
    private static final long msInHour = 1000*60*60;
    private static final long msInMin = 1000*60;
    private JSONObject actchangejson;
    private String DialgMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_change_item);
        ButterKnife.bind(this);
        //接收数据
        freshbean = (FreshActInfoBean) getIntent().getSerializableExtra("freshBean");
        if (freshbean!= null) {
            Toast.makeText(this, "activesBean==" + freshbean.toString(), Toast.LENGTH_SHORT).show();
            Log.e("ID是：",freshbean.getActive_id()+"");
            setDataForView(freshbean);
        }
        mTxActchangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPick((TextView) v);
            }
        });
        mIbActiveChangeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //修改提交按钮
        mBtnActchangeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String actchange_place = mEtActaddPlace.getText().toString().trim();
                final String actchange_title = mEtActchangeTitle.getText().toString().trim();
                final String actchange_artdcl = mEtActchangeArtdcl.getText().toString().trim();
                final String actchange_theme = mEtActchangeTheme.getText().toString();
                final String actchange_time = mTxActchangeTime.getText().toString();
                final String actchange_num = mTvActchangeMaxnum.getText().toString();
                if (TextUtils.isEmpty(actchange_place) || TextUtils.isEmpty(actchange_title) ||
                        TextUtils.isEmpty(actchange_artdcl) || TextUtils.isEmpty(actchange_theme)) {
                    Toast.makeText(ActChangeItemActivity.this, "请填入完整的活动信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    actchangejson = new JSONObject();
                    try {
                        actchangejson.put("active_id", freshbean.getActive_id());
                        actchangejson.put("actchange_place", actchange_place);
                        actchangejson.put("actchange_title", actchange_title);
                        actchangejson.put("actchange_artdcl",actchange_artdcl);
                        actchangejson.put("actchange_theme", actchange_theme);
                        actchangejson.put("actchange_time", actchange_time);
                        actchangejson.put("actchange_usernum",actchange_num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                   /* new Thread() {
                        @Override
                        public void run() {*/
                            try {
                                OkHttpUtils.post().url(BASIC_URL+"/servlet/ChangeActServlet")
                                        .addParams("actchangejson", actchangejson.toJSONString())
                                        //   .addParams("password", "123")
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {

                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e("TAG","请求数据为  "+response.toString());
                                                JSONObject jsonObject = JSON.parseObject(response);
                                                String  msg = jsonObject.getString("msg");
                                                if(msg.equals("success")){
                                                    showToastInAnyThread("活动修改成功..");
                                                    mBtnActchangeUp.setClickable(false);
                                                    mBtnActchangeUp.setBackgroundColor(Color.parseColor("#969696"));
                                                    Intent intent = new Intent(ActChangeItemActivity.this,ActchangeActivity.class);
                                                    ActChangeItemActivity.this.startActivity(intent);
                                                    finish();

                                                }else{
                                                    //Toast.makeText(CreateactActivity.this,jsonObject.getString("erro"),Toast.LENGTH_SHORT).show();
                                                    showToastInAnyThread("活动修改失败..");
                                                }


                                            }
                                        });
                            } catch (Exception e) {
                                e.printStackTrace();
                                showToastInAnyThread("请求失败");
                            }
                        }
                 /*   }.start();
                }*/
            }
        });

        mBtnActchangeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActChangeItemActivity.this,freshbean.getCreate_time(),Toast.LENGTH_SHORT).show();
                Date now = new Date();	//获取现在的日期对象
                Date thedate = null;
                try {
                    thedate =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                            .parse(freshbean.getCreate_time());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int i = now.compareTo(thedate);
                if(i > 0){
                 /*   Toast.makeText(ActChangeItemActivity.this,"指定日期在现在之前",
                            Toast.LENGTH_SHORT).show();*/
                    long ms = Math.abs(now.getTime() - thedate.getTime());
                    Log.e("DATE","相距"+ms/msInDay + "天" + (ms%msInDay)/msInHour +
                            "小时"+(ms%msInHour)/msInMin+"分钟");
              /*      if(ms/msInMin<30){
                        Toast.makeText(ActChangeItemActivity.this,"活动开启后30分钟才能结束活动！",Toast.LENGTH_SHORT).show();
                    }else{*/
                        String url = BASIC_URL+"/servlet/ActfinishServlet";
                        OkHttpUtils
                                .get()
                                .url(url)
                            //    .addParams("uid", userID)
                                .addParams("aid", freshbean.getActive_id()+"")
                                .build()
                                .execute(new StringCallback()
                                {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.e(TAG,"请求失败  "+e.getMessage().toString());
                                        Toast.makeText(ActChangeItemActivity.this, "数据传输出现了问题，请重试==",
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
                                        // OldJSON = response.toString();
                                        JSONObject jsonObject = JSON.parseObject(response);
                                        String  msg = jsonObject.getString("msg");
                                        if(msg.equals("success")) {
                                            showToastInAnyThread("已结束活动，主页将更新饭友对你的最新评价==");
                                            Intent intent = new Intent(ActChangeItemActivity.this, ActchangeActivity.class);
                                            ActChangeItemActivity.this.startActivity(intent);
                                        }else{
                                            Toast.makeText(ActChangeItemActivity.this, "操作失败",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                });
                   /* }*/
                }else if(i < 0){
                    Toast.makeText(ActChangeItemActivity.this,"指定日期在现在之后",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ActChangeItemActivity.this,"日期相等",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
    private void showDialogPick(final TextView timeText) {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //实例化TimePickerDialog对象
        final TimePickerDialog timePickerDialog = new TimePickerDialog(ActChangeItemActivity.this, new TimePickerDialog.OnTimeSetListener() {
            //选择完时间后会调用该回调函数
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.append(" " + hourOfDay + ":" + minute);
                //设置TextView显示最终选择的时间
                timeText.setText(time);
                Log.e("time",time+"");
            }
        }, hour, minute, true);
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(ActChangeItemActivity.this, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                //选择完日期后弹出选择时间对话框
                timePickerDialog.show();
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();
    }

    //加减按钮的点击事件
    public void iv_3(View view){
        int num=Integer.parseInt(mTvActchangeMaxnum.getText().toString());
        if(num>1){
            num-=1;
        }

        mTvActchangeMaxnum.setText(Integer.toString(num));

    }
    public void iv_4(View view){
        int num=Integer.parseInt(mTvActchangeMaxnum.getText().toString());
        if(num<5){   //最多5人
            num+=1;
        }

        mTvActchangeMaxnum.setText(Integer.toString(num));

    }

    private void setDataForView(FreshActInfoBean freshbean) {
        mEtActchangeTitle.setText(freshbean.getActive_name());

        mEtActchangeTheme.setText(freshbean.getActive_theme());
        mEtActchangeArtdcl.setText(freshbean.getActive_depict());
        mEtActaddPlace.setText(freshbean.getActive_place());
        mTvActchangeMaxnum.setText(freshbean.getMaxnum()+"");
        mTxActchangeTime.setText(freshbean.getCreate_time());
    }
    /**
     * 在任何线程当中都可以调用弹出吐司方法
     * @param result
     */
    private void showToastInAnyThread(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                //应用程序的上下文
            }
        });
    }
}
