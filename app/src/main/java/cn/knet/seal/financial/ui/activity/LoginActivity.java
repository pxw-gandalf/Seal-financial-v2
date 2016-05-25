/**
 * 
 */
package cn.knet.seal.financial.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okhttputils.OkHttpUtils;

import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.R;
import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.api.callback.DialogCallback;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.bean.response.AuthResponse;
import cn.knet.seal.financial.global.KnetAppManager;
import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.ui.widget.DoubleClickExitHelper;
import cn.knet.seal.financial.util.CacheUtils;
import cn.knet.seal.financial.util.LogUtil;
import cn.knet.seal.financial.util.MD5Utils;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 登陆类
 * 
 * ClassName: LoginActivity <br/>
 * Date: 2015年3月5日 下午1:45:54 <br/>
 * 
 * @author yangying@knet.cn
 * @version 1.0
 * @since 1.0
 */
public class LoginActivity extends BaseActivity implements OnClickListener{

    private static final String TAG = LoginActivity.class.getSimpleName();

    //输入用户名
    private EditText mEtLoginMobile;
    //输入密码
    private EditText mEtLoginPwd;
    //登陆按钮
    private Button mBtnLogin;
    private DoubleClickExitHelper mDoubleClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        KnetAppManager.getAppManager().addActivity(this);
    }

    protected void initUI() {
        mDoubleClick = new DoubleClickExitHelper(this);
        mEtLoginMobile = (EditText) findViewById(R.id.et_login_mobile);
        mEtLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        // 登录时隐藏软键盘
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEtLoginPwd.getWindowToken(), 0);
        mEtLoginMobile.setText(mEtLoginMobile.getText().toString().replaceAll(" ", ""));
        mEtLoginPwd.setText(mEtLoginPwd.getText().toString().replaceAll(" ", ""));
        final String mobile = mEtLoginMobile.getText().toString();
        final String pwd = mEtLoginPwd.getText().toString();
        if(TextUtils.isEmpty(mobile)){
            ToastUtil.showToast(getResources().getString(R.string.login_name_hint));
            return ;
        }
        if(TextUtils.isEmpty(pwd)){
            ToastUtil.showToast(getResources().getString(R.string.login_pwd_hint));
            return ;
        }
        // 登录
        OkHttpUtils.post(KnetFinancialHttpApi.URI_GET_TOKEN)
                .tag(TAG)
                .headers(new BaseHeader().getLoginHeader(this,mobile, MD5Utils.md5(pwd).toLowerCase()))
                .execute(new DialogCallback<AuthResponse>(LoginActivity.this,AuthResponse.class,
                        getResources().getString(R.string.login_waiting)) {

                    @Override
                    public void loadingDialogCancel() {
                        OkHttpUtils.getInstance().cancelTag(TAG);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, AuthResponse authResponse, Request request, @Nullable Response response) {
                        String token = authResponse.getToken();
                        String msg = authResponse.getMsg();
                        String permission = authResponse.getPermission();
                        if(token == null || permission == null){
                            EventBus.getDefault().post(new StringMsgEvents(msg));
                        }else{
                            // 缓存
                            CacheUtils cacheUtils = CacheUtils.get(LoginActivity.this);
                            cacheUtils.put(KnetConstants.TOKEN,token);
                            cacheUtils.put(KnetConstants.UID,mobile);
                            cacheUtils.put(KnetConstants.PWD,MD5Utils.md5(pwd).toLowerCase());
                            cacheUtils.put(KnetConstants.PERMISSION,permission);
                            cacheUtils.put(KnetConstants.IS_LOGIN,true);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }
                    }
                });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            OkHttpUtils.getInstance().cancelTag(this);
            return mDoubleClick.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
