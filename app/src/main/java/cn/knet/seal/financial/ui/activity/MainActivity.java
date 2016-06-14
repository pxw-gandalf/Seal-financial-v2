package cn.knet.seal.financial.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.db.UploadDBHelper;
import cn.knet.seal.financial.global.KnetAppManager;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.ui.fragment.HomeFragment;
import cn.knet.seal.financial.ui.fragment.MoreFragment;
import cn.knet.seal.financial.ui.fragment.UploadListFragment;
import cn.knet.seal.financial.ui.widget.DoubleClickExitHelper;
import cn.knet.seal.financial.util.DialogHelp;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 主函数，默认加载首页
 *
 * ClassName: MainActivity <br/>
 * Date: 2016/5/19 13:59 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DoubleClickExitHelper mDoubleClick;
    private UploadDBHelper mUploadDBHelper;
    private LinearLayout mLlLocation;
    private ImageView mIvLocation;
    private FrameLayout mFlNotice;
    private ImageView mIvRed;
    private TextView mTvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KnetAppManager.getAppManager().addActivity(this);
        initUI();
        // 检查是否有待上传数据
        checkUnUploadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    private void checkUnUploadData() {
        mUploadDBHelper = new UploadDBHelper(this, KnetConstants.DB_NAME);
        if(mUploadDBHelper.queryUnDownloaded().size() > 0){
            DialogHelp.getConfirmDialog(this, getString(R.string.home_exit_unUpload_task), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            },null).show();
        }
    }

    /**
     * toolbar参数
     */
    private void setToolBar() {
        mLlLocation = (LinearLayout) findViewById(R.id.ll_location);
        mTvLocation = (TextView) findViewById(R.id.tv_main_location);
        mIvLocation = (ImageView) findViewById(R.id.iv_main_location);
        mFlNotice = (FrameLayout) findViewById(R.id.fl_notice);
        mIvRed = (ImageView) findViewById(R.id.iv_red);
    }

    private void initUI() {
        setToolBar();

        mDoubleClick = new DoubleClickExitHelper(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        // 左右滑动时的效果
        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_location:
                ToastUtil.showToast("location");
                break;
            case R.id.fl_notice:
                ToastUtil.showToast("notice");
                break;
        }
    }

    private class MainAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(HomeFragment.newInstance("",""));
            fragments.add(UploadListFragment.newInstance(1));
            fragments.add(MoreFragment.newInstance());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClick.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onEventMainThread(StringMsgEvents stringMsgEvents){
        ToastUtil.showToast(stringMsgEvents.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
