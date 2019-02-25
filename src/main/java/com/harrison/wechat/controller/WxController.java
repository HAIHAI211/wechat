package com.harrison.wechat.controller;


import com.harrison.wechat.dto.WeChatAccessTokenInfo;
import com.harrison.wechat.exception.AesException;
import com.harrison.wechat.utils.HttpUtils;
import com.harrison.wechat.utils.WeChatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
@Slf4j
public class WxController {
    @GetMapping("")
    public String init(String signature, String timestamp, String nonce, String echostr) throws AesException {
        WeChatUtils.verifyUrl(signature,timestamp,nonce);
        return echostr;
    }

    /**
    *返回结果必须是一个页面，写到restcontroller里，只是为了突出获取openId的目的性，最终必须返回页面给客户端
    * */
    @GetMapping("/getOpenId")
    public String next(String code) throws Exception{
        log.info("code", code);
        WeChatAccessTokenInfo weChatAccessTokenInfo = HttpUtils.fetchOpenId(code);
        return "该用户的openid是：" + weChatAccessTokenInfo.getOpenId();
    }
}
