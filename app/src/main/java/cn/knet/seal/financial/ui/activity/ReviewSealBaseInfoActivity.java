package cn.knet.seal.financial.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.ReviewStatusEnum;
import cn.knet.seal.financial.bean.response.BankInfo;
import cn.knet.seal.financial.bean.response.ReviewInfo;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.ObjectMsgEvent;
import cn.knet.seal.financial.util.DeviceUtils;
import cn.knet.seal.financial.util.DialogHelp;
import de.greenrobot.event.EventBus;

/**
 * 单条可信贷订单中的详情
 *
 * ClassName: ReviewSealBaseInfoActivity <br/>
 * Date: 2016/6/1 16:19 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class ReviewSealBaseInfoActivity extends BaseActivity {

    private static ReviewInfo mReviewSealBaseInfo;
    /** 基本信息 */
    private TextView mTvReviewDetailUnit;
    private TextView mTvReviewDetailAddress;
    private TextView mTvReviewDetailName;
    private TextView mTvReviewDetailMobile;
    private TextView mTvReviewDetailDate;
    /** 银行列表容器 */
    private LinearLayout mLlBankListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_seal_base_info);
        initUI();
    }

    private void initUI() {
        // 单位
        mTvReviewDetailUnit = (TextView) this.findViewById(R.id.tv_review_detail_unitname);
        // 地址
        mTvReviewDetailAddress = (TextView) findViewById(R.id.tv_review_detail_address);
        // 申请人
        mTvReviewDetailName = (TextView) findViewById(R.id.tv_review_detail_contactname);
        // 手机
        mTvReviewDetailMobile = (TextView) findViewById(R.id.tv_review_detail_contactmobile);
        // 下户日期
        mTvReviewDetailDate = (TextView) findViewById(R.id.tv_review_detail_expectreviewdate);
        mLlBankListContainer = (LinearLayout) findViewById(R.id.ll_base_info_container);
        mTvReviewDetailDate.setText(mReviewSealBaseInfo.getExpectReviewDate());
        mTvReviewDetailUnit.setText(mReviewSealBaseInfo.getUnitName());
        mTvReviewDetailAddress.setText(mReviewSealBaseInfo.getAddress());
        mTvReviewDetailName.setText(mReviewSealBaseInfo.getApplyUser());
        if (!TextUtils.isEmpty(mReviewSealBaseInfo.getApplyUserMobile())) {
            mTvReviewDetailMobile.setText(mReviewSealBaseInfo.getApplyUserMobile());
            findViewById(R.id.rl_review_detail_mobile).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogHelp.getConfirmDialog(ReviewSealBaseInfoActivity.this, getString(R.string.more_help_phone),
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse("tel:" + mReviewSealBaseInfo.getApplyUserMobile());
                            Intent intent = new Intent(Intent.ACTION_CALL, uri);
                            startActivity(intent);
                        }
                    },null).show();
                }
            });
        }else{
            mTvReviewDetailMobile.setText("未提供");
        }
        // 初始化银行列表
        if(ReviewStatusEnum.REVIEW_GRABED.getValue().equals(mReviewSealBaseInfo.getStatus())){
            // 已接单的初始化银行列表
            initBankList();
        }
    }

    private void initBankList() {
        List<BankInfo> bankList = mReviewSealBaseInfo.getBankList();
        RelativeLayout bankListView;
        if(null != bankList){
            for (int i = 0; i < bankList.size(); i++) {
                bankListView = (RelativeLayout) View.inflate(ReviewSealBaseInfoActivity.this, R.layout.activity_review_base_info_child, null);
                ImageView icon = (ImageView) bankListView.findViewById(R.id.iv_bank_icon);
                TextView bankName = (TextView) bankListView.findViewById(R.id.tv_bank_name);
                TextView status = (TextView) bankListView.findViewById(R.id.tv_review_status);
//                TextView review = (TextView) bankListView.findViewById(R.id.bt_review);
                bankName.setText(bankList.get(i).getBankName());
                if (ReviewStatusEnum.REVIEW_WAIT.getValue().equals(bankList.get(i).getBankReviewStatus())) {
                    status.setTextColor(getResources().getColor(R.color.red));
                }
                status.setText(ReviewStatusEnum.get(bankList.get(i).getBankReviewStatus()).getText());
                ImageLoader.getInstance().displayImage(bankList.get(i).getLogoUrl(), icon, KnetConstants.options);
                RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        DeviceUtils.dp2px(ReviewSealBaseInfoActivity.this, 50));
                bankListView.setTag(bankList.get(i));
                mLlBankListContainer.addView(bankListView, relLayoutParams);
            }
        }
    }

    @Override
    void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.review_base_info));
        toolbar.setNavigationIcon(R.mipmap.left);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_pressed));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void onEventMainThread(ObjectMsgEvent objectMsgEvent){
        // 接收传递过来的对象
        mReviewSealBaseInfo = (ReviewInfo)objectMsgEvent.getObject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 获取详情
     * @param view
     */
    public void getDetail(View view){
        // 跳转下户界面
        BankInfo bankInfo = (BankInfo) view.getTag();
        EventBus.getDefault().register(new ReviewFilesActivity());
        EventBus.getDefault().post(new ObjectMsgEvent(bankInfo));
        startActivity(new Intent(ReviewSealBaseInfoActivity.this,ReviewFilesActivity.class));
    }
}
