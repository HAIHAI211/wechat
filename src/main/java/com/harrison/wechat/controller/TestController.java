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
public class TestController {
    @GetMapping("")
    public String init(String signature, String timestamp, String nonce, String echostr) throws AesException {
        log.info("hehe");
        WeChatUtils.verifyUrl(signature,timestamp,nonce);
        return echostr;
    }
    @GetMapping("/next")
    public String next(String code) throws Exception{
        log.info("code", code);
        WeChatAccessTokenInfo weChatAccessTokenInfo = HttpUtils.fetchOpenId(code);
        return "该用户的openid是：" + weChatAccessTokenInfo.getOpenId();
    }
}
