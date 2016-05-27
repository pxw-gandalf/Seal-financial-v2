/**
 * 
 */
package cn.knet.seal.financial.bean.response;

/**
 * 升级响应类
 * 
 * @author yangying
 * @date 2013-1-8 上午10:28:51
 * @version 1.0
 *
 */
public class UpdateResponse extends BaseResponse{
	
	/**  
     * serialVersionUID:序列id
     */
    private static final long serialVersionUID = -4373897160549700939L;

    private int versionCode;
    private String versionName;
    private String descp;
    private String downloadUrl;
    
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public UpdateResponse(){
        
    }
}
