package cn.knet.seal.financial.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.ReviewStatusEnum;
import cn.knet.seal.financial.bean.response.BankInfo;
import cn.knet.seal.financial.global.ObjectMsgEvent;
import de.greenrobot.event.EventBus;

public class ReviewFilesActivity extends BaseActivity {
    private static BankInfo mBankInfo;

    private LinearLayout mLlReviewPics;
    private TextView mTvReviewPicCount;
    private LinearLayout mLlReviewAudio;
    private TextView mTvReviewAudioCount;
    private LinearLayout mLlReviewVideo;
    private TextView mTvReviewVideoCount;
    private LinearLayout mLlReviewRemark;
    private TextView mTvReviewRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_files);

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

        if(mBankInfo.getBankReviewStatus().equals(ReviewStatusEnum.REVIEW_GRAB)){
            // TODO: 2016/6/3
        }

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

    public void onEventMainThread(ObjectMsgEvent objectMsgEvent){
        // 接收传递过来的对象
        mBankInfo = (BankInfo) objectMsgEvent.getObject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
