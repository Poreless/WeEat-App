package com.client.weeat.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.client.weeat.R;
import com.client.weeat.app.ActiveShowActivity;
import com.client.weeat.app.ListActiveActivity;
import com.client.weeat.app.LoadWebActivity;
import com.client.weeat.home.bean.ActivesBean;
import com.client.weeat.home.bean.ResultBeanDate;
import com.client.weeat.util.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

import static com.client.weeat.R.id.tv_more_active_school;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    //定义类型==广告
    public static final int EXTEND = 0;
    //定义类型==活动
    public static final int ACTIVE_NEAR = 1;

    //定义类型==活动
    public static final int ACTIVE_SCHOOL = 2;
    //定义类型==活动
    public static final int ACTIVE_LOVE = 3;
    //定义类型==活动
    public static final int ACTIVE_FRIEND = 4;
    /**
     * 用来初始化布局
     */
    private final Context mcontext;
    private LayoutInflater mLayoutInflater;
    private final ResultBeanDate.ResultBean resultBean;
    //当前类型
    private int currentType = EXTEND;
    private static final String ACTIVES_BEAN="activesBean";

    public HomeFragmentAdapter(Context mcontext, ResultBeanDate.ResultBean resultBean) {
        this.mcontext = mcontext;
        this.resultBean=resultBean;
        mLayoutInflater=LayoutInflater.from(mcontext);
    }

    /**相当于getview
     * 创建viewhold
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==EXTEND){
            return new BannerViewHolder(mcontext,mLayoutInflater.inflate(R.layout.banner_viewpager,
                    null));
        } else if(viewType==ACTIVE_NEAR){
            return new ActiveViewHolder(mcontext,mLayoutInflater.inflate(R.layout.active_item,
                    null));
        }
        else if(viewType==ACTIVE_SCHOOL){
            return new SchoolViewHolder(mcontext,mLayoutInflater.inflate(R.layout.active_item_school,
                    null));
        }
        else if(viewType==ACTIVE_LOVE){
            return new LoveViewHolder(mcontext,mLayoutInflater.inflate(R.layout.active_item_love,
                    null));
        }
        else if(viewType==ACTIVE_FRIEND){
            return new FriendViewHolder(mcontext,mLayoutInflater.inflate(R.layout.active_item_friend,
                    null));
        }
        return null;
    }

    /**
     * 相当于getview中的绑定数据模块
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if (getItemViewType(position) == EXTEND){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            // 设置数据 Banner 的数据
            bannerViewHolder.setData(resultBean.getExtend_info());
        } else if (getItemViewType(position) == ACTIVE_NEAR){
           ActiveViewHolder activeViewHolder = (ActiveViewHolder) holder;
            // 设置数据 active 的数据
           activeViewHolder.setData(resultBean.getActive_info());
        }
       else if (getItemViewType(position) == ACTIVE_SCHOOL){
           SchoolViewHolder schoolViewHolder = (SchoolViewHolder) holder;
           // 设置数据 active 的数据
           schoolViewHolder.setData(resultBean.getActive_school());
       }
       else if (getItemViewType(position) == ACTIVE_LOVE){
           LoveViewHolder loveViewHolder = (LoveViewHolder) holder;
           // 设置数据 active 的数据
           loveViewHolder.setData(resultBean.getActive_love());
       }
       else if (getItemViewType(position) == ACTIVE_FRIEND){
           FriendViewHolder friendViewHolder = (FriendViewHolder) holder;
           // 设置数据 active 的数据
           friendViewHolder.setData(resultBean.getActive_friend());
       }
    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case EXTEND:
                currentType = EXTEND;
                break;
            case ACTIVE_NEAR:
                currentType = ACTIVE_NEAR;
                break;
            case ACTIVE_SCHOOL:
                currentType = ACTIVE_SCHOOL;
                break;
            case ACTIVE_LOVE:
                currentType = ACTIVE_LOVE;
                break;
            case ACTIVE_FRIEND:
                currentType = ACTIVE_FRIEND;
                break;
        }
        return currentType;
    }

    /**
     * 当前编辑的模块数
     * @return
     */
    @Override
    public int getItemCount() {
        return 5;
    }
}
/**
 * 以下为活动展示的实现类
 */
