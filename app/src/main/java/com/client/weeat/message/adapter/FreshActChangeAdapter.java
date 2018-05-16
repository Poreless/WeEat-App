package com.client.weeat.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.weeat.R;
import com.client.weeat.message.bean.FreshActBean;

import java.util.List;

/**
 * @author Admin
 * 活动管理的适配器
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FreshActChangeAdapter extends BaseAdapter {
    private final Context mcontext;
    private final List<FreshActBean.ResultBean.FreshListBean> datas;
    public FreshActChangeAdapter(Context mcontext, List<FreshActBean.ResultBean.FreshListBean> freshactinfo) {
        this.mcontext = mcontext;
        this.datas = freshactinfo;
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
            convertView = View.inflate(mcontext, R.layout.actchange_item,null);
            viewHolder = new ViewHolder();
            viewHolder.actchange_name= (TextView) convertView.findViewById(R.id.actchange_name);
            viewHolder.actchange_time= (TextView) convertView.findViewById(R.id.actchange_time);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        FreshActBean.ResultBean.FreshListBean freshactInfo = datas.get(position);
        viewHolder.actchange_name.setText("#"+freshactInfo.getActive_name()+"#");
        viewHolder.actchange_time.setText(freshactInfo.getCreate_time());
        return convertView;
    }

    static class ViewHolder {
        TextView actchange_name;
        TextView actchange_time;
    }
}
