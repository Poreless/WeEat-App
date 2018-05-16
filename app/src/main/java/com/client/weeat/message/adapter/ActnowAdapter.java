package com.client.weeat.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.weeat.R;
import com.client.weeat.message.bean.ActnowBean;
import com.client.weeat.util.ShowTiemTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActnowAdapter extends BaseAdapter{
    private final Context mcontext;
    private final List<ActnowBean.ResultBean> datas;
    private static final long msInDay = 1000*60*60*24;
    private static final long msInHour = 1000*60*60;
    private static final long msInMin = 1000*60;
    public ActnowAdapter(Context mcontext, List<ActnowBean.ResultBean> actinfo) {
        this.mcontext = mcontext;
        this.datas = actinfo;
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
            convertView = View.inflate(mcontext, R.layout.active_now_item,null);
            viewHolder = new ViewHolder();
            viewHolder.message= (ShowTiemTextView) convertView.findViewById(R.id.actnow_message);
            viewHolder.num= (TextView) convertView.findViewById(R.id.actnow_num);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ActnowBean.ResultBean actInfo = datas.get(position);
        Date now = new Date();	//获取现在的日期对象
        Date thedate = null;
        try {
            thedate =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    .parse(actInfo.getAct().getCreate_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int i = now.compareTo(thedate);
        long ms = Math.abs(now.getTime() - thedate.getTime());
        if(i > 0) {
                 /*   Toast.makeText(ActChangeItemActivity.this,"指定日期在现在之前",
                            Toast.LENGTH_SHORT).show();*/
/*            viewHolder.message.setText("活动:#" + actInfo.getAct().getActive_name() + "#已经进行了:" +
                    ms / msInDay + "天" + (ms % msInDay) / msInHour +
                    "小时" + (ms % msInHour) / msInMin + "分钟");
            Log.e("DATE", "相距" + ms / msInDay + "天" + (ms % msInDay) / msInHour +
                    "小时" + (ms % msInHour) / msInMin + "分钟");*/
           viewHolder.message.setTimes(actInfo.getAct().getActive_name(),thedate);

            if(!viewHolder.message.isRun()){
                viewHolder.message.start();
            }
        }else {
/*            viewHolder.message.setText("距离:#"+actInfo.getAct().getActive_name()+"#开始还有:"+
                        ms/msInDay + "天" + (ms%msInDay)/msInHour +
                        "小时"+(ms%msInHour)/msInMin+"分钟");*/
                viewHolder.message.setTimes(actInfo.getAct().getActive_name(),thedate);
                if(!viewHolder.message.isRun()){
                    viewHolder.message.start();
                }
            }

        viewHolder.num.setText("当前人数:"+actInfo.getNum());

        return convertView;
    }
    static class ViewHolder {
        ShowTiemTextView message;
        TextView num;
        TextView comment_tip;  //额外参数
    }
}
