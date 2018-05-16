package com.client.weeat.message.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.message.bean.MsgBeanDate;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActaskListAdapter extends BaseAdapter {

    private final Context mcontext;
    private final List<MsgBeanDate.ResultBean.RequestActInfoBean> datas;
    public ActaskListAdapter(Context mcontext, List<MsgBeanDate.ResultBean.RequestActInfoBean> requestinfo) {
        this.mcontext = mcontext;
        this.datas = requestinfo;
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
        String tip = null;
        if(convertView == null){
            convertView = View.inflate(mcontext, R.layout.request_item,null);
            viewHolder = new ViewHolder();
            viewHolder.req_usericon = (ImageView) convertView.findViewById(R.id.req_user_uri);
            viewHolder.req_username = (TextView) convertView.findViewById(R.id.req_user_name);
            viewHolder.reqmsg = (TextView) convertView.findViewById(R.id.req_act_name);
            viewHolder.reqtip = (TextView) convertView.findViewById(R.id.req_act_tip);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        MsgBeanDate.ResultBean.RequestActInfoBean requestInfoBean = datas.get(position);
        Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+requestInfoBean.getUser().getUserIcon()).into(viewHolder.req_usericon);
        Log.e("TAG",Constants.BASIC_URL_USERICON+requestInfoBean.getUser().getUserIcon());
        viewHolder.req_username.setText(requestInfoBean.getUser().getUserName());
        viewHolder.reqmsg.setText("请求加入  #"+requestInfoBean.getActive_name()+"#");
        switch (requestInfoBean.getNum()){
            case "1":
                tip = "已同意";
                break;
            case "2":
                tip = "已拒绝";
                break;
            case "3":
                tip = "已忽略";
                break;

        }
        viewHolder.reqtip.setText(tip);
        return convertView;
    }
    static class ViewHolder {
        ImageView req_usericon;
        TextView req_username;
        TextView reqmsg;
        TextView reqtip;
    }
}
