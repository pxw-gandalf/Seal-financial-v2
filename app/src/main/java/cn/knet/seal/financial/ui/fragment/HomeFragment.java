package cn.knet.seal.financial.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.api.callback.NormalCallback;
import cn.knet.seal.financial.bean.ReviewStatusEnum;
import cn.knet.seal.financial.bean.request.ReviewListRequest;
import cn.knet.seal.financial.bean.response.ReviewInfo;
import cn.knet.seal.financial.bean.response.ReviewListResponse;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.ui.listener.BankListExtendListener;
import cn.knet.seal.financial.ui.widget.DividerItemDecoration;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

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
    private final String TAG = this.getClass().getSimpleName();
    private PullToRefreshView mPullToRefreshView;
    private RecyclerView mRvHome;
    private HomeAdapter mHomeAdapter;
    private List<ReviewInfo> mReviewList;
    private int mMinHeight;
    private int mMaxHeight;

    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.moren)                    //加载时的状态
            .showImageForEmptyUri(R.mipmap.moren)                  //网络链接失败
            .showImageOnFail(R.mipmap.moren)                       //加载失败时
            .cacheInMemory(true)                                        //是否在内存中缓存
            .cacheOnDisk(true)                                          //是否缓存在SD卡
            .considerExifParams(true).build()              ;                     //自动辨认图片方向（如倒置手机时）
