package com.harrison.wechat.utils;

import com.harrison.wechat.constant.WeChatConstants;
import com.harrison.wechat.dto.WeChatAccessTokenInfo;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static RestTemplate restTemplate;

    public static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        }
        return restTemplate;
    }

    public static WeChatAccessTokenInfo fetchOpenId(String code) throws Exception{
        Map<String,String> map = new HashMap();
        map.put("appid", WeChatConstants.APPID);
        map.put("secret", WeChatConstants.APPSECRET);
        map.put("code",code);
        map.put("grant_type","authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type={grant_type}";
        // ResponseEntity<WeChatAccessTokenInfo> responseEntity = getRestTemplate().getForEntity(url, WeChatAccessTokenInfo.class, map);
        return getRestTemplate().getForObject(url
                , WeChatAccessTokenInfo.class,map);

    }
}
