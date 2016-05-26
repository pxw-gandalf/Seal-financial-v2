package cn.knet.seal.financial.api.callback;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lzy.okhttputils.callback.AbsCallback;

import org.json.JSONObject;

import cn.knet.seal.financial.global.KnetErrorRequestException;
import cn.knet.seal.financial.global.StringMsgEvents;
import de.greenrobot.event.EventBus;
import okhttp3.Response;

/**
 * 将返回的json转换为
 *
 * ClassName: JsonCallback <br/>
 * Date: 2016/5/23 18:19 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Class<T> clazz;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) return null;

        JSONObject jsonObject = new JSONObject(responseData);
        final String msg = jsonObject.optString("msg", "");
        final int code = jsonObject.optInt("code", 0);
        // 对返回码进行初级的判断
        switch (code) {
            case 0:
                if (clazz != null) return new Gson().fromJson(responseData, clazz);
                break;
            default:
                throw new KnetErrorRequestException(msg);
        }
        return null;
    }
}
