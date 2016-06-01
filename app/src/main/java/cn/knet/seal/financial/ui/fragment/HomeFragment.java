package cn.knet.seal.financial.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.api.callback.NormalCallback;
import cn.knet.seal.financial.bean.request.ReviewListRequest;
import cn.knet.seal.financial.bean.response.ReviewInfo;
import cn.knet.seal.financial.bean.response.ReviewListResponse;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.global.MyItemClickListener;
import cn.knet.seal.financial.ui.activity.ReviewSealBaseInfoActivity;
import cn.knet.seal.financial.ui.adapter.HomeAdapter;
import cn.knet.seal.financial.ui.widget.DividerItemDecoration;
import cn.knet.seal.financial.util.ToastUtil;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 主页
 * <p/>
 * ClassName: HomeFragment <br/>
 * Date: 2016/5/30 14:27 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class HomeFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    private PullToRefreshView mPullToRefreshView;
    private RecyclerView mRvHome;
    private HomeAdapter mHomeAdapter;
    private List<ReviewInfo> mReviewList;

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
        mRvHome = (RecyclerView) view.findViewById(R.id.rv_home_fragment);
        mRvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvHome.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        mPullToRefreshView.setOnRefreshListener(new RefreshListener());
        mPullToRefreshView.setAutoRefresh();
    }

    /**
     * 刷新
     */
    class RefreshListener implements PullToRefreshView.OnRefreshListener {
        @Override
        public void onRefresh() {
            refreshNewData();
        }
    }

    private void refreshNewData() {
        ReviewListRequest reviewListRequest = new ReviewListRequest();
        reviewListRequest.setPageNum(1);
        reviewListRequest.setPageSize(20);
//        reviewListRequest.setReviewStatus(ReviewStatusEnum.REVIEW_WAIT.getValue());
        String json = new Gson().toJson(reviewListRequest);
        OkHttpUtils.post(KnetFinancialHttpApi.URI_GET_REVIEW_LIST)
                .tag(TAG)
                .headers(new BaseHeader().getRequestHeader(getActivity()))
                .postJson(json)
                .execute(new NormalCallback<ReviewListResponse>(getActivity(), ReviewListResponse.class) {
                    @Override
                    public void onResponse(boolean isFromCache, ReviewListResponse reviewListResponse, Request request, @Nullable Response response) {
                        if (null != reviewListResponse) {
                            mReviewList = reviewListResponse.getReviewList();
                            mRvHome.setAdapter(mHomeAdapter = new HomeAdapter(mReviewList, getActivity()));
                            mHomeAdapter.setOnItemClickListener(new MyItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    ToastUtil.showToast(position + "--->" + ((ReviewInfo) view.getTag()).getUnitName());
//                                    EventBus.getDefault().register(new ReviewSealBaseInfoActivity());
                                    startActivity(new Intent(getActivity(),ReviewSealBaseInfoActivity.class));
                                }
                            });
                        }
                        mPullToRefreshView.setRefreshing(false);
                    }

                    @Override
                    public void onAfter(boolean isFromCache, @Nullable ReviewListResponse reviewListResponse, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onAfter(isFromCache, reviewListResponse, call, response, e);
                        mPullToRefreshView.setRefreshing(false);
                    }
                });
    }
}
