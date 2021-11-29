package com.yicen.flutter_service_demo.config;

import com.yicen.flutter_service_demo.exception.DemoException;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("request---->>>content type :" + request.getContentType() +"\n" + "request.url----->>>>:" + request.getRequestURL());
        log.info("response :" + response);
        HandlerMethod method = (HandlerMethod) handler;
        if (method.getMethod().isAnnotationPresent(NoNeedLogin.class)){
            return  true;
        }else {
            String token = request.getHeader("token");
            log.info("token ---- >>>>>> " + token);
            if (StringUtil.isNullOrEmpty(token)){
                throw new DemoException("无token，请登录");
            }else {

                boolean result = JwtUtil.verity(token);
                if (result){
                    return true;
                }else {
                    throw new DemoException("token失效，请重新登录");
                }
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


}
