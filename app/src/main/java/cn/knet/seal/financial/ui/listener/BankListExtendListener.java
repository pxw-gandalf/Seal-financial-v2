package cn.knet.seal.financial.ui.listener;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.knet.seal.financial.bean.response.ReviewInfo;

/**
 *
 *
 * ClassName: BankListExtendListener <br/>
 * Date: 2016/5/31 15:45 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class BankListExtendListener implements View.OnClickListener {

    private LinearLayout mllContainer;
    private ReviewInfo reviewInfo;
    private ImageView ivSort;
    private int mMaxHeight;
    private int mMinHeight;

    public BankListExtendListener(ReviewInfo reviewInfo, LinearLayout mLlContainer, ImageView ivReviewSort,
                                  int maxHeight,int minHeight) {
        this.reviewInfo = reviewInfo;
        this.mllContainer = mLlContainer;
        this.ivSort = ivReviewSort;
        this.mMaxHeight = maxHeight;
        this.mMinHeight = minHeight;
    }

    @Override
    public void onClick(View v) {
        ValueAnimator animator;
        if (reviewInfo.isExtend()) {
            animator = ValueAnimator.ofInt(mMaxHeight, mMinHeight);
            RotateAnimation animation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(200);
            animation.setFillAfter(true);
            ivSort.startAnimation(animation);
        } else {
            animator = ValueAnimator.ofInt(mMinHeight, mMaxHeight);
            RotateAnimation animation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(200);
            animation.setFillAfter(true);
            ivSort.startAnimation(animation);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int animatedValue = (Integer) animator.getAnimatedValue();
                //将动画当前的值设置为tv_des的高度
                mllContainer.getLayoutParams().height = animatedValue;
                mllContainer.requestLayout();
                //伸展动画的时候整个view需要往上滚动
            }
        });
        animator.setDuration(200);
        animator.start();
        reviewInfo.setExtend(!reviewInfo.isExtend());
    }
}
