/**
 * 
 */
package cn.knet.seal.financial.bean.response;

import java.util.List;

/**
 * 下户列表响应类
 * 
 * ClassName: ReviewListResponse <br/>  
 * Date: 2015年2月26日 下午4:19:02 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 */
public class ReviewListResponse extends BaseResponse{

    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 1099507985932822718L;
    /**
     * 页号
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    
    /**
     * 列表总数
     */
    private Integer totalNum;
    /**
     * 下户类型列表
     */
    private List<ReviewInfo> reviewList;


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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }


    public List<ReviewInfo> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewInfo> reviewList) {
        this.reviewList = reviewList;
    }

    public ReviewListResponse() {

    }

}
