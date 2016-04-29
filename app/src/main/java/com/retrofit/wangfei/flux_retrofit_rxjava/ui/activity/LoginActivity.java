package com.retrofit.wangfei.flux_retrofit_rxjava.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.action.LoginActionCreator;
import com.retrofit.wangfei.flux_retrofit_rxjava.dispatcher.Dispatcher;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.GitHubUser;
import com.retrofit.wangfei.flux_retrofit_rxjava.store.LoginStore;
import com.retrofit.wangfei.flux_retrofit_rxjava.ui.base.BaseActivity;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.DebugLog;
import org.greenrobot.eventbus.Subscribe;
import butterknife.ButterKnife;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Android Studio
 * User: fei.wang
 * Date: 2016-03-23
 * Time: 9:57
 * Email: 929728742@qq.com
 * Description:
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText name, password;
    private TextView button;
    private CircularProgressBar progress_bar;
    private Dispatcher dispatcher;
    private LoginActionCreator loginActionCreator;
    private LoginStore loginStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSystemBarTintDrawable("#24b7a4");
        initDependencies();
        name = (EditText) findViewById(R.id.extractEditText);
        password = (EditText) findViewById(R.id.extractEditText2);
        button = (TextView) findViewById(R.id.button);
        progress_bar = (CircularProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
        DebugLog.i("王飞");
    }

    @NonNull
    private void initDependencies() {
        loginStore = LoginStore.getInstance();
        loginStore.register(this);
        dispatcher = Dispatcher.getInstance();
        dispatcher.register(loginStore);
        loginActionCreator = LoginActionCreator.getInstance(dispatcher);
    }

    @Subscribe
    public void onEventMainThread(LoginStore.LoadingStartChangeEvent changeEvent) {
        if (changeEvent != null) {
            progress_bar.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void onEventMainThread(LoginStore.InitViewChangeEvent changeEvent) {
        progress_bar.setVisibility(View.GONE);
        if (changeEvent != null) {
            GitHubUser userList = loginStore.getUserList();
            Toast.makeText(this, userList.status + " " + userList.msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onEventMainThread(LoginStore.ErrorChangeEvent changeEvent) {
        progress_bar.setVisibility(View.GONE);
        if (changeEvent != null) {
            Throwable throwable = loginStore.getThrowable();
            Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginStore.unRegister(this);
        dispatcher.unRegister(loginStore);
        DebugLog.wtf("上海");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                DebugLog.e("徐汇");
                loginActionCreator.fetechData(name.getText().toString(), password.getText().toString());
                readyGo(MainActivity.class);
                break;
        }
    }
}
