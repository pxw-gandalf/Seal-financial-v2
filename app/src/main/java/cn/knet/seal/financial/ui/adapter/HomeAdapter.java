package cn.knet.seal.financial.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.ReviewStatusEnum;
import cn.knet.seal.financial.bean.response.ReviewInfo;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.MyItemClickListener;
import cn.knet.seal.financial.ui.listener.BankListExtendListener;

/**
 * ClassName:  <br/>
 * Date: 2016/6/1 14:32 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    private int maxHeight;
    private int minHeight;
    private List<ReviewInfo> reviewList;
    private MyItemClickListener mItemClickListener;


    public HomeAdapter(List<ReviewInfo> mReviewList, Activity activity) {
        this.reviewList = mReviewList;
        this.context = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return KnetConstants.VIEW_TYPE_HOME_HEADER;
        }else if(position == 1){
            return KnetConstants.VIEW_TYPE_HOME_OPERATE;
        }else if(position == 2){
            return KnetConstants.VIEW_TYPE_HOME_TIPS;
        }else{
            return KnetConstants.VIEW_TYPE_HOME_LIST;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == KnetConstants.VIEW_TYPE_HOME_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_header,
                    parent, false);
            return new MyHeaderHolder(view);
        } else if (viewType == KnetConstants.VIEW_TYPE_HOME_OPERATE) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_operate,
                    parent, false);
            return new MyOperateHolder(view);
        } else if(viewType == KnetConstants.VIEW_TYPE_HOME_TIPS){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_tip,parent, false);
            return new MyTipHolder(view);
        } else if (viewType == KnetConstants.VIEW_TYPE_HOME_LIST) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_home_item,
                    parent, false);
            return new MyListViewHolder(view,mItemClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case KnetConstants.VIEW_TYPE_HOME_HEADER:
                break;
            case KnetConstants.VIEW_TYPE_HOME_OPERATE:
                break;
            case KnetConstants.VIEW_TYPE_HOME_TIPS:
                break;
            case KnetConstants.VIEW_TYPE_HOME_LIST:
                final ReviewInfo reviewInfo = reviewList.get(position - 3);
                if(null != reviewInfo){
                    ((MyListViewHolder) holder).tvBankCount.setText(null == reviewInfo.getBankList() ? 0 + "" : reviewInfo.getBankList().size() + "");
                    ((MyListViewHolder) holder).tvReviewAddress.setText(reviewInfo.getAddress());
                    ((MyListViewHolder) holder).tvReviewCompany.setText(reviewInfo.getUnitName());
                    ((MyListViewHolder) holder).tvReviewDate.setText(reviewInfo.getApplyDate());
                    if (ReviewStatusEnum.REVIEW_GRAB.getValue().equals(reviewInfo.getStatus())) {
                        ((MyListViewHolder) holder).btReviewTakeOrder.setVisibility(View.VISIBLE);
                        ((MyListViewHolder) holder).ivReviewed.setVisibility(View.GONE);
//                holder.btReviewTakeOrder.setOnClickListener(new TakeOrderClickListener(mReviewInfo.getSealLogId()));
                    } else {
                        ((MyListViewHolder) holder).btReviewTakeOrder.setVisibility(View.GONE);
                        ((MyListViewHolder) holder).ivReviewed.setVisibility(View.VISIBLE);

                    }
                    initBankList(((MyListViewHolder) holder), reviewInfo);

                    ((MyListViewHolder) holder).llReviewBankContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            ((MyListViewHolder) holder).llReviewBankContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            int childHeight = ((MyListViewHolder) holder).llReviewBankContainer.getChildAt(0).getHeight();

                            if (reviewInfo.getBankList().size() > 2) {
                                if (childHeight > minHeight) {
                                    // 列表滑动时获取到的子view高度是0，所以第一次正确获取后就保存，而且每个子view的高度是一定的
                                    // 默认显示两行
                                    minHeight = childHeight * 2;
                                }
                                maxHeight = ((MyListViewHolder) holder).llReviewBankContainer.getMeasuredHeight();
                                if (!reviewInfo.isExtend()) {
                                    ((MyListViewHolder) holder).llReviewBankContainer.getLayoutParams().height = minHeight;
                                    ((MyListViewHolder) holder).llReviewBankContainer.requestLayout();
                                } else {
                                    ((MyListViewHolder) holder).llReviewBankContainer.getLayoutParams().height = maxHeight;
                                    ((MyListViewHolder) holder).llReviewBankContainer.requestLayout();
                                }
                                ((MyListViewHolder) holder).rlReviewBankList.setOnClickListener(new BankListExtendListener(reviewInfo,
                                        ((MyListViewHolder) holder).llReviewBankContainer, ((MyListViewHolder) holder).ivReviewSort, maxHeight, minHeight));
                            } else {
                                ((MyListViewHolder) holder).ivReviewSort.setVisibility(View.GONE);
                            }
                        }
                    });
                    holder.itemView.setTag(reviewInfo);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return reviewList.size() + 3;
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
        if (null != reviewInfo.getBankList()) {
            holder.tvBankCount.setText(reviewInfo.getBankList().size() + "个贷款机构");
            for (int i = 0; i < reviewInfo.getBankList().size(); i++) {
                childView = View.inflate(context, R.layout.activity_review_list_item_child, null);
                ImageView ivIcon = (ImageView) childView.findViewById(R.id.iv_bank_icon);
                TextView tvCompany = (TextView) childView.findViewById(R.id.tv_bank_name);
                TextView tvReviewStatus = (TextView) childView.findViewById(R.id.tv_review_status);
                tvReviewStatus.setText(ReviewStatusEnum.get(reviewInfo.getBankList().get(i).getBankReviewStatus()).getText());
                ImageLoader.getInstance().displayImage(reviewInfo.getBankList().get(i).getLogoUrl(), ivIcon, KnetConstants.options);
                tvCompany.setText(reviewInfo.getBankList().get(i).getBankName());
                holder.llReviewBankContainer.addView(childView);
            }
        }
    }

    /**
     * 自定义了条目点击事件
     */
    class MyListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvReviewCompany;
        TextView tvReviewAddress;
        TextView tvReviewDate;
        Button btReviewTakeOrder;
        RelativeLayout rlReviewBankList;
        LinearLayout llReviewBankContainer;
        TextView tvBankCount;
        ImageView ivReviewSort;
        ImageView ivReviewed;
        MyItemClickListener mListener;

        public MyListViewHolder(View itemView,MyItemClickListener mListener) {
            super(itemView);
            tvReviewCompany = (TextView) itemView.findViewById(R.id.tv_review_company);
            tvReviewAddress = (TextView) itemView.findViewById(R.id.tv_review_address);
            tvReviewDate = (TextView) itemView.findViewById(R.id.tv_review_date);
            tvBankCount = (TextView) itemView.findViewById(R.id.tv_bank_count);
            btReviewTakeOrder = (Button) itemView.findViewById(R.id.bt_review_order_take);
            ivReviewSort = (ImageView) itemView.findViewById(R.id.iv_review_sort);
            llReviewBankContainer = (LinearLayout) itemView.findViewById(R.id.ll_review_bank_container);
            rlReviewBankList = (RelativeLayout) itemView.findViewById(R.id.rl_review_bank_list);
            ivReviewed = (ImageView) itemView.findViewById(R.id.iv_reviewed);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }
    }

    class MyHeaderHolder extends RecyclerView.ViewHolder{

        public MyHeaderHolder(View itemView) {
            super(itemView);
        }
    }

    class MyOperateHolder extends RecyclerView.ViewHolder{

        public MyOperateHolder(View itemView) {
            super(itemView);
        }
    }

    class MyTipHolder extends RecyclerView.ViewHolder{

        public MyTipHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }
}