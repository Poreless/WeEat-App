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
import com.client.weeat.message.adapter.CommentListAdapter;
import com.client.weeat.message.bean.FinishActBean;
import com.client.weeat.user.bean.UserBeanDate;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * 待评价列表
 */
public class CommentActivity extends AppCompatActivity {

    @Bind(R.id.ib_comment_back)
    ImageButton mIbCommentBack;
    @Bind(R.id.ib_comment_more)
    ImageButton mIbCommentMore;
    @Bind(R.id.comment_listview)
    ListView mCommentListview;

    private String userID;
    private String user_info_json;
    private FinishActBean.ResultBean resultBean;
    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initDate();
        mCommentListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CommentActivity.this, CommentItemActivity.class);
                intent.putExtra("USERID", userID);
                intent.putExtra("ACTID", resultBean.getActive_finish_info()
                .get(position).getActive_id()+"");
                CommentActivity.this.startActivity(intent);
            }
        });
        mIbCommentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, HomeActivity.class);
                intent.putExtra("fragmentID", 2);  //传到会话fragment
                CommentActivity.this.startActivity(intent);
                finish();
            }
        });
    }
    private void initDate() {
        try {
            user_info_json = this.getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json, UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID().toString();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
            Log.e(TAG, "待评价列表的数据内容初始化..");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = BASIC_URL + "/servlet/CommentServlet";
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
                        Toast.makeText(CommentActivity.this, "数据传输出现了问题，请重试==",
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
        FinishActBean finishactBean = JSON.parseObject(json, FinishActBean.class);
        resultBean = finishactBean.getResult();
        if (resultBean.getActive_finish_info()!= null) {
            //设置适配器
            adapter = new CommentListAdapter(this,resultBean.getActive_finish_info());
            mCommentListview.setAdapter(adapter);
        } else {
            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }

    }
}
