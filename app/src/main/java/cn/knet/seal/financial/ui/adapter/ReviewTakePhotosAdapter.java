package cn.knet.seal.financial.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.ReviewPhotoTypeEnum;
import cn.knet.seal.financial.bean.response.BankInfo;

/**
 * 下户照片适配器，总体
 *
 * ClassName: ReviewTakePhotosAdapter <br/>
 * Date: 2016/6/1 14:32 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class ReviewTakePhotosAdapter extends RecyclerView.Adapter<ReviewTakePhotosAdapter.MyViewHolder> {

    private final BankInfo bankInfo;
    private Activity context;


    public ReviewTakePhotosAdapter(Activity activity, BankInfo bankInfo) {
        this.context = activity;
        this.bankInfo = bankInfo;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_review_take_photos_item,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvReviewPhotoTitle.setText(ReviewPhotoTypeEnum.LIST_NAMES.get(position));
        holder.rvReviewPhotos.setLayoutManager(new GridLayoutManager(context, 4));
        holder.rvReviewPhotos.setAdapter(new ReviewTakePhotosChildAdapter(context,bankInfo,ReviewPhotoTypeEnum.LIST_VALUES.get(position)));

        holder.itemView.setTag(ReviewPhotoTypeEnum.LIST_VALUES.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    @Override
    public int getItemCount() {
        return ReviewPhotoTypeEnum.LIST_NAMES.size();
    }


    /**
     * 自定义了条目点击事件
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvReviewPhotoTitle;
        RecyclerView rvReviewPhotos;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvReviewPhotoTitle = (TextView) itemView.findViewById(R.id.tv_review_photo_title);
            rvReviewPhotos = (RecyclerView) itemView.findViewById(R.id.rv_review_take_photos_item);
        }
    }
}