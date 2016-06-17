package cn.knet.seal.financial.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.File;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.bean.response.BankInfo;
import cn.knet.seal.financial.bean.response.ReviewDetailInfo;
import cn.knet.seal.financial.bean.response.ReviewInfo;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.ObjectMsgEvent;
import cn.knet.seal.financial.ui.adapter.ReviewTakePhotosAdapter;
import cn.knet.seal.financial.ui.widget.DividerItemDecoration;
import cn.knet.seal.financial.util.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 下户拍摄拍照
 * 因为照片处理的逻辑较为复杂，所以分为两类，
 * 1、下户展示照片，2、下户拍摄照片
 *
 * ClassName: ReviewTakePhotosActivity <br/>
 * Date: 2016/6/15 15:28 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class ReviewTakePhotosActivity extends BaseActivity {
    /** 下户照片集合 */
    private RecyclerView mRvTakePhotos;
    private static BankInfo mBankInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_take_photos);
        initUI();
        scanLocalFiles();
    }

    private void initUI() {

        mRvTakePhotos = (RecyclerView) findViewById(R.id.rv_review_take_photos);
        mRvTakePhotos.setLayoutManager(new LinearLayoutManager(this));
        mRvTakePhotos.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    /**
     * 扫描本地文件
     */
    private void scanLocalFiles() {
        String orderId = mBankInfo.getSealLogId();
        String bankId = mBankInfo.getAppInfoId();
        String path = KnetConstants.getReviewImageCache(false,orderId,bankId);
        File localFile = new File(path);
        if(localFile.exists()){
            File[] files = localFile.listFiles();
            for (int j = 0; j < files.length; j++) {
                //大图小图层文件
                File[] childFiles = files[j].listFiles();
                boolean isExist = false;
                for (int k = 0; k < childFiles.length; k++) {
                    //如果大图不存在，则不再回显
                    if(childFiles[k].getName().equals("big") && childFiles[k].list().length > 0){
                        isExist = true;
                    }
                }
                if(isExist){
//                    tmp.add(files[j].getName());
                    LogUtil.e("files ---------->", files[j].getName());
                }
            }
        }

        mRvTakePhotos.setAdapter(new ReviewTakePhotosAdapter(this,mBankInfo));
    }

    public void onEventMainThread(ObjectMsgEvent objectMsgEvent){
        // 接收传递过来的对象
        mBankInfo = (BankInfo)objectMsgEvent.getObject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.review_pics));
        toolbar.setNavigationIcon(R.mipmap.left);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_pressed));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
