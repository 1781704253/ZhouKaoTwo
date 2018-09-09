package zhangyanran201800906.bwie.com.day07.utils;

import android.os.AsyncTask;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 匹诺曹 on 2018/9/8.
 */

public class NetUtils {
    private static final NetUtils ourInstance = new NetUtils();

    public static NetUtils getInstance() {
        return ourInstance;
    }

    private NetUtils() {
    }
    //获取他的网络地址
    public void getDataFromService(String url) {
        new LoadData().execute(url);
    }
    public interface NetCallback {
        void onSuccess(String result);
    }

    private NetCallback netCallback;

    public void setNetCallback(NetCallback netCallback) {
        this.netCallback = netCallback;
    }

    //请求网络数据的类
    class LoadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            // 网络请求
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return CharStreams.toString(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (netCallback != null) {
                netCallback.onSuccess(s);
            }
        }
    }
}
