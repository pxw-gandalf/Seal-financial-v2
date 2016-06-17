package cn.knet.seal.financial.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.ReviewPhotoTypeEnum;
import cn.knet.seal.financial.bean.ReviewSourcePhoto;
import cn.knet.seal.financial.bean.response.BankInfo;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.util.ToastUtil;

/**
 * 单个照片类型的item适配器
 *
 * ClassName:  ReviewTakePhotosChildAdapter<br/>
 * Date: 2016/6/1 14:32 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class ReviewTakePhotosChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String photoType;
    private BankInfo bankInfo;
    private Activity context;
    /** 拍摄后储存在该集合中 */
    private List<ReviewSourcePhoto> sourcePhotoList = new ArrayList<>();

    public ReviewTakePhotosChildAdapter(Activity activity, BankInfo bankInfo, String photoType) {
        this.context = activity;
        this.bankInfo = bankInfo;
        this.photoType = photoType;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = getItemCount();
        // itemCount够11个，说明已经拍够了10张照片，则只显示照片，不显示拍照的图标
        /*if(itemCount == 11){
            return KnetConstants.VIEW_TYPE_REVIEW_SHOW_PHOTO;
        }else */
        if(itemCount < 11 && itemCount > 0){
            if (position == itemCount - 1){
                return KnetConstants.VIEW_TYPE_REVIEW_TAKE_PHOTO;
            }else{
                return KnetConstants.VIEW_TYPE_REVIEW_SHOW_PHOTO;
            }
        }else {
            return KnetConstants.VIEW_TYPE_REVIEW_SHOW_PHOTO;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == KnetConstants.VIEW_TYPE_REVIEW_TAKE_PHOTO){
            view = LayoutInflater.from(context).inflate(R.layout.activity_review_take_photos_child_logo,
                    parent, false);
            return new MyTakePhotoHolder(view);
        }else if(viewType == KnetConstants.VIEW_TYPE_REVIEW_SHOW_PHOTO){
            view = LayoutInflater.from(context).inflate(R.layout.activity_review_take_photos_child_item,
                    parent, false);
            return new MyShowPhotoHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == KnetConstants.VIEW_TYPE_REVIEW_SHOW_PHOTO){
            ((MyShowPhotoHolder)holder).ivPhotos.setOnClickListener(new PhotoOnClickListener());
        }else{
            ((MyTakePhotoHolder)holder).ivTakePhotoLogo.setOnClickListener(
                    new TakePhotoOnClickListener(photoType));
        }

        // 绑定数据
        holder.itemView.setTag(null);
    }

    @Override
    public int getItemCount() {
        return sourcePhotoList.size() < KnetConstants.REVIEW_MAX_PHOTO_SIZE ?
                sourcePhotoList.size() + 1 : sourcePhotoList.size();
    }


    /**
     * 图片展示
     */
    class MyShowPhotoHolder extends RecyclerView.ViewHolder{
        ImageView ivPhotos;
        public MyShowPhotoHolder(View itemView) {
            super(itemView);
            ivPhotos = (ImageView) itemView.findViewById(R.id.iv_review_take_photo_child);
        }
    }

    /**
     * 拍照的logo
     */
    class MyTakePhotoHolder extends RecyclerView.ViewHolder{
        ImageView ivTakePhotoLogo;
        public MyTakePhotoHolder(View itemView) {
            super(itemView);
            ivTakePhotoLogo = (ImageView) itemView.findViewById(R.id.iv_review_take_photo_logo);
        }
    }

    class PhotoOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ToastUtil.showToast(v.getId()+"--");
        }
    }

    /**
     * 拍照
     */
    class TakePhotoOnClickListener implements View.OnClickListener{

        private String photoType;

        public TakePhotoOnClickListener(String photoType) {
            this.photoType = photoType;
        }

        @Override
        public void onClick(View v) {
            // 定位
            // 定位成功后打开相机
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            // 根据文件地址创建文件(存放的是原图)
            String name = "pic_" + System.currentTimeMillis();
            File file = new File(KnetConstants.getReviewBigImageCache(true,photoType,
                    bankInfo.getSealLogId(),bankInfo.getAppInfoId())
                    + File.separator
                    + name);
            Uri uri = Uri.fromFile(file);
            // 设置系统相机拍摄照片完成后图片文件的存放地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            cameraPhoto = new CameraPhoto(file.getAbsolutePath(), adapter.getPhotoType(),longitude,latitude);
            context.startActivity(intent);
        }
    }

}