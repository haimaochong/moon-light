package com.light.base.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.light.base.searcher.WebSearchFilter;

/**
 * 列表查询常用工具
 * 
 * @author lihh
 * 
 */
public class GridUtils {

	private static JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();

	public final static String PAGE_JSON_TOTAL = "records"; // 记录总数
	public final static String PAGE_JSON_DATA = "rows"; // 表格数据
	public final static String TOTAL_PAGE = "total"; // 总页数
	public final static String CURRENT_PAGE = "page"; // 当前页数

	private GridUtils() {
	}

	/**
	 * 创建表格分页的查询对象
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页记录数
	 * @param sort
	 *            排序
	 * @return
	 */
	public static Pageable buildPageable(Integer pageNo, Integer pageSize, Sort sort) {
		int defaultPageSize = 10;
		int defaultPageNo = 1;
		if (null == pageNo || pageNo.intValue() <= 0) {
			pageNo = defaultPageNo;
		}
		if (null == pageSize || pageSize.intValue() <= 0) {
			pageSize = defaultPageSize;
		}
		return new PageRequest(pageNo - 1, pageSize, sort);
	}

	/**
	 * 返回页转化为json
	 * 
	 * @param page
	 * @return
	 */
	public static String toJson(Page<?> page) {
		return jsonMapper.toJson(dealPageInfo(page));
	}

	/**
	 * list转化为json
	 * 
	 * @param data
	 * @return
	 */
	public static String toJson(List<?> data) {
		return jsonMapper.toJson(data);
	}

	/**
	 * page+list转化为json
	 * 
	 * @param page
	 * @param data
	 * @return
	 */
	public static String toJson(Page<?> page, List<?> data) {
		Map<String, Object> result = dealPageInfo(page);
		result.put(PAGE_JSON_DATA, null == data ? Lists.newArrayList() : data);
		return jsonMapper.toJson(result);
	}

	/**
	 * 转换翻页数据为map
	 * @param page
	 * @return
	 */
	private static Map<String, Object> dealPageInfo(Page<?> page) {
		Map<String, Object> result = Maps.newHashMap();
		if (null == page) {
			return result;
		}

		result.put(PAGE_JSON_TOTAL, page.getTotalElements());
		List<?> content = page.getContent();
		result.put(PAGE_JSON_DATA, null == content ? Lists.newArrayList() : content);
		result.put(TOTAL_PAGE, page.getTotalPages());
		result.put(CURRENT_PAGE, page.getNumber() + 1);
		return result;
	}
	
	/**
     * 用于字符串的查询条件增加，非空时，向filters增加查询条件
     *
     * @param filters
     * @param field
     * @param operator
     * @param value
     */
    public static void addSearchFilterNotBlank(List<WebSearchFilter> filters, String field,
                                               WebSearchFilter.Operator operator, String value) {
        if (StringUtils.isNotBlank(value)) {
            filters.add(new WebSearchFilter(field, operator, value));
        }
    }

    /**
     * 用于查询条件的增加，不为null时，增加查询条件
     *
     * @param filters
     * @param field
     * @param operator
     * @param value
     */
    public static void addSearchFilterNotNull(List<WebSearchFilter> filters, String field,
                                              WebSearchFilter.Operator operator, Object value) {
        // 由于前段传回的条件有可能为字符串类型，且为""，因此此处增加对""的判断
        if (null != value && !"".equals(value)) {
            filters.add(new WebSearchFilter(field, operator, value));
        }
    }
	
}
