package cn.knet.seal.financial.api.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;

import com.lzy.okhttputils.request.BaseRequest;

import cn.knet.seal.financial.R;
import cn.knet.seal.financial.global.KnetErrorRequestException;
import cn.knet.seal.financial.global.StringMsgEvents;
import cn.knet.seal.financial.util.DeviceUtils;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 普通请求，不带加载框
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
public abstract class NormalCallback<T> extends JsonCallback<T> {

    private Context context;

    public NormalCallback(Activity activity, Class<T> clazz) {
        super(clazz);
        context = activity;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 先检查是否有网络
        if(new DeviceUtils(context).getNetworkType() == 0){
            EventBus.getDefault().post(new StringMsgEvents(context.getString(R.string.common_net_warning)));
            return;
        }
    }

    @Override
    public void onAfter(boolean isFromCache, @Nullable T t, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onAfter(isFromCache, t, call, response, e);
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
}
