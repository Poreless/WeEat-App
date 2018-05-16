package com.client.weeat.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.message.adapter.ActaskListAdapter;
import com.client.weeat.message.bean.MsgBeanDate;
import com.client.weeat.user.bean.UserBeanDate;
import com.longsh.optionframelibrary.OptionCenterDialog;
import com.longsh.optionframelibrary.OptionMaterialDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 消息页面->请求申请->申请展示与处理
 */
public class ActmanageActivity extends AppCompatActivity {
    private ListView listmanage;
    private ImageButton bt_back;
    private String userID;
    private String user_info_json;
    private MsgBeanDate.ResultBean resultBean;
    private MsgBeanDate.ResultBean.RequestActInfoBean requestInfoBean;
    private ActaskListAdapter adapter;
    private static final String REQUEST_BEAN = "requestBean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actmanage);
        listmanage = (ListView) findViewById(R.id.actmanage_listview);
        bt_back = (ImageButton) findViewById(R.id.ib_request_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                Intent intent = new Intent(ActmanageActivity.this, HomeActivity.class);
                intent.putExtra("fragmentID", 2);  //传到会话fragment
                ActmanageActivity.this.startActivity(intent);
                finish();
            }
        });
        initDate();
        listmanage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ArrayList<String> list = new ArrayList<>();
                list.add("同意");
                list.add("拒绝");
                list.add("忽略");
                final String r_aid = resultBean.getRequest_act_info().get(position).getActive_id();
                final String r_usid = resultBean.getRequest_act_info().get(position).getUser().getUserID();
                final OptionCenterDialog optionCenterDialog = new OptionCenterDialog();
                optionCenterDialog.show(ActmanageActivity.this, list);
                optionCenterDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                Toast.makeText(ActmanageActivity.this,"同意",Toast.LENGTH_SHORT).show();
                                sendMessage(r_aid,r_usid,"1");
                                break;
                            case 1:
                                Toast.makeText(ActmanageActivity.this,"拒绝",Toast.LENGTH_SHORT).show();
                                sendMessage(r_aid,r_usid,"2");
                                break;
                            case 2:
                                Toast.makeText(ActmanageActivity.this,"忽略",Toast.LENGTH_SHORT).show();
                                sendMessage(r_aid,r_usid,"3");
                                break;
                        }
                        optionCenterDialog.dismiss();
                    }


                });
            }
        });


    }
    private void sendMessage(String r_aid, String r_usid, String num) {
        String url = BASIC_URL+"/servlet/DoaskServlet";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("r_aid",r_aid)
                .addParams("r_usid",r_usid)
                .addParams("num",num)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"请求失败  "+e.getMessage().toString());
                        Toast.makeText(ActmanageActivity.this, "数据传输出现了问题，请重试==",
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
                        //processDate(response);
                        // OldJSON = response.toString();

                        JSONObject jsonObject = JSON.parseObject(response);
                        String  msg = jsonObject.getString("result");
                        final OptionMaterialDialog mMaterialDialog = new OptionMaterialDialog(ActmanageActivity.this);
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
                                initDate();
                    }

                });

    }
    protected void initDate() {
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json, UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID().toString();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
            Log.e(TAG, "HomeFragment的数据内容初始化..");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = BASIC_URL + "/servlet/MessageServlet";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("uid", userID)
                //  .addParams("password", "123")*/
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "请求失败  " + e.getMessage().toString());
                        Toast.makeText(ActmanageActivity.this, "数据传输出现了问题，请重试==",
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
                        //数据接收成功后，解析数据，创建适配器
                        processDate(response);
                        // OldJSON = response.toString();
                    }

                });
    }

    private void processDate(String json) {

        MsgBeanDate resultBeanDate = JSON.parseObject(json, MsgBeanDate.class);
        resultBean = resultBeanDate.getResult();
        if (resultBean.getRequest_act_info()!= null) {
            //设置适配器
            adapter = new ActaskListAdapter(this,resultBean.getRequest_act_info());
            listmanage.setAdapter(adapter);
        } else {
            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }

    }
}