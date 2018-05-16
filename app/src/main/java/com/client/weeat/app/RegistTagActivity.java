package com.client.weeat.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.user.bean.UserBeanDate;
import com.client.weeat.util.FlowLayout;
import com.client.weeat.util.TagAdapter;
import com.client.weeat.util.TagFlowLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import okhttp3.Call;

import static com.client.weeat.util.Constants.BASIC_URL;

public class RegistTagActivity extends AppCompatActivity {
    private String mVals[] = { "家常菜", "海鲜", "快餐 ", "自助餐", "奶茶", "果汁", "烧烤",
            "沙拉", "麻辣烫", "甜食", "小吃" };
    private ArrayList<String> sel = new ArrayList<>();
    private SharedPreferences sp;
    private int[] background = { R.drawable.blue, R.drawable.green,
            R.drawable.red, R.drawable.black};
    private TagFlowLayout mFlowLayout;
    private Button btup;
    private Context mContext = null;
    private List<TextView> tvs = null;
    private UserBeanDate.ResultBean.UserInfoBean userInfoBean;
    private String user_info_json;
    private String userID;
    private JSONArray tagjson;  //不需要键值
    private JSONObject tagname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_event_test);
        init();
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        mFlowLayout = (TagFlowLayout)findViewById(R.id.id_flowlayout);
        btup = (Button) findViewById(R.id.btn_interest_up);
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userInfoBean = userBeanDate.getResult().getUser_info();
            userID = userBeanDate.getResult().getUser_info().getUserID().toString();  //获取用户信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
        {

            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s)
            {
                return s.equals("Android");
            }
        });


        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            public Bitmap mKeyWordBackground;

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                Toast.makeText(mContext, mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
/*                mKeyWordBackground = BitmapFactory.decodeResource(getResources(), R.drawable.flag_02);
                Drawable b = new BitmapDrawable(mKeyWordBackground);
                view.setBackground(b);*/
                int key = 0;
                for(int i =0;i<sel.size();i++){
                    if(sel.get(i).equals(mVals[position])){
                        sel.remove(i);
                        key = 1;
                    }
                }
                if(key==0){
                    sel.add(mVals[position]);
                }
                return true;
            }
        });


        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener()
        {
            @Override
            public void onSelected(Set<Integer> selectPosSet)
            {
                Toast.makeText(mContext,"choose:" + selectPosSet.toString(),Toast.LENGTH_SHORT);

            }


        });

        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagjson = new JSONArray();
                try {
                    String str="";
                    for(int i=0;i<sel.size();i++){
                        str+=sel.get(i)+" ";
                        tagname = new JSONObject();
                        tagname.put("name",sel.get(i));
                        tagjson.add(tagname);
                    }
                    Toast.makeText(RegistTagActivity.this,str,Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //userID = "14211160101"; //测试用
                OkHttpUtils.post().url(BASIC_URL+"/servlet/TagServlet")
                         .addParams("taglistjson", tagjson.toJSONString())
                        .addParams("uid", userID)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                JSONObject jsonObject = JSON.parseObject(response);
                                String  msg = jsonObject.getString("msg");
                                if(msg.equals("success")){
                                    sp = RegistTagActivity.this.getSharedPreferences("user_info", RegistTagActivity.this.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("tag_info_json", jsonObject.getString("result"));
                                    editor.commit();//必须提交，否则保存不成功
                                    Log.e("TAG","请求数据为  "+jsonObject.getString("result"));

                                    Intent intent = new Intent(RegistTagActivity.this, HomeActivity.class);
                                    RegistTagActivity.this.startActivity(intent);
                                    finish();
                                }else{

                                }
                            }
                        });
            }
        });



    }







    //子视图的更新与监听，这里统一适配器
    private void init() {
        initData();
        initChildViews();
        initEvent();

    }

    private void initData() {
        mContext = this;
        tvs = new LinkedList<TextView>();
    }

    private void initChildViews() {

       /* mFlowLayout = (CoustomFlowLayout) findViewById(R.id.flowlayout);
        LayoutInflater mInflater = LayoutInflater.from(this);
        // MarginLayoutParams lp = new MarginLayoutParams(
        // LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // lp.leftMargin = 5;
        // lp.rightMargin = 5;
        // lp.topMargin = 5;
        // lp.bottomMargin = 5;
        Random random = new Random();
        //做内容适配工作
        for (int i = 0; i < mNames.length; i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                    mFlowLayout, false);
            tv.setText(mNames[i]);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(22);
            tv.setGravity(0);
            tv.setBackgroundResource(R.drawable.flag_01);
            tvs.add(tv);
            mFlowLayout.addView(tv);
        }*/
    }

    private void initEvent() {
/*        String str = new String();
        tvs.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = mContext.getSharedPreferences("user_info", mContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user_info_json", ""+mNames[0]);
                editor.commit();//必须提交，否则保存不成功

            }
        });*/
    }
}
