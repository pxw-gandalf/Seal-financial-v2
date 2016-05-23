package cn.knet.seal.financial;

/**
 * 全局EventBus，替代Handler
 *
 * ClassName: GlobalEvents <br/>
 * Date: 2016/5/19 16:12 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class GlobalEvents {

    /** 处理全局的UI消息展示 */
    public static int COMMON_UI_MSG = 0x4B000001;
    public static int COMMON_UI_NET_ERROR = 0x4B000002;


    public int type;
    public Object obj;
    public Object obj1;

    public GlobalEvents setType(int type) {
        this.type = type;
        return this;
    }

    public GlobalEvents setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public GlobalEvents setObj1(Object obj1) {
        this.obj1 = obj1;
        return this;
    }
}
