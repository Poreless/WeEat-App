package com.client.weeat.friend.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.client.weeat.R;
import com.client.weeat.app.FindFriendActivity;
import com.client.weeat.app.FriendMessageAgreeActivity;
import com.client.weeat.base.BaseFragment;
import com.client.weeat.friend.adapter.FriendListViewAdapter;
import com.client.weeat.friend.bean.FriendBeanDate;
import com.client.weeat.user.bean.UserBeanDate;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * 通讯录fragment
 */
public class FriendFragment extends BaseFragment {
    private FriendListViewAdapter adapter;
    private ListView lv_friend;
    private String userID;
    private String friendID;
    private String user_info_json;
    private FriendBeanDate.ResultBean resultBean;
    private RelativeLayout re_newfriend;
    private ImageButton initdate;
    @Override
    public View initView() {
        Log.e(TAG,"FriendFragment的UI内容初始化..");
        View view = View.inflate(mcontext, R.layout.fragment_friend, null);
        lv_friend = (ListView) view.findViewById(R.id.friend_listview);
        re_newfriend = (RelativeLayout) view.findViewById(R.id.re_newfriends);
        initdate = (ImageButton) view.findViewById(R.id.ib_friend_init);
        re_newfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, FindFriendActivity.class);
                mcontext.startActivity(intent);
            }
        });
        lv_friend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                friendID = resultBean.getFriend_info().get(position).getFid();
                Toast.makeText(mcontext,friendID,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, FriendMessageAgreeActivity.class);
                intent.putExtra("FRIENDID", friendID);
                intent.putExtra("USERID", userID);
                mcontext.startActivity(intent);
            }
        });
        initdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDate();
            }
        });
        return view;
    }

    /**
     * 数据服务
     */
    @Override
    protected void initDate() {
        super.initDate();
        try {
            user_info_json = this.getActivity().getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
            Log.e(TAG,"HomeFragment的数据内容初始化..");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = BASIC_URL+"/servlet/FriendServlet";
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
                        Toast.makeText(mcontext, "数据传输出现了问题，请重试==",
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
        FriendBeanDate resultBeanDate = JSON.parseObject(json,FriendBeanDate.class);
        resultBean = resultBeanDate.getResult();
        if(resultBean.getFriend_info()!=null){
            //设置适配器
            adapter = new FriendListViewAdapter(mcontext,resultBean.getFriend_info());
            lv_friend.setAdapter(adapter);
        }else{
            Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }

    }
}
