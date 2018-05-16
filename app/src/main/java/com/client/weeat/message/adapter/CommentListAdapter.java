package com.client.weeat.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.message.bean.FinishActBean;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class CommentListAdapter extends BaseAdapter {
    private final Context mcontext;
    private final List<FinishActBean.ResultBean.ActiveFinishInfoBean> datas;
    public CommentListAdapter(Context mcontext, List<FinishActBean.ResultBean.ActiveFinishInfoBean> finishinfo) {
        this.mcontext = mcontext;
        this.datas = finishinfo;
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
            convertView = View.inflate(mcontext, R.layout.act_finish_item,null);
            viewHolder = new ViewHolder();
            viewHolder.comment_usericon= (ImageView) convertView.findViewById(R.id.comment_user_uri);
            viewHolder.comment_username= (TextView) convertView.findViewById(R.id.comment_user_name);
            viewHolder.comment_decpl= (TextView) convertView.findViewById(R.id.comment_act_name);
            viewHolder.comment_finishtime= (TextView) convertView.findViewById(R.id.comment_act_finishtime);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        FinishActBean.ResultBean.ActiveFinishInfoBean finishactInfo = datas.get(position);
        viewHolder.comment_username.setText("创办人:"+finishactInfo.getUser().getUserName());
        viewHolder.comment_finishtime.setText("结束时间:"+finishactInfo.getFinish_time());
        viewHolder.comment_decpl.setText("【"+finishactInfo.getActive_name()+"】");
        try {
            Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+finishactInfo.getUser().getUserIcon())
                    .into(viewHolder.comment_usericon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
    static class ViewHolder {
        ImageView comment_usericon;
        TextView comment_username;
        TextView comment_decpl;
        TextView comment_finishtime;
        TextView comment_tip;  //额外参数
    }
}
