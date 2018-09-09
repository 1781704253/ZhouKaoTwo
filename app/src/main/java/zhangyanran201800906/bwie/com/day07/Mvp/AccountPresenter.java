package zhangyanran201800906.bwie.com.day07.Mvp;

/**
 * Created by 匹诺曹 on 2018/9/6.
 */

public class AccountPresenter {
    //view层的引用
    private AccountView view;
    //model层的引用
    private AccountModel model;
    //view层的有参---model需要手动修改
    public AccountPresenter(AccountView view) {
        this.view = view;
        model = new AccountModel();
    }

    public void reg(String name, String password) {
        //调用Model层注册
        model.reg(name, password, new AccountModel.AccountCallack() {
            @Override
            public void onSuccess(String msg) {
                view.showSuccess(msg);
            }

            @Override
            public void onError(String errorMsg) {
                view.showError("注册失败");
            }
        });
    }

    public void login(String name, String password) {
        model.login(name, password, new AccountModel.AccountCallack() {
            @Override
            public void onSuccess(String msg) {
                view.login_success(msg);
            }

            @Override
            public void onError(String errorMsg) {
                view.showError("登录失败");
            }
        });
    }
}
