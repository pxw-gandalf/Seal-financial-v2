package in.srain.cube.views.ptr;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 自定义下拉刷新头部
 *
 * ClassName: PtrCustomerAnimHeader <br/>
 * Date: 2016/6/14 9:37 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class PtrCustomerAnimHeader extends FrameLayout implements PtrUIHandler {
    private static ImageView mIvEarth;
    private Context context;
    private AnimationDrawable anim;

    public PtrCustomerAnimHeader(Context context) {
        super(context);
        this.context = context;
        initViews(null);
    }
    public PtrCustomerAnimHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public PtrCustomerAnimHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }


    private void initViews(AttributeSet attributeSet) {
        buildAnimation();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.customer_header, this);
        mIvEarth = (ImageView) header.findViewById(R.id.iv_earth);
        mIvEarth.setImageResource(R.drawable.earth_anim);
        anim = (AnimationDrawable) mIvEarth.getDrawable();
    }

    /**
     * 初始化动画
     */
    private void buildAnimation() {


    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Log.e("onUIReset","onUIReset");
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Log.e("onUIRefreshPrepare","onUIRefreshPrepare");
        anim.stop();
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Log.e("onUIRefreshBegin","onUIRefreshBegin");
        anim.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        Log.e("onUIRefreshComplete","onUIRefreshComplete");
        anim.stop();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
