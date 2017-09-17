package com.light.base.vo;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.light.base.utils.JsonMapper;

/**
 * 前端的数据VO
 * 
 * @author lihh
 * 
 */
public class ResultVO {

	public static final int SUC_COD = 0;
	public static final int ERR_COD = -1;
	public static final int SP_COD = 1;

	private int code;
	private String msg;
	private Object body;

	public ResultVO() {
	}

	public static ResultVO err(String msg) {
		ResultVO vo = new ResultVO();
		vo.setCode(ERR_COD);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO special(String msg) {
		ResultVO vo = new ResultVO();
		vo.setCode(SP_COD);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO err(String msg, Object... args) {
		ResultVO vo = new ResultVO();
		vo.setCode(ERR_COD);
		FormattingTuple tuple = MessageFormatter.arrayFormat(msg, args);
		vo.setMsg(tuple.getMessage());
		return vo;
	}

	public static ResultVO error(String msg, Object body) {
		ResultVO vo = new ResultVO();
		vo.setMsg(msg);
		vo.setCode(ERR_COD);
		vo.setBody(body);
		return vo;
	}

	public static ResultVO suc(Object body) {
		ResultVO vo = new ResultVO();
		vo.setCode(SUC_COD);
		vo.setBody(body);
		return vo;
	}

	public static ResultVO ok() {
		ResultVO vo = new ResultVO();
		vo.setCode(SUC_COD);
		return vo;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return JsonMapper.nonEmptyMapper().toJson(this);
	}
}
