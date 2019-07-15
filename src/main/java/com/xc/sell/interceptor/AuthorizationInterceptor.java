package com.xc.sell.interceptor;

import com.xc.sell.annotation.AuthToken;
import com.xc.sell.enums.ConstantsKit;
import com.xc.sell.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private String httpHeaderName = "X-Nideshop-Token";
    private String unauthorizedErrorMessage = "401 unauthorized";
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if(method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null){
            String token = request.getHeader(httpHeaderName);
            log.info("get token from reques is {}", token);
            if(token == null || token.length() == 0){
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                throw new AuthException("Token 不能为空");
            }
            String openId = redisUtil.get(token).toString();
            if(openId != null){
                long expire = redisUtil.getExpire(token);
                if(expire < ConstantsKit.TOKEN_RESET_TIME.getToken_expire_time()){
                    redisUtil.expire(openId, ConstantsKit.TOKEN_EXPIRE_TIME.getToken_expire_time());
                    redisUtil.expire(token, ConstantsKit.TOKEN_EXPIRE_TIME.getToken_expire_time());
                }
            }else{
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
