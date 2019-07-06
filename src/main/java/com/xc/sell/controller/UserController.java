package com.xc.sell.controller;

import com.xc.sell.config.WechatConfig;
import com.xc.sell.dataobject.UserInfo;
import com.xc.sell.dto.InUserInfo;
import com.xc.sell.dto.LoginDataDTO;
import com.xc.sell.dto.OpenIdDTO;
import com.xc.sell.service.UserInfoService;
import com.xc.sell.util.JsonUtil;
import com.xc.sell.util.KeyUtil;
import com.xc.sell.util.ResultVOUtil;
import com.xc.sell.util.TokenUtil;
import com.xc.sell.viewObject.LoginVO;
import com.xc.sell.viewObject.ResultVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@Slf4j
@RequestMapping("/buyer")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WechatConfig wechatConfig;


    @PostMapping("/login")
    @ResponseBody
    public ResultVO login(@RequestBody LoginDataDTO loginDataDTO){

        /*发送reques请求*/
        InUserInfo inUserInfo = JsonUtil.json2Obj(loginDataDTO.getUserInfo().getRawData(), InUserInfo.class);
        RestTemplate restTemplate = new RestTemplate();
        String params = "appid=" + wechatConfig.getAppid() + "&secret=" + wechatConfig.getSecret() + "&js_code=" +loginDataDTO.getCode();
        String url = "https://api.weixin.qq.com/sns/jscode2session?" + params;
        String response = restTemplate.getForObject(url, String.class);
        log.info(response);
        OpenIdDTO openIdDTO =  JsonUtil.json2Obj(response, OpenIdDTO.class);

        /*根据openid查找用户信息*/
        UserInfo userInfo = userInfoService.findByOpenId(openIdDTO.getOpenid());
        if(userInfo == null){
            /*注册用户*/
            log.info("正在创建用户");
            userInfo = new UserInfo();
            userInfo.setPhone("1234567");
            userInfo.setPassword("12345");
            userInfo.setUsername(inUserInfo.getNickName());
            userInfo.setOpenId(openIdDTO.getOpenid());
            userInfo.setId(KeyUtil.getUniqueKey());
            userInfoService.crate(userInfo);
        }

        LoginVO loginVO = new LoginVO();
        loginVO.setOpenid(openIdDTO.getOpenid());
        loginVO.setToken(TokenUtil.createToken(loginVO.getOpenid(), userInfo.getId()));
        loginVO.setUserInfo(inUserInfo);

        return ResultVOUtil.success(loginVO);
//        return null;
    }
}
