package com.client.weeat.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.home.bean.IconGridBean;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class IconGridAdapter extends BaseAdapter {
    private final Context mcontext;
    private final List<IconGridBean.ResultBean> datas;

    public IconGridAdapter(Context mcontext, List<IconGridBean.ResultBean> active_info) {
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
            convertView = View.inflate(mcontext, R.layout.grid_icon_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_usericon = (ImageView) convertView.findViewById(R.id.iv_icon_user);
            viewHolder.tv_username = (TextView) convertView.findViewById(R.id.icon_username);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        IconGridBean.ResultBean userInfoBean = datas.get(position);
        //根据位置得到对应的数据
        try {
            Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+userInfoBean.getIcon()).into(viewHolder.iv_usericon);
            viewHolder.tv_username.setText(userInfoBean.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
    static class ViewHolder {
        ImageView iv_usericon;
        TextView tv_username;
    }
}