class ActiveViewHolder extends  RecyclerView.ViewHolder{
    private Context mcontext;
    private TextView tv_more_active;
    private GridView gv_active;
    private ActiveGridViewAdapter adapter;
    private static final String ACTIVES_BEAN="activesBean";
    private static final String ACTIVES_LIST="list";


    /**
     * 视图
     * @param mcontext
     * @param itemView
     */
    public ActiveViewHolder(Context mcontext,View itemView) {
        super(itemView);
        this.mcontext = mcontext;
        tv_more_active = (TextView) itemView.findViewById(R.id.tv_more_active);
        gv_active = (GridView) itemView.findViewById(R.id.gv_active);

    }

    /**
     * 数据
     * @param active_info
     */
    public void setData(final List<ResultBeanDate.ResultBean.ActiveInfoBean> active_info) {
        adapter = new ActiveGridViewAdapter(mcontext,active_info);
        gv_active.setAdapter(adapter);


        //设置item的监听,活动下的gv
        gv_active.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mcontext, "position=="+position, Toast.LENGTH_SHORT).show();
                //活动信息类
                ResultBeanDate.ResultBean.ActiveInfoBean activeInfoBean =  active_info.get(position);

                ActivesBean activesBean = new ActivesBean();
                activesBean.setActive_name(activeInfoBean.getActive_name());
                activesBean.setActive_place(activeInfoBean.getActive_place());
                activesBean.setActive_depict(activeInfoBean.getActive_depict());
                activesBean.setActive_id(activeInfoBean.getActive_id()+"");
                activesBean.setCreate_time(activeInfoBean.getCreate_time());
                activesBean.setActive_theme(activeInfoBean.getActive_theme());
                activesBean.setActshow_usericon(activeInfoBean.getUser().getUserIcon());
                activesBean.setActshow_username(activeInfoBean.getUser().getUserName());
                activesBean.setActshow_userid(activeInfoBean.getUser().getUserID());
                startActivesInfoActivity(activesBean);
            }
        });
        //另一活动下的lv
        tv_more_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里不用序列化，用json传
                JSONArray array = new JSONArray();
                for(int i =0;i< active_info.size();i++){
                    ResultBeanDate.ResultBean.ActiveInfoBean activeInfoBean =  active_info.get(i);
                    JSONObject json = new JSONObject();
                    json.put("name",activeInfoBean.getActive_name());
                    json.put("place",activeInfoBean.getActive_place());
                    json.put("dep",activeInfoBean.getActive_depict());
                    json.put("id",activeInfoBean.getActive_id());
                    json.put("time",activeInfoBean.getCreate_time());
                    json.put("theme",activeInfoBean.getActive_theme());
                    json.put("acticon",activeInfoBean.getActive_depict());
                    json.put("usericon",activeInfoBean.getUser().getUserIcon());
                    json.put("username",activeInfoBean.getUser().getUserName());
                    json.put("userid",activeInfoBean.getUser().getUserID());
                    json.put("num",activeInfoBean.getMaxnum());
                    array.add(json);

                }
                Intent intent = new Intent(mcontext, ListActiveActivity.class);
                intent.putExtra(ACTIVES_LIST, array.toJSONString());
                intent.putExtra("TYPE", 1);    //适配器选择
                Log.e("JSON",JSONObject.toJSON(active_info).toString());
                mcontext.startActivity(intent);
            }
        });
    }



        /**
     * 启动活动信息列表页面
     * @param activesBean
     */

    private void startActivesInfoActivity(ActivesBean activesBean) {
        Intent intent = new Intent(mcontext, ActiveShowActivity.class);
        intent.putExtra(ACTIVES_BEAN, activesBean);
        mcontext.startActivity(intent);
    }

}

/**
 * 学校为条件
 */
class SchoolViewHolder extends  RecyclerView.ViewHolder {
    private Context mcontext;
    private TextView tv_more_active;
    private GridView gv_active;
    private ActiveGridViewSchoolAdapter adapter;
    private static final String ACTIVES_BEAN = "activesBean";
    private static final String ACTIVES_LIST="list";


