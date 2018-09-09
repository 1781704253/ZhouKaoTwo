package zhangyanran201800906.bwie.com.day07.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;
import zhangyanran201800906.bwie.com.day07.Bean.DataUser;
import zhangyanran201800906.bwie.com.day07.R;
import zhangyanran201800906.bwie.com.day07.adapter.XListViewAdapter;
import zhangyanran201800906.bwie.com.day07.utils.NetUtils;

public class ShowActivity extends AppCompatActivity {

    private ImageView imag_one;
    private XListView x_list_view;
    private String api = "https://www.zhaoapi.cn/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=";
    private int page=1;
    private NetUtils netUtils;
    private ArrayList<DataUser.DataBean> dataBeanList = new ArrayList<>();
    private XListViewAdapter xListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        imag_one = findViewById(R.id.image_one);
        x_list_view = findViewById(R.id.x_list_view);
        imag_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActivity.this, LiuShi.class);
                startActivity(intent);
            }
        });
        //开启刷新
        x_list_view.setPullLoadEnable(true);
        x_list_view.setPullRefreshEnable(true);
        //设置监听回调
        x_list_view.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //上拉刷新
                page=1;
                netUtils.getDataFromService(api+page);
            }

            @Override
            public void onLoadMore() {
                //下拉加载
                page+=1;
                netUtils.getDataFromService(api+page);
            }
        });
//设置adapter展示数据
        xListViewAdapter = new XListViewAdapter(ShowActivity.this, dataBeanList);
        x_list_view.setAdapter(xListViewAdapter);

//调用工具类
        netUtils = NetUtils.getInstance();
        netUtils.setNetCallback(new NetUtils.NetCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                DataUser dataUser = gson.fromJson(result, DataUser.class);
                if (page==1){
                    //将其他页面数据清除
                    dataBeanList.clear();
                }
                //累积把加载的数据放到集合中
                dataBeanList.addAll(dataUser.getData());
                //刷新适配器
                xListViewAdapter.notifyDataSetChanged();
                //停止刷新和加载
                x_list_view.stopRefresh();
                x_list_view.stopLoadMore();
            }
        });
        netUtils.getDataFromService(api+page);
    }
}

