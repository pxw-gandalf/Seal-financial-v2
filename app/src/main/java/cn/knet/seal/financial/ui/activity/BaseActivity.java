package cn.knet.seal.financial.ui.activity;

import android.support.v7.app.AppCompatActivity;

import cn.knet.seal.financial.GlobalEvents;
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
 * @since 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {


    public void onEventMainThread(GlobalEvents globalEvents){
        if (globalEvents.type == GlobalEvents.COMMON_UI_NET_ERROR){
            ToastUtil.showToast(globalEvents.obj.toString());
        }else if (globalEvents.type == GlobalEvents.COMMON_UI_MSG){
            ToastUtil.showToast(globalEvents.obj.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
