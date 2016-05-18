package cn.knet.seal.financial.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.ui.fragment.HomeFragment;
import cn.knet.seal.financial.ui.fragment.MoreFragment;
import cn.knet.seal.financial.ui.fragment.UploadListFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBar();
        initUI();
    }

    /**
     * toolbar参数
     */
    private void setToolBar() {
        // 屏蔽默认标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("金融助手");
    }


    private void initUI() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);
    }

    private class MainAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(HomeFragment.newInstance("",""));
            fragments.add(UploadListFragment.newInstance(1));
            fragments.add(MoreFragment.newInstance("",""));
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

}
