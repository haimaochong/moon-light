package com.light.base.searcher;

/**
 * 查询条件
 * 
 * @author lihh
 *
 */
public class WebSearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE, IN, NEQ, ISNULL, NOTNULL
	}

	private String fieldName;

	private Object value;

	private Operator operator;

	public WebSearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

}
