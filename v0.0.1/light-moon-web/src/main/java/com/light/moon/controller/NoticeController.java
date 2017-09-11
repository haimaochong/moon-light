package com.light.moon.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.light.moon.entity.NoticeEntity;
import com.light.moon.service.NoticeService;
import com.light.moon.utils.GridUtils;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Resource
	private NoticeService noticeService;

	@RequestMapping
	public String index(ModelMap model, Integer pageIndex) {
		Pageable pageable = GridUtils.buildPageable(pageIndex, 20, new Sort(Direction.DESC, "createTime"));

		Page<NoticeEntity> page = noticeService.findAll(pageable);
		model.addAttribute("noticeList", page.getContent());

		return "notice/notice";
	}

	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, ModelMap model) {
		NoticeEntity notice = noticeService.findOne(id);
		model.addAttribute("notice", notice);

		NoticeEntity preNotice = noticeService.queryPreNotice(id);
		model.addAttribute("preNotice", preNotice);

		NoticeEntity nextNotice = noticeService.queryNextNotice(id);
		model.addAttribute("nextNotice", nextNotice);

		return "notice/noticeDetail";
	}

}
