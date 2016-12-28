package com.github.binarywang.demo.spring.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.demo.spring.service.WeixinService;

@RestController
@RequestMapping("/wechat/oauth2Test")
public class WxMpController {
	@Autowired
	private WeixinService wxService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@GetMapping(produces = "text/plain;charset=utf-8")
	public String doGet(@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "state", required = false) String state) {
		this.logger.info("\n code and state：[{}, {}]", code, state);
		String openId = this.wxService.getOpenIdByCode(code);
		return "openId:" + openId;
	}
}
