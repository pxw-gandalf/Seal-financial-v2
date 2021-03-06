package cn.knet.seal.financial.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.api.callback.NormalCallback;
import cn.knet.seal.financial.bean.ReviewStatusEnum;
import cn.knet.seal.financial.bean.request.ReviewInfoDetailRequest;
import cn.knet.seal.financial.bean.response.BankInfo;
import cn.knet.seal.financial.bean.response.ReviewInfoDetailResponse;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.ObjectMsgEvent;
import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrCustomerAnimHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 新建的下户操作列表
 * 所有操作不在同一个列表下展示，都跳转至下一页面
 * <p/>
 * ClassName: ReviewFilesActivity <br/>
 * Date: 2016/6/6 14:09 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class ReviewFilesActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = ReviewFilesActivity.class.getSimpleName();
    private static BankInfo mBankInfo;

    private PtrClassicFrameLayout mPtf;
    private LinearLayout mLlReviewPics;
    private TextView mTvReviewPicCount;
    private LinearLayout mLlReviewAudio;
    private TextView mTvReviewAudioCount;
    private LinearLayout mLlReviewVideo;
    private TextView mTvReviewVideoCount;
    private LinearLayout mLlReviewRemark;
    private TextView mTvReviewRemark;
    private RelativeLayout mRlReviewExistBar;
    private boolean isExistReviewedBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_files);
        isExistReviewedBank = getIntent().getBooleanExtra(KnetConstants.IS_EXIST_REVIEWED_BANK, false);

        initUI();

    }

    private void initUI() {
        mLlReviewPics = (LinearLayout) findViewById(R.id.ll_review_pics);
        mTvReviewPicCount = (TextView) findViewById(R.id.tv_review_pic_count);
        mLlReviewAudio = (LinearLayout) findViewById(R.id.ll_review_audio);
        mTvReviewAudioCount = (TextView) findViewById(R.id.tv_review_audio_count);
        mLlReviewVideo = (LinearLayout) findViewById(R.id.ll_review_video);
        mTvReviewVideoCount = (TextView) findViewById(R.id.tv_review_video_count);
        mLlReviewRemark = (LinearLayout) findViewById(R.id.ll_review_remark);
        mTvReviewRemark = (TextView) findViewById(R.id.tv_review_remark);
        mPtf = (PtrClassicFrameLayout) findViewById(R.id.ptf_review_files);
        mRlReviewExistBar = (RelativeLayout) findViewById(R.id.rl_review_exist_bar);

        mLlReviewPics.setOnClickListener(this);
        mLlReviewAudio.setOnClickListener(this);
        mLlReviewVideo.setOnClickListener(this);
        mLlReviewRemark.setOnClickListener(this);

        PtrCustomerAnimHeader header = new PtrCustomerAnimHeader(this);
        mPtf.setHeaderView(header);
        mPtf.addPtrUIHandler(header);
        mPtf.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getReviewDetailInfo();
            }
        });
        mPtf.autoRefresh();
    }


    @Override
    void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.review_file_list));
        toolbar.setNavigationIcon(R.mipmap.left);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_pressed));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onEventMainThread(ObjectMsgEvent objectMsgEvent) {
        // 接收传递过来的对象
        mBankInfo = (BankInfo) objectMsgEvent.getObject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getReviewDetailInfo() {
        if (mBankInfo.getBankReviewStatus().equals(ReviewStatusEnum.REVIEW_WAIT.getValue()) ||
                mBankInfo.getBankReviewStatus().equals(ReviewStatusEnum.REVIEW_MODIFY.getValue())
                ) {
            // TODO: 2016/6/3 本地扫描
            if (isExistReviewedBank) {
                mRlReviewExistBar.setVisibility(View.VISIBLE);
                mRlReviewExistBar.setOnClickListener(new ChoiceExistReviewDataListener());
            }

            mPtf.refreshComplete();

        } else {
            // 从服务器获取该贷款机构的下户数据信息
            final ReviewInfoDetailRequest reviewInfoDetailRequest = new ReviewInfoDetailRequest();
            reviewInfoDetailRequest.setAppInfoId(mBankInfo.getAppInfoId());
            reviewInfoDetailRequest.setBankType(mBankInfo.getBankType());
            OkHttpUtils.post(KnetFinancialHttpApi.URI_GET_REVIEW)
                    .tag(TAG)
                    .headers(new BaseHeader().getRequestHeader(this))
                    .postJson(new Gson().toJson(reviewInfoDetailRequest))
                    .execute(new NormalCallback<ReviewInfoDetailResponse>(this, ReviewInfoDetailResponse.class) {
                        @Override
                        public void onResponse(boolean isFromCache, ReviewInfoDetailResponse reviewResponse, Request request, @Nullable Response response) {
                            if (null != reviewResponse) {
                                int imageSize = reviewResponse.getReviewDetail().getImageUrlList().size();
                                int audioSize = reviewResponse.getReviewDetail().getAudioUrlList().size();
                                int videoSize = reviewResponse.getReviewDetail().getVideoUrlList().size();
                                mTvReviewPicCount.setText("已提交" + imageSize + "张照片");
                                mTvReviewAudioCount.setText("已提交" + audioSize + "条录音");
                                mTvReviewVideoCount.setText("已提交" + videoSize + "条视频");
                            }
                        }

                        @Override
                        public void onAfter(boolean isFromCache, @Nullable ReviewInfoDetailResponse reviewInfoDetailResponse, Call call, @Nullable Response response, @Nullable Exception e) {
                            super.onAfter(isFromCache, reviewInfoDetailResponse, call, response, e);
                            mPtf.refreshComplete();
                        }
                    });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_review_pics:
                // 跳转拍照页面
                if(mBankInfo.getBankReviewStatus().equals(ReviewStatusEnum.REVIEW_MODIFY.getValue())
                        || mBankInfo.getBankReviewStatus().equals(ReviewStatusEnum.REVIEW_WAIT.getValue())){
                    // 拍照页面
                    EventBus.getDefault().register(new ReviewTakePhotosActivity());
                    EventBus.getDefault().post(new ObjectMsgEvent(mBankInfo));
                    startActivity(new Intent(ReviewFilesActivity.this,ReviewTakePhotosActivity.class));
                }else {
                    // 显示照片页面

                }
                break;
            case R.id.ll_review_audio:
                break;
            case R.id.ll_review_video:
                break;
        }
    }

    class ChoiceExistReviewDataListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
