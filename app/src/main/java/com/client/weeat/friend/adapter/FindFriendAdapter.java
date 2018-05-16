package com.client.weeat.friend.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.friend.bean.FindFriendBean;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FindFriendAdapter extends BaseAdapter {
    private final Context mcontext;
    private final List<FindFriendBean.ResultBean.UserInfoBean> datas;
    public FindFriendAdapter(Context mcontext, List<FindFriendBean.ResultBean.UserInfoBean> friendinfo) {
        this.mcontext = mcontext;
        this.datas = friendinfo;
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
            convertView = View.inflate(mcontext, R.layout.friend_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_friend = (ImageView) convertView.findViewById(R.id.frienduri);
            viewHolder.tv_friendname = (TextView) convertView.findViewById(R.id.friendname);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        FindFriendBean.ResultBean.UserInfoBean friendInfoBean = datas.get(position);
        Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+friendInfoBean.getUserIcon()).into(viewHolder.iv_friend);
        viewHolder.tv_friendname.setText(friendInfoBean.getUserName());
        return convertView;
    }
    static class ViewHolder {
        ImageView iv_friend;
        TextView tv_friendname;
    }
}
