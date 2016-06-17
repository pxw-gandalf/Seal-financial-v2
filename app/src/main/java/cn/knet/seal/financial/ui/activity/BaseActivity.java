package cn.knet.seal.financial.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.global.KnetAppManager;
import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * activity基类
 * 处理全局的eventBus,只处理了消息类
 *
 * ClassName: BaseActivity <br/>
 * Date: 2016/5/23 18:52 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 3.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        KnetAppManager.getAppManager().addActivity(this);
        initToolbar();
    }

    // 子类实现
    abstract void initToolbar();


    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    public void onEventMainThread(StringMsgEvents stringMsgEvents){
        ToastUtil.showToast(stringMsgEvents.getMsg());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

