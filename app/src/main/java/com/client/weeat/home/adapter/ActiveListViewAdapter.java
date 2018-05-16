package com.client.weeat.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.home.bean.ActListBean;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActiveListViewAdapter extends BaseAdapter{
    private final Context mcontext;
    private final List<ActListBean> datas;

    public ActiveListViewAdapter(Context mcontext, List<ActListBean> active_info) {
        this.mcontext = mcontext;
        this.datas= active_info;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mcontext, R.layout.item_active_list_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_user = (ImageView) convertView.findViewById(R.id.lv_iv_user);
            viewHolder.tv_username = (TextView) convertView.findViewById(R.id.lv_tv_username);
            viewHolder.tv_actplace = (TextView) convertView.findViewById(R.id.lv_tv_actplace);
            viewHolder.tv_actnum = (TextView) convertView.findViewById(R.id.lv_tv_actnum);
            viewHolder.tv_actname = (TextView) convertView.findViewById(R.id.lv_tv_actname);
            viewHolder.tv_startime = (TextView) convertView.findViewById(R.id.lv_tv_startime);
            viewHolder.tv_acttheme = (TextView) convertView.findViewById(R.id.lv_tv_acttheme);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ActListBean activeInfoBean = datas.get(position);
        //根据位置得到对应的数据
        try {
            viewHolder.tv_username.setText(activeInfoBean.getUser().getUserName());
            Log.e("UserName",activeInfoBean.getUser().getUserName());

            if(activeInfoBean.getActive_place()!=null) {
                viewHolder.tv_actplace.setText(activeInfoBean.getActive_place());
                Log.e("Active_place", activeInfoBean.getActive_place());
            }
            viewHolder.tv_actname.setText("#"+activeInfoBean.getActive_name()+"#");//活动简述
            Log.e("Active_name",activeInfoBean.getActive_name());
            viewHolder.tv_actnum.setText("人数 "+activeInfoBean.getMaxnum()+" 人");
            viewHolder.tv_acttheme.setText(activeInfoBean.getActive_theme()+"|");
            Log.e("Active_num",activeInfoBean.getMaxnum()+"");
            if(activeInfoBean.getUser().getUserIcon()!=null) {
                Glide.with(mcontext).load(Constants.BASIC_URL_USERICON + activeInfoBean.getUser().getUserIcon())
                        //.placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)  //设置等待与错误
                        .error(R.drawable.icon_callserver_unpressed).into(viewHolder.iv_user);
            }

            viewHolder.tv_startime.setText(activeInfoBean.getCreate_time());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
    static class ViewHolder {
        ImageView iv_user;
        TextView tv_username;
        TextView tv_actplace;
        TextView tv_actname;
        TextView tv_actnum;
        TextView tv_startime;
        TextView tv_acttheme;
    }
}
