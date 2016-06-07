/**
 * 
 */
package cn.knet.seal.financial.bean.request;


import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * 下户详情请求
 * 
 * ClassName: ReviewInfoDetailRequest <br/>  
 * Date: 2015年2月27日 下午4:32:14 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 * @version 2.7
 *      请求参数发生改变，增加金融机构类型。pxw
 */
public class ReviewInfoDetailRequest extends KnetBaseBean {
	
    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 5835195789306146592L;

    /**
     * 下户信息id
     */
    private String appInfoId;
    private String bankType;

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(String appInfoId) {
        this.appInfoId = appInfoId;
    }

    public ReviewInfoDetailRequest(){
		
	}
	
}
