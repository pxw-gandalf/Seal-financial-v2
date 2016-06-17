package cn.knet.seal.financial.bean;

/**
 * 拍摄时候储存的照片
 *
 *
 * ClassName: ReviewSourcePhoto <br/>
 * Date: 2016/6/17 13:42 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class ReviewSourcePhoto extends KnetBaseBean {
    /** 原始图片位置 */
    private String filePath;
    /** 图片类型 */
    private String photoType;
    /** 经纬度 **/
    private double longitude;
    private double latitude;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
