package cn.knet.seal.financial.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.lzy.okhttputils.OkHttpUtils;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.api.callback.DialogCallback;
import cn.knet.seal.financial.bean.response.BaseResponse;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.util.CacheUtils;
import cn.knet.seal.financial.util.MD5Utils;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import okhttp3.Response;

public class MoreModifyPwdActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private EditText mNormalPwd;
    private EditText mNewPwd;
    private EditText mConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_modify_pwd);

        initUI();
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_more);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.more_change_pwd));
        toolbar.setNavigationIcon(R.mipmap.left);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_pressed));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNormalPwd = (EditText) findViewById(R.id.et_normal_pwd);
        mNewPwd = (EditText) findViewById(R.id.et_new_pwd);
        mConfirmPwd = (EditText) findViewById(R.id.et_confirm_pwd);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_modify_pwd, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_modify_pwd){
            String normalPwd = mNormalPwd.getText().toString();
            String newPwd = mNewPwd.getText().toString();
            String confirmPwd = mConfirmPwd.getText().toString();
            checkPwd(normalPwd,newPwd,confirmPwd);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 密码规则校验
     * @param normalPwd
     * @param newPwd
     * @param confirmPwd
     */
    private void checkPwd(String normalPwd, String newPwd, String confirmPwd) {
        if(TextUtils.isEmpty(normalPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd)) {
            ToastUtil.show(MoreModifyPwdActivity.this, R.string.login_pwd_hint);
            return;
        }
        if(normalPwd.length() < 6 || newPwd.length() < 6 || confirmPwd.length() < 6) {
            ToastUtil.show(MoreModifyPwdActivity.this, R.string.more_pwd_length_err);
            return;
        }
        if(newPwd.equals(confirmPwd)) {
            InputMethodManager imm = (InputMethodManager) MoreModifyPwdActivity.this.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mConfirmPwd.getWindowToken(), 0);
            modifyPwd(MD5Utils.md5(normalPwd).toLowerCase(),
                    MD5Utils.md5(confirmPwd).toLowerCase());
        } else {
            ToastUtil.show(MoreModifyPwdActivity.this, R.string.more_pwd_compare_err);
        }
    }

    /**
     * 修改密码
     * @param oldPwd    原始密码，md5值
     * @param newPwd    新密码，MD5值
     */
    private void modifyPwd(String oldPwd, String newPwd) {
        String localUid = CacheUtils.get(this).getAsString(KnetConstants.UID);
        String localPws = CacheUtils.get(this).getAsString(KnetConstants.PWD);
        if(TextUtils.isEmpty(localPws) || TextUtils.isEmpty(localUid)){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
            ToastUtil.showToast(getString(R.string.common_get_statue_err));
        }else {
            OkHttpUtils.put(KnetFinancialHttpApi.URI_CHANGE_PWD)
                    .tag(TAG)
                    .headers(new BaseHeader().getUpdateAuthHeadMap(this,oldPwd,newPwd))
                    .execute(new DialogCallback<BaseResponse>(MoreModifyPwdActivity.this,BaseResponse.class,getString(R.string.common_loading)) {
                        @Override
                        public void loadingDialogCancel() {
                            OkHttpUtils.getInstance().cancelTag(TAG);
                        }
                        @Override
                        public void onResponse(boolean isFromCache, BaseResponse baseResponse, Request request, @Nullable Response response) {
                            if(null != baseResponse){
                                EventBus.getDefault().post(new StringMsgEvents(getString(R.string.more_pwd_success)));
                                // 设置登录标识为false
                                startActivity(new Intent(MoreModifyPwdActivity.this,LoginActivity.class));
                                finish();
                            }
                        }
                    });
        }
    }
}