    /**
     * 视图
     *
     * @param mcontext
     * @param itemView
     */
    public SchoolViewHolder(Context mcontext, View itemView) {
        super(itemView);
        this.mcontext = mcontext;
        tv_more_active = (TextView) itemView.findViewById(tv_more_active_school);
        gv_active = (GridView) itemView.findViewById(R.id.gv_active_school);

    }

    /**
     * 数据
     *
     * @param active_info
     */
    public void setData(final List<ResultBeanDate.ResultBean.ActiveSchoolBean> active_info) {
        adapter = new ActiveGridViewSchoolAdapter(mcontext, active_info);
        gv_active.setAdapter(adapter);


        //设置item的监听
        gv_active.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mcontext, "position==" + position, Toast.LENGTH_SHORT).show();
                //活动信息类
                ResultBeanDate.ResultBean.ActiveSchoolBean activeInfoBean = active_info.get(position);

                ActivesBean activesBean = new ActivesBean();
                activesBean.setActive_name(activeInfoBean.getActive_name());
                activesBean.setActive_place(activeInfoBean.getActive_place());
                activesBean.setActive_depict(activeInfoBean.getActive_depict());
                activesBean.setActive_id(activeInfoBean.getActive_id()+"");
                activesBean.setCreate_time(activeInfoBean.getCreate_time());
                activesBean.setActive_theme(activeInfoBean.getActive_theme());
                activesBean.setActshow_usericon(activeInfoBean.getUser().getUserIcon());
                activesBean.setActshow_username(activeInfoBean.getUser().getUserName());
                activesBean.setActshow_userid(activeInfoBean.getUser().getUserID());
                startActivesInfoActivity(activesBean);
            }
        });

        //另一活动下的lv
        tv_more_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里不用序列化，用json传
                JSONArray array = new JSONArray();
                for(int i =0;i< active_info.size();i++){
                    ResultBeanDate.ResultBean.ActiveSchoolBean activeInfoBean =  active_info.get(i);
                    JSONObject json = new JSONObject();
                    json.put("name",activeInfoBean.getActive_name());
                    json.put("place",activeInfoBean.getActive_place());
                    json.put("dep",activeInfoBean.getActive_depict());
                    json.put("id",activeInfoBean.getActive_id());
                    json.put("time",activeInfoBean.getCreate_time());
                    json.put("theme",activeInfoBean.getActive_theme());
                    json.put("acticon",activeInfoBean.getActive_depict());
                    json.put("usericon",activeInfoBean.getUser().getUserIcon());
                    json.put("username",activeInfoBean.getUser().getUserName());
                    json.put("userid",activeInfoBean.getUser().getUserID());
                    json.put("num",activeInfoBean.getMaxnum());
                    array.add(json);

                }
                Intent intent = new Intent(mcontext, ListActiveActivity.class);
                intent.putExtra(ACTIVES_LIST, array.toJSONString());
                intent.putExtra("TYPE", 1);    //适配器选择
                Log.e("JSON",JSONObject.toJSON(active_info).toString());
                mcontext.startActivity(intent);
            }
        });
    }
    /**
     * 启动活动信息列表页面
     * @param activesBean
     */

    private void startActivesInfoActivity(ActivesBean activesBean) {
        Intent intent = new Intent(mcontext, ActiveShowActivity.class);
        intent.putExtra(ACTIVES_BEAN, activesBean);
        mcontext.startActivity(intent);
    }
}

/**
 * 好友为条件
 */
class FriendViewHolder extends  RecyclerView.ViewHolder {
    private Context mcontext;
    private TextView tv_more_active;
    private GridView gv_active;
    private ActiveGridViewFriendAdapter adapter;
    private static final String ACTIVES_BEAN = "activesBean";
    private static final String ACTIVES_LIST="list";


    /**
     * 视图
     *
     * @param mcontext
     * @param itemView
     */
    public FriendViewHolder(Context mcontext, View itemView) {
        super(itemView);
        this.mcontext = mcontext;
        tv_more_active = (TextView) itemView.findViewById(R.id.tv_more_active_friend);
        gv_active = (GridView) itemView.findViewById(R.id.gv_active_friend);

    }

