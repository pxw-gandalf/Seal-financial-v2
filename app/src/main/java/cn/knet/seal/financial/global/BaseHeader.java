package cn.knet.seal.financial.global;

import android.content.Context;
import android.os.Build;

import com.lzy.okhttputils.model.HttpHeaders;

import java.util.Map;

import cn.knet.seal.financial.api.KnetFinancialHttpApi;
import cn.knet.seal.financial.util.CacheUtils;
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

    /**
     * 获取登录时的消息头
     * @param context 上下文
     * @param name  登录名
     * @param pwd   密码（md5）
     * @return
     */
    public HttpHeaders getLoginHeader(Context context, String name, String pwd) {
        api = new KnetFinancialHttpApi();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.put(uid, name);
        httpHeaders.put(t, String.valueOf(System.currentTimeMillis()));
        httpHeaders.put(pt, buildpt(name, httpHeaders, pwd));
        httpHeaders.put(d, MD5Utils.md5(new DeviceUtils(context).getIMEI()));

        return httpHeaders;
    }

    /**
     * 登录后，网络请求的消息头
     * @param context
     * @return
     */
    public HttpHeaders getRequestHeader(Context context){
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = CacheUtils.get(context).getAsString("token");
        httpHeaders.put("token",token);
        httpHeaders.put("t",String.valueOf(System.currentTimeMillis()));
        httpHeaders.put("ct",buildct(token,httpHeaders,context));
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

    private String buildct(String token, HttpHeaders baseHeader, Context context) {
        if ("nubia".equals(Build.MANUFACTURER.toLowerCase())
                || "vivo".equals(Build.MANUFACTURER.toLowerCase())
                || "xiaomi".equals(Build.MANUFACTURER.toLowerCase())
                || "meizu".equals(Build.MANUFACTURER.toLowerCase())
                || "letv".equals(Build.MANUFACTURER.toLowerCase())) {
            return MD5Utils.md5(token + baseHeader.get(t) + CacheUtils.get(context).getAsString(KnetConstants.PWD)).toLowerCase();
        } else {
            return api.ct(token,baseHeader.get(t),CacheUtils.get(context).getAsString(KnetConstants.PWD)).toLowerCase();
        }
    }
}
