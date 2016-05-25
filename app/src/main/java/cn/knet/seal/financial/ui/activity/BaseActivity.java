package cn.knet.seal.financial.ui.activity;

import android.support.v7.app.AppCompatActivity;

import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * activity基类
 * 处理全局的eventBus
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
