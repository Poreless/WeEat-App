package com.client.weeat.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.client.weeat.home.adapter.ActiveListViewAdapter;
import com.client.weeat.home.bean.ActListBean;
import com.client.weeat.home.bean.ActivesBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListActiveActivity extends AppCompatActivity {

    @Bind(R.id.list_active_more)
    ImageButton mListActiveMore;
    @Bind(R.id.lv_actlist)
    ListView mLvActlist;
    @Bind(R.id.activity_actives_info)
    LinearLayout mActivityActivesInfo;
    @Bind(R.id.ib_actlist_back)
    ImageButton mIbActlistBack;
    private String Active_String;
    private ArrayList<ActListBean> datas;
    private ActListBean.UserBean user;
    private ActiveListViewAdapter adapter;
    private ActListBean active;
    private static final String ACTIVES_BEAN = "activesBean";
    private JSONArray json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_active);
        ButterKnife.bind(this);
        Active_String = new String();
        mIbActlistBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            //接收数据
            Active_String = (String) getIntent().getSerializableExtra("list");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Active_String != null) {
            datas = new ArrayList<ActListBean>();
            json = JSONArray.parseArray(Active_String);
            if (json.size() > 0) {
                for (int i = 0; i < json.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject act = json.getJSONObject(i);
                    active = new ActListBean();
                    // active.setActive_icon(act.getString("acticon"));
                    active.setActive_name(act.getString("name"));
                    active.setActive_theme(act.getString("theme"));
                    active.setActive_place(act.getString("place"));
                    active.setCreate_time(act.getString("time"));
                    active.setMaxnum(act.getString("num"));
                    user = new ActListBean.UserBean();
                    user.setUserIcon(act.getString("usericon"));
                    user.setUserName(act.getString("username"));
                    active.setUser(user);
                    datas.add(active);
                }
            }
        }


        adapter = new ActiveListViewAdapter(this, datas);
        mLvActlist.setAdapter(adapter);
        mLvActlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject act = json.getJSONObject(position);
                //二次转发
                ActivesBean activesBean = new ActivesBean();
                activesBean.setActive_name(act.getString("name"));
                activesBean.setActive_place(act.getString("place"));
                activesBean.setActive_depict(act.getString("dep"));
                activesBean.setActive_id(act.getString("id"));
                activesBean.setCreate_time(act.getString("place"));
                activesBean.setActive_theme(act.getString("theme"));
                activesBean.setActshow_usericon(act.getString("usericon"));
                activesBean.setActshow_username(act.getString("username"));
                activesBean.setActshow_userid(act.getString("userid"));
                startActivesInfoActivity(activesBean);
            }
        });

    }

    private void startActivesInfoActivity(ActivesBean activesBean) {
        Intent intent = new Intent(this, ActiveShowActivity.class);
        intent.putExtra(ACTIVES_BEAN, activesBean);
        this.startActivity(intent);
    }


}
