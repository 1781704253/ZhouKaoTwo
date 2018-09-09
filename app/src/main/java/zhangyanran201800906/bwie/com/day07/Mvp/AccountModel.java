package zhangyanran201800906.bwie.com.day07.Mvp;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zhangyanran201800906.bwie.com.day07.Bean.User;

/**
 * Created by 匹诺曹 on 2018/9/6.
 */



public class AccountModel {
    private OkHttpClient client;
    //创建方法供P层调用
    public AccountModel(){
        client = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();
    }

    public void reg(String name, String password, final AccountCallack callack) {
        FormBody formBody = new FormBody
                .Builder()
                .add("mobile",name)
                .add("password",password)
                .build();
        Request builder = new Request
                .Builder()
                .url("https://www.zhaoapi.cn/user/reg")
                .post(formBody)
                .build();
        client.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callack.onSuccess(response.body().string());
            }
        });
    }

    public void login(String name, String password, final AccountCallack callack) {
        FormBody formBody = new FormBody
                .Builder()
                .add("mobile",name)
                .add("password",password)
                .build();
        Request builder = new Request
                .Builder()
                .url("https://www.zhaoapi.cn/user/login")
                .post(formBody)
                .build();
        client.newCall(builder).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                callack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    String result = response.body().string();
                    Gson gson = new Gson();
                    User user = gson.fromJson(result, User.class);
                    String code = user.getCode();
                    callack.onSuccess(code);
                }
            }
        });
    }

    //返回接口
    public interface AccountCallack{
        void onSuccess(String msg);
        void onError(String errorMsg);
        //void login_success(String msg);
    }

}
