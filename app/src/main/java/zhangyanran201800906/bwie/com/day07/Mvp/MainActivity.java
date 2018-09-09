package zhangyanran201800906.bwie.com.day07.Mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import zhangyanran201800906.bwie.com.day07.R;
import zhangyanran201800906.bwie.com.day07.Activity.ShowActivity;

public class MainActivity extends AppCompatActivity implements AccountView {
    /*注解初始化函数*/
    @ViewInject(R.id.edit_name)
    private EditText edit_name;
    @ViewInject(R.id.edit_pasword)
    private EditText edit_pasword;
    private AccountPresenter presenter;
    private String mName,mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.创建AccountPresenter（P层）2.并且连接接口生成接口中的方法
        presenter = new AccountPresenter(this);
        x.view().inject(this);
    }
    /*初始化点击事件*/
    @Event({R.id.but_reg,R.id.but_login})
    private void accClick(View view){
        switch (view.getId()){
            case R.id.but_reg:
                //读取用户名和密码
                readNameAndPassword();
                //正则
                if (mName.matches("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$")){
                    if (mPassword.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")){
                        //1.将获取的用户名和密码发送到presenter（P层）2.生成对应的方法
                        presenter.reg(mName,mPassword);
                    }else{
                        Toast.makeText(MainActivity.this,"密码不能少于八位的数字字母的组合",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"请输入正确的手机格式",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.but_login:
                //读取用户名和密码
                readNameAndPassword();
                if (mName.matches("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$")){
                    if (mPassword.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")){
                        //1.将获取的用户名和密码发送到presenter（P层）2.生成对应的方法
                        presenter.login(mName,mPassword);
                    }else{
                        Toast.makeText(MainActivity.this,"密码不能少于八位的数字字母的组合",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"请输入正确的手机格式",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void readNameAndPassword() {
        mName = edit_name.getText().toString();
        mPassword = edit_pasword.getText().toString();
    }
    /*成功以后获取返回值吐司*/
    @Override
    public void showSuccess(final String msg) {
        //需要在线程中执行
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showError(final String msg) {
        //需要在线程中执行
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void login_success(String msg) {
        if (msg.equals("1")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"手机号或者密码错误",Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Intent intent = new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent);
        }
    }
}
