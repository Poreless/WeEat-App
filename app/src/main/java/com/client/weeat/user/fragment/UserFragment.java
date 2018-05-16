package com.client.weeat.user.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.client.weeat.R;
import com.client.weeat.app.ExitActivity;
import com.client.weeat.app.MainActivity;
import com.client.weeat.app.SettingActivity;
import com.client.weeat.base.BaseFragment;
import com.client.weeat.user.adapter.EDListAdapter;
import com.client.weeat.user.adapter.FavoriteAdapter;
import com.client.weeat.user.bean.CommentBeanDate;
import com.client.weeat.user.bean.UserBeanDate;
import com.client.weeat.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.client.weeat.util.Constants.BASIC_URL;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * 用户中心fragment
 */
public class UserFragment extends BaseFragment {

    private UserBeanDate.ResultBean.UserInfoBean userInfoBean;
    private String user_info_json;
    private String tag_info_json;
    private ImageView iv_me_icon;
    private TextView tv_me_name;
    private String userID;
    private CommentBeanDate.ResultBean resultBean;
    private EDListAdapter adapter;
    private ExpandableListView edlist;
    private TextView focustv;
    private ArrayList<String> taglist;
    private GridView gv_favorite;
    private FavoriteAdapter mFavoriteAdapter;
    private Button Setting;
    private ImageButton initdate;
    /**
     * 切换刷新
     */
    //protected boolean isCreated = false;
    @Override
    public View initView() {
        Log.e(TAG, "UserFragment的UI内容初始化..");
        View view = View.inflate(mcontext, R.layout.fragment_user, null);
        iv_me_icon = (ImageView) view.findViewById(R.id.iv_me_icon);
        tv_me_name = (TextView) view.findViewById(R.id.tv_me_name);
        edlist = (ExpandableListView) view.findViewById(R.id.expand_list);
        Setting = (Button) view.findViewById(R.id.msg_change);
        gv_favorite = (GridView) view.findViewById(R.id.gv_favorite);
        //        设置分组项的点击监听事件
        initdate = (ImageButton) view.findViewById(R.id.ib_init);
        edlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"打开设置",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, SettingActivity.class);
                mcontext.startActivity(intent);
            }
        });
        initdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDate();
            }
        });
        //focustv = (TextView) view.findViewById(R.id.fragment_user_focus);
        //防止焦点丢失，顶部设置焦点
  /*      focustv.setFocusable(true);
        focustv.setFocusableInTouchMode(true);
        focustv.requestFocus();*/

        iv_me_icon.setFocusable(true);
        iv_me_icon.setFocusableInTouchMode(true);
        iv_me_icon.requestFocus();
        return view;
    }

    /**
     * 数据服务
     */
    @Override
    protected void initDate() {
        super.initDate();
        //用户基本信息
        try {
            user_info_json = this.getActivity().getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("user_info_json", "");
            Log.e("user_info_json",user_info_json);
            UserBeanDate userBeanDate = JSON.parseObject(user_info_json,UserBeanDate.class);
            userInfoBean = userBeanDate.getResult().getUser_info();
            userID = userBeanDate.getResult().getUser_info().getUserID().toString();  //获取用户信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        //用户兴趣的本地json解析
        try {
            tag_info_json = this.getActivity().getSharedPreferences("user_info", MODE_PRIVATE)
                    .getString("tag_info_json", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        taglist = new ArrayList<>();
        JSONArray json = JSONArray.parseArray(tag_info_json);
        //在浏览器中显示为utf-8
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject tag = json.getJSONObject(i);
                taglist.add(tag.get("name").toString());
                Log.e("兴趣"+i,tag.get("name").toString());
            }
        }
        mFavoriteAdapter = new FavoriteAdapter(mcontext,taglist);
        gv_favorite.setAdapter(mFavoriteAdapter);
        Log.e(TAG, "UserFragment的数据内容初始化..");
        //判断是否登录
        if (TextUtils.isEmpty(user_info_json)) {
            //本地无用户信息
            iv_me_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, MainActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        } else {
            iv_me_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, ExitActivity.class);
                    mcontext.startActivity(intent);
                }
            });
            Log.e("user_info_json",user_info_json);
            if(userInfoBean.getSchool()!=null) {
                tv_me_name.setText(userInfoBean.getUserName() + "  " + userInfoBean.getSchool());
            }else{
                tv_me_name.setText(userInfoBean.getUserName());
            }
           if(userInfoBean.getUserSex()!=null){
                if(userInfoBean.getUserSex().equals("男")){
                    Drawable img_on, img_off;
                    Resources res = getResources();
                    img_off = res.getDrawable(R.drawable.sex_man);
                    //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                    tv_me_name.setCompoundDrawables(null, null, img_off, null); //设置左图标
                }
                if(userInfoBean.getUserSex().equals("女")){
                    Drawable img_on, img_off;
                    Resources res = getResources();
                    img_off = res.getDrawable(R.drawable.sex_woman);
                    //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                    tv_me_name.setCompoundDrawables(null, null, img_off, null); //设置左图标

                }
            }

            Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+userInfoBean.getUserIcon()).asBitmap()
                    .error(R.drawable.icon_callserver_unpressed).into(new BitmapImageViewTarget(iv_me_icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            iv_me_icon.setImageBitmap(resource);
                        }
                    });
        }

        //网络数据

        String url = BASIC_URL+"/servlet/HomeCommentServlet";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("uid", userID)
/*                .addParams("username", "hyman")
                .addParams("password", "123")*/
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


/*    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //当此fragment正当前显示是，执行该操作，
            Log.e("message","调用setUserVisibleHint方法");
            initDate();
        } else {
            // 相当于Fragment的onPause
            // System.out.println("ChatFragment ---setUserVisibleHint---isVisibleToUser - FALSE");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initDate();
        }
    }*/
    private void processDate(String json) {

        CommentBeanDate commentBean = JSON.parseObject(json, CommentBeanDate.class);
        resultBean = commentBean.getResult();
        if (resultBean.getActive_comment()!= null) {
            //设置适配器
            adapter = new EDListAdapter(mcontext,resultBean.getActive_comment());
            edlist .setAdapter(adapter);
        } else {
            // Log.e(TAG,"解析成功，该活动为："+resultBean.getFriend_info().get(1).getFid());

        }
    }
}
