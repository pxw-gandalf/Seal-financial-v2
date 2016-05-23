package cn.knet.seal.financial.bean;

import android.content.Context;
import android.os.Build;

import com.lzy.okhttputils.model.HttpHeaders;

import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.util.DeviceUtils;
import cn.knet.seal.financial.util.MD5Utils;

/**
 * 登录校验头
 * ClassName: BaseHeader <br/>
 * Date: 2016/5/23 11:12 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class BaseHeader extends HttpHeaders {

    /**
     * 当前接口版本
     */
    private String v = "v";
    /**
     * 用户id
     */
    private String uid = "uid";
    /**
     * 时间戳
     */
    private String t = "t";
    /**
     * 用户验证token
     */
    private String pt = "pt";
    /**
     * 用户标识码
     */
    private String d = "d";
    private KnetFinancialHttpApi api;

    public HttpHeaders getBaseHeader(Context context, String name, String pwd) {
        api = new KnetFinancialHttpApi();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.put(uid, name);
        httpHeaders.put(t, String.valueOf(System.currentTimeMillis()));
        httpHeaders.put(pt, buildpt(name, httpHeaders, pwd));
        httpHeaders.put(d, MD5Utils.md5(new DeviceUtils(context).getIMEI()));

        return httpHeaders;
    }

    private String buildpt(String uid, HttpHeaders baseHeader, String p) {
        if ("nubia".equals(Build.MANUFACTURER.toLowerCase())
                || "vivo".equals(Build.MANUFACTURER.toLowerCase())
                || "xiaomi".equals(Build.MANUFACTURER.toLowerCase())
                || "meizu".equals(Build.MANUFACTURER.toLowerCase())
                || "letv".equals(Build.MANUFACTURER.toLowerCase())) {
            return MD5Utils.md5(uid + p + baseHeader.get(t)).toLowerCase();
        } else {
            return api.pt(uid, baseHeader.get(t), p);
        }
    }
}
