package cn.knet.seal.financial.global;

/**
 * String类型的event消息
 *
 * ClassName: StringMsgEvents <br/>
 * Date: 2016/5/24 10:05 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class StringMsgEvents {
    String msg;

    public StringMsgEvents(String msg) {
        super();
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
}
