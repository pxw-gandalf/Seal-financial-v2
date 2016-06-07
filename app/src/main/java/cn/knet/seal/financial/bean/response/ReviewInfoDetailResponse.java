/**
 * 
 */
package cn.knet.seal.financial.bean.response;



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
public class ReviewInfoDetailResponse extends BaseResponse{

    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 1099507985932822718L;
    /**
     * 下户信息详情
     */
    private ReviewDetailInfo reviewDetail;

    public ReviewDetailInfo getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(ReviewDetailInfo reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public ReviewInfoDetailResponse() {

    }

}
