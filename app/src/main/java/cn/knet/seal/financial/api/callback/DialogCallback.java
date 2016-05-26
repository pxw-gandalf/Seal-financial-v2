package cn.knet.seal.financial.api.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;

import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.request.BaseRequest;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.global.KnetConstants;
import cn.knet.seal.financial.global.KnetErrorRequestException;
import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.util.CacheUtils;
import cn.knet.seal.financial.util.DeviceUtils;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 带进度框
 *
 * ClassName: DialogCallback <br/>
 * Date: 2016/5/23 18:23 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    private ProgressDialog dialog;
    private Context context;
    private LoadingDialogCancelListener cancelListener;

    private void initDialog(Activity activity,String msg) {
        context = activity;
        cancelListener = new LoadingDialogCancelListener();
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setOnCancelListener(cancelListener);
        dialog.setMessage(msg);
    }

    public DialogCallback(Activity activity, Class<T> clazz,String msg) {
        super(clazz);
        context = activity;
        initDialog(activity,msg);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 先检查是否有网络
        if(new DeviceUtils(context).getNetworkType() == 0){
            EventBus.getDefault().post(new StringMsgEvents(context.getString(R.string.common_net_warning)));
            return;
        }
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(boolean isFromCache, @Nullable T t, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onAfter(isFromCache, t, call, response, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        // 处理网络超时等异常
        if(null != e){
            if(e instanceof KnetErrorRequestException){
                // 属于正常访问，但是错误请求的异常，直接抛出
                EventBus.getDefault().post(new StringMsgEvents(e.getMessage()));
            }else {
                if(!TextUtils.isEmpty(e.getMessage())){
                    if(e.getMessage().contains("Socket closed")){
                        EventBus.getDefault().post(new StringMsgEvents(context.getString(R.string.common_net_cancel)));
                    }else{
                        EventBus.getDefault().post(new StringMsgEvents(context.getString(R.string.common_net_exceptional)));
                    }
                }else{
                    EventBus.getDefault().post(new StringMsgEvents(context.getString(R.string.common_net_exceptional)));
                }
            }
        }
    }

    private class LoadingDialogCancelListener implements DialogInterface.OnCancelListener{

        @Override
        public void onCancel(DialogInterface dialog) {
            loadingDialogCancel();
        }
    }

    public abstract void loadingDialogCancel();
}
