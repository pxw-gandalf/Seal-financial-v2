package cn.knet.seal.financial.bean.response;

import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * @author pxw
 * @desc
 * @date 2016/4/25 17:32
 * @update
 */
public class BankInfo extends KnetBaseBean {
    private String reviewType;
    private String bankName;
    private String bankType;
    private String sealLogId;
    private String reviewId;
    private String bankReviewStatus;
    private String appInfoId;
    private String logoUrl;

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getSealLogId() {
        return sealLogId;
    }

    public void setSealLogId(String sealLogId) {
        this.sealLogId = sealLogId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getBankReviewStatus() {
        return bankReviewStatus;
    }

    public void setBankReviewStatus(String bankReviewStatus) {
        this.bankReviewStatus = bankReviewStatus;
    }

    public String getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(String appInfoId) {
        this.appInfoId = appInfoId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
