package com.client.weeat.friend.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.friend.bean.FriendRequestBean;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FriendRequestListAdapter extends BaseAdapter {
    private final Context mcontext;
    private final List<FriendRequestBean.ResultBean> datas;
    public FriendRequestListAdapter(Context mcontext, List<FriendRequestBean.ResultBean> friendrequestinfo) {
        this.mcontext = mcontext;
        this.datas = friendrequestinfo;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        String tip = null;
        if(convertView == null){
            convertView = View.inflate(mcontext, R.layout.friend_request_item,null);
            viewHolder = new ViewHolder();
            viewHolder.req_usericon = (ImageView) convertView.findViewById(R.id.req_friend_uri);
            viewHolder.req_username = (TextView) convertView.findViewById(R.id.req_friend_name);
            viewHolder.reqtime = (TextView) convertView.findViewById(R.id.req_friend_time);
            viewHolder.reqtip = (TextView) convertView.findViewById(R.id.req_friend_tip);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        FriendRequestBean.ResultBean requestInfoBean = datas.get(position);
        Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+requestInfoBean.getIcon()).into(viewHolder.req_usericon);
        viewHolder.req_username.setText(requestInfoBean.getName());
        viewHolder.reqtime.setText("申请时间："+requestInfoBean.getTime());
        switch (requestInfoBean.getNum()){
            case 1:
                tip = "已同意";
                break;
            case 2:
                tip = "已拒绝";
                break;
            case 3:
                tip = "已忽略";
                break;

        }
        viewHolder.reqtip.setText(tip);
        return convertView;
    }
    static class ViewHolder {
        ImageView req_usericon;
        TextView req_username;
        TextView reqtime;
        TextView reqtip;
    }
}
