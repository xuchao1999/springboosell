package com.xc.sell.controller;

import com.xc.sell.config.WechatConfig;
import com.xc.sell.dataobject.UserInfo;
import com.xc.sell.dto.InUserInfo;
import com.xc.sell.dto.LoginDataDTO;
import com.xc.sell.dto.LogoutDTO;
import com.xc.sell.dto.OpenIdDTO;
import com.xc.sell.enums.ConstantsKit;
import com.xc.sell.service.UserInfoService;
import com.xc.sell.util.*;
import com.xc.sell.viewObject.LoginVO;
import com.xc.sell.viewObject.ResultVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequestMapping("/buyer")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtil redisUtil;



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

        String token = tokenUtil.createToken(openIdDTO.getOpenid(), userInfo.getId());

        redisUtil.set(openIdDTO.getOpenid(), token);
        redisUtil.expire(openIdDTO.getOpenid(), ConstantsKit.TOKEN_EXPIRE_TIME.getToken_expire_time());
        redisUtil.set(token, openIdDTO.getOpenid());
        redisUtil.expire(token, ConstantsKit.TOKEN_EXPIRE_TIME.getToken_expire_time());

        log.info("loginToken: {}", token);

        LoginVO loginVO = new LoginVO();
        loginVO.setOpenid(openIdDTO.getOpenid());
        loginVO.setToken(token);
        loginVO.setUserInfo(inUserInfo);

        return ResultVOUtil.success(loginVO);
//        return null;
    }

    @PostMapping("/logout")
    public ResultVO logout(HttpServletRequest request){
        String token = tokenUtil.getToten(request);
        log.info("logoutToken: {}", token);
        String openId = redisUtil.get(token).toString();
        redisUtil.del(token, openId);
        LogoutDTO logoutDTO = new LogoutDTO(openId);
        ResultVO<LoginDataDTO> resultVO = ResultVOUtil.success(logoutDTO);
        return resultVO;
    }
}
