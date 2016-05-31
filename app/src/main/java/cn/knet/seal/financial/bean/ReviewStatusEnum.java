package cn.knet.seal.financial.bean;

/**
 * 下户进度
 * 
 * ClassName: ReviewStatusEnum <br/>  
 * Date: 2015年3月10日 下午1:26:40 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 * @update
 *      12/14: 状态接单改为待接单
 */
public enum ReviewStatusEnum {
    REVIEW_GRAB("REVIEW_GRAB","待接单"),
    REVIEW_GRABED("REVIEW_GRABED","已接单"),
    REVIEW_WAIT("REVIEW_WAIT","待下户"),
    REVIEWING("REVIEWING","下户中"),
    REVIEW_AUDITING("REVIEW_AUDITING","审核中"), 
    REVIEW_MODIFY("REVIEW_MODIFY","待修改"),
    REVIEW_COMPLETE("REVIEW_COMPLETE","下户完成"),
    REVIEW_INVALID("REVIEW_INVALID","撤单");
    
	private String value;
	private String text;

	/**
	 * 
	 * @param value
	 * @param text
	 */
	ReviewStatusEnum(final String value, final String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	public static ReviewStatusEnum get(String enumKey) {
        for (ReviewStatusEnum em : ReviewStatusEnum.values()) {
            if (em.getValue().equals(enumKey)) {
                return em;
            }
        }
        throw new IllegalArgumentException("Can't get enum with this enumValue.");
    }

    public static ReviewStatusEnum getByText(String enumText) {
        for (ReviewStatusEnum em : ReviewStatusEnum.values()) {
            if (em.getText().equals(enumText)) {
                return em;
            }
        }
        throw new IllegalArgumentException("Can't get enum with this enumValue.");
    }
}
