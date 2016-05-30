/**
 * @desc  
 * @date 2016-1-15
 */
package cn.knet.seal.financial.bean;

import java.io.Serializable;

/**
 * @desc 下户拍照时将存入数据库的图片信息，包括经纬度
 * @date 2016-1-15-下午1:52:28
 */
public class ReviewPicInfo implements Serializable {
    private static final long serialVersionUID = 4701930688967816443L;
    
    private String reviewID;
    private String photoType;
    private String fileName;
    private double lng;
    private double lat;
    
    
    
    /**
     * @return 返回 reviewID。
     */
    public String getReviewID() {
        return reviewID;
    }
    /**
     * @param reviewID 要设置的 reviewID。
     */
    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }
    /**
     * @return 返回 photoType。
     */
    public String getPhotoType() {
        return photoType;
    }
    /**
     * @param photoType 要设置的 photoType。
     */
    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }
    /**
     * @return 返回 fileName。
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @param fileName 要设置的 fileName。
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return 返回 lng。
     */
    public double getLng() {
        return lng;
    }
    /**
     * @param lng 要设置的 lng。
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
    /**
     * @return 返回 lat。
     */
    public double getLat() {
        return lat;
    }
    /**
     * @param lat 要设置的 lat。
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
    
    
    
}