//            .displayer(new FadeInBitmapDisplayer(800)).build();        //设置图片展示的样式(这里是个动画



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
     * 适配器
     */
    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        private List<ReviewInfo> reviewList;

        public HomeAdapter(List<ReviewInfo> mReviewList) {
            this.reviewList = mReviewList;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if(viewType == KnetConstants.VIEW_TYPE_HOME_HEADER){
                return null;
            }else if(viewType == KnetConstants.VIEW_TYPE_HOME_OPERATE){
                return null;
            }else if(viewType == KnetConstants.VIEW_TYPE_HOME_LIST){
                view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_item,
                        parent, false);
                return new MyListViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            final ReviewInfo reviewInfo = reviewList.get(position);
            if(holder instanceof MyListViewHolder){
                if(null != reviewInfo){
                    ((MyListViewHolder)holder).tvBankCount.setText(null == reviewInfo.getBankList() ? 0+"" : reviewInfo.getBankList().size()+"");
                    ((MyListViewHolder)holder).tvReviewAddress.setText(reviewInfo.getAddress());
                    ((MyListViewHolder)holder).tvReviewCompany.setText(reviewInfo.getUnitName());
                    ((MyListViewHolder)holder).tvReviewDate.setText(reviewInfo.getApplyDate());
                    if(ReviewStatusEnum.REVIEW_GRAB.getValue().equals(reviewInfo.getStatus())){
                        ((MyListViewHolder)holder).btReviewTakeOrder.setVisibility(View.VISIBLE);
//                holder.btReviewTakeOrder.setOnClickListener(new TakeOrderClickListener(mReviewInfo.getSealLogId()));
                    }else{
                        ((MyListViewHolder)holder).btReviewTakeOrder.setVisibility(View.GONE);
                    }
                    initBankList(((MyListViewHolder)holder), reviewInfo);

                    ((MyListViewHolder)holder).llReviewBankContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            ((MyListViewHolder)holder).llReviewBankContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            int childHeight = ((MyListViewHolder)holder).llReviewBankContainer.getChildAt(0).getHeight();

                            if(reviewInfo.getBankList().size() > 2){
                                if (childHeight > mMinHeight) {
                                    // 列表滑动时获取到的子view高度是0，所以第一次正确获取后就保存，而且每个子view的高度是一定的
                                    // 默认显示两行
                                    mMinHeight = childHeight * 2;
                                }
                                mMaxHeight = ((MyListViewHolder)holder).llReviewBankContainer.getMeasuredHeight();
                                if (!reviewInfo.isExtend()) {
                                    ((MyListViewHolder)holder).llReviewBankContainer.getLayoutParams().height = mMinHeight;
                                    ((MyListViewHolder)holder).llReviewBankContainer.requestLayout();
                                } else {
                                    ((MyListViewHolder)holder).llReviewBankContainer.getLayoutParams().height = mMaxHeight;
                                    ((MyListViewHolder)holder).llReviewBankContainer.requestLayout();
                                }
                                ((MyListViewHolder)holder).rlReviewBankList.setOnClickListener(new BankListExtendListener(reviewInfo,
                                        ((MyListViewHolder)holder).llReviewBankContainer, ((MyListViewHolder)holder).ivReviewSort,mMaxHeight,mMinHeight));
                            }else {
                                ((MyListViewHolder)holder).ivReviewSort.setVisibility(View.GONE);
                            }
                        }
                    });
                    holder.itemView.setTag(reviewInfo);
                }
            }else{

            }


        }

        @Override
        public int getItemCount() {
            return reviewList.size();
        }



        /**
         * 初始化银行列表数据
         *
         * @param holder
         * @param reviewInfo
         */
        private void initBankList(MyListViewHolder holder, ReviewInfo reviewInfo) {
            holder.llReviewBankContainer.removeAllViews();
            View childView;
            if(null != reviewInfo.getBankList()){
                holder.tvBankCount.setText(reviewInfo.getBankList().size() + "个贷款机构");
                for (int i=0; i<reviewInfo.getBankList().size(); i++) {
                    childView = View.inflate(getActivity(), R.layout.activity_review_list_item_child, null);
                    ImageView ivIcon = (ImageView) childView.findViewById(R.id.iv_bank_icon);
                    TextView tvCompany = (TextView) childView.findViewById(R.id.tv_bank_name);
                    TextView tvReviewStatus = (TextView) childView.findViewById(R.id.tv_review_status);
                    tvReviewStatus.setText(ReviewStatusEnum.get(reviewInfo.getBankList().get(i).getBankReviewStatus()).getText());
                    ImageLoader.getInstance().displayImage(reviewInfo.getBankList().get(i).getLogoUrl(), ivIcon,options);
                    tvCompany.setText(reviewInfo.getBankList().get(i).getBankName());
                    holder.llReviewBankContainer.addView(childView);
                }
            }
        }

        class MyListViewHolder extends RecyclerView.ViewHolder {
            TextView tvReviewCompany;
            TextView tvReviewAddress;
            TextView tvReviewDate;
            Button btReviewTakeOrder;
            RelativeLayout rlReviewBankList;
            LinearLayout llReviewBankContainer;
            TextView tvBankCount;
            ImageView ivReviewSort;
            public MyListViewHolder(View itemView) {
                super(itemView);
                tvReviewCompany = (TextView) itemView.findViewById(R.id.tv_review_company);
                tvReviewAddress = (TextView) itemView.findViewById(R.id.tv_review_address);
                tvReviewDate = (TextView) itemView.findViewById(R.id.tv_review_date);
                tvBankCount = (TextView) itemView.findViewById(R.id.tv_bank_count);
                btReviewTakeOrder = (Button) itemView.findViewById(R.id.bt_review_order_take);
                ivReviewSort = (ImageView) itemView.findViewById(R.id.iv_review_sort);
                llReviewBankContainer = (LinearLayout) itemView.findViewById(R.id.ll_review_bank_container);
                rlReviewBankList = (RelativeLayout) itemView.findViewById(R.id.rl_review_bank_list);
            }
        }
    }

    /**
     * 刷新
     */
    class RefreshListener implements PullToRefreshView.OnRefreshListener{
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
                .execute(new NormalCallback<ReviewListResponse>(getActivity(),ReviewListResponse.class) {
                    @Override
                    public void onResponse(boolean isFromCache, ReviewListResponse reviewListResponse, Request request, @Nullable Response response) {
                        if(null != reviewListResponse){
                            mReviewList = reviewListResponse.getReviewList();
                            mRvHome.setAdapter(mHomeAdapter = new HomeAdapter(mReviewList));
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
