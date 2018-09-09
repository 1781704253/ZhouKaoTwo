package zhangyanran201800906.bwie.com.day07.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zhangyanran201800906.bwie.com.day07.Bean.DataUser;
import zhangyanran201800906.bwie.com.day07.R;

/**
 * Created by 匹诺曹 on 2018/9/8.
 */

public class XListViewAdapter extends BaseAdapter {
    Context context;
    List<DataUser.DataBean> dataBeanList;

    public XListViewAdapter(Context context, List<DataUser.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        String[] split = dataBeanList.get(position).getImages().split("\\|");
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.xlistview_layout,null,false);
            holder.brief = convertView.findViewById(R.id.x_text);
            holder.lbImage = convertView.findViewById(R.id.x_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //赋值
        holder.brief.setText(dataBeanList.get(position).getTitle());
        Picasso.with(context).load(split[0]).into(holder.lbImage);
        return convertView;
    }
    class ViewHolder {
        TextView brief;
        ImageView lbImage;
    }
}
