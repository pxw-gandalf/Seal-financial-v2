package cn.knet.seal.financial.ui.activity;

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
import cn.knet.seal.financial.global.KnetAppManager;
import cn.knet.seal.financial.ui.fragment.HomeFragment;
import cn.knet.seal.financial.ui.fragment.MoreFragment;
import cn.knet.seal.financial.ui.fragment.UploadListFragment;
import cn.knet.seal.financial.ui.widget.DoubleClickExitHelper;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBar();
        initUI();
        KnetAppManager.getAppManager().addActivity(this);
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
    }

    private void initUI() {
        mDoubleClick = new DoubleClickExitHelper(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        // 左右滑动时的效果
        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);
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
