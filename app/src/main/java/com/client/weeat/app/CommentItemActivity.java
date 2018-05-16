package com.client.weeat.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.weeat.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static com.client.weeat.R.id.ratingBar;
import static com.client.weeat.util.Constants.BASIC_URL;


/**
 * 这个activity则为具体活动的评价操作
 */
public class CommentItemActivity extends AppCompatActivity {
    private RatingBar rb_comment;
    private EditText comment_text;
    private ImageButton ib_comment_item_back;
    private Button btn_comment_up;
    private String user_id;
    private String act_id;
    private String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_item);
        rb_comment = (RatingBar) findViewById(ratingBar);
        comment_text = (EditText) findViewById(R.id.comment_text);
        ib_comment_item_back = (ImageButton) findViewById(R.id.ib_comment_item_back);
        btn_comment_up = (Button) findViewById(R.id.btn_comment_up);
        user_id = (String) getIntent().getSerializableExtra("USERID");
        act_id = (String) getIntent().getSerializableExtra("ACTID");
        Log.e("user_id",user_id);
        Log.e("act_id",act_id);
        rb_comment.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                num = Float.toString(rating);
            }
        });
        ib_comment_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_comment_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text= comment_text.getText().toString().trim();
                if (TextUtils.isEmpty(text)||TextUtils.isEmpty(num)){
                    Toast.makeText(CommentItemActivity.this, "请填入完整的活动信息", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String url = BASIC_URL + "/servlet/CommentAddServlet";
                            OkHttpUtils
                                    .post()
                                    .url(url)
                                    .addParams("uid", user_id)
                                    .addParams("aid", act_id)
                                    .addParams("num", num)
                                    .addParams("text", text)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e(TAG, "请求失败  " + e.getMessage().toString());
                                            Toast.makeText(CommentItemActivity.this, "数据传输出现了问题，请重试==",
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
                                            JSONObject jsonObject = JSON.parseObject(response);
                                            String  msg = jsonObject.getString("msg");
                                            if(msg.equals("success")) {
                                                showToastInAnyThread("评价成功！！");
                                                Intent intent = new Intent(CommentItemActivity.this, CommentActivity.class);
                                                CommentItemActivity.this.startActivity(intent);
                                                finish();
                                            }else{
                                                Toast.makeText(CommentItemActivity.this, "评价失败",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                     }


                }

        });

    }
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
