package com.client.weeat.home.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.client.weeat.R;
import com.client.weeat.app.CreateactActivity;
import com.client.weeat.base.BaseFragment;
import com.client.weeat.home.adapter.HomeFragmentAdapter;
import com.client.weeat.home.bean.ResultBeanDate;
import com.client.weeat.user.bean.UserBeanDate;
import com.longsh.optionframelibrary.OptionBottomDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * OKhttp框架https://github.com/hongyangAndroid/okhttputils
 * 主页面fragment
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView rvHome;   //存在着子视图并对应子适配器，无数据时相当于无内容的ListView
    private ImageView ib_top;
    private ImageView ib_add;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private String OldJSON = null;
    private String userID;
    private String user_info_json;
    /**
     * 返回的数据
     */
    private ResultBeanDate.ResultBean resultBean;
    private HomeFragmentAdapter adapter;
    private Dialog mAddDialog;
    /* private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_active: // 添加活动
                    Toast.makeText(mcontext,"创建活动==",Toast.LENGTH_SHORT).show();
                    break;
                // 添加动态
                case R.id.btn_add_community:
                    Toast.makeText(mcontext,"添加个人动态==",Toast.LENGTH_SHORT).show();
                    break;
                // 取消
                case R.id.btn_cancel:
                    Toast.makeText(mcontext,"取消对话框",Toast.LENGTH_SHORT).show();
                    if(mAddDialog!=null) {
                        mAddDialog.dismiss();
                    }
                    break;
            }
        }
    };*/
    @Override
    public View initView() {
        Log.e(TAG,"HomeFragment的UI内容初始化..");
        View view = View.inflate(mcontext, R.layout.fragment_home, null);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        ib_add = (ImageView) view.findViewById(R.id.ib_add);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        tv_message_home = (TextView)
                view.findViewById(R.id.tv_message_home);
        tv_message_home.setFocusable(true);
        tv_message_home.setFocusableInTouchMode(true);
        tv_message_home.requestFocus();
        // 设置点击事件
        initListener();
        return view;
    }

    private void initListener() {
        // 添加按钮的监听

        ib_add.setOnClickListener(new View.OnClickListener() {
            /**
             * 添加上拉对话框
             * @param v
             */
            @Override
            public void onClick(View v) {
/*                mAddDialog = new Dialog(mcontext, R.style.my_dialog);
                Dialog mAddDialog = new Dialog(mcontext, R.style.my_dialog);
*//*                mAddDialog.setContentView(R.layout.layout_add_control); //尝试解决dissmiss问题
                LinearLayout root = (LinearLayout) mAddDialog.getWindow().getDecorView();*//*
                LinearLayout root = (LinearLayout) LayoutInflater.from(mcontext).inflate(
                        R.layout.layout_add_control, null);
                root.findViewById(R.id.btn_add_active).setOnClickListener(btnlistener);
                root.findViewById(R.id.btn_add_community).setOnClickListener(btnlistener);
                root.findViewById(R.id.btn_cancel).setOnClickListener(btnlistener);
                mAddDialog.setContentView(root);
                Window dialogWindow = mAddDialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
                WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                lp.x = 0; // 新位置X坐标
                lp.y = -20; // 新位置Y坐标
                lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
                //      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
                //      lp.alpha = 9f; // 透明度
                root.measure(0, 0);
                lp.height = root.getMeasuredHeight();
                lp.alpha = 9f; // 透明度
                dialogWindow.setAttributes(lp);
                mAddDialog.show();*/
                if(userID==null){
                    Toast.makeText(mcontext, "请先登录", Toast.LENGTH_SHORT).show();
                }else{

                    List<String> stringList = new ArrayList<String>();
                    stringList.add("添加活动");
                    final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(mcontext, stringList);
                    optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(mcontext, CreateactActivity.class);
                            mcontext.startActivity(intent);
                            optionBottomDialog.dismiss();
                        }
                    });

                }
            }
        });
        // 置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        // 搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, " 搜索",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // 消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(mcontext, " 进行坐标更改",
                        Toast.LENGTH_SHORT).show();*/
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
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userID = userBeanDate.getResult().getUser_info().getUserID();  //获取用户信息
            //adapter = new ActiveGridViewAdapter(mcontext,active_info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG,"HomeFragment的数据内容初始化..");
        String url = BASIC_URL+"/servlet/HomeServlet";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("uid", userID)
                /*.addParams("password", "123")*/
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"请求失败  "+e.getMessage().toString());
                        Toast.makeText(mcontext, "数据传输出现了问题，请重试==",
                                Toast.LENGTH_SHORT).show();
                    }

                    /**
                     * 网络请求回调，异步请求
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"请求数据为  "+response.toString());
                        //数据接收成功后，解析数据，创建适配器
                        processDate(response);
                       // OldJSON = response.toString();
                    }
                });
    }

    private void processDate(String json) {

        ResultBeanDate resultBeanDate = JSON.parseObject(json,ResultBeanDate.class);
        resultBean = resultBeanDate.getResult();
        if(resultBean!=null){
           //设置适配器
            adapter = new HomeFragmentAdapter(mcontext,resultBean);
            rvHome.setAdapter(adapter);
            //设置布局管理者
            GridLayoutManager manager =  new GridLayoutManager(mcontext,1);
            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 1){
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }else{
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回1
                    return 1;
                }
            });
            rvHome.setLayoutManager(manager);
        }else{
            Log.e(TAG,"解析成功，该活动为："+resultBean.getActive_info().get(1).getActive_name());

        }


    }

}
