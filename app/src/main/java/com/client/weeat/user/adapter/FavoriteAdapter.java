package com.client.weeat.user.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.weeat.R;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FavoriteAdapter extends BaseAdapter {

    private final Context mcontext;
    private final List<String> datas;
    public FavoriteAdapter(Context mcontext, List<String> favoriteinfo) {
        this.mcontext = mcontext;
        this.datas = favoriteinfo;
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
            convertView = View.inflate(mcontext, R.layout.item_favorite,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_favorite = (TextView) convertView.findViewById(R.id.tv_favorite);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        String favorite  = datas.get(position);
        viewHolder.tv_favorite.setText(favorite);
        return convertView;
    }
    static class ViewHolder {
        TextView tv_favorite;
    }
}
