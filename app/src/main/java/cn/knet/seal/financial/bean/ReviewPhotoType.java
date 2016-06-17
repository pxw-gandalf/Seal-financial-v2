package cn.knet.seal.financial.bean;

import java.io.Serializable;

/**
 * 下户拍照的图片类型对象
 * 
 * ClassName: PhotoType <br/>  
 * Date: 2015年5月21日 上午10:24:44 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 */
public class ReviewPhotoType implements Serializable {

    private static final long serialVersionUID = 6933987388535920734L;
//    private PhotoTypeEnum type;
    private String type;
    private int min;
    private int max;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    
}
