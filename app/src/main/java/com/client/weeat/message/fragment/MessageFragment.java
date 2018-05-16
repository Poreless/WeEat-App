package com.client.weeat.message.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.client.weeat.R;
import com.client.weeat.app.ActchangeActivity;
import com.client.weeat.app.ActmanageActivity;
import com.client.weeat.app.CommentActivity;
import com.client.weeat.app.FriendRequestActivity;
import com.client.weeat.base.BaseFragment;
import com.client.weeat.message.adapter.ActnowAdapter;
import com.client.weeat.message.bean.ActnowBean;
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
 * 会话消息fragment
 */
public class MessageFragment extends BaseFragment {
    private LinearLayout ll_friend_ask;
    private LinearLayout ll_act_ask;
    private LinearLayout ll_act_change;
    private LinearLayout ll_act_comment;
    private String userID;
    private String user_info_json;
    private ListView lv_message;
    private ActnowBean.ResultBean resultBean;
    private ActnowAdapter adapter;
    private ImageButton fresh;
    @Override
    public View initView() {
        Log.e(TAG,"MessageFragment的UI内容初始化..");
        View view = View.inflate(mcontext, R.layout.fragment_message, null);
        ll_friend_ask = (LinearLayout) view.findViewById(R.id.ll_friend_ask);
        ll_act_ask = (LinearLayout) view.findViewById(R.id.ll_active_ask);
        ll_act_change = (LinearLayout) view.findViewById(R.id.ll_active_change);
        ll_act_comment = (LinearLayout) view.findViewById(R.id.ll_active_comment);
        lv_message = (ListView) view.findViewById(R.id.message_listview);
        fresh = (ImageButton) view.findViewById(R.id.ib_fragment_message);
        initDate();
        initListener();
        return view;
    }

    private void initListener() {
        // 添加按钮的监听
        ll_act_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"打开活动申请==",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, ActmanageActivity.class);
                mcontext.startActivity(intent);

            }
        });
        ll_act_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"打开活动管理==",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, ActchangeActivity.class);
                mcontext.startActivity(intent);

            }
        });
        ll_act_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"打开活动评价==",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, CommentActivity.class);
                mcontext.startActivity(intent);

            }
        });
        ll_friend_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"打开好友请求==",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, FriendRequestActivity.class);
                mcontext.startActivity(intent);

            }
        });
        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDate();
            }
        });
    }

    /**
     * 数据服务
     */
    @Override
    protected void initDate() {
        super.initDate();
        try {
            user_info_json = mcontext.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json, UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID().toString();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
            Log.e(TAG, "HomeFragment的数据内容初始化..");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = BASIC_URL + "/servlet/ActnowServlet";
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
        ActnowBean nowBean = JSON.parseObject(json, ActnowBean.class);
        if (nowBean.getResult()!=null) {
            //设置适配器
           /* handler.sendEmptyMessageDelayed(0,1000);*/
            adapter = new ActnowAdapter(mcontext,nowBean.getResult());
            lv_message.setAdapter(adapter);
        } else {
            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }

    }
/*    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0,1000);

        }
    };*/

    /**
     * 登录按钮的点击事件
     *
     * @param view
     */

}
