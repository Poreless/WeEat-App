package com.client.weeat.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.client.weeat.util.Constants.BASIC_URL;

public class AdminHomeActivity extends AppCompatActivity {

    @Bind(R.id.admin_name)
    TextView mAdminName;
    @Bind(R.id.ib_admin)
    ImageButton mIbAdmin;
    @Bind(R.id.search_user)
    EditText mSearchUser;
    @Bind(R.id.admin_search)
    Button mAdminSearch;
    @Bind(R.id.admin_searchid)
    TextView mAdminSearchid;
    @Bind(R.id.admin_searchname)
    TextView mAdminSearchname;
    @Bind(R.id.admin_searchtime)
    TextView mAdminSearchtime;
    @Bind(R.id.admin_item)
    LinearLayout mAdminItem;
    @Bind(R.id.admin_delete)
    Button mAdminDelete;
    private String adminID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);
        try {
            //adminID = (String) getIntent().getSerializableExtra("adminID");
            adminID =this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("adminID", "");
            Log.e("adminID", adminID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdminName.setText("欢迎:"+adminID);
    }
    public void search(View view) {
        final String userID = mSearchUser.getText().toString().trim();
        if(userID!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
            OkHttpUtils.post().url(BASIC_URL+"/servlet/UserSearchServlet")
                    .addParams("uid", userID)
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
                                JSONObject message = jsonObject.getJSONObject("result");
                                mAdminSearchid.setText(message.getString("userid"));
                                mAdminSearchname.setText(message.getString("username"));
                                mAdminSearchtime.setText(message.getString("time"));
                            }else{
                                Toast.makeText(AdminHomeActivity.this,"用户不存在",Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }



            }).start();
        }
    }

    public void delete(View view) {
        final String userID = mAdminSearchid.getText().toString().trim();
        if(userID!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpUtils.post().url(BASIC_URL+"/servlet/UserDeleteServlet")
                            .addParams("uid", userID)
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
                                        Toast.makeText(AdminHomeActivity.this,"删除用户成功",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(AdminHomeActivity.this, AdminHomeActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(AdminHomeActivity.this,"删除用户失败",Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                }

            }).start();
        }
    }
}
