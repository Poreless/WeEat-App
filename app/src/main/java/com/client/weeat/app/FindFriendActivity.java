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
import com.client.weeat.friend.adapter.FindFriendAdapter;
import com.client.weeat.friend.bean.FindFriendBean;
import com.client.weeat.user.bean.UserBeanDate;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 用于显示好友推荐列表
 */
public class FindFriendActivity extends AppCompatActivity {
    private FindFriendAdapter adapter;
    private ListView lv_friend;
    private String userID;
    private String friendID;
    private String user_info_json;
    private FindFriendBean.ResultBean resultBean;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        lv_friend = (ListView)findViewById(R.id.lv_findfriend);
        back = (ImageButton) findViewById(R.id.ib_friend_find_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initDate();
        lv_friend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                friendID = resultBean.getUser_info().get(position).getUserID();
                Toast.makeText(FindFriendActivity.this,friendID,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FindFriendActivity.this, FriendMessageActivity.class);
                intent.putExtra("FRIENDID", friendID);
                intent.putExtra("USERID", userID);
                FindFriendActivity.this.startActivity(intent);
            }
        });
    }

    protected void initDate() {
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
            Log.e(TAG,"HomeFragment的数据内容初始化..");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = BASIC_URL+"/servlet/FriendFindServlet";
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
                        Toast.makeText(FindFriendActivity.this, "数据传输出现了问题，请重试==",
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
        FindFriendBean resultBeanDate = JSON.parseObject(json,FindFriendBean.class);
        resultBean = resultBeanDate.getResult();
        if(resultBean.getUser_info()!=null){
            //设置适配器
            adapter = new FindFriendAdapter(this,resultBean.getUser_info());
            lv_friend.setAdapter(adapter);
        }else{

        }

    }

}
