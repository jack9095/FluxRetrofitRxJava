package com.retrofit.wangfei.flux_retrofit_rxjava.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.wangfei.flux_retrofit_rxjava.R;
import com.retrofit.wangfei.flux_retrofit_rxjava.action.LoginActionCreator;
import com.retrofit.wangfei.flux_retrofit_rxjava.dispatcher.Dispatcher;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.GitHubUser;
import com.retrofit.wangfei.flux_retrofit_rxjava.model.User;
import com.retrofit.wangfei.flux_retrofit_rxjava.store.LoginStore;
import com.retrofit.wangfei.flux_retrofit_rxjava.util.MD5Utils;

import org.greenrobot.eventbus.Subscribe;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-23
 * Time: 9:57
 * QQ: 929728742
 * Description:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name,password;
    private TextView button;
    private CircularProgressBar progress_bar;
    private Dispatcher dispatcher;
    private LoginActionCreator loginActionCreator;
    private LoginStore loginStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
        name = (EditText) findViewById(R.id.extractEditText);
        password = (EditText) findViewById(R.id.extractEditText2);
        button = (TextView) findViewById(R.id.button);
        progress_bar = (CircularProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
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
    public void onLoadingStartChangeEvent(LoginStore.LoadingStartChangeEvent changeEvent){
        if (changeEvent != null) {
            progress_bar.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void onInitViewChangeEvent(LoginStore.InitViewChangeEvent changeEvent){
        progress_bar.setVisibility(View.GONE);
        if (changeEvent != null) {
            GitHubUser userList = loginStore.getUserList();
            Toast.makeText(this,userList.status + " " +userList.msg ,Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onErrorChangeEvent(LoginStore.ErrorChangeEvent changeEvent){
        progress_bar.setVisibility(View.GONE);
        if (changeEvent != null) {
            Throwable throwable = loginStore.getThrowable();
            Toast.makeText(this,throwable.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginStore.unRegister(this);
        dispatcher.unRegister(loginStore);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                loginActionCreator.fetechData(name.getText().toString(),password.getText().toString());
                break;
        }
    }
}
