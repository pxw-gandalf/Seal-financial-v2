package cn.knet.seal.financial.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.db.UploadDBHelper;
import cn.knet.seal.financial.global.KnetAppManager;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.ui.fragment.HomeFragment;
import cn.knet.seal.financial.ui.fragment.MoreFragment;
import cn.knet.seal.financial.ui.fragment.UploadListFragment;
import cn.knet.seal.financial.ui.widget.DoubleClickExitHelper;
import cn.knet.seal.financial.util.DialogHelp;

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
public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private DoubleClickExitHelper mDoubleClick;
    private UploadDBHelper mUploadDBHelper;

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
    void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        // 屏蔽默认标题
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setTitle("金融助手");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_pressed));
    }

    private void initUI() {
        mDoubleClick = new DoubleClickExitHelper(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        // 左右滑动时的效果
        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
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

}
