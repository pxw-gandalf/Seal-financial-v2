package cn.knet.seal.financial.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.api.callback.DialogCallback;
import cn.knet.seal.financial.bean.request.UpdateRequest;
import cn.knet.seal.financial.bean.response.UpdateResponse;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.service.DownloadService;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 更新管理类
 * <p/>
 * ClassName:UpdateManager  <br/>
 * Date: 2016/5/27 11:22 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class UpdateManager {
    private final String TAG = UpdateManager.class.getSimpleName();
    private Activity mContext;

    public UpdateManager(Activity context) {
        this.mContext = context;
    }

    /**
     * 检查更新
     */
    public void checkUpdate() {
        String versionCode = String.valueOf(new DeviceUtils(mContext).getVersionCode());
        String versionName = new DeviceUtils(mContext).getVersionName();
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setVersionCode(versionCode);
        updateRequest.setVersionName(versionName);

        OkHttpUtils.post(KnetFinancialHttpApi.URI_UPDATE_CHECK)
                .tag(TAG)
                .headers(new BaseHeader().getRequestHeader(mContext))
                .postJson(new Gson().toJson(updateRequest))
                .execute(new DialogCallback<UpdateResponse>(mContext, UpdateResponse.class, mContext.getString(R.string.more_check_update)) {
                    @Override
                    public void loadingDialogCancel() {
                        OkHttpUtils.getInstance().cancelTag(TAG);
                    }

                    @Override
                    public void onResponse(boolean isFromCache, UpdateResponse updateResponse, Request request, @Nullable Response response) {
                        if (null != updateResponse) {
                            showUpdateDialog(updateResponse);
                        }
                    }
                });
    }

    /**
     * 弹出新版本对话框
     * @param updateResponse
     */
    private void showUpdateDialog(final UpdateResponse updateResponse) {
        DialogHelp.getConfirmDialog(mContext, mContext.getString(R.string.more_new_version_title),
                updateResponse.getDescp(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openDownLoadService(mContext,updateResponse.getDownloadUrl(),updateResponse.getVersionName());
                    }
                }).show();
    }

    /**
     * 开启下载服务
     * @param context
     * @param downurl
     * @param tilte
     */
    public void openDownLoadService(Context context, String downurl, String tilte) {
        final ICallbackResult callback = new ICallbackResult() {

            @Override
            public void OnBackResult(Object s) {}
        };
        ServiceConnection conn = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
                binder.addCallback(callback);
                binder.start();

            }
        };
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, downurl);
        intent.putExtra(DownloadService.BUNDLE_KEY_TITLE, tilte);
        context.startService(intent);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

}
