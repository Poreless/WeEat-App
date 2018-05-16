package com.client.weeat.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.ui.ProvinceAdapter;
import com.client.weeat.ui.SchoolAdapter;
import com.github.yoojia.fireeye.FireEye;
import com.github.yoojia.fireeye.Form;
import com.github.yoojia.fireeye.MessageDisplay;
import com.github.yoojia.fireeye.Result;
import com.github.yoojia.fireeye.StaticPattern;
import com.github.yoojia.fireeye.TextViewLoader;
import com.github.yoojia.fireeye.ValuePattern;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

import static com.client.weeat.R.id.school;
import static com.client.weeat.util.Constants.BASIC_URL;

public class RegistActivity extends AppCompatActivity {
    private EditText et_register_number;
    private EditText et_register_stid;
    private EditText et_register_name;
    private EditText et_register_pwd;
    private EditText mSelectSchool;
    private EditText et_register_pwdagain;
    private Button btn_register;
    private JSONObject registjson;
    private Map<String, String> RegistInfoMap = new HashMap<String, String>();
    private Result r;
    private FireEye fireEye;
    private Form form;
    private String date;
    private SharedPreferences sp;

    /**
     * popView相关
     **/
    private View parent;
    private ListView mProvinceListView;
    private ListView mSchoolListView;
    private TextView mTitle;
    private PopupWindow mPopWindow;
    /**
     * Adapter相关
     **/
    private ProvinceAdapter mProvinceAdapter;
    private SchoolAdapter mSchoolAdapter;
    private ArrayList<String> cities;

