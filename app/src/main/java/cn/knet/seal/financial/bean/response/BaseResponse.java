/**
 * 
 */
package cn.knet.seal.financial.bean.response;

import java.io.Serializable;

/**
 * 基本响应类
 * 
 * 
 * ClassName: BaseResponse <br/>
 * Date: 2015年2月10日 上午10:11:33 <br/>
 * 
 * @author yangying@knet.cn
 * @version 1.0
 * @since 1.0
 */
public class BaseResponse implements Serializable {

    /**
     * 从服务器的响应代码
     */
    private String code;
    /**
     * 从服务器的响应消息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    protected BaseResponse() {
        
    }
    
}