    /**
     * 数据
     *
     * @param active_info
     */
    public void setData(final List<ResultBeanDate.ResultBean.ActiveFriendBean> active_info) {
        adapter = new ActiveGridViewFriendAdapter(mcontext, active_info);
        gv_active.setAdapter(adapter);


        //设置item的监听
        gv_active.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mcontext, "position==" + position, Toast.LENGTH_SHORT).show();
                //活动信息类
                ResultBeanDate.ResultBean.ActiveFriendBean activeInfoBean = active_info.get(position);

                ActivesBean activesBean = new ActivesBean();
                activesBean.setActive_name(activeInfoBean.getActive_name());
                activesBean.setActive_place(activeInfoBean.getActive_place());
                activesBean.setActive_depict(activeInfoBean.getActive_depict());
                activesBean.setActive_id(activeInfoBean.getActive_id()+"");
                activesBean.setCreate_time(activeInfoBean.getCreate_time());
                activesBean.setActive_theme(activeInfoBean.getActive_theme());
                activesBean.setActshow_usericon(activeInfoBean.getUser().getUserIcon());
                activesBean.setActshow_username(activeInfoBean.getUser().getUserName());
                activesBean.setActshow_userid(activeInfoBean.getUser().getUserID());
                startActivesInfoActivity(activesBean);
            }
        });

        tv_more_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里不用序列化，用json传
                JSONArray array = new JSONArray();
                for(int i =0;i< active_info.size();i++){
                    ResultBeanDate.ResultBean.ActiveFriendBean activeInfoBean =  active_info.get(i);
                    JSONObject json = new JSONObject();
                    json.put("name",activeInfoBean.getActive_name());
                    json.put("place",activeInfoBean.getActive_place());
                    json.put("dep",activeInfoBean.getActive_depict());
                    json.put("id",activeInfoBean.getActive_id());
                    json.put("time",activeInfoBean.getCreate_time());
                    json.put("theme",activeInfoBean.getActive_theme());
                    json.put("acticon",activeInfoBean.getActive_depict());
                    json.put("usericon",activeInfoBean.getUser().getUserIcon());
                    json.put("username",activeInfoBean.getUser().getUserName());
                    json.put("userid",activeInfoBean.getUser().getUserID());
                    json.put("num",activeInfoBean.getMaxnum());
                    array.add(json);

                }
                Intent intent = new Intent(mcontext, ListActiveActivity.class);
                intent.putExtra(ACTIVES_LIST, array.toJSONString());
                intent.putExtra("TYPE", 1);    //适配器选择
                Log.e("JSON",JSONObject.toJSON(active_info).toString());
                mcontext.startActivity(intent);
            }
        });
    }
    /**
     * 启动活动信息列表页面
     * @param activesBean
     */

    private void startActivesInfoActivity(ActivesBean activesBean) {
        Intent intent = new Intent(mcontext, ActiveShowActivity.class);
        intent.putExtra(ACTIVES_BEAN, activesBean);
        mcontext.startActivity(intent);
    }
}

/**
 * 爱好为条件
 */
class LoveViewHolder extends  RecyclerView.ViewHolder {
    private Context mcontext;
    private TextView tv_more_active;
    private GridView gv_active;
    private ActiveGridViewLoveAdapter adapter;
    private static final String ACTIVES_BEAN = "activesBean";
    private static final String ACTIVES_LIST="list";


    /**
     * 视图
     *
     * @param mcontext
     * @param itemView
     */
    public LoveViewHolder(Context mcontext, View itemView) {
        super(itemView);
        this.mcontext = mcontext;
        tv_more_active = (TextView) itemView.findViewById(R.id.tv_more_active_love);
        gv_active = (GridView) itemView.findViewById(R.id.gv_active_love);

    }

    /**
     * 数据
     *
     * @param active_info
     */
    public void setData(final List<ResultBeanDate.ResultBean.ActiveLoveBean> active_info) {
        adapter = new ActiveGridViewLoveAdapter(mcontext, active_info);
        gv_active.setAdapter(adapter);


        //设置item的监听
        gv_active.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mcontext, "position==" + position, Toast.LENGTH_SHORT).show();
                //活动信息类
                ResultBeanDate.ResultBean.ActiveLoveBean activeInfoBean = active_info.get(position);

