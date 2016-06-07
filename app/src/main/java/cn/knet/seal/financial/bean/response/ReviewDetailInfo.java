/**
 *
 */
package cn.knet.seal.financial.bean.response;

import java.util.ArrayList;
import java.util.List;

import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * 下户信息详情
 * <p/>
 * ClassName: ReviewInfo <br/>
 * Date: 2015年2月26日 下午4:53:23 <br/>
 *
 * @author yangying@knet.cn
 * @version 1.0
 * @since 1.0
 */
public class ReviewDetailInfo extends KnetBaseBean {

    /** id */
    private String id;
    /** 单位名称 */
    private String unitName;
    /** 下户类型 */
    private String reviewType;
    /** 下户进度状态 */
    private String reviewStatus;
    /** 新修改字段 */
    private String bankReviewStatus;
    /** 通知下户日期 */
    private String expectReviewDate;
    /** 地区  */
    private String district;
    /** 地址 */
    private String address;
    /** 申请用户名称 */
    private String applyUser;
    /** 申请用户手机号 */
    private String applyUserMobile;
    /** 描述 */
    private String comments;
    private String remark;
    /** 经度 */
    private String lat;
    /** 纬度 */
    private String lng;
    /** 金融机构 */
    private String bankName;
    /** 已下户的金融机构 */
    private String reviewedCount;
    /** 拍照图片列表 */
    private ArrayList<ReviewImageInfo> imageUrlList;
    /** 下户录音列表 */
    private ArrayList<ReviewAudioInfo> audioUrlList;
    /** 视频列表 */
    private ArrayList<ReviewVideoInfo> videoUrlList;


    public List<ReviewVideoInfo> getVideoUrlList() {
        return videoUrlList;
    }

    public void setVideoUrlList(ArrayList<ReviewVideoInfo> videoUrlList) {
        this.videoUrlList = videoUrlList;
    }

    /**
     * pending的图片类型：若存在多个用,分割
     */
    private String pendingPhotoTypes;

    /**
     * @return 返回 bankName。
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName 要设置的 bankName。
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return 返回 audioUrlList。
     */
    public List<ReviewAudioInfo> getAudioUrlList() {
        return audioUrlList;
    }

    /**
     * @param audioUrlList 要设置的 audioUrlList。
     */
    public void setAudioUrlList(ArrayList<ReviewAudioInfo> audioUrlList) {
        this.audioUrlList = audioUrlList;
    }

    public List<ReviewImageInfo> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(ArrayList<ReviewImageInfo> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReviewDetailInfo() {

    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getExpectReviewDate() {
        return expectReviewDate;
    }

    public void setExpectReviewDate(String expectReviewDate) {
        this.expectReviewDate = expectReviewDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUserMobile() {
        return applyUserMobile;
    }

    public void setApplyUserMobile(String applyUserMobile) {
        this.applyUserMobile = applyUserMobile;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPendingPhotoTypes() {
        return pendingPhotoTypes;
    }

    public void setPendingPhotoTypes(String pendingPhotoTypes) {
        this.pendingPhotoTypes = pendingPhotoTypes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getReviewedCount() {
        return reviewedCount;
    }

    public void setReviewedCount(String reviewedCount) {
        this.reviewedCount = reviewedCount;
    }

    public String getBankReviewStatus() {
        return bankReviewStatus;
    }

    public void setBankReviewStatus(String bankReviewStatus) {
        this.bankReviewStatus = bankReviewStatus;
    }

    @Override
    public String toString() {
        return "ReviewInfoDetail{" +
                "id='" + id + '\'' +
                ", unitName='" + unitName + '\'' +
                ", reviewType='" + reviewType + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", bankReviewStatus='" + bankReviewStatus + '\'' +
                ", expectReviewDate='" + expectReviewDate + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", applyUser='" + applyUser + '\'' +
                ", applyUserMobile='" + applyUserMobile + '\'' +
                ", comments='" + comments + '\'' +
                ", remark='" + remark + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", bankName='" + bankName + '\'' +
                ", reviewedCount='" + reviewedCount + '\'' +
                ", imageUrlList=" + imageUrlList +
                ", audioUrlList=" + audioUrlList +
                ", videoUrlList=" + videoUrlList +
                ", pendingPhotoTypes='" + pendingPhotoTypes + '\'' +
                '}';
    }
}
