/**
 * 
 */
package cn.knet.seal.financial.api;

/**
 * 中网金融api接口类
 * 
 * ClassName: KnetFinancialHttpApi <br/>
 * Date: 2015年2月12日 上午11:34:43 <br/>
 * 
 * @author yangying@knet.cn
 * @version 1.0
 * @since 1.0
 * update:
 *      项目重构
 */
public class KnetFinancialHttpApi {

    private static final String TAG = KnetFinancialHttpApi.class.getSimpleName();

    // 接口域名(线上地址)
//     private static final String DOMAIN = "api.fin.knet.cn";
    // 支持移动网络接口
//     private static final String DOMAIN = "http://" + "202.173.14.69:9912" + "/";
    // 20160216 测试主干地址
//    private static final String DOMAIN = "http://" + "192.168.249.7:9912" + "/";
    // 20160216 测试分支
    // 分支2
    private static final String DOMAIN = "http://" + "192.168.249.48:9912" + "/";

//    private static final String DOMAIN = "192.168.100.90:8080";

    private static final String DOMAIN_WEB = "yida.knet.cn";
    private static final String DOMAIN_WEB2 = "banksearch.knet.cn";

    // 1.1 查询下户的所有类型
    // private static String URI_GET_REVIEW_TYPE_ALL =
    // "review/type/getall.action";
    // 1.2 查询下户列表
    public static final String URI_GET_REVIEW_LIST = DOMAIN + "review/list.shtml";
    // 1.3 查询下户详细
    public static final String URI_GET_REVIEW = DOMAIN +  "review/detail.shtml";
    // 1.4 下户操作
    public static final String URI_REVIEW_ACTION = DOMAIN +  "review/action.shtml";
    // 1.5 下户数据有效性检查
    // private static String URI_REVEIW_CHECK_EXPIRED =
    // "review/checkReviewExpired.shtml";
    // 1.6 下户数据数量
    public static final String URI_GET_REVIEW_COUNT = DOMAIN +  "review/taskCount.shtml";
    // 1.7 下户数据接单
    public static final String URI_REVIEW_GRAB = DOMAIN +  "review/grab.shtml";
    // 1.8 下户金融结构
    public static final String URI_REVIEW_FINA_ORG_LIST = DOMAIN +  "tools/bankList.shtml";
    // 1.9 银行列表
    public static final String URI_REVIEW_SEAL_BASE_INFO = DOMAIN +  "review/bankList.shtml";

    // 2.1 资料上传列表
    public static final String URI_MATERIAL_LIST = DOMAIN +  "material/list.shtml";
    // 2.2 资料上传的详情
    public static final String URI_MATERIAL_DETAIL = DOMAIN +  "material/detail.shtml";
    // 2.3 资料上传
    public static final String URI_MATERIAL_ACTION = DOMAIN +  "material/action.shtml";

    // 3.1 物流录入列表
    public static final String URI_LOGISTICS_LIST = DOMAIN +  "logistics/list.shtml";
    // 3.2 物流录入详情
    public static final String URI_LOGISTICS_DETAIL = DOMAIN +  "logistics/detail.shtml";
    // 3.3 物流录入
    public static final String URI_LOGISTICS_ACTION = DOMAIN +  "logistics/action.shtml";
    // 3.4 物流修改
    public static final String URI_LOGISTICS_EDIT = DOMAIN +  "logistics/edit.shtml";

    // 4.1 请求token
    public static final String URI_GET_TOKEN = DOMAIN +  "tools/token.shtml";
    // 4.2 上传图片
    public static final String URI_UPLOAD = DOMAIN +  "tools/upload.shtml";
    // 4.3 下载图片
    public static final String FILE_ID = "_@#@_";
    public static final String URI_DOWNLOAD = DOMAIN +  "tools/download/" + FILE_ID + ".shtml";
    // 4.4 升级检查接口
    public static final String URI_UPDATE_CHECK = DOMAIN +  "tools/update_check.shtml";
    // 4.5 修改密码
    public static final String URI_CHANGE_PWD = DOMAIN +  "tools/changepwd.shtml";
    // 4.7 获取用户附近的商户列表
    public static final String URI_NEAR_BY = DOMAIN +  "tools/nearby.shtml";