                ActivesBean activesBean = new ActivesBean();
                activesBean.setActive_name(activeInfoBean.getActive_name());
                activesBean.setActive_place(activeInfoBean.getActive_place());
                activesBean.setActive_depict(activeInfoBean.getActive_depict());
                activesBean.setActive_id(activeInfoBean.getActive_id()+"");
                activesBean.setCreate_time(activeInfoBean.getCreate_time());
                activesBean.setActive_theme(activeInfoBean.getActive_theme());
                activesBean.setActshow_usericon(activeInfoBean.getUser().getUserIcon());
                activesBean.setActshow_username(activeInfoBean.getUser().getUserName());
                activesBean.setActshow_userid(activeInfoBean.getUser().getUserID());
                startActivesInfoActivity(activesBean);
            }
        });
        tv_more_active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里不用序列化，用json传
                JSONArray array = new JSONArray();
                for(int i =0;i< active_info.size();i++){
                    ResultBeanDate.ResultBean.ActiveLoveBean activeInfoBean =  active_info.get(i);
                    JSONObject json = new JSONObject();
                    json.put("name",activeInfoBean.getActive_name());
                    json.put("place",activeInfoBean.getActive_place());
                    json.put("dep",activeInfoBean.getActive_depict());
                    json.put("id",activeInfoBean.getActive_id());
                    json.put("time",activeInfoBean.getCreate_time());
                    json.put("theme",activeInfoBean.getActive_theme());
                    json.put("acticon",activeInfoBean.getActive_depict());
                    json.put("usericon",activeInfoBean.getUser().getUserIcon());
                    json.put("username",activeInfoBean.getUser().getUserName());
                    json.put("userid",activeInfoBean.getUser().getUserID());
                    json.put("num",activeInfoBean.getMaxnum());
                    array.add(json);

                }
                Intent intent = new Intent(mcontext, ListActiveActivity.class);
                intent.putExtra(ACTIVES_LIST, array.toJSONString());
                intent.putExtra("TYPE", 1);    //适配器选择
                Log.e("JSON",JSONObject.toJSON(active_info).toString());
                mcontext.startActivity(intent);
            }
        });
    }
    /**
     * 启动活动信息列表页面
     * @param activesBean
     */

    private void startActivesInfoActivity(ActivesBean activesBean) {
        Intent intent = new Intent(mcontext, ActiveShowActivity.class);
        intent.putExtra(ACTIVES_BEAN, activesBean);
        mcontext.startActivity(intent);
    }
}
/**
 * 以下为横屏广播的实现类
 */
class BannerViewHolder extends RecyclerView.ViewHolder {
    private Banner banner;
    private Context mcontext;


    public BannerViewHolder(Context mcontext, View itemView) {
        super(itemView);
        this.mcontext = mcontext;
        this.banner = (Banner) itemView.findViewById(R.id.banner);

    }


    public void setData(List<ResultBeanDate.ResultBean.ExtendInfoBean> extend_info) {
        List<String> imagesUrl = new ArrayList<>();
        //得到图片地址集合
        for(int i=0;i<extend_info.size();i++){
            String imageUrl = extend_info.get(i).getIcon_url();
            Log.e("URL"+i,imageUrl);
            imagesUrl.add(imageUrl);
        }
        //设置循环指示点
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置切换动画效果==手风琴
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setImages(imagesUrl, new OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
              //联网请求图片，使用glid jar包
                Log.e("URL:",Constants.BASIC_URL_IMG+url);
               // Glide.get(mcontext).clearMemory();   //缓存清理
                Glide.with(mcontext).load(Constants.BASIC_URL_IMG+url)
                     //   .skipMemoryCache(true)   //跳过缓存
                        .diskCacheStrategy(DiskCacheStrategy.ALL)  //缓存全尺寸
                        .into(view);
            }
        });
        //设置Item的点击事件
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mcontext, "position:"+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, LoadWebActivity.class);
                intent.putExtra("webviewID", position);  //传到html5显示层
                mcontext.startActivity(intent);
            }
        });
    }
}