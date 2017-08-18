package com.light.moon.searcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

/**
 * 动态查询对象
 * 
 * @author wuwei
 */
public class DynamicSpecifications {

	public static <T> Specification<T> bySearchFilter(final Class<T> entityClazz,
			final Collection<WebSearchFilter> searchFilters) {
		final Set<WebSearchFilter> filterSet = new HashSet<WebSearchFilter>();

		// 自定义
		for (WebSearchFilter searchFilter : searchFilters) {
			filterSet.add(searchFilter);
		}

		return new Specification<T>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (filterSet != null && !filterSet.isEmpty()) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (WebSearchFilter filter : filterSet) {
						// nested path translate, 如Task的名为"user.name"的filedName,
						// 转换为Task.user.name属性
						Path expression = null;
						if (StringUtils.isNotBlank(filter.getFieldName())) {
							String[] names = StringUtils.split(filter.getFieldName(), ".");
							expression = root.get(names[0]);
							for (int i = 1; i < names.length; i++) {
								expression = expression.get(names[i]);
							}
							// 自动进行enum和date的转换。
							Class clazz = expression.getJavaType();
							if (Date.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
								filter.setValue(convert2Date((String) filter.getValue()));
							} else if (Enum.class.isAssignableFrom(clazz)
									&& !filter.getValue().getClass().equals(clazz)) {
								filter.setValue(convertEnum(filter, clazz));
							} else if (Boolean.class.isAssignableFrom(clazz)
									&& !filter.getValue().getClass().equals(clazz)) {
								filter.setValue(Boolean.valueOf(filter.getValue().toString()));
							} else if (boolean.class.isAssignableFrom(clazz)
									&& !filter.getValue().getClass().equals(clazz)) {
								filter.setValue(Boolean.valueOf(filter.getValue().toString()));
							}
						}

						// logic operator
						switch (filter.getOperator()) {
						case EQ:
							predicates.add(builder.equal(expression, filter.getValue()));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.getValue() + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
							break;
						case IN:
							predicates.add(builder.and(expression.in((Object[]) filter.getValue())));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression, filter.getValue()));
							break;
						case ISNULL:
							predicates.add(builder.isNull(expression));
							break;
						case NOTNULL:
							predicates.add(builder.isNotNull(expression));
							break;
						}
					}
					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}

		};
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object convertEnum(WebSearchFilter filter, Class clazz) {
		if (filter.getValue().getClass().isArray()) {
			List<Enum<?>> vals = Lists.newArrayList();
			for (Object item : (Object[]) filter.getValue()) {
				vals.add(convert2Enum(clazz, item));
			}
			return vals.toArray();
		} else {
			return convert2Enum(clazz, filter.getValue());
		}
	}

	private static Date convert2Date(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private static <E extends Enum<E>> E convert2Enum(Class<E> enumClass, Object enumString) {
		if (enumString instanceof Enum<?>) {
			return (E) enumString;
		}
		return EnumUtils.getEnum(enumClass, (String) enumString);
	}
}