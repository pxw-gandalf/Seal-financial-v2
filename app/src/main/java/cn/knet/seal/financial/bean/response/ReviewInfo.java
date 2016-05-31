/**
 * 
 */
package cn.knet.seal.financial.bean.response;

import java.util.List;

import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * 下户信息
 * 
 * ClassName: ReviewInfo <br/>  
 * Date: 2015年2月26日 下午2:42:53 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 * @version 2.5
 *      增加金融机构字段
 *      可信贷订单中使用，增加字段
 */
public class ReviewInfo extends KnetBaseBean {

    private static final long serialVersionUID = 1099507985932822718L;

	/** id */
	private String appInfoId;
    /** 可信贷订单id */
    private String sealLogId;
	/**  单位名称 */
	private String unitName;
	/** 下户类型 */
	private String reviewType;
	/** 下户进度状态 */
	private String reviewStatus;
	/** 通知下户日期 */
	private String expectReviewDate;
	/** 申请时间 */
	private String applyDate;
	/** 实际经营地址 */
	private String address;
	/** 贷款机构名称 */
	private String bankName;
    /** 订单状态 待接单，已接单 */
    private String status;

    /** 贷款申请人 */
    private String applyUser;
    /** 申请人手机号 */
    private String applyUserMobile;

    /** 下户改版为以可信贷订单为粒度 */
    private List<BankInfo> bankList;

    private boolean isExtend;


    public String getApplyUserMobile() {
        return applyUserMobile;
    }

    public void setApplyUserMobile(String applyUserMobile) {
        this.applyUserMobile = applyUserMobile;
    }
    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public boolean isExtend() {
        return isExtend;
    }

    public void setExtend(boolean extend) {
        isExtend = extend;
    }

    public List<BankInfo> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankInfo> bankList) {
        this.bankList = bankList;
    }

    public String getSealLogId() {
        return sealLogId;
    }

    public void setSealLogId(String sealLogId) {
        this.sealLogId = sealLogId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(String appInfoId) {
        this.appInfoId = appInfoId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
