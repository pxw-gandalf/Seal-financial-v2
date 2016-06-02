package cn.knet.seal.financial.global;

import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * 对象类型的eventBus
 * 要继承KnetBaseBean
 * <p/>
 * ClassName: ObjectMsgEvent  <br/>
 * Date: 2016/6/2 15:22 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class ObjectMsgEvent {
    int type;
    KnetBaseBean knetBaseBean;

    public ObjectMsgEvent(KnetBaseBean knetBaseBean) {
        super();
        this.knetBaseBean = knetBaseBean;
    }

    public ObjectMsgEvent(KnetBaseBean knetBaseBean, int type) {
        super();
        this.knetBaseBean = knetBaseBean;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public KnetBaseBean getObject() {
        return knetBaseBean;
    }

}
