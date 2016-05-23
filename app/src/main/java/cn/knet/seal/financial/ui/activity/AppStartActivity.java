package cn.knet.seal.financial.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import cn.knet.seal.financial.Constants;
import cn.knet.seal.financial.GlobalEvents;
import cn.knet.seal.financial.R;
import cn.knet.seal.financial.util.CacheUtils;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 *
 * 欢迎界面
 * ClassName: AppStartActivity <br/>
 * Date: 2016/5/18 15:44 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version: 1.0
 * @since 1.0
 * @update:
 *
 */
public class AppStartActivity extends Activity {
    private CacheUtils cacheUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = View.inflate(this,R.layout.content_app_start,null);
        cacheUtils = CacheUtils.get(this);

        setContentView(view);

        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(1000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });

    }

    /**
     * 跳转到主页
     */
    private void redirectTo() {
        // 判断本地是否有登录缓存
        String pwd = cacheUtils.getAsString(Constants.PWD);
        String uid = cacheUtils.getAsString(Constants.UID);
        String token = cacheUtils.getAsString(Constants.TOKEN);
        if(!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(uid) && !TextUtils.isEmpty(token)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
