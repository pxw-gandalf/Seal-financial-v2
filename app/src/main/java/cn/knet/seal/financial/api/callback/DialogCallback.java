package cn.knet.seal.financial.api.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;

import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.request.BaseRequest;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.global.BaseHeader;
import cn.knet.seal.financial.global.KnetConstants;
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

    private void initDialog(Activity activity,String msg) {
        context = activity;
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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
        if(DeviceUtils.getNetworkType() == 0){
            EventBus.getDefault().post(new StringMsgEvents(context.getString(R.string.net_warning)));
            return;
        }
        String isLogin = CacheUtils.get(context).getAsString(KnetConstants.IS_LOGIN);
        if(!TextUtils.isEmpty(isLogin) && isLogin.equals("true")){
            // 如果登录成功，则网络请求自动加上消息头
            HttpHeaders httpHeaders = new BaseHeader().getRequestHeader(context);
            request.headers(httpHeaders);
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
    }
}
