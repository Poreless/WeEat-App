package com.client.weeat.app;

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
import com.client.weeat.R;
import com.client.weeat.message.adapter.FreshActChangeAdapter;
import com.client.weeat.message.bean.FreshActBean;
import com.client.weeat.message.bean.FreshActInfoBean;
import com.client.weeat.user.bean.UserBeanDate;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 活动管理概览界面
 * Rx-z89FiQC
 */
public class ActchangeActivity extends AppCompatActivity {
    private ListView listchange;
    private ImageButton bt_back;
    private String userID;
    private String user_info_json;
    private FreshActBean.ResultBean resultBean;
    private FreshActChangeAdapter adapter;
    private static final String FRESH_BEAN="freshBean";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actchange);
        listchange = (ListView) findViewById(R.id.actchange_listview);
        bt_back = (ImageButton) findViewById(R.id.ib_actchange_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActchangeActivity.this, HomeActivity.class);
                intent.putExtra("fragmentID", 2);  //传到会话fragment
                ActchangeActivity.this.startActivity(intent);
                finish();
            }
        });
        initDate();
        //设置管理活动列表的点击事件，传参，跳转
        listchange.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActchangeActivity.this, "position=="+position, Toast.LENGTH_SHORT).show();
                FreshActBean.ResultBean.FreshListBean freshbean = resultBean.getFresh_list().get(position);
                FreshActInfoBean freshInfo = new FreshActInfoBean();
                freshInfo.setActive_id(freshbean.getActive_id());
                freshInfo.setActive_name(freshbean.getActive_name());
                freshInfo.setActive_depict(freshbean.getActive_depict());
                freshInfo.setActive_place(freshbean.getActive_place());
                freshInfo.setCreate_time(freshbean.getCreate_time());
                freshInfo.setMaxnum(freshbean.getMaxnum());

                Log.e("THEME",freshbean.getActive_theme());
                freshInfo.setActive_theme(freshbean.getActive_theme());
                startFreshActInfoActivity(freshInfo);
            }


        });

    }

    //页面跳转，意图传递数据
    private void startFreshActInfoActivity(FreshActInfoBean freshInfo) {
        Intent intent = new Intent(ActchangeActivity.this, ActChangeItemActivity.class);
        intent.putExtra(FRESH_BEAN, freshInfo);
        this.startActivity(intent);

    }
    private void initDate() {
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

        String url = BASIC_URL + "/servlet/ActchangeServlet";
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
                        Toast.makeText(ActchangeActivity.this, "数据传输出现了问题，请重试==",
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
        FreshActBean freshactBean = JSON.parseObject(json, FreshActBean.class);
        resultBean = freshactBean.getResult();
        if (resultBean.getFresh_list()!= null) {
            //设置适配器
            adapter = new FreshActChangeAdapter(this,resultBean.getFresh_list());
            listchange.setAdapter(adapter);
        } else {
            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }

    }

}
