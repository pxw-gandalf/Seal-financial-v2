package cn.knet.seal.financial.bean;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * ClassName: ReviewPhotoTypeEnum <br/>  
 * Date: 2015年6月8日 上午11:28:04 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 * @see
 */
public enum ReviewPhotoTypeEnum {
    ROAD_AND_COMPANY("ROAD_AND_COMPANY", "公司马路合影"),
    LANDMARK("LANDMARK", "明显地标物"),
    SIGNBOARD("SIGNBOARD", "招牌、门牌号"),
    OPERATION_EQUIPMENT("OPERATION_EQUIPMENT", "运营设备"),
    WAREHOUSE_INVENTORY("WAREHOUSE_INVENTORY", "仓库存货"),
    EMPLOYEE_OR_CUSTOMER("EMPLOYEE_OR_CUSTOMER", "员工、客户"),
    PRODUCTS_OF_CUSTOMER("PRODUCTS_OF_CUSTOMER", "客户经营的产品"),
    COMPANY_LICENSE("COMPANY_LICENSE", "公司证照"),
    OFFICE_EQUIPMENT("OFFICE_EQUIPMENT", "办公设备"),
    DRIVERS_LICENSE("DRIVERS_LICENSE_VEHICLE", "驾驶执照、行驶证、车辆实体"),
    CUSTOMER_OPPLACE_TOGETHER("CUSTOMER_OPPLACE_TOGETHER", "客户与经营场地合影"),
    REVIEW_FORM("REVIEW_FORM", "下户调查表"),
    OPERATING_FULL_YEAR("OPERATING_FULL_YEAR", "实际经营满1年判断"),
    ACTUAL_OPERATING_PLACE("ACTUAL_OPERATING_PLACE", "实际经营场地"),
    ACTUAL_RESIDENCE_ADDRESS("ACTUAL_RESIDENCE_ADDRESS", "实际居住地址"),
    OPERATING_RELATED_PARTY_ADDRESS("OPERATING_RELATED_PARTY_ADDRESS", "经营关联方地址"),
    OTHER("OTHER", "其他");

	private static final Map<String, String> MAPPING = new LinkedHashMap<String, String>();
	public static final List<String> LIST_NAMES = new ArrayList<>();
	public static final List<String> LIST_VALUES = new ArrayList<>();

	/**
	 * value 枚举值
	 */
	private String key;
	/**
	 * text 枚举描述
	 */
	private String value;

	/**
	 * 静态域
	 */
	static {
		for (ReviewPhotoTypeEnum em : ReviewPhotoTypeEnum.values()) {
			MAPPING.put(em.getKey(), em.getValue());
			LIST_NAMES.add(em.getValue());
			LIST_VALUES.add(em.getKey());
		}
	}

	/**
	 * 构造方法
	 *
	 * @param value
	 */
	ReviewPhotoTypeEnum(final String key, final String value) {
		this.key = key;
		this.value = value;
	}

    public String getKey() {
        return key;
    }

	public String getValue() {
		return value;
	}

    /**
	 * 静态的获取枚举的方法
	 * 
	 * @author <a href="mailto:yangying@knet.cn">yangying</a>
	 * @date 2015年1月4日 下午5:38:26
	 * @param enumKey
	 * @return
	 *
	 */
	public static ReviewPhotoTypeEnum get(String enumKey) {
		for (ReviewPhotoTypeEnum em : ReviewPhotoTypeEnum.values()) {
			if (em.getKey().equals(enumKey)) {
				return em;
			}
		}
		throw new IllegalArgumentException("Can't get enum with this enumValue.");
	}

	/**
	 * 获取枚举map
	 * 
	 * @author <a href="mailto:yangying@knet.cn">yangying</a>
	 * @date 2015年1月4日 下午5:38:49
	 * @return 所有map
	 *
	 */
	public static Map<String, String> mapping() {
		return MAPPING;
	}

}
