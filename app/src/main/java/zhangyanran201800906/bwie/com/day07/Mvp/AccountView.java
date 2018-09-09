package zhangyanran201800906.bwie.com.day07.Mvp;

/**
 * Created by 匹诺曹 on 2018/9/6.
 */

public interface AccountView {
    void showSuccess(String msg);
    void showError(String msg);
    void login_success(String msg);
}
