package com.client.weeat.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.user.bean.UserBeanDate;
import com.client.weeat.util.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.client.weeat.util.Constants.BASIC_URL;

public class MainActivity extends AppCompatActivity {

    private EditText et_userid;
    private EditText et_passwd;
    private CheckBox cb_remember;
    private UserBeanDate.ResultBean.UserInfoBean userInfoBean;
    private TextView tv_regist_tip;

    /**
     * 1.定义一个共享参数(存放数据方便的api)
     */
    private SharedPreferences sp;
    private Button bt_login;
    private Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //super.调用父类方法，savedInstanceState是保存当前Activity的状态信息
        setContentView(R.layout.activity_main);
        /**
         * HomeActivity -> MainActivity 活动未销毁，不会执行oncreate
         */

        //测试，省略登录项  -> 首页

   /*        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            MainActivity.this.startActivity(intent);
            finish();*/

        //测试，省略登录项  -> 通讯录
/*       Intent intent = new Intent(MainActivity.this,RegistTagActivity.class);
        MainActivity.this.startActivity(intent);
        finish();*/
        // 2.通过上下文得到一个共享参数的实例对象
        sp = this.getSharedPreferences("config", this.MODE_PRIVATE);

        et_userid = (EditText) findViewById(R.id.et_userid);
        et_passwd = (EditText) findViewById(R.id.et_passwd);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);
        bt_login = (Button) findViewById(R.id.bt_login);
        admin = (Button) findViewById(R.id.bt_login_admin);
        tv_regist_tip= (TextView) findViewById(R.id.tv_regist_tip);
        tv_regist_tip.setOnClickListener(new View.OnClickListener() {
            //通过匿名的方法实现按钮点击响应事件
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistActivity.class);
                MainActivity.this.startActivity(intent);
                //finish();
            }
        });
        restoreInfo();
    }

    /**
     * 从sp文件当中读取信息
     */
    private void restoreInfo() {
        String userID = sp.getString("userID", "");
        String password = sp.getString("password", "");
        et_userid.setText(userID);
        et_passwd.setText(password);
    }

    /**
     * 登录按钮的点击事件
     *
     * @param view
     */
    public void login(View view) {
        final String userID = et_userid.getText().toString().trim();
        final String password = et_passwd.getText().toString().trim();

        if (TextUtils.isEmpty(userID) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // 判断是否需要记录用户名和密码
            if (cb_remember.isChecked()) {
                // 被选中状态，需要记录用户名和密码
                // 3.将数据保存到sp文件中
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userID", userID);
                editor.putString("password", password);
                editor.commit();// 提交数据，类似关闭流，事务
            }

            //bt_login.setEnabled(false);
            new Thread() {
                @Override
                public void run() {
                    try {
                        //Thread.sleep(5000);
                        String path = BASIC_URL+"/servlet/LoginServlet";
                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //通信方式为HttpURLConnect
                        conn.setRequestMethod("POST");
                        conn.setConnectTimeout(5000);

                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 设置发送的数据为表单类型，会被添加到http body当中

                        String data = "userID=" + URLEncoder.encode(userID, "utf-8") + "&password=" + URLEncoder.encode(password);

                        conn.setRequestProperty("Content-Length", String.valueOf(data.length()));

                        // post的请求是把数据以流的方式写给了服务器
                        // 指定请求输出模式
                        conn.setDoOutput(true);// 运行当前的应用程序给服务器写数据
                        conn.getOutputStream().write(data.getBytes());

                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream is = conn.getInputStream();
                            String result = StreamUtils.readStream(is);   //读取返回数据

                            JSONObject jsonObject = JSON.parseObject(result);
                            String  msg = jsonObject.getString("msg");
                            if(msg.equals("success")) {
                            /*进行登陆跳转*/
/*                                UserBeanDate userBeanDate = JSON.parseObject(result,UserBeanDate.class);
                                userInfoBean = userBeanDate.getResult().getUser_info();*/
                                sp = MainActivity.this.getSharedPreferences("user_info", MainActivity.this.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("user_info_json", result);
                                editor.commit();//必须提交，否则保存不成功

                                //这里做兴趣的提取，保存本地
                                editor.putString("tag_info_json", jsonObject.getString("tag"));
                                Log.e("TAG","请求数据为  "+jsonObject.getString("tag"));
                                editor.commit();//必须提交，否则保存不成功

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                MainActivity.this.startActivity(intent);
                                finish();

                                showToastInAnyThread(result);
                            }else{
                                showToastInAnyThread("用户名或密码错误==");
                            }

                        } else {
                            showToastInAnyThread("请求失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastInAnyThread("请求失败");
                    }
                }
            }.start();
        }

    }

    public void admin(View view) {
        final String userID = et_userid.getText().toString().trim();
        final String password = et_passwd.getText().toString().trim();

        if (TextUtils.isEmpty(userID) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // 判断是否需要记录用户名和密码
            if (cb_remember.isChecked()) {
                // 被选中状态，需要记录用户名和密码
                // 3.将数据保存到sp文件中
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userID", userID);
                editor.putString("password", password);
                editor.commit();// 提交数据，类似关闭流，事务
            }

            //bt_login.setEnabled(false);
            new Thread() {
                @Override
                public void run() {
                    try {
                        //Thread.sleep(5000);
                        String path = BASIC_URL+"/servlet/AdminServlet";
                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //通信方式为HttpURLConnect
                        conn.setRequestMethod("POST");
                        conn.setConnectTimeout(5000);

                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 设置发送的数据为表单类型，会被添加到http body当中

                        String data = "adminID=" + URLEncoder.encode(userID, "utf-8") + "&password=" + URLEncoder.encode(password);

                        conn.setRequestProperty("Content-Length", String.valueOf(data.length()));

                        // post的请求是把数据以流的方式写给了服务器
                        // 指定请求输出模式
                        conn.setDoOutput(true);// 运行当前的应用程序给服务器写数据
                        conn.getOutputStream().write(data.getBytes());

                        int code = conn.getResponseCode();
                        if (code == 200) {
                            InputStream is = conn.getInputStream();
                            String result = StreamUtils.readStream(is);   //读取返回数据

                            JSONObject jsonObject = JSON.parseObject(result);
                            String  msg = jsonObject.getString("msg");
                            if(msg.equals("success")) {

                                Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                                intent.putExtra("adminID", userID);
                                MainActivity.this.startActivity(intent);

                                sp = MainActivity.this.getSharedPreferences("user_info", MainActivity.this.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("adminID", userID);
                                editor.commit();//必须提交，否则保存不成功
                                finish();



                                showToastInAnyThread(result);
                            }else{
                                showToastInAnyThread("用户名或密码错误==");
                            }

                        } else {
                            showToastInAnyThread("请求失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToastInAnyThread("请求失败");
                    }
                }
            }.start();
        }

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
