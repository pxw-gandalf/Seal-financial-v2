/**
 * 
 */
package cn.knet.seal.financial.bean.response;

/**
 * 用户身份验证响应类
 * 
 * ClassName: AuthResponse <br/>  
 * Date: 2015年2月28日 上午9:17:52 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 */
public class AuthResponse extends BaseResponse{

    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 1099507985932822718L;
    /**
     * token
     */
    private String token;
    
    private String permission;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public AuthResponse() {

    }
}
