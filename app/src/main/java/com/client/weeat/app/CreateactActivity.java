package com.client.weeat.app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.friend.bean.FriendBeanDate;
import com.client.weeat.user.bean.UserBeanDate;
import com.longsh.optionframelibrary.OptionMaterialDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Calendar;

import okhttp3.Call;

import static com.client.weeat.util.Constants.BASIC_URL;

public class CreateactActivity extends AppCompatActivity {
    private TextView timeText;
    private EditText et_actadd_place;
    private EditText et_actadd_title;
    private EditText et_actadd_artdcl;
    private EditText et_actadd_theme;
    private TextView tv_actadd_maxnum;
    private Button btn_addact;
    private String userID;
    private String DialgMsg;
    private String user_info_json;
    private int Act_Usernum=5;// 默认5人
    private FriendBeanDate.ResultBean resultBean;
    private JSONObject addactjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createact);
        timeText = (TextView) findViewById(R.id.tx_actadd_time);
        et_actadd_place = (EditText) findViewById(R.id.et_actadd_place);
        et_actadd_title = (EditText) findViewById(R.id.et_actadd_title);
        et_actadd_artdcl = (EditText) findViewById(R.id.et_actadd_artdcl);
        et_actadd_theme = (EditText) findViewById(R.id.et_actadd_theme);
        tv_actadd_maxnum = (TextView) findViewById(R.id.tv_actadd_maxnum);
        btn_addact  = (Button) findViewById(R.id.btn_addact);
        //安卓原生的时间选择控件
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将timeText传入用于显示所选择的时间
                showDialogPick((TextView) v);
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
        final TimePickerDialog timePickerDialog = new TimePickerDialog(CreateactActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateactActivity.this, new DatePickerDialog.OnDateSetListener() {
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
    public void iv_1(View view){
        int num=Integer.parseInt(tv_actadd_maxnum.getText().toString());
        if(num>1){
            num-=1;
        }

        tv_actadd_maxnum.setText(Integer.toString(num));

    }
    public void iv_2(View view){
        int num=Integer.parseInt(tv_actadd_maxnum.getText().toString());
        if(num<5){   //最多5人
            num+=1;
        }

        tv_actadd_maxnum.setText(Integer.toString(num));

    }
    public void create(View view) {
        //获取用户ID
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String actadd_place = et_actadd_place.getText().toString().trim();
        final String actadd_title = et_actadd_title.getText().toString().trim();
        final String actadd_artdcl = et_actadd_artdcl.getText().toString().trim();
        final String actaddr_theme = et_actadd_theme.getText().toString();
        final String actaddr_time = timeText.getText().toString();
       if (TextUtils.isEmpty(actadd_place) || TextUtils.isEmpty(actadd_title) ||
               TextUtils.isEmpty(actadd_artdcl) || TextUtils.isEmpty(actaddr_theme)) {
            Toast.makeText(this, "请填入完整的活动信息", Toast.LENGTH_SHORT).show();
            return;
        }
       else{
           addactjson = new JSONObject();
           try {
               addactjson.put("user_id", userID);
               addactjson.put("actadd_place", actadd_place);
               addactjson.put("actadd_title", actadd_title);
               addactjson.put("actadd_artdcl",actadd_artdcl);
               addactjson.put("actaddr_theme", actaddr_theme);
               addactjson.put("actaddr_time", actaddr_time);
               addactjson.put("actaddr_usernum",Act_Usernum);
           } catch (Exception e) {
               e.printStackTrace();
           }
           new Thread() {
               @Override
               public void run() {
                   try {
                       OkHttpUtils.post().url(BASIC_URL+"/servlet/AddActServlet")
                               .addParams("addactjson", addactjson.toJSONString())
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

                                           DialgMsg = "活动请求成功";
                                           btn_addact.setClickable(false);
                                           btn_addact.setBackgroundColor(Color.parseColor("#969696"));

                                       }else{
                                           DialgMsg = "活动创建失败";
                                           //Toast.makeText(CreateactActivity.this,jsonObject.getString("erro"),Toast.LENGTH_SHORT).show();
                                       }
                                       final OptionMaterialDialog mMaterialDialog = new OptionMaterialDialog(CreateactActivity.this);
                                       mMaterialDialog
                                               //                .setTitle("这是标题")
                                               //                .setTitleTextColor(R.color.colorPrimary)
                                               //                .setTitleTextSize((float) 22.5)
                                               .setMessage(DialgMsg)
                                               //                .setMessageTextColor(R.color.colorPrimary)
                                               //                .setMessageTextSize((float) 16.5)
                                               .setPositiveButtonTextColor(R.color.colorAccent)
                                               //                .setNegativeButtonTextColor(R.color.colorPrimary)
                                                //                .setPositiveButtonTextSize(15)
                                               //                .setNegativeButtonTextSize(15)
                                               .setPositiveButton("确定", new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       Intent intent = new Intent(CreateactActivity.this,HomeActivity.class);
                                                       CreateactActivity.this.startActivity(intent);
                                                       finish();
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

                                   }
                               });
                   } catch (Exception e) {
                       e.printStackTrace();
                       showToastInAnyThread("请求失败");
                   }
               }
           }.start();
       }


    }


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