    // 可信贷计算器页面
    public static final String URI_CALCULATOR = "pages/yida/mobile/cgbchina.jsp";
    // 可信贷进度查询页面
    public static final String URI_BANK_QUERY = "prepareBankFollowUp.action";
    // 可信贷进度 条件查询
    public static final String URI_CREDIT_QUERY = DOMAIN +  "bankquery/query.shtml";
    // 可信贷进度 列表数据
    public static final String URI_CREDIT_LIST = DOMAIN +  "bankquery/list.shtml";
    // 可信贷进度 详情
    public static final String URI_CREDIT_DETAIL = DOMAIN +  "bankquery/commonDetail.shtml";

    public static final String URI_INVALID = DOMAIN +  "pages/invalid.shtml";

    // 客户筛查 获取状态和注册商
    public static final String URI_CUSTOMER_QUERRY = DOMAIN +  "customer/query.shtml";
    // 客户筛查 获取查询到的数据列表
    public static final String URI_CUSTOMER_LIST = DOMAIN +  "customer/list.shtml";
    // 客户筛查 获取单条数据详情
    public static final String URI_CUSTOMER_DETAIL = DOMAIN +  "customer/detail.shtml";
    // 客户筛查 审核
    public static final String URI_CUSTOMER_AUDIT = DOMAIN +  "customer/audit.shtml";

    // 验证请求token
    public static final String HEAD_TOKEN = "token";
    // 时间戳
    public static final String HEAD_T = "t";
    // 校验值
    public static final String HEAD_CT = "ct";
    // 当前接口版本
    public static final String HEAD_V = "v";
    public static final String HEAD_V_VALUE = "2.0";

    // 用户id
    public static final String HEAD_UID = "uid";
    // 用户验证token
    public static final String HEAD_PT = "pt";
    // 用户标识码
    public static final String HEAD_D = "d";
    // change pwd check
    public static final String HEAD_CPT = "cpt";
    // change pwd
    public static final String HEAD_NP = "np";

    private static final String param_versionCode = "versionCode";
    private static final String param_versionName = "versionName";



    public String getPageCal() {
        return urlweb(URI_CALCULATOR);
    }

    public String getBankQuery() {
        return urlweb2(URI_BANK_QUERY);
    }

    public String getPageInvalid() {
        return urlweb(URI_INVALID);
    }

    /**
     * 拼装url url: <br/>
     * 
     * Date: 2015年3月14日 下午1:39:33 <br/>
     * 
     * @author yangying@knet.cn
     * 
     * @param uri
     * @return
     */
    private String url(String uri) {
        return "http://" + DOMAIN + "/" + uri;
    }

    private String urlweb(String uri) {
        return "http://" + DOMAIN_WEB + "/" + uri;
    }

    private String urlweb2(String uri) {
        return "http://" + DOMAIN_WEB2 + "/" + uri;
    }

    /**
     * 图片下载url downloadUrl: <br/>
     * 
     * Date: 2015年3月14日 下午1:39:23 <br/>
     * 
     * @author yangying@knet.cn
     * 
     * @param id
     * @return
     */
    public String downloadUrl(String id) {
        return url(URI_DOWNLOAD).replace(FILE_ID, id);
    }

    /**
     * 
     * ct: 获取校验token：<br/>
     * ct = md5(token+t+md5(password))
     * 
     * Date: 2015年2月26日 上午11:43:02 <br/>
     * 
     * @author yangying@knet.cn
     * 
     * @param token token
     * @param t 时间戳
     * @param p md5(pwd)
     * @return ct
     */
    public native String ct(String token, String t, String p);

    /**
     * 
     * pt: 获取pwd token<br/>
     * 
     * Date: 2015年2月27日 上午11:59:46 <br/>
     * 
     * @author yangying@knet.cn
     * 
     * @param u
     * @param t
     * @param p
     * @return
     */
    public native String pt(String u, String t, String p);

    /**
     * 
     * cpt: 获取cpt <br/>
     * 
     * Date: 2015年5月19日 下午3:19:06 <br/>
     * 
     * @author yangying@knet.cn
     * 
     * @param t
     * @param p
     * @param np
     * @return
     */
    public native String cpt(String token, String t, String p, String np);

    static {
        try {
            System.loadLibrary("core_v1_0");
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