    public RegistActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regist);
        et_register_number = (EditText) findViewById(R.id.et_register_number);
        et_register_stid = (EditText) findViewById(R.id.et_register_stid);
        et_register_name = (EditText) findViewById(R.id.et_register_name);
        mSelectSchool = (EditText) findViewById(R.id.tv_register_school);
        et_register_pwd = (EditText) findViewById(R.id.et_register_pwd);
        et_register_pwdagain = (EditText) findViewById(R.id.et_register_pwdagain);
        /**
         * 使用Fire Eye
         * 自定义显示出错消息的方式，默认是在 EditText 右边显示一个浮动提示框
         */
        MessageDisplay messageDisplay = new MessageDisplay() {
            @Override
            public void dismiss(TextView field) {
                field.setError(null);
            }

            @Override
            public void show(TextView field, String message) {
                field.setError(message);
                Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };
        // 绑定表单View
        View formView = findViewById(R.id.et_register_form);
        form = new Form(formView);


        initPopView();
        mSelectSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });

    }


    private void initPopView() {
        parent = this.getWindow().getDecorView();
        View popView = View.inflate(this, R.layout.view_select_province_list, null);
        mTitle = (TextView) popView.findViewById(R.id.list_title);

        mProvinceListView = (ListView) popView.findViewById(R.id.province);
        mSchoolListView = (ListView) popView.findViewById(school);

        mProvinceListView.setOnItemClickListener(itemListener);
        mSchoolListView.setOnItemClickListener(itemListener);



        mProvinceAdapter = new ProvinceAdapter(this);
        mProvinceListView.setAdapter(mProvinceAdapter);

        mSchoolAdapter = new SchoolAdapter(this);
        mSchoolListView.setAdapter(mSchoolAdapter);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        mPopWindow = new PopupWindow(popView, width, height);
        ColorDrawable dw = new ColorDrawable(0x30000000);
        mPopWindow.setBackgroundDrawable(dw);
        getWindow().setAttributes(lp);  //全屏

        mPopWindow.setFocusable(true);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);//允许在外侧点击取消

        loadProvince();
        mPopWindow.setOnDismissListener(listener);
    }

    private void showPopWindow() {
        mPopWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
    private void loadProvince() {
        cities =  new ArrayList<>();
        cities.add("温州");
        cities.add("杭州");
        cities.add("宁波");
        cities.add("绍兴");
        mProvinceAdapter.setList(cities);
        //动态刷新列表
        mProvinceAdapter.notifyDataSetChanged();

    }
    /**
     * ListView Item点击事件
     */
    AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (parent == mProvinceListView) {
                mTitle.setText("选择学校");

                mProvinceListView.setVisibility(View.GONE);
                mSchoolListView.setVisibility(View.VISIBLE);

                ArrayList<String> schoollist = new ArrayList<>();
                switch (position){
                    case 0:
                        schoollist.add("温州大学");
                        schoollist.add("温州医科大学");
                        schoollist.add("温州科技职业技术学院");
                        schoollist.add("温州大学城市学院");
                        break;
                    case 1:
                        schoollist.add("杭州电子科技大学");
                        schoollist.add("浙江工业大学");
                        schoollist.add("浙江财经大学");
                        schoollist.add("浙江大学");
                        break;
                    case 2:
                        schoollist.add("宁波大学");
                        break;
                    case 3:
                        schoollist.add("绍兴文理学院");
                        break;
                }
                mSchoolAdapter.setList(schoollist);
                //动态刷新列表
                mSchoolAdapter.notifyDataSetChanged();
            } else if (parent == mSchoolListView) {
                String schoolName = (String) mSchoolListView.getItemAtPosition(position);
                mSelectSchool.setText(schoolName);
                mPopWindow.dismiss();
            }
        }
    };

    /**
     * popWindow消失监听事件
     */
    PopupWindow.OnDismissListener listener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            mTitle.setText("选择地区");
            mProvinceListView.setVisibility(View.VISIBLE);
            mSchoolAdapter.setList(new ArrayList<String>());
            mSchoolAdapter.notifyDataSetChanged();
            mSchoolListView.setVisibility(View.GONE);
        }
    };
    /**
     * 注册按钮事件
     * @param view
     */
    public void regist(View view) {
        final String PhoneNumber = et_register_number.getText().toString().trim();
        final String IDNumber = et_register_stid.getText().toString().trim();
        final String RegistName = et_register_name.getText().toString().trim();
        final String RegistPwd = et_register_pwd.getText().toString();
        final String RegistSchool= mSelectSchool.getText().toString();
        //fireEye是一个轻量级的表单验证库，后续将会做更多的验证
        fireEye = new FireEye(RegistActivity.this);
        fireEye.add(form.byId(R.id.et_register_number),
                StaticPattern.Required.setMessage("手机号格式不正确"),
                StaticPattern.Mobile);   //手机号码格式
        fireEye.add(form.byId(R.id.et_register_stid),
                StaticPattern.Numeric, StaticPattern.Required
                );  //仅数字
        fireEye.add(form.byId(R.id.et_register_name),
                StaticPattern.Required);
        fireEye.add(form.byId(R.id.et_register_pwd),
                StaticPattern.Required);
        fireEye.add(form.byId(R.id.et_register_pwdagain),
                StaticPattern.Required);
        fireEye.add(form.byId(R.id.et_register_pwd),       //验证密码
                ValuePattern.Required,
                ValuePattern.EqualsTo.lazy(new TextViewLoader(form.byId(R.id.et_register_pwdagain))));
        //学号限制位数
        r = fireEye.test();

        if(r.passed){
            registjson = new JSONObject();
            try {
                registjson.put("phonenumber", PhoneNumber);
                registjson.put("idnumber", IDNumber);
                registjson.put("registname", RegistName);
                registjson.put("registpwd", RegistPwd);
                registjson.put("school", RegistSchool);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Toast.makeText(RegistActivity.this,registjson.toJSONString(),Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
       /*             MediaType mediaType = MediaType.parse("application/json");*/
           /*         OkHttpClient client=new OkHttpClient();
                    //application/json表示传递的是一个json格式的对象
                    RequestBody requestBody = new FormBody.Builder().add("username","reoger").add("passwd","123").build();
                    //2.2 构建一个builder
                    Request.Builder builder = new Request.Builder();
                    //2.3 完成Request的构建
                    Request request = builder.post(requestBody).url(BASIC_URL+"/servlet/RegistServlet").build();
                    //3 获得Call对象
                    okhttp3.Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("TAG","Error"+e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                        }
                    });*/
                   OkHttpUtils.post().url(BASIC_URL+"/servlet/RegistServlet")
                            .addParams("registjson", registjson.toJSONString())
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
                                sp = RegistActivity.this.getSharedPreferences("user_info", RegistActivity.this.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("user_info_json", response.toString());
                                editor.commit();//必须提交，否则保存不成功
                                Log.e("TAG","请求数据为  "+response.toString());

                                Intent intent = new Intent(RegistActivity.this,RegistTagActivity.class);
                                RegistActivity.this.startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(RegistActivity.this,jsonObject.getString("erro"),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
 /*                   try {
                        //Thread.sleep(5000);
                        String path = BASIC_URL+"/servlet/LoginServlet";
                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //通信方式为HttpURLConnect
                        conn.setRequestMethod("POST");
                        conn.setConnectTimeout(5000);

                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 设置发送的数据为表单类型，会被添加到http body当中

                        String data = "registjson=" + URLEncoder.encode(registjson.toJSONString(), "utf-8") ;

                        conn.setRequestProperty("Content-Length", String.valueOf(data.length()));

                        // post的请求是把数据以流的方式写给了服务器
                        // 指定请求输出模式
                        conn.setDoOutput(true);// 运行当前的应用程序给服务器写数据
                        conn.getOutputStream().write(data.getBytes());
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }*/

                }

            }).start();
        }else{

        }



    }

    /**
     * 表单校验函数
     */

}
