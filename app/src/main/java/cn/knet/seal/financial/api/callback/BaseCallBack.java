package cn.knet.seal.financial.api.callback;


import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;

import cn.knet.seal.financial.KnetFinancialApplication;
import cn.knet.seal.financial.util.CacheUtils;

/**
 * 登录成功之后的全局请求回调
 * 目的在于请求前添加消息头
 *
 * ClassName: BaseCallBack <br/>
 * Date: 2016/5/23 15:29 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public abstract class BaseCallBack<T> extends AbsCallback<T> {

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        CacheUtils cacheUtils = CacheUtils.get(KnetFinancialApplication.getInstance().getApplicationContext());
        request.headers("token",cacheUtils.getAsString("token"))
                .headers("t","")
                .headers("ct","");
    }
}
