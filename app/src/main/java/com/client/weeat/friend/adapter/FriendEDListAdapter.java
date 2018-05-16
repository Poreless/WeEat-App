package com.client.weeat.friend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.client.weeat.R;
import com.client.weeat.friend.bean.FriendMessageBean;
import com.client.weeat.util.Constants;

import java.util.List;

/**
 * @author Admin //与UserFragment的相似，只是数据源不同
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class FriendEDListAdapter extends BaseExpandableListAdapter{

    private final Context mcontext;
    private final List<FriendMessageBean.ResultBean.ActiveCommentBean> datas;
    public FriendEDListAdapter(Context mcontext, List<FriendMessageBean.ResultBean.ActiveCommentBean> commentinfo) {
        this.mcontext = mcontext;
        this.datas = commentinfo;
    }
    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(groupPosition).getCommentlist().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupPosition);//指定分组数据
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.get(groupPosition).getCommentlist().get(childPosition);
        //某分组的指定子选项
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mcontext, R.layout.list_group_item,null);
            viewHolder = new GroupViewHolder();
            viewHolder.comment_activename= (TextView) convertView.findViewById(R.id.user_comment_title);
            viewHolder.comment_actbegintime= (TextView) convertView.findViewById(R.id.user_comment_begintime);
            viewHolder.comment_actfinishtime= (TextView) convertView.findViewById(R.id.user_comment_finishtime);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        FriendMessageBean.ResultBean.ActiveCommentBean commentInfo = datas.get(groupPosition);
        viewHolder.comment_activename.setText("活动名:"+"【"+commentInfo.getActive_name()+"】");
        viewHolder.comment_actbegintime.setText("时间:"+commentInfo.getCreate_time());
        viewHolder.comment_actfinishtime.setText(commentInfo.getFinish_time());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mcontext, R.layout.list_child_item,null);
            viewHolder = new ChildViewHolder();
            viewHolder.comment_usericon= (ImageView) convertView.findViewById(R.id.user_comment_url);
            viewHolder.comment_username= (TextView) convertView.findViewById(R.id.user_comment_username);
            viewHolder.comment_text= (TextView) convertView.findViewById(R.id.user_comment_text);
            viewHolder.comment_time= (TextView) convertView.findViewById(R.id.user_comment_time);
            viewHolder.comment_num= (TextView) convertView.findViewById(R.id.user_comment_starnum);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        FriendMessageBean.ResultBean.ActiveCommentBean.CommentlistBean commentlistInfo = datas.get(groupPosition).getCommentlist().get(childPosition);
        Log.e("123",commentlistInfo.getUser().getUserName());
        viewHolder.comment_username.setText(commentlistInfo.getUser().getUserName());
        viewHolder.comment_text.setText("评论了你："+commentlistInfo.getText());
        viewHolder.comment_time.setText(commentlistInfo.getDate());
        viewHolder.comment_num.setText("综合评星："+commentlistInfo.getNum()+"星");
        try {
            Glide.with(mcontext).load(Constants.BASIC_URL_USERICON+commentlistInfo.getUser().getUserIcon())
                    .into(viewHolder.comment_usericon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    static class GroupViewHolder {
        TextView comment_activename;
        TextView comment_actbegintime;
        TextView comment_actfinishtime;
    }
    static class ChildViewHolder {
        ImageView comment_usericon;
        TextView comment_username;
        TextView comment_text;
        TextView comment_time;
        TextView comment_num;  //评星
    }

}
