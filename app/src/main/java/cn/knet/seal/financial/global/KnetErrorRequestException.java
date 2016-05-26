package cn.knet.seal.financial.global;

/**
 * 自定义异常类
 * 处理服务端返回错误请求
 *
 * ClassName: KnetErrorRequestException <br/>
 * Date: 2016/5/26 16:16 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class KnetErrorRequestException extends Exception {
    public KnetErrorRequestException(){

    }
    public KnetErrorRequestException(String msg){
        super(msg);
    }
}
