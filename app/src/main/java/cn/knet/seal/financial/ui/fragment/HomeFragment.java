package cn.knet.seal.financial.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.phoenix.PullToRefreshView;

import cn.knet.seal.financial.R;

/**
 * 主页
 *
 * ClassName: HomeFragment <br/>
 * Date: 2016/5/30 14:27 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class HomeFragment extends Fragment {
    private PullToRefreshView mPullToRefreshView;


    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.ptf_home);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1500);
            }
        });
    }
}
