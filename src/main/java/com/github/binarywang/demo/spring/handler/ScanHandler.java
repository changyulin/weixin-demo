package com.github.binarywang.demo.spring.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.binarywang.demo.spring.builder.TextBuilder;
import com.github.binarywang.demo.spring.service.WeixinService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * @author Binary Wang
 *
 */

@Component
public class ScanHandler extends AbstractHandler {
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

		WeixinService weixinService = (WeixinService) wxMpService;

		// 获取微信用户基本信息
		WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

		try {
			System.out.println("+++scan+++");
			return new TextBuilder().build("感谢扫码", wxMessage, weixinService);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		return null;
	}
}
