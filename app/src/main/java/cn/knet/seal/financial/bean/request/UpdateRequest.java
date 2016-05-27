/**
 * 
 */
package cn.knet.seal.financial.bean.request;


import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * 升级响应类
 * 
 * @author yangying
 * @date 2013-1-8 上午10:28:51
 * @version 1.0
 *
 */
public class UpdateRequest extends KnetBaseBean {
	
	/**  
     * serialVersionUID:序列id
     */
    private static final long serialVersionUID = -6486720345122262348L;
    
    private String versionCode;
    
    private String versionName;
	
	public UpdateRequest(){
		
	}
	
	public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
	
}
