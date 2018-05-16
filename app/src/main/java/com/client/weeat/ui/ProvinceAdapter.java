package com.client.weeat.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.weeat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonycheng on 2015/9/19.
 */
public class ProvinceAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList = new ArrayList<>();

    public ProvinceAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<String> list){
        this.mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_province_list, null, false);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.province_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        String provinceList = mList.get(position);
        if (provinceList!= null){
            holder.nameView.setText(provinceList);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView nameView;
    }
}
