package cn.knet.seal.financial.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.response.BankInfo;
import cn.knet.seal.financial.global.ObjectMsgEvent;
import de.greenrobot.event.EventBus;

public class ReviewActivity extends BaseActivity {
    private static BankInfo mBankInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    @Override
    void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.review_title));
        toolbar.setNavigationIcon(R.mipmap.left);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_pressed));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onEventMainThread(ObjectMsgEvent objectMsgEvent){
        // 接收传递过来的对象
        mBankInfo = (BankInfo)objectMsgEvent.getObject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
