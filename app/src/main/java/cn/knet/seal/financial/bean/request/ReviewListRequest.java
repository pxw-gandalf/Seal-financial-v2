/**
 * 
 */
package cn.knet.seal.financial.bean.request;


import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * 下户列表请求
 * 
 * ClassName: ReviewListRequest <br/>  
 * Date: 2015年2月26日 下午4:01:16 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 * @version 2.5
 *      查询条件增加金融机构
 */
public class ReviewListRequest extends KnetBaseBean {
	
    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 5835195789306146592L;

    /**
     * 页号
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 下户进度状态
     */
    private String reviewStatus;
    /**
     * 金融机构
     */
    private String reviewType;
    /**
     * 单位名称
     */
    private String keyword;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }



    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
    
    /**
     * @return 返回 reviewType。
     */
    public String getReviewType() {
        return reviewType;
    }

    /**
     * @param reviewType 要设置的 reviewType。
     */
    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
