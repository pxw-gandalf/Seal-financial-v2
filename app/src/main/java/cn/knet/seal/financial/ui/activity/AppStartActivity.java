package cn.knet.seal.financial.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import cn.knet.seal.financial.R;

public class AppStartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = View.inflate(this,R.layout.content_app_start,null);
        setContentView(view);

        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(800);
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
     * 跳转到...
     */
    private void redirectTo() {
        /*Intent uploadLog = new Intent(this, LogUploadService.class);
        startService(uploadLog);*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
